package com.github.zhilizhan.bhtpvz.common.entity.zombie.bhtpvz;

import com.github.zhilizhan.bhtpvz.common.impl.zombie.BHTPvZZombies;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.Level;

import java.util.Objects;

public class AirborneZombieEntity extends PVZZombieEntity {
    private static final EntityDataAccessor<Boolean> HAS_LINE = SynchedEntityData.defineId(AirborneZombieEntity.class, EntityDataSerializers.BOOLEAN);
    private final MoveControl FlyController = new FlyingMoveControl(this, 360, true);
    private final MoveControl GroundController = new MoveControl(this);
    private PathNavigation FlyNavigator;
    private PathNavigation GroundNavigator;

    public AirborneZombieEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HAS_LINE, true);
    }

    @Override
    protected void registerGoals() {
        // define at here to avoid crash.
        this.FlyNavigator = new FlyingPathNavigation(this, level);
        this.GroundNavigator = new GroundPathNavigation(this, level);
        super.registerGoals();
    }

    @Override
    protected void initAttributes() {
        super.initAttributes();
        Objects.requireNonNull(this.getAttribute(Attributes.FLYING_SPEED)).setBaseValue(ZombieUtil.FLY_FAST);
        this.setNoGravity(this.hasLine());
        this.moveControl = this.hasLine() ? FlyController : GroundController;
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> data) {
        super.onSyncedDataUpdated(data);
        if(data.equals(HAS_LINE)) {
            this.setNoGravity(this.hasLine());
            this.moveControl = this.hasLine() ? FlyController : GroundController;
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if(this.hasLine() && this.canHitLine(source)) {
            this.onLineExplode();
            amount = 0;
        }
        return super.hurt(source, amount);
    }

    private boolean canHitLine(DamageSource source) {

        if(source instanceof PVZEntityDamageSource) {
            return ((PVZEntityDamageSource) source).isIceDamage();
        }
        return false;
    }

    public void onLineExplode(){
        if(! level.isClientSide) {
            EntityUtil.playSound(this, SoundEvents.WOOL_FALL);
        }
        this.setLine(false);
    }

    @Override
    public boolean canClimbWalls() {
        return super.canClimbWalls() && ! this.hasLine();
    }

    @Override
    public float getLife() {
        return 30;
    }

    @Override
    public float getWalkSpeed() {
        return ZombieUtil.WALK_LITTLE_SLOW;
    }

    @Override
    public boolean canBeButtered() {
        return ! this.hasLine();
    }

    @Override
    public PathNavigation getNavigation() {
        if(this.hasLine()) {
            if(! (this.navigation instanceof FlyingPathNavigation)) {
                this.navigation = this.FlyNavigator;
            }
        } else {
            if(! (this.navigation instanceof GroundPathNavigation)) {
                this.navigation = this.GroundNavigator;
            }
        }
        return super.getNavigation();
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if(compound.contains("has_line")) {
            this.setLine(compound.getBoolean("has_line"));
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("has_line", this.hasLine());
    }

    public void setLine(boolean has) {
        this.entityData.set(HAS_LINE, has);
    }

    public boolean hasLine() {
        return this.entityData.get(HAS_LINE);
    }

    @Override
    public ZombieType getZombieType() {
        return BHTPvZZombies.AIRBORNE_ZOMBIE;
    }
}
