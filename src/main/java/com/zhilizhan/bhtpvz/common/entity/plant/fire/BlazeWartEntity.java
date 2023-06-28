package com.zhilizhan.bhtpvz.common.entity.plant.fire;

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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class BlazeWartEntity extends PVZPlantEntity {
    public BlazeWartEntity(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new BlazeWartEntity.BlazeWartAttackGoal(this));
        this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, false, 3.0F, 3.0F));
    }

    protected void normalPlantTick() {
        super.normalPlantTick();
        if (!this.level.isClientSide && this.isPlantInSuperMode() && this.getSuperTime() % 5 == 0) {
            float range = 5.0F;
            EntityUtil.getTargetableEntities(this, EntityUtil.getEntityAABB(this, range, range)).forEach((target) -> {
                target.hurt(PVZEntityDamageSource.normal(this), this.getAttackDamage() * 5.0F);
                EntityUtil.playSound(this, SoundRegister.SWING.get());
                target.setSecondsOnFire(5);
            });
        }

    }

    public void attackTarget(LivingEntity target) {
        EntityUtil.playSound(this, SoundRegister.SWING.get());
        target.hurt(PVZEntityDamageSource.normal(this), this.getAttackDamage());
        target.setSecondsOnFire(3);
    }
    @Override
    public float getLife() {
        return 80;
    }
    public boolean canPAZTarget(Entity entity) {
        return entity instanceof BalloonZombieEntity ? true : super.canPAZTarget(entity);
    }

    public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
        super.addAlmanacEntries(list);
        list.addAll(Arrays.asList(Pair.of(PAZAlmanacs.ATTACK_DAMAGE, this.getAttackDamage()), Pair.of(PAZAlmanacs.ATTACK_CD, this.getAttackCD())));
    }

    public int getAttackCD() {
        return 15;
    }

    public float getAttackDamage() {
        return this.getSkillValue(SkillTypes.MORE_SWING_DAMAGE)*0.75f;
    }

    public int getSuperTimeLength() {
        return 120;
    }

    public IPlantType getPlantType() {
        return  BHTPvZPlants. BLAZE_WART;
    }

    private final class BlazeWartAttackGoal extends Goal {
        private final BlazeWartEntity attacker;

        public BlazeWartAttackGoal(BlazeWartEntity attacker) {
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
            this.attacker.setTarget(null);
            this.attacker.setAttackTime(0);
        }

        public void tick() {
            LivingEntity target = this.attacker.getTarget();
            this.attacker.getLookControl().setLookAt(target, 30.0F, 30.0F);
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
}
