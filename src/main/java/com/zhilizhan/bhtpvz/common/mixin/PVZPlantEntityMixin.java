package com.zhilizhan.bhtpvz.common.mixin;

import com.hungteen.pvz.common.entity.AbstractPAZEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.utils.AlgorithmUtil;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.common.api.ISteelPumpkin;
import com.zhilizhan.bhtpvz.common.block.BHTPvZBlocks;
import com.zhilizhan.bhtpvz.common.network.BHTPvZPacketHandler;
import com.zhilizhan.bhtpvz.common.network.toclient.SteelPumpkinSyncPacket;
import com.zhilizhan.bhtpvz.config.BHTPvZConfig;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.network.PacketDistributor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mixin(value = PVZPlantEntity.class,remap = false)
public abstract class PVZPlantEntityMixin extends AbstractPAZEntity implements ISteelPumpkin {
    private static final DataParameter<Boolean> DATA_HAS_STEEL_PUMPKIN  = EntityDataManager.defineId(PVZPlantEntity.class, DataSerializers.BOOLEAN);

    private final static int STEEL_PUMPKIN_FLAG = 5;// 钢南瓜的状态位

    public PVZPlantEntityMixin(EntityType<?> arg, World arg2) {
        super((EntityType<? extends CreatureEntity>) arg, arg2);
    }

    @Redirect(method = "shouldWilt",at = @At(value = "INVOKE", target = "Lcom/hungteen/pvz/common/entity/plant/PVZPlantEntity;isInWater()Z"))
    public boolean shouldWilt(PVZPlantEntity instance) {
        return !instance.isInWater() && !this.level.getBlockState(instance.blockPosition().below()).is(BHTPvZBlocks.WATER_POT.get());
    }

    @Inject(method = "canBeTargetBy", at = @At("HEAD"), cancellable = true)
    public void canBeTargetBy(LivingEntity living, CallbackInfoReturnable<Boolean> cir) {
        boolean flag = AlgorithmUtil.BitOperator.hasBitOne(this.getPAZState(), 5);
        boolean flag1 = !getNearbyPlant().isEmpty();
        if(flag&&flag1){
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
    @Unique
    private List<LivingEntity> getNearbyPlant() {
        float range = 10.0F;

        List<LivingEntity> originalList = EntityUtil.getFriendlyLivings(this, EntityUtil.getEntityAABB(this, range, range));

        List<LivingEntity> filteredList = originalList.stream()
                .filter(entity -> entity instanceof PVZPlantEntity && !(AlgorithmUtil.BitOperator.hasBitOne(((PVZPlantEntity)entity).getPAZState(), 5))&&entity!=this)
                .collect(Collectors.toList());
        return filteredList;
    }
    @Inject(method = "shouldPlantRegularSleep", at = @At("HEAD"), cancellable = true)
    protected void shouldPlantRegularSleep(CallbackInfoReturnable<Boolean> cir) {
        Biome biome = this.level.getBiome(this.blockPosition());
        boolean flag = Objects.equals(biome.getRegistryName(), new ResourceLocation(BHTPvZ.MOD_ID, "night"));
        boolean flag1 = this.level.getBlockState(blockPosition().below())== Blocks.MYCELIUM.defaultBlockState()&&BHTPvZConfig.COMMON_CONFIG.EntitySettings.PlantSetting.MyceliumSleep.get();

        if(flag||flag1)cir.setReturnValue(false);
    }

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    protected void defineSynchedData(CallbackInfo ci) {
        this.entityData.define(DATA_HAS_STEEL_PUMPKIN, false);
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    public void addAdditionalSaveData(CompoundNBT compound, CallbackInfo ci) {
           compound.putBoolean("has_steel_pumpkin", this.entityData.get(DATA_HAS_STEEL_PUMPKIN));
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    public void readAdditionalSaveData(CompoundNBT compound, CallbackInfo ci) {
        if (compound.contains("has_steel_pumpkin")) {
            this.entityData.set(DATA_HAS_STEEL_PUMPKIN, compound.getBoolean("has_steel_pumpkin"));
        }
    }

    @Inject(method = "removeOuterPlant", at = @At("TAIL"))
    public void removeOuterPlant(CallbackInfo ci) {
      this.setSteelPumpkin(false);
    }

    @Override
    public boolean getCachedSteelPumpkin() {
        return this.entityData.get(DATA_HAS_STEEL_PUMPKIN);
    }

    @Override
    public void setCachedSteelPumpkin(boolean cachedSteelPumpkin){
        this.entityData.set(DATA_HAS_STEEL_PUMPKIN,cachedSteelPumpkin);
    }

    @Override
    public boolean hasSteelPumpkin() {
        if (this.level != null && this.level.isClientSide) {
            return this.getCachedSteelPumpkin();
        }
        return AlgorithmUtil.BitOperator.hasBitOne(this.getPAZState(), STEEL_PUMPKIN_FLAG);
    }

    @Override
    public void setSteelPumpkin(boolean hasSteelPumpkin) {
        AlgorithmUtil.BitOperator.setBit(this.getPAZState(), STEEL_PUMPKIN_FLAG, hasSteelPumpkin);
        this.entityData.set(DATA_HAS_STEEL_PUMPKIN, hasSteelPumpkin);
        if (!this.level.isClientSide) {
            sendSteelPumpkinUpdate((PVZPlantEntity)(Object)this, hasSteelPumpkin);
        }
    }

    private static void sendSteelPumpkinUpdate(PVZPlantEntity plant, boolean flag) {
        SteelPumpkinSyncPacket packet = new SteelPumpkinSyncPacket(plant.getId(), flag);
        // 发送给所有追踪该实体的玩家
        BHTPvZPacketHandler.CHANNEL.send(
                PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> plant),
                packet
        );
    }
}

