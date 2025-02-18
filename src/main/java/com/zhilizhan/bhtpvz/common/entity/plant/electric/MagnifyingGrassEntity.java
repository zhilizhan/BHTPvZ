package com.zhilizhan.bhtpvz.common.entity.plant.electric;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.ai.goal.target.PVZNearestTargetGoal;
import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.common.entity.misc.drop.DropEntity;
import com.hungteen.pvz.common.entity.misc.drop.SunEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.common.entity.plant.light.SunFlowerEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import com.zhilizhan.bhtpvz.common.damagesource.BHTPvZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.entity.bullet.LightBeamEntity;
import com.zhilizhan.bhtpvz.common.impl.BHTPvZSkill;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class MagnifyingGrassEntity extends PlantShooterEntity {
    private static final DataParameter<Integer> DATA_ID_ATTACK_TARGET= EntityDataManager.defineId(MagnifyingGrassEntity.class, DataSerializers.INT);


    private LivingEntity clientSideCachedAttackTarget;
    private int clientSideAttackTime;
    public MagnifyingGrassEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }
    public int sunCost = 50;
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new SuperAttackGoal(this));
        this.targetSelector.addGoal(1, new PVZNearestTargetGoal(this, false,true, 10, 10));
    }
    @Override
    protected AbstractBulletEntity createBullet() {
        LightBeamEntity beam = new LightBeamEntity(this.level, this);
        beam.setGlowing(true);
        return beam;
    }
    @Override
    public float getAttackDamage() {
        return this.getSkillValue(BHTPvZSkill.LIGHT_BEAM_DAMAGE);
    }

    public float getSuperAttackDamage() {
        return this.getSkillValue(BHTPvZSkill.MAGNIFYING_GRASS_DAMAGE);
    }
    @Override
    public void performShoot(double forwardOffset, double rightOffset, double heightOffset, boolean needSound, double angleOffset) {
        Optional.ofNullable(this.getTarget()).ifPresent((target) -> {
            Vector3d vec = EntityUtil.getNormalisedVector2d(this, target);
            double deltaY = (double)(this.getDimensions(this.getPose()).height * 0.3F) + heightOffset;
            double deltaX = forwardOffset * vec.x - rightOffset * vec.z;
            double deltaZ = forwardOffset * vec.z + rightOffset * vec.x;
            AbstractBulletEntity bullet = this.createBullet();
            bullet.setPos(this.getX() + deltaX, this.getY() + deltaY, this.getZ() + deltaZ);
            bullet.shootPea(target.getX() - bullet.getX(), target.getY() + (double)target.getBbHeight() - bullet.getY(), target.getZ() - bullet.getZ(), this.getBulletSpeed(), angleOffset);
            if (needSound) {
                EntityUtil.playSound(this, this.getShootSound());
            }

            bullet.summonByOwner(this);
            bullet.setAttackDamage(this.getAttackDamage());
            this.level.addFreshEntity(bullet);
        });
    }
    @Override
    public ActionResultType interactAt(PlayerEntity player, Vector3d vec3d, Hand hand) {
        if (!this.level.isClientSide && this.getAttackTime()<=0) {
            if (PlayerUtil.getResource(player, Resources.SUN_NUM) > sunCost && this.getTarget()!=null && this.getAttackTime()==0 && !this.isDeadOrDying()) {
                if(!player.isCreative()) PlayerUtil.addResource(player, Resources.SUN_NUM, -sunCost);
                this.setAttackTime(1);
                return ActionResultType.SUCCESS;
            }
        }
        return super.interactAt(player, vec3d, hand);
    }

    @Override
    public void shootBullet() {
        if (!this.isPlantInSuperMode()) {
          this.performShoot(0.2, 0.0, 0.0, this.getAttackTime() == 1, 0.0);
        }
    }
    @Override
    public int getShootCD() {
        return getSuperTime();
    }
    public int getSuperTimeLength() {
        return 200;
    }
    @Override
    public void startShootAttack() {}
    private void setActiveAttackTarget(int activeAttackTargetId) {
        this.entityData.set(DATA_ID_ATTACK_TARGET, activeAttackTargetId);
    }
    public boolean hasActiveAttackTarget() {
        return this.entityData.get(DATA_ID_ATTACK_TARGET) != 0;
    }

   @Override
    public void normalPlantTick() {
        super.normalPlantTick();
        Optional.ofNullable(this.getTarget()).ifPresent((target) -> {
            EntityUtil.getNormalisedVector2d(this, target);

            if (this.hasActiveAttackTarget()) {
                if (this.clientSideAttackTime < 0) {
                    ++this.clientSideAttackTime;
                }

                LivingEntity lv3 = this.getActiveAttackTarget();
                if (lv3 != null) {
                    this.getLookControl().setLookAt(lv3, 90.0F, 90.0F);
                    this.getLookControl().tick();
                }
            }else tickSunCollect();
        });
    }
    private void tickSunCollect() {
        if (this.level.isClientSide || this.tickCount % 20 != 0) {
            return;
        }
        List<SunEntity> sunList = this.level.getEntitiesOfClass(SunEntity.class, MathUtil.getAABBWithPos(this.blockPosition(), 10.0), sun -> sun.getDropState() == DropEntity.DropStates.NORMAL && !sun.removed);

        if (sunList.isEmpty()) {
            return;
        }

        SunEntity sunEntity = sunList.get(0);
        if (sunEntity == null) {
            return;
        }

        if (this.getAttackTime() <= 0 && sunEntity.getAmount() >= this.sunCost) {
            double speed = 0.15;
            Vector3d now = new Vector3d(this.blockPosition().getX() + 0.5, this.blockPosition().getY() + 1.0, this.blockPosition().getZ() + 0.5);
            Vector3d vec = now.subtract(sunEntity.position());

            if (vec.length() <= 1.0) {
                if (sunEntity.getAmount() <= 0) {
                    sunEntity.remove();
                } else {
                    sunEntity.setAmount(sunEntity.getAmount() - this.sunCost);
                }
                this.setAttackTime(1);
            } else {
                sunEntity.setDeltaMovement(vec.normalize().scale(speed));
            }
        }
    }



    @Nullable
    public LivingEntity getActiveAttackTarget() {
        if (!this.hasActiveAttackTarget()) {
            return null;
        } else if (this.level.isClientSide) {
            if (this.clientSideCachedAttackTarget != null) {
                return this.clientSideCachedAttackTarget;
            } else {
                Entity entity = this.level.getEntity(this.entityData.get(DATA_ID_ATTACK_TARGET));
                if (entity instanceof LivingEntity) {
                    this.clientSideCachedAttackTarget = (LivingEntity)entity;
                    return this.clientSideCachedAttackTarget;
                } else {
                    return null;
                }
            }
        } else {
            return this.getTarget();
        }
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_ATTACK_TARGET, 0);
    }

    public void onSyncedDataUpdated(DataParameter<?> key) {
        super.onSyncedDataUpdated(key);
        if (DATA_ID_ATTACK_TARGET.equals(key)) {
            this.clientSideAttackTime = 0;
            this.clientSideCachedAttackTarget = null;
        }

    }

    public int getAttackDuration() {
        return this.getSuperTime();
    }

    static class SuperAttackGoal extends Goal {
        private final MagnifyingGrassEntity grass;
        private int attackTime;

        public SuperAttackGoal(MagnifyingGrassEntity arg) {
            this.grass = arg;
        }

        public boolean canUse() {
            LivingEntity lv = this.grass.getTarget();
            return lv != null && lv.isAlive() && this.grass.isPlantInSuperMode();
        }

        public boolean canContinueToUse() {
            LivingEntity lv = this.grass.getTarget();
            return super.canContinueToUse() && (EntityUtil.isEntityValid(lv) &&this.grass.distanceToSqr(lv) > 1.0) && this.grass.isPlantInSuperMode();
        }

        public void start() {
            LivingEntity lv = this.grass.getTarget();

            this.attackTime = -10;
            if(EntityUtil.isEntityValid(lv))this.grass.getLookControl().setLookAt(lv, 90.0F, 90.0F);
            this.grass.hasImpulse = true;
        }

        public void stop() {
            this.grass.setActiveAttackTarget(0);
            this.grass.setTarget(null);
        }

        public void tick() {
            LivingEntity living = this.grass.getTarget();
            if(EntityUtil.isEntityValid(living)){
            this.grass.getLookControl().setLookAt(living, 90.0F, 90.0F);
            if (!this.grass.canSee(living)) {
                this.grass.setTarget(null);
            } else {
                ++this.attackTime;
                if (this.attackTime == 0 && this.grass.getTarget() != null) {
                    this.grass.setActiveAttackTarget(this.grass.getTarget().getId());
                    if (!this.grass.isSilent()) {
                        this.grass.level.broadcastEntityEvent(this.grass, (byte) 21);
                    }
                } else if (this.attackTime >= this.grass.getAttackDuration()) {
                    float baseDamage = this.grass.getSuperAttackDamage();
                    float inflate = 8.0f;
                    AxisAlignedBB entityAABB = this.grass.getBoundingBox().inflate(inflate, inflate / 2, inflate);
                    List<SunFlowerEntity> sunFlower = this.grass.level.getEntitiesOfClass(SunFlowerEntity.class, entityAABB);
                    float finalDamage = baseDamage * sunFlower.size();

                    living.hurt(BHTPvZEntityDamageSource.magnifyingGrass(this.grass, this.grass), finalDamage);
                    this.grass.setTarget(null);
                }
            }
                super.tick();
            }
        }
    }
    @Override
    public IPlantType getPlantType() {
        return BHTPvZPlants.MAGNIFYING_GRASS;
    }
}
