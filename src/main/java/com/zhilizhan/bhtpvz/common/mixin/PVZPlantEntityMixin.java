package com.zhilizhan.bhtpvz.common.mixin;

import com.hungteen.pvz.common.entity.AbstractPAZEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.utils.AlgorithmUtil;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.common.block.BHTPvZBlocks;
import com.zhilizhan.bhtpvz.common.util.BHTPVZUtils;
import com.zhilizhan.bhtpvz.config.BHTPvZConfig;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
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
public abstract class PVZPlantEntityMixin extends AbstractPAZEntity{

    public PVZPlantEntityMixin(EntityType<?> arg, World arg2) {
        super((EntityType<? extends CreatureEntity>) arg, arg2);
    }

    @Redirect(method = "shouldWilt",at = @At(value = "INVOKE", target = "Lcom/hungteen/pvz/common/entity/plant/PVZPlantEntity;isInWater()Z"))
    public boolean shouldWilt(PVZPlantEntity instance) {
        return !instance.isInWater() && !this.level.getBlockState(instance.blockPosition().below()).is(BHTPvZBlocks.WATER_POT.get());
    }
    //STEEL PUMPKIN FLAG = 5
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
    @Inject(method = "removeOuterPlant", at = @At("TAIL"))
    public void removeOuterPlant(CallbackInfo ci) {
      BHTPVZUtils.setSteelPumpkin((PVZPlantEntity)(Object)this,false);
    }

}

