package com.zhilizhan.bhtpvz.common.entity.zombie.bhtpvz;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.grass.DancingZombieEntity;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.zhilizhan.bhtpvz.common.impl.zombie.BHTPvZZombies;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;

import java.util.EnumSet;
import java.util.Optional;

public class DancerBackupEntity extends PVZZombieEntity {
    public static final int DANCE_CD = 100;
    protected Optional<MJZombieEntity> owner = Optional.empty();
    private static final int MIN_REST_CD = 60;
    private static final int MAX_REST_CD = 300;
    private int restTick = 0;

    public DancerBackupEntity(EntityType<? extends PathfinderMob> type, Level worldIn) {
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
            if (this.needFollow()) {
                this.owner.ifPresent((dancer) -> {
                    this.setAttackTime(dancer.getAttackTime());
                    this.yRot = dancer.yRot;
                    this.xRot = dancer.xRot;
                    this.yHeadRot = dancer.yHeadRot;
                });
            }
        }

    }

    public float getLife() {
        return 20.0F;
    }

    public boolean needFollow() {
        return this.owner.isPresent() && EntityUtil.isEntityValid((Entity)this.owner.get());
    }

    public void setDancingOwner(MJZombieEntity dancer) {
        this.owner = Optional.ofNullable(dancer);
    }

    protected void setRestTick() {
        this.restTick = MathUtil.getRandomMinMax(this.random, 60, 300);
    }

    protected void updateSpeed() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(this.getAttackTime() > 0 ? 0.0 : 0.1899999976158142);
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("zombie_rest_tick")) {
            this.restTick = compound.getInt("zombie_rest_tick");
        }

        if (compound.contains("dancing_owner")) {
            Entity entity = this.level.getEntity(compound.getInt("dancing_owner"));
            if (EntityUtil.isEntityValid(entity) && entity instanceof DancingZombieEntity) {
                this.owner = Optional.ofNullable((MJZombieEntity)entity);
            }
        }

    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("zombie_rest_tick", this.restTick);
        this.owner.ifPresent((dancer) -> {
            compound.putInt("dancing_owner", dancer.getId());
        });
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
