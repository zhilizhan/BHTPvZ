package com.zhilizhan.bhtpvz.common.entity.zombie.bhtpvz;

import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.entity.ai.goal.attack.PVZZombieAttackGoal;
import com.hungteen.pvz.common.entity.plant.spear.SpikeRockEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.roof.ImpEntity;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.common.impl.zombie.BHTPvZZombies;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

import java.util.EnumSet;

public class Edgar090547Entity extends PVZZombieEntity {
    public Edgar090547Entity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        this.setIsWholeBody();
    }

    protected PVZEntityDamageSource getZombieAttackDamageSource() {
        return PVZEntityDamageSource.causeCrushDamage(this);
    }

    public int getCrushCD() {
        return 30;
    }
    private final class CrushAttackGoal extends Goal {
        protected final Edgar090547Entity attacker;

        public CrushAttackGoal(Edgar090547Entity creature) {
            this.attacker = creature;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        public boolean canUse() {
            return this.attacker.getAttackTime() > 0;
        }

        public boolean canContinueToUse() {
            return this.attacker.getAttackTime() > 0;
        }

        public void tick() {
            if (this.attacker.canNormalUpdate() && this.attacker.getAttackTime() > 0) {
                this.attacker.setAttackTime(this.attacker.getAttackTime() - 1);
                if (this.attacker.getAttackTime() == this.attacker.getCrushCD() / 3&& this.attacker.getTarget()!=null) {
                    this.attacker.doHurtTarget(this.attacker.getTarget());
                }
            }
        }
    }
    protected void registerAttackGoals() {
        this.goalSelector.addGoal(3, new Edgar090547Entity.MoveToTargetGoal(this, true));
        this.goalSelector.addGoal(2, new Edgar090547Entity.CrushAttackGoal(this));
    }
    private final class MoveToTargetGoal extends PVZZombieAttackGoal {
        public MoveToTargetGoal(Edgar090547Entity creature, boolean useLongMemory) {
            super(creature, useLongMemory);
        }

        public boolean canUse() {
            return this.zombie.getAttackTime() == 0 && super.canUse();
        }

        public boolean canContinueToUse() {
            return this.zombie.getAttackTime() == 0 && super.canContinueToUse();
        }

        protected void checkAndPerformAttack(LivingEntity target) {
            double dis = EntityUtil.getNearestDistance(this.attacker, target);
            double range = this.getAttackReachSqr(target);
            if (range >= dis && this.attackTick <= 0) {
                this.attackTick = this.zombie.getAttackCD();
                if(random.nextInt(2) == 1){this.attacker.swing(InteractionHand.MAIN_HAND);}
                else{this.attacker.swing(InteractionHand.OFF_HAND);}
                this.attacker.swing(InteractionHand.MAIN_HAND);
                this.zombie.setAttackTime(Edgar090547Entity.this.getCrushCD());
            }

        }
    }
    protected void initAttributes() {
        super.initAttributes();
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(Angry()?0.36000000596046448:0.25000000596046448);
    }
    public boolean Angry() {
        return this.canNormalUpdate() && this.getHealth() / this.getMaxHealth() < 0.5F;
    }

    protected float getModifyAttackDamage(Entity entity, float f) {
        return this.Angry() ?  EntityUtil.getMaxHealthDamage((LivingEntity)entity) : 15;
    }
    @Override
    public float getLife() {
        return 300.0f;
    }
    public int getArmorToughness() {
        return 15;
    }

    @Override
    public boolean canPAZTarget(Entity target) {
        if (target instanceof SpikeRockEntity) {
            return true;
        }
        return super.canPAZTarget(target);
    }

    @Override
    public EntityDimensions getDimensions(Pose poseIn) {
        if (this.isMiniZombie()) {
            return EntityDimensions.scalable(0.3f, 1.0f);
        }
        return EntityDimensions.scalable(0.4f, 2.0f);
    }

    // Explosion
    @Override
    protected void dropAllDeathLoot(DamageSource damageSourceIn) {
        this.level.explode(this, getX(), getY(), getZ(), 2.5f, Explosion.BlockInteraction.NONE);
        EntityUtil.playSound(this, (SoundEvent) SoundRegister.IMP_HAPPY.get());
        ImpEntity imp = (ImpEntity)((EntityType) EntityRegister.IMP.get()).create(this.level);
        EntityUtil.onEntitySpawn(this.level, imp, this.blockPosition());
        super.dropAllDeathLoot(damageSourceIn);
    }/*
    public int getGlimmerCD() {
       return  (int) this.distanceToSqr(this.getTarget())>47 ? 0 :(int) this.distanceToSqr(this.getTarget())/2;}
    }
    */

    @Override
    public ZombieType getZombieType() {
        return BHTPvZZombies.EDGAR_090547;
    }
}
