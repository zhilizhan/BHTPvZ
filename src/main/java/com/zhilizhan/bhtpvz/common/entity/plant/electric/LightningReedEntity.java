package com.zhilizhan.bhtpvz.common.entity.plant.electric;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.ai.goal.target.PVZNearestTargetGoal;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.common.damagesource.BHTPvZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.entity.bullet.LightningBeamEntity;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Optional;

public class LightningReedEntity extends PVZPlantEntity {
    private static final EntityDataAccessor<Integer> DATA_ID_ATTACK_TARGET = SynchedEntityData.defineId(LightningReedEntity.class, EntityDataSerializers.INT);

    private LivingEntity clientSideCachedAttackTarget;
    private int clientSideAttackTime;

    public LightningReedEntity(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }


    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(4, new LightningAttackGoal(this));
        this.targetSelector.addGoal(1, new PVZNearestTargetGoal(this, false,true, 10, 10));
    }


    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_ATTACK_TARGET, 0);
    }


    private void setActiveAttackTarget(int activeAttackTargetId) {
        this.entityData.set(DATA_ID_ATTACK_TARGET, activeAttackTargetId);

    }
    public boolean hasActiveAttackTarget() {
        return (Integer)this.entityData.get(DATA_ID_ATTACK_TARGET) !=0;
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
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);
        if (DATA_ID_ATTACK_TARGET.equals(key)) {
            this.clientSideAttackTime = 0;
            this.clientSideCachedAttackTarget = null;
        }
    }


    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimension) {
        return dimension.height * 0.5F;
    }


    public float getAttackAnimationScale(float partialTick) {
        return ((float)this.clientSideAttackTime + partialTick);
    }

    public float getAttackDamage() {
        return this.getSkillValue(SkillTypes.PEA_DAMAGE)*0.75f;
    }

    @Override
    public IPlantType getPlantType() {
        return BHTPvZPlants.LIGHTNING_REED;
    }


    static class LightningAttackGoal extends Goal {
        private final LightningReedEntity attacker;
        private int attackTime;

        public LightningAttackGoal(LightningReedEntity arg) {
            this.attacker = arg;
            this.setFlags(EnumSet.of(Flag.LOOK));
        }

        public boolean canUse() {
            LivingEntity lv = this.attacker.getTarget();
            return lv != null && lv.isAlive();
        }

        public boolean canContinueToUse() {
            return super.canContinueToUse() && (this.attacker.distanceToSqr(this.attacker.getTarget()) > 8.0);
        }

        public void start() {
            this.attackTime = -10;
            this.attacker.getLookControl().setLookAt(this.attacker.getTarget(), 90.0F, 90.0F);
            this.attacker.hasImpulse = true;
        }

        public void stop() {
            this.attacker.setActiveAttackTarget(0);
            this.attacker.setTarget((LivingEntity) null);
        }
        public void tick() {
            LivingEntity target1 = this.attacker.getTarget();
            this.attacker.getLookControl().setLookAt(target1, 90.0F, 90.0F);

            if (!this.attacker.canSee(target1)) {
                this.attacker.setTarget((LivingEntity)null);
            } else {
                ++this.attackTime;
                if (this.attackTime == 0) {
                    if (target1 != null) {
                        this.attacker.setActiveAttackTarget(target1.getId());

                    if (!this.attacker.isSilent()) {
                        this.attacker.level.broadcastEntityEvent(this.attacker, (byte)21);
                    }
                    float f = attacker.getAttackDamage();
                      LightningBeamEntity beam = BHTPvZEntityTypes.LIGHTNING_BEAM.get().create(attacker.level);
                        BlockPos pos = target1.getEntity().blockPosition();

                        target1.hurt(BHTPvZEntityDamageSource.lightningBeam(this.attacker, this.attacker), f);
                        EntityUtil.onEntitySpawn(attacker.level, beam, pos);
                        this.attacker.setTarget((LivingEntity)null);
                    }
                }

                super.tick();
            }
        }

    }

}
