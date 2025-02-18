package com.zhilizhan.bhtpvz.common.entity.plant.enforce;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.ai.goal.target.PVZNearestTargetGoal;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.PAZAlmanacs;
import com.mojang.datafixers.util.Pair;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class RotateRadishEntity extends PVZPlantEntity {

    public RotateRadishEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new RotateRadishEntity.RotateAttackGoal(this));
        this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, false, false, 3.0F, 3.0F));

    }

    public void performAttack() {
        for (int i = 0; i < 5; ++i) {
            EntityUtil.spawnParticle(this, 7);
        }

        float range = 2.0F;
        EntityUtil.getTargetableEntities(this, EntityUtil.getEntityAABB(this, range, range)).forEach((target) -> {
            double d1 = target.getX() - this.getX();

            double d0;
            for(d0 = target.getZ() - this.getZ(); d1 * d1 + d0 * d0 < 1.0E-4; d0 = (Math.random() - Math.random()) * 0.01) {
                d1 = (Math.random() - Math.random()) * 0.01;
            }
             if(target instanceof LivingEntity&& EntityUtil.isEntityValid(target)) {
                 ((LivingEntity) target).hurtDir = (float) (Math.atan2(d0, d1) * 57.2957763671875 - (double) this.yRot);
                 ((LivingEntity) target).knockback(2, d1, d0);
             }
            target.hurt(PVZEntityDamageSource.normal(this), this.getAttackDamage());

        });
        EntityUtil.playSound(this, SoundRegister.SWING.get());
    }
    //大招
    protected void normalPlantTick() {
        super.normalPlantTick();
        if (!this.level.isClientSide && this.isPlantInSuperMode() && this.getSuperTime() % 5 == 0) {
            float range = 5.0F;
            EntityUtil.getTargetableEntities(this, EntityUtil.getEntityAABB(this, range, range)).forEach((target) -> {
                double d1 = target.getX() - this.getX();

                double d0;
                for(d0 = target.getZ() - this.getZ(); d1 * d1 + d0 * d0 < 1.0E-4; d0 = (Math.random() - Math.random()) * 0.01) {
                    d1 = (Math.random() - Math.random()) * 0.01;
                }

                target.hurt(PVZEntityDamageSource.normal(this), this.getAttackDamage() * 5.0F);
                ((LivingEntity)target).knockback(0.5f, d1, d0);
                EntityUtil.spawnParticle(target, 7);
                EntityUtil.playSound(this, (SoundEvent)SoundRegister.SWING.get());
            });
        }

    }


    public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
        super.addAlmanacEntries(list);
        list.addAll(Arrays.asList(Pair.of(PAZAlmanacs.ATTACK_DAMAGE, this.getAttackDamage()), Pair.of(PAZAlmanacs.ATTACK_CD, this.getAttackCD())));
    }

    public int getAttackCD() {
        return 30;
    }
    public float getAttackDamage() {
        return this.getSkillValue(SkillTypes.MORE_SWING_DAMAGE)/1.5F;
    }

    private final class RotateAttackGoal extends Goal {
        private final RotateRadishEntity attacker;

        public RotateAttackGoal(RotateRadishEntity attacker) {
            this.attacker = attacker;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        public boolean canUse() {
            LivingEntity living = this.attacker.getTarget();
            if (!EntityUtil.isEntityValid(living)) {
                return false;
            } else {
                return this.attacker.canSee(living) && EntityUtil.getAttackRange(this.attacker, living, 3.0) >= EntityUtil.getNearestDistance(this.attacker, living);
            }
        }

        public boolean canContinueToUse() {
            LivingEntity living = this.attacker.getTarget();
            if (!EntityUtil.isEntityValid(living)) {
                return false;
            } else {
                return this.attacker.canSee(living) && EntityUtil.getAttackRange(this.attacker, living, 3.0) >= EntityUtil.getNearestDistance(this.attacker, living);
            }
        }

        public void stop() {
            this.attacker.setTarget((LivingEntity)null);
            this.attacker.setAttackTime(0);
        }

        public void tick() {
            LivingEntity target = this.attacker.getTarget();;
            if (this.attacker.getAttackTime() == 0) {
                if (this.attacker.getAttackDamage() >= EntityUtil.getCurrentHealth(target)) {
                    this.attacker.setAttackTime(1);
                } else {
                    this.attacker.setAttackTime(-1);
                }
            } else if (this.attacker.getAttackTime() > 0) {
                this.attacker.setAttackTime(this.attacker.getAttackTime() + 1);
                if (this.attacker.getAttackTime() == this.attacker.getAttackCD() * 4 / 5) {
                    this.attacker.performAttack();
                } else if (this.attacker.getAttackTime() >= this.attacker.getAttackCD()) {
                    this.attacker.setAttackTime(0);
                }
            } else {
                this.attacker.setAttackTime(this.attacker.getAttackTime() - 1);
                if (-this.attacker.getAttackTime() == this.attacker.getAttackCD() * 4 / 5) {
                    this.attacker.performAttack();
                } else if (-this.attacker.getAttackTime() >= this.attacker.getAttackCD()) {
                    this.attacker.setAttackTime(0);
                }
            }

        }
    }
    public int getSuperTimeLength() {
        return 120;
    }
    public IPlantType getPlantType() {
        return BHTPvZPlants.ROTATE_RADISH;

    }
}