package com.zhilizhan.bhtpvz.common.entity.plant.appease;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.itembullet.PeaEntity;
import com.hungteen.pvz.common.entity.plant.appease.PeaShooterEntity;
import com.hungteen.pvz.common.item.spawn.card.PlantCardItem;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class PeaPodEntity extends PeaShooterEntity {
    private static final EntityDataAccessor<Integer> DATA_COUNT  = SynchedEntityData.defineId(PeaPodEntity.class, EntityDataSerializers.INT);

    public PeaPodEntity(EntityType<? extends PathfinderMob> type, Level worldIn) {
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
    public InteractionResult interactAt(Player player, Vec3 vec3d, InteractionHand hand) {
        if (!this.level.isClientSide) {
            ItemStack stack = player.getItemInHand(hand);
            PlantCardItem item = (PlantCardItem) stack.getItem();
            if (stack.getItem() instanceof PlantCardItem && item.plantType== BHTPvZPlants.PEA_POD && this.getCount()<5) {
                  this.addCount();
                return InteractionResult.SUCCESS;
            }
        }

        return super.interactAt(player, vec3d, hand);
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_COUNT,1);
    }
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
         compound.putInt("Count", this.getCount()-1);
    }

    public void readAdditionalSaveData(CompoundTag compound) {
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
    public IPlantType getPlantType() {
        return BHTPvZPlants.PEA_POD;
    }

}
