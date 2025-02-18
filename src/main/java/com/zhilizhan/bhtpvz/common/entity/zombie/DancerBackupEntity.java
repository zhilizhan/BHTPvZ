package com.zhilizhan.bhtpvz.common.entity.zombie;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.zhilizhan.bhtpvz.common.impl.zombie.BHTPvZZombies;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

import java.util.EnumSet;

public class DancerBackupEntity extends PVZZombieEntity {

    protected MJZombieEntity owner;

    private int restTick = 0;

    public DancerBackupEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
        this.canCollideWithZombie = false;
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new DancerBackupEntity.ZombieDanceGoal(this));
    }

    public void normalZombieTick() {
        super.normalZombieTick();
        if (!this.level.isClientSide) {
            this.updateSpeed();
            if (this.needFollow()&&EntityUtil.isEntityValid(this.owner)) {
                this.setAttackTime(owner.getAttackTime());
                this.yRot = owner.yRot;
                this.xRot = owner.xRot;
                this.yHeadRot = owner.yHeadRot;
            }
        }

    }

    public float getLife() {
        return 20.0F;
    }

    public boolean needFollow() {
        return EntityUtil.isEntityValid(this.owner);
    }

    public void setDancingOwner(MJZombieEntity dancer) {
        this.owner = dancer;
    }

    protected void setRestTick() {
        this.restTick = MathUtil.getRandomMinMax(this.random, 60, 300);
    }

    protected void updateSpeed() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(this.getAttackTime() > 0 ? 0.0 : 0.1899999976158142);
    }

    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("zombie_rest_tick")) {
            this.restTick = compound.getInt("zombie_rest_tick");
        }

        if (compound.contains("dancing_owner")) {
            Entity entity = this.level.getEntity(compound.getInt("dancing_owner"));
            if (EntityUtil.isEntityValid(entity) && entity instanceof MJZombieEntity) {
                this.owner = (MJZombieEntity)entity;
            }
        }

    }

    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("zombie_rest_tick", this.restTick);
        if(EntityUtil.isEntityValid(this.owner)){
            compound.putInt("dancing_owner", owner.getId());
        }
    }

    static class ZombieDanceGoal extends Goal {
        private final DancerBackupEntity dancer;

        public ZombieDanceGoal(DancerBackupEntity dancer) {
            this.dancer = dancer;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        public boolean canUse() {
            if (this.dancer.needFollow()) {
                return false;
            } else if (this.dancer.getAttackTime() > 0) {
                return true;
            } else if (this.dancer.restTick > 0) {
                --this.dancer.restTick;
                return false;
            } else if (this.dancer.getRandom().nextFloat() < 0.05F) {
                this.dancer.setAttackTime(100);
                return true;
            } else {
                return false;
            }
        }

        public void start() {
            this.dancer.updateSpeed();
        }

        public boolean canContinueToUse() {
            return this.dancer.getAttackTime() > 0;
        }

        public void tick() {
            int tick = this.dancer.getAttackTime();
            if (tick == 1) {
                this.dancer.setRestTick();
            }

            this.dancer.setAttackTime(Math.max(0, tick - 1));
        }

        public void stop() {
            this.dancer.updateSpeed();
        }
    }
    public ZombieType getZombieType() {
        return BHTPvZZombies.DANCER_BACKUP_ZOMBIE;
    }
}
