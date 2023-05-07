package com.zhilizhan.bhtpvz.common.entity.plant.enforce;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.ai.goal.target.PVZNearestTargetGoal;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.pool.BalloonZombieEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.PAZAlmanacs;
import com.mojang.datafixers.util.Pair;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class RotateRadishEntity extends PVZPlantEntity {
    private static final float SHOOT_HEIGHT = 0.2F;
    private static final int SHOOT_NUM = 8;

    public RotateRadishEntity(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new RotateRadishAttackGoal(this));
        this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, false, 3.0F, 3.0F));
    }

    protected void normalPlantTick() {
        super.normalPlantTick();
        if (!this.level.isClientSide && this.isPlantInSuperMode() && this.getSuperTime() % 5 == 0) {
            float range = 5.0F;
            EntityUtil.getTargetableEntities(this, EntityUtil.getEntityAABB(this, 5.0, 5.0)).forEach((target) -> {
                target.hurt(PVZEntityDamageSource.normal(this), this.getAttackDamage() * 5.0F);
                EntityUtil.spawnParticle(target, 7);
                EntityUtil.playSound(this, (SoundEvent) SoundRegister.SWING.get());
            });
        }

    }

    public void attackTarget(LivingEntity target) {
        EntityUtil.playSound(this, (SoundEvent)SoundRegister.SWING.get());
        EntityUtil.spawnParticle(target, 7);
        target.hurt(PVZEntityDamageSource.normal(this), this.getAttackDamage());
    }

    public boolean canPAZTarget(Entity entity) {
        return entity instanceof BalloonZombieEntity ? true : super.canPAZTarget(entity);
    }

    public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
        super.addAlmanacEntries(list);
        list.addAll(Arrays.asList(Pair.of(PAZAlmanacs.ATTACK_DAMAGE, this.getAttackDamage()), Pair.of(PAZAlmanacs.ATTACK_CD, this.getAttackCD())));
    }

    public int getAttackCD() {
        return 10;
    }

    public float getAttackDamage() {
        return this.getSkillValue(SkillTypes.MORE_SWING_DAMAGE);
    }

    public int getSuperTimeLength() {
        return 120;
    }



    private final class RotateRadishAttackGoal extends Goal {
        private final RotateRadishEntity attacker;

        public RotateRadishAttackGoal(RotateRadishEntity attacker) {
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
                return this.attacker.canSee(living) && EntityUtil.getAttackRange(this.attacker, living, 5.0) >= EntityUtil.getNearestDistance(this.attacker, living);
            }
        }

        public void stop() {
            this.attacker.setTarget((LivingEntity)null);
            this.attacker.setAttackTime(0);
        }

        public void tick() {
            LivingEntity target = this.attacker.getTarget();
            if (this.attacker.getAttackTime() == 0) {
                if (this.attacker.getAttackDamage() >= EntityUtil.getCurrentHealth(target)) {
                    this.attacker.setAttackTime(1);
                } else {
                    this.attacker.setAttackTime(-1);
                }
            } else if (this.attacker.getAttackTime() > 0) {
                this.attacker.setAttackTime(this.attacker.getAttackTime() + 1);
                if (this.attacker.getAttackTime() == this.attacker.getAttackCD() * 4 / 5) {
                    this.attacker.attackTarget(target);
                } else if (this.attacker.getAttackTime() >= this.attacker.getAttackCD()) {
                    this.attacker.setAttackTime(0);
                }
            } else {
                this.attacker.setAttackTime(this.attacker.getAttackTime() - 1);
                if (-this.attacker.getAttackTime() == this.attacker.getAttackCD() * 4 / 5) {
                    this.attacker.attackTarget(target);
                } else if (-this.attacker.getAttackTime() >= this.attacker.getAttackCD()) {
                    this.attacker.setAttackTime(0);
                }
            }

        }
    }
    public IPlantType getPlantType() {
        return BHTPvZPlants.ROTATE_RADISH;
    }
}

