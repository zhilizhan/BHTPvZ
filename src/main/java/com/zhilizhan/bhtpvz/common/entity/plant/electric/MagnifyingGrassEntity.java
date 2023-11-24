package com.zhilizhan.bhtpvz.common.entity.plant.electric;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.ai.goal.target.PVZNearestTargetGoal;
import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.common.entity.plant.light.SunFlowerEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import com.zhilizhan.bhtpvz.common.damagesource.BHTPvZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.entity.bullet.LightBeamEntity;
import com.zhilizhan.bhtpvz.common.impl.BHTPvZSkill;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class MagnifyingGrassEntity extends PlantShooterEntity {
    private static final EntityDataAccessor<Integer> DATA_ID_ATTACK_TARGET= SynchedEntityData.defineId(MagnifyingGrassEntity.class, EntityDataSerializers.INT);

    private LivingEntity clientSideCachedAttackTarget;
    private int clientSideAttackTime;
    public MagnifyingGrassEntity(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }
    public int sunCost = 50;
    protected void registerGoals() {
        this.goalSelector.addGoal(4, new SpuerAttackGoal(this));
        this.targetSelector.addGoal(1, new PVZNearestTargetGoal(this, false,true, 10, 10));

    }
    @Override
    protected AbstractBulletEntity createBullet() {
        LightBeamEntity beam = new LightBeamEntity(this.level, this);
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
    @Override
    public InteractionResult interactAt(Player player, Vec3 vec3d, InteractionHand hand) {
        if (!this.level.isClientSide) {
            if (PlayerUtil.getResource(player, Resources.SUN_NUM) > sunCost && this.getTarget()!=null && this.getAttackTime()==0 && !this.isDeadOrDying()) {
                if(!player.isCreative()) PlayerUtil.addResource(player, Resources.SUN_NUM, -sunCost);
                this.setAttackTime(1);
                return InteractionResult.SUCCESS;
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
        return (Integer)this.entityData.get(DATA_ID_ATTACK_TARGET) != 0;
    }
    public float getAttackAnimationScale(float partialTick) {
        return ((float)this.clientSideAttackTime + partialTick) / (float)this.getAttackDuration();
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
                    this.getAttackAnimationScale(0.0F);
                }
            }

        });
    }
    @Nullable
    public LivingEntity getActiveAttackTarget() {
        if (!this.hasActiveAttackTarget()) {
            return null;
        } else if (this.level.isClientSide) {
            if (this.clientSideCachedAttackTarget != null) {
                return this.clientSideCachedAttackTarget;
            } else {
                Entity lv = this.level.getEntity((Integer)this.entityData.get(DATA_ID_ATTACK_TARGET));
                if (lv instanceof LivingEntity) {
                    this.clientSideCachedAttackTarget = (LivingEntity)lv;
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

    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);
        if (DATA_ID_ATTACK_TARGET.equals(key)) {
            this.clientSideAttackTime = 0;
            this.clientSideCachedAttackTarget = null;
        }

    }

    public int getAttackDuration() {
        return this.getSuperTime();
    }
    static class SpuerAttackGoal extends Goal {
        private final MagnifyingGrassEntity grass;
        private int attackTime;

        public SpuerAttackGoal(MagnifyingGrassEntity arg) {
            this.grass = arg;
        }

        public boolean canUse() {
            LivingEntity lv = this.grass.getTarget();
            return lv != null && lv.isAlive() && this.grass.isPlantInSuperMode();
        }

        public boolean canContinueToUse() {
            return super.canContinueToUse() && ( this.grass.distanceToSqr(this.grass.getTarget()) > 12.0) && this.grass.isPlantInSuperMode();
        }

        public void start() {
            this.attackTime = -10;
            this.grass.getLookControl().setLookAt(this.grass.getTarget(), 90.0F, 90.0F);
            this.grass.hasImpulse = true;
        }

        public void stop() {
            this.grass.setActiveAttackTarget(0);
            this.grass.setTarget((LivingEntity)null);
        }

        public void tick() {
            LivingEntity lv = this.grass.getTarget();
            this.grass.getLookControl().setLookAt(lv, 90.0F, 90.0F);
            if (!this.grass.canSee(lv)) {
                this.grass.setTarget((LivingEntity)null);
            } else {
                ++this.attackTime;
                if (this.attackTime == 0) {
                    this.grass.setActiveAttackTarget(this.grass.getTarget().getId());
                    if (!this.grass.isSilent()) {
                        this.grass.level.broadcastEntityEvent(this.grass, (byte)21);
                    }
                } else if (this.attackTime >= this.grass.getAttackDuration()) {
                    float baseDamage = this.grass.getSuperAttackDamage();
                    float inflate = 8.0f;
                    AABB entityAABB = this.grass.getBoundingBox().inflate(inflate, inflate/2, inflate);
                    List<SunFlowerEntity> sunFlower = this.grass.level.getEntitiesOfClass(SunFlowerEntity.class, entityAABB);
                    float finalDamage = baseDamage*sunFlower.size();

                    if (lv != null) {
                        lv.hurt(BHTPvZEntityDamageSource.magnifyingGrass(this.grass, this.grass), finalDamage);
                    }
                    this.grass.setTarget((LivingEntity)null);
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
