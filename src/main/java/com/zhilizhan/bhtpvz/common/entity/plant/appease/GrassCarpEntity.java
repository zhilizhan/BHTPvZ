package com.zhilizhan.bhtpvz.common.entity.plant.appease;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.common.entity.plant.appease.PeaShooterEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.common.entity.bullet.itembullet.FishPeaEntity;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class GrassCarpEntity extends PeaShooterEntity {


    public GrassCarpEntity(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
        this.setCharmed(true);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new FloatGoal(this));
    }
    //修改射击高度
    @Override
    public void performShoot(double forwardOffset, double rightOffset, double heightOffset, boolean needSound, double angleOffset) {
        Optional.ofNullable(this.getTarget()).ifPresent((target) -> {
            Vec3 vec = EntityUtil.getNormalisedVector2d(this, target);
            double deltaY = (double)(this.getDimensions(this.getPose()).height * 0.3F) + heightOffset;
            double deltaX = forwardOffset * vec.x - rightOffset * vec.z;
            double deltaZ = forwardOffset * vec.z + rightOffset * vec.x;
            AbstractBulletEntity bullet = this.createBullet();
            bullet.setPos(this.getX() + deltaX, this.getY() + deltaY, this.getZ() + deltaZ);
            bullet.shootPea(target.getX() - bullet.getX(), target.getY() + (double)target.getBbHeight() - bullet.getY(), target.getZ() - bullet.getZ(), (double)this.getBulletSpeed(), angleOffset);
            if (needSound) {
                EntityUtil.playSound(this, this.getShootSound());
            }

            bullet.summonByOwner(this);
            bullet.setAttackDamage(this.getAttackDamage());
            this.level.addFreshEntity(bullet);
        });
    }

    public void normalPlantTick() {
        super.normalPlantTick();
         if (!this.level.isClientSide && this.canAttackNow()) {
            this.shootBullet();
        }

        if (this.getAttackTime() > 0) {
            this.setAttackTime(this.getAttackTime() - 1);
        }
    }
    protected AbstractBulletEntity createBullet() {
        return new FishPeaEntity(this.level);
    }
    public void startShootAttack() {
        this.setAttackTime(1);
    }
    public EntityDimensions getDimensions(Pose poseIn) {
        return EntityDimensions.scalable(0.8F, 0.8F);
    }

    @Override
    public IPlantType getPlantType() {
        return BHTPvZPlants.GRASS_CARP;
    }

}