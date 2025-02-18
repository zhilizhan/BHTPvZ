package com.zhilizhan.bhtpvz.common.entity.plant.arma;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantPultEntity;
import com.zhilizhan.bhtpvz.common.entity.bullet.ChorusFruitEntity;
import com.zhilizhan.bhtpvz.common.entity.bullet.PoppedChorusFruitEntity;
import com.zhilizhan.bhtpvz.common.impl.BHTPvZSkill;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.entity.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class ChorusFruitPultEntity extends PlantPultEntity {

    private static final DataParameter<Integer>  CURRENT_BULLET = EntityDataManager.defineId(ChorusFruitPultEntity.class, DataSerializers.INT);

    public ChorusFruitPultEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(CURRENT_BULLET, ChorusFruitTypes.POPPED.ordinal());
    }
    @Override
    public int getPultCD() {
        return 100;
    }

    @Override
    public void startPultAttack() {
        super.startPultAttack();
        this.changeBullet();
    }

    protected void changeBullet() {
        if (this.isPlantInSuperMode() && !this.isSuperOut) {
            this.setCurrentBullet(ChorusFruitTypes.POPPED);
        } else {
            this.setCurrentBullet(this.getRandom().nextInt(2) == 0 ? ChorusFruitTypes.NORMAL : ChorusFruitTypes.POPPED);
        }
    }

    public void performPult(LivingEntity target1) {
        super.performPult(target1);
        this.setCurrentBullet(ChorusFruitTypes.POPPED);
    }

    protected PultBulletEntity createBullet() {
        return !this.isPlantInSuperMode() && this.getCurrentBullet() != ChorusFruitTypes.NORMAL ? new PoppedChorusFruitEntity(this.level, this) : new ChorusFruitEntity(this.level, this);
    }

    public float getSuperDamage() {
        return 2.0F * this.getAttackDamage();
    }

    public float getAttackDamage() {
        return this.getSkillValue(BHTPvZSkill.MORE_CHORUS_FRUIT_DAMAGE);
    }


    public EntitySize getDimensions(Pose poseIn) {
        return EntitySize.scalable(0.75F, 0.8F);
    }

    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("current_bullet_type")) {
            this.setCurrentBullet(ChorusFruitTypes.values()[compound.getInt("current_bullet_type")]);
        }

    }

    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("current_bullet_type", this.getCurrentBullet().ordinal());
    }

    public void setCurrentBullet(ChorusFruitTypes type) {
        this.entityData.set(CURRENT_BULLET, type.ordinal());
    }

    public ChorusFruitTypes getCurrentBullet() {
        return ChorusFruitTypes.values()[this.entityData.get(CURRENT_BULLET)];
    }

    public IPlantType getPlantType() {
        return BHTPvZPlants.CHORUS_FRUIT_PULT;
    }


    public enum ChorusFruitTypes {
        NORMAL,
        POPPED;

        ChorusFruitTypes() {
        }
    }
}
