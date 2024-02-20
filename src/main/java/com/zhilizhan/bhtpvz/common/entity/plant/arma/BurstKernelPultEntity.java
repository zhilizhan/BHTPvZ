package com.zhilizhan.bhtpvz.common.entity.plant.arma;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantPultEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.common.entity.bullet.BurstCornEntity;
import com.zhilizhan.bhtpvz.common.entity.bullet.CornEntity;
import com.zhilizhan.bhtpvz.common.entity.bullet.itembullet.PopCornEntity;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;

public class BurstKernelPultEntity extends PlantPultEntity {

    private static final EntityDataAccessor<Integer> CURRENT_BULLET;
    private BurstKernelPultEntity upgradeEntity;

    public BurstKernelPultEntity(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(CURRENT_BULLET, CornTypes.POP_CORN.ordinal());
    }

    public void onPlantUpgrade(PVZPlantEntity plantEntity) {
        super.onPlantUpgrade(plantEntity);
        if (this.upgradeEntity != null) {
            this.upgradeEntity.remove();
        }

    }

    public boolean canBeUpgrade(Player player) {
        this.upgradeEntity = this.getNearByPult(player);
        return super.canBeUpgrade(player) && EntityUtil.isEntityValid(this.upgradeEntity);
    }

    private BurstKernelPultEntity getNearByPult(Player player) {
        float range = 1.5F;
        List<BurstKernelPultEntity> list = this.level.getEntitiesOfClass(BurstKernelPultEntity.class, EntityUtil.getEntityAABB(this, range, range), (pult) -> !pult.is(this) && pult.getPlantType() == BHTPvZPlants.BURST_KERNEL_PULT && !EntityUtil.canAttackEntity(pult, player));
        return list.size() == 0 ? null : list.get(0);
    }

    public void startPultAttack() {
        super.startPultAttack();
        this.changeBullet();
    }

    protected void changeBullet() {
        if (this.isPlantInSuperMode() && !this.isSuperOut) {
            this.setCurrentBullet(CornTypes.POP_CORN);
        } else if(this.getRandom().nextInt(2) == 0){
            this.setCurrentBullet(CornTypes.POP_CORN);}
        else {
            this.setCurrentBullet(this.getRandom().nextInt(6) == 0 ? CornTypes.BURST_CORN : CornTypes.CORN);
        }
    }

    public void performPult(LivingEntity target1) {
        super.performPult(target1);
        this.setCurrentBullet(CornTypes.POP_CORN);
    }

    protected PultBulletEntity createBullet() {
        return !this.isPlantInSuperMode() && this.getCurrentBullet() == CornTypes.POP_CORN ? new PopCornEntity(this.level, this):(this.getCurrentBullet() == CornTypes.CORN?new CornEntity(this.level,this):new BurstCornEntity(level,this));
    }

    public float getSuperDamage() {
        return 2.0F * this.getAttackDamage();
    }

    public float getAttackDamage() {
        return 5;
    }

    public EntityDimensions getDimensions(Pose poseIn) {
        return EntityDimensions.scalable(0.8F, 1.0F);
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("current_bullet_type")) {
            this.setCurrentBullet(BurstKernelPultEntity.CornTypes.values()[compound.getInt("current_bullet_type")]);
        }

    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("current_bullet_type", this.getCurrentBullet().ordinal());
    }

    public void setCurrentBullet(BurstKernelPultEntity.CornTypes type) {
        this.entityData.set(CURRENT_BULLET, type.ordinal());
    }

    public BurstKernelPultEntity.CornTypes getCurrentBullet() {
        return BurstKernelPultEntity.CornTypes.values()[this.entityData.get(CURRENT_BULLET)];
    }
    public IPlantType getPlantType() {
        return BHTPvZPlants.BURST_KERNEL_PULT;
    }

    static {
        CURRENT_BULLET = SynchedEntityData.defineId(BurstKernelPultEntity.class, EntityDataSerializers.INT);
    }

    public enum CornTypes {
        CORN,
        BURST_CORN,
        POP_CORN;

        CornTypes() {
        }
    }
}
