package com.zhilizhan.bhtpvz.common.entity.zombie;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.WorldUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.impl.zombie.BHTPvZZombies;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.*;

public class MJZombieEntity extends PVZZombieEntity {
    private static final DataParameter<Integer> SUMMON_TIME;

    private static final float[][] POS_OFFSET;

    private final List<Optional<DancerBackupEntity>> Dancers = new ArrayList<>(4);
    private int summonCnt = 0;
    private int restTick = 0;

    public MJZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
        this.canCollideWithZombie = false;
        this.setRestTick();

        for (int i = 0; i < 4; ++i) {
            this.Dancers.add(Optional.empty());
        }

        this.clearDancers();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SUMMON_TIME, 0);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new MJZombieEntity.ZombieDanceGoal(this));
        this.goalSelector.addGoal(0, new MJZombieEntity.SummonDancerGoal(this));
    }

    public void tickDance() {
        for (int i = 0; i < 4; ++i) {
            this.Dancers.get(i).ifPresent((dancer) -> {
            });
        }

    }

    public void summonEmptyDancers() {
        for (int i = 0; i < 4; ++i) {
            if (!this.Dancers.get(i).isPresent() && this.summonCnt < this.getMaxSummonCnt()) {
                DancerBackupEntity dancer = (DancerBackupEntity) ((EntityType<?>) BHTPvZEntityTypes.DANCER_BACKUP_ZOMBIE.get()).create(this.level);
                BlockPos pos = WorldUtil.getSuitableHeightPos(this.level, this.blockPosition().offset(POS_OFFSET[i][0], 0.0, POS_OFFSET[i][1]));
                if (dancer != null) {
                    dancer.setDancingOwner(this);
                    dancer.setZombieRising();

                    ZombieUtil.copySummonZombieData(this, dancer);

                    this.setDancer(i, dancer);
                    ++this.summonCnt;
                    EntityUtil.onEntitySpawn(this.level, dancer, pos);
                }
                this.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 40, 5, false, false));
            }
        }

    }

    protected void setRestTick() {
        this.restTick = MathUtil.getRandomMinMax(this.random, 60, 300);
    }

    protected void updateSpeed() {
        Objects.requireNonNull(this.getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(this.getAttackTime() > 0 ? 0.0 : (double) this.getWalkSpeed());
    }

    public float getWalkSpeed() {
        return 0.19F;
    }

    protected boolean hasEmptyPlace() {
        for (int i = 0; i < 4; ++i) {
            if (!this.Dancers.get(i).isPresent()) {
                return true;
            }

            if (!EntityUtil.isEntityValid((Entity) ((Optional<?>) this.Dancers.get(i)).orElse(null))) {
                this.Dancers.set(i, Optional.empty());
                return true;
            }
        }

        return false;
    }

    private void clearDancers() {
        for (int i = 0; i < 4; ++i) {
            this.Dancers.set(i, Optional.empty());
        }

    }

    private void setDancer(int pos, Entity entity) {
        if (EntityUtil.isEntityValid(entity) && entity instanceof DancerBackupEntity) {
            this.Dancers.set(pos, Optional.of((DancerBackupEntity) entity));
        }

    }

    public int getMaxSummonCnt() {
        return 25;
    }

    public float getLife() {
        return 50.0F;
    }

    public int getArmorToughness() {
        return 15;
    }

    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("zombie_summon_tick")) {
            this.setSummonTime(compound.getInt("zombie_summon_tick"));
        }

        if (compound.contains("zombie_rest_tick")) {
            this.restTick = compound.getInt("zombie_rest_tick");
        }

        if (compound.contains("dancer_ids")) {
            CompoundNBT nbt = compound.getCompound("dancer_ids");

            for (int i = 0; i < 4; ++i) {
                if (nbt.contains("dancer_" + i)) {
                    this.setDancer(i, this.level.getEntity(nbt.getInt("dancer_" + i)));
                }
            }
        }

    }

    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("zombie_summon_tick", this.getSummonTime());
        compound.putInt("zombie_rest_tick", this.restTick);
        CompoundNBT nbt = new CompoundNBT();

        for (int i = 0; i < 4; ++i) {
            if (this.Dancers.get(i).isPresent()) {
                DancerBackupEntity dancer = (DancerBackupEntity) ((Optional<?>) this.Dancers.get(i)).orElse(null);
                if (EntityUtil.isEntityValid(dancer)) {
                    nbt.putInt("dancer_" + i, dancer.getId());
                }
            }
        }

        compound.put("dancer_ids", nbt);
    }

    public int getSummonTime() {
        return this.entityData.get(SUMMON_TIME);
    }

    public void setSummonTime(int time) {
        this.entityData.set(SUMMON_TIME, time);
    }



    static {
        SUMMON_TIME = EntityDataManager.defineId(MJZombieEntity.class, DataSerializers.INT);
        POS_OFFSET = new float[][]{{2.0F, 0.0F}, {-2.0F, 0.0F}, {0.0F, 2.0F}, {0.0F, -2.0F}};
    }

    static class SummonDancerGoal extends Goal {
        private final MJZombieEntity dancer;

        public SummonDancerGoal(MJZombieEntity dancer) {
            this.dancer = dancer;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        public boolean canUse() {
            if (this.dancer.summonCnt >= this.dancer.getMaxSummonCnt()) {
                return false;
            } else if (this.dancer.getSummonTime() > 0) {
                return true;
            } else if (this.dancer.restTick > 0 && this.dancer.hasEmptyPlace() && this.dancer.getRandom().nextFloat() < 0.05F) {
                this.dancer.setSummonTime(10);
                return true;
            } else {
                return false;
            }
        }

        public void start() {
            this.dancer.updateSpeed();
        }

        public boolean canContinueToUse() {
            return this.dancer.getSummonTime() > 0;
        }

        public void tick() {
            int tick = this.dancer.getSummonTime();
            if (tick == 5) {
                this.dancer.summonEmptyDancers();
            }

            this.dancer.setSummonTime(Math.max(0, tick - 1));
        }

        public void stop() {
            this.dancer.updateSpeed();
        }
    }

    static class ZombieDanceGoal extends Goal {
        private final MJZombieEntity dancer;

        public ZombieDanceGoal(MJZombieEntity dancer) {
            this.dancer = dancer;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        public boolean canUse() {
            if (this.dancer.getAttackTime() > 0) {
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

            this.dancer.tickDance();
            this.dancer.setAttackTime(Math.max(0, tick - 1));
        }

        public void stop() {
            this.dancer.updateSpeed();
        }
    }
    public ZombieType getZombieType() {
        return BHTPvZZombies.MJ_ZOMBIE;
    }
}