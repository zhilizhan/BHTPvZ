package com.zhilizhan.bhtpvz.common.entity.plant.appease;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.advancement.trigger.PlayerPlacePAZTrigger;
import com.hungteen.pvz.common.entity.bullet.itembullet.PeaEntity;
import com.hungteen.pvz.common.entity.plant.appease.PeaShooterEntity;
import com.hungteen.pvz.common.event.events.SummonCardUseEvent;
import com.hungteen.pvz.common.item.spawn.card.ImitaterCardItem;
import com.hungteen.pvz.common.item.spawn.card.PlantCardItem;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class PeaPodEntity extends PeaShooterEntity {
    private static final DataParameter<Integer> DATA_COUNT  = EntityDataManager.defineId(PeaPodEntity.class, DataSerializers.INT);

    public PeaPodEntity(EntityType<? extends PeaShooterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public void shootBullet() {
        if (this.isPlantInSuperMode()){
            if(this.getCount()>=1&&this.tickCount%20==0) {this.performShoot(0.2, 0.0, -0.75, true, 0.0);}//1
        } else {
            if(this.getCount()>=1) {this.performShoot(0.2, 0.0, -0.75, true, 0.0);}//1
            if(this.getCount()>=2) {this.performShoot(0.2, 1.0, -0.75, false, -4.5);}//2
            if(this.getCount()>=3) {this.performShoot(0.2, -1.0, -0.75, false, 4.5);}//3
            if(this.getCount()>=4) {this.performShoot(0.2, 0.0, 0.0, false, 0.0);}//4
            if(this.getCount()>=5) {this.performShoot(0.2, 0.0, 0.75, false, 0.0);}//5
        }
    }

    @Override
    public ActionResultType interactAt(PlayerEntity player, Vector3d vec3d, Hand hand) {
        if (!this.level.isClientSide) {
            ItemStack stack = player.getItemInHand(hand);
            PlantCardItem item = (PlantCardItem) stack.getItem();
            IPlantType plantType = item.plantType;
            ItemStack plantStack = getPlantStack(stack);
            int sunCost = item.getCardSunCost(player, stack);
            boolean flag = !player.getCooldowns().isOnCooldown(item)&& plantType== BHTPvZPlants.PEA_POD;
            boolean flag1 = sunCost <= PlayerUtil.getResource(player, Resources.SUN_NUM) || player.isCreative();
            if (flag && flag1 && this.getCount()<5){
                MinecraftForge.EVENT_BUS.post(new SummonCardUseEvent(player, stack,plantStack));
                if (player instanceof ServerPlayerEntity) {
                    PlayerPlacePAZTrigger.INSTANCE.trigger((ServerPlayerEntity) player, PlayerPlacePAZTrigger.PlaceTypes.PLANT.toString().toLowerCase(), item.plantType.getIdentity());
                }
                if (PlayerUtil.isPlayerSurvival(player)) {
                    if (item.isEnjoyCard) {
                        stack.shrink(1);
                    } else if(!PlayerUtil.isPAZLocked(player, plantType)){
                        PlantCardItem.handlePlantCardCoolDown(player, stack, item.getDefaultInstance(), item);
                    }
                    PlayerUtil.addResource(player, Resources.SUN_NUM, -sunCost);
                } else {
                    player.getCooldowns().addCooldown(stack.getItem(), 10);
                }
                this.addCount();
                return ActionResultType.SUCCESS;
            }
                return ActionResultType.FAIL;
        }

        return super.interactAt(player, vec3d, hand);
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_COUNT,1);
    }
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
         compound.putInt("Count", this.getCount()-1);
    }

    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        int i = compound.getInt("Count");
        if (i < 0) {
            i = 0;
        }
        this.setCount(i + 1);
    }
    private void setCount(int count) {
        this.entityData.set(DATA_COUNT, count);
    }

    private void addCount(){
        int count = this.getCount();
        this.entityData.set(DATA_COUNT, count + 1);
    }

    public int getCount() {
        return this.entityData.get(DATA_COUNT);
    }

    protected PeaEntity.Type getShootType() {
        return this.isPlantInSuperMode() ? PeaEntity.Type.HUGE : PeaEntity.Type.NORMAL;
    }
    private static ItemStack getPlantStack(ItemStack stack) {
        return ImitaterCardItem.getDoubleStack(stack).getSecond();
    }
    public IPlantType getPlantType() {
        return BHTPvZPlants.PEA_POD;
    }

}
