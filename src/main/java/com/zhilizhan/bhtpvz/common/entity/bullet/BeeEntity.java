package com.zhilizhan.bhtpvz.common.entity.bullet;

import com.hungteen.pvz.api.enums.PVZGroupType;
import com.hungteen.pvz.api.interfaces.IHasGroup;
import com.hungteen.pvz.api.interfaces.IHasOwner;
import com.hungteen.pvz.common.entity.EntityGroupHander;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class BeeEntity extends PathfinderMob  implements IHasGroup, IHasOwner {
    protected Entity owner;
    protected UUID ownerId;
    protected PVZGroupType groupType;
    private int limitedLifeTicks = 500;
    public BeeEntity(EntityType<? extends PathfinderMob> arg, Level arg2) {
        super(arg, arg2);
        this.isNoGravity();;
        this.groupType = this.getInitialEntityGroup();
        this.moveControl = new FlyingMoveControl(this, 20, true);
        this.xpReward = 0;
    }
    public BeeEntity(EntityType<? extends PathfinderMob> type, Level worldIn, Entity livingEntityIn) {
        super(type, worldIn);
        this.summonByOwner(livingEntityIn);
    }
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.399999976158142, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false, (entity) -> {
            return EntityUtil.canSeeEntity(this, entity) && EntityUtil.canTargetEntity(this.getOwnerOrSelf(), entity);
        }));
    }
    public void summonByOwner(Entity owner) {
        this.owner = owner;
        this.ownerId = owner.getUUID();
        this.groupType = EntityUtil.getEntityGroup(owner);
    }
    public void tick(){
        super.tick();
        if(--limitedLifeTicks<=0){
            this.remove();
        }
        if(this.getTarget()==null||this.getOwner()==null){
            limitedLifeTicks--;
        }
    }
    protected PathNavigation createNavigation(Level level) {
        FlyingPathNavigation flyingpathnavigator = new FlyingPathNavigation(this, level) {};
        flyingpathnavigator.setCanOpenDoors(false);
        flyingpathnavigator.setCanFloat(false);
        flyingpathnavigator.setCanPassDoors(true);
        return flyingpathnavigator;
    }
    public void setLimitedLife(int limitedLifeTicks) {
        this.limitedLifeTicks = limitedLifeTicks;
    }
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.owner = null;
        if (compound.contains("owner", 10)) {
            this.ownerId = NbtUtils.loadUUID(compound.getCompound("owner"));
        }
        if (compound.contains("group_owner_type")) {
            this.groupType = EntityGroupHander.getGroup(compound.getInt("group_owner_type"));
        }
        this.setLimitedLife(compound.getInt("LifeTicks"));
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        if (this.ownerId != null) {
            compound.put("owner", NbtUtils.createUUID(this.ownerId));
        }
        compound.putInt("group_owner_type", this.groupType.ordinal());
        compound.putInt("LifeTicks", this.limitedLifeTicks);
    }
    public void setOwner(LivingEntity player) {
        this.owner = player;
    }
    public Entity getOwnerOrSelf() {
        return (Entity)(this.getOwner() == null ? this : this.getOwner());
    }

    @Nullable
    public Entity getOwner() {
        if (EntityUtil.isEntityValid(this.owner) && this.ownerId != null && this.level instanceof ServerLevel) {
            this.owner = ((ServerLevel)this.level).getEntity(this.ownerId);
        }

        return this.owner;
    }

    public boolean causeFallDamage(float fallDistance, float damageMultiplier) {
        return false;
    }

    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
    }
    public Optional<UUID> getOwnerUUID() {
        return Optional.ofNullable(this.uuid);
    }

    public PVZGroupType getInitialEntityGroup() {
        return PVZGroupType.NEUTRALS;
    }

    public PVZGroupType getEntityGroupType() {
        return this.groupType;
    }
    public EntityDimensions getDimensions(Pose poseIn) {
        return EntityDimensions.scalable(0.4F, 0.4F);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 1).add(Attributes.FLYING_SPEED, 0.5000000238418579).add(Attributes.KNOCKBACK_RESISTANCE, 5.4002).add(Attributes.ATTACK_DAMAGE, 0.0).add(Attributes.FOLLOW_RANGE, 34.0);
    }
}
