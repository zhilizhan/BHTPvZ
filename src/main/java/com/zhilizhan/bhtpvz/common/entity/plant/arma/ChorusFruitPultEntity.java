package com.zhilizhan.bhtpvz.common.entity.plant.arma;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.common.entity.plant.arma.KernelPultEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantPultEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.common.entity.bullet.ChorusFruitEntity;
import com.zhilizhan.bhtpvz.common.entity.bullet.PoppedChorusFruitEntity;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;

public class ChorusFruitPultEntity extends PlantPultEntity {

    private static final EntityDataAccessor<Integer> CURRENT_BULLET;
    private static final int BUTTER_CHANCE = 10;


    public ChorusFruitPultEntity(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(CURRENT_BULLET, KernelPultEntity.CornTypes.KERNEL.ordinal());
    }




    private ChorusFruitPultEntity getNearByPult(Player player) {
        float range = 1.5F;
        List<ChorusFruitPultEntity> list = this.level.getEntitiesOfClass(ChorusFruitPultEntity.class, EntityUtil.getEntityAABB(this, 1.5, 1.5), (pult) -> {
            return !pult.is(this) && pult.getPlantType() == PVZPlants.KERNEL_PULT && !EntityUtil.canAttackEntity(pult, player);
        });
        return list.size() == 0 ? null : (ChorusFruitPultEntity)list.get(0);
    }

    public void startPultAttack() {
        super.startPultAttack();
        this.changeBullet();
    }

    protected void changeBullet() {
        if (this.isPlantInSuperMode() && !this.isSuperOut) {
            this.setCurrentBullet(ChorusFruitTypes.POPPED);
        } else {
            this.setCurrentBullet(this.getRandom().nextInt(3) == 0 ? ChorusFruitTypes.NORMAL : ChorusFruitTypes.POPPED);
        }
    }

    public void performPult(LivingEntity target1) {
        super.performPult(target1);
        this.setCurrentBullet(ChorusFruitTypes.POPPED);
    }

    protected PultBulletEntity createBullet() {
        return (PultBulletEntity)(!this.isPlantInSuperMode() && this.getCurrentBullet() != ChorusFruitTypes.NORMAL ? new PoppedChorusFruitEntity(this.level, this) : new ChorusFruitEntity(this.level, this));
    }

    public float getSuperDamage() {
        return 2.0F * this.getAttackDamage();
    }

    public float getAttackDamage() {
        return this.getSkillValue(SkillTypes.MORE_KERNEL_DAMAGE);
    }



    public int getButterDuration() {
        return 100;
    }

    public EntityDimensions getDimensions(Pose poseIn) {
        return EntityDimensions.scalable(0.8F, 1.0F);
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("current_bullet_type")) {
            this.setCurrentBullet(ChorusFruitTypes.values()[compound.getInt("current_bullet_type")]);
        }

    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("current_bullet_type", this.getCurrentBullet().ordinal());
    }

    public void setCurrentBullet(ChorusFruitTypes type) {
        this.entityData.set(CURRENT_BULLET, type.ordinal());
    }

    public ChorusFruitTypes getCurrentBullet() {
        return ChorusFruitTypes.values()[(Integer)this.entityData.get(CURRENT_BULLET)];
    }

    public IPlantType getPlantType() {
        return BHTPvZPlants.CHORUS_FRUIT_PULT;
    }

    static {
        CURRENT_BULLET = SynchedEntityData.defineId(ChorusFruitPultEntity.class, EntityDataSerializers.INT);
    }

    public static enum ChorusFruitTypes {
        NORMAL,
        POPPED;

        private ChorusFruitTypes() {
        }
    }
}
