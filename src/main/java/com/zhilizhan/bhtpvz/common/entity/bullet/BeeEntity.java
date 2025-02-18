package com.zhilizhan.bhtpvz.common.entity.bullet;

import com.hungteen.pvz.api.enums.PVZGroupType;
import com.hungteen.pvz.api.interfaces.IHasGroup;
import com.hungteen.pvz.api.interfaces.IHasOwner;
import com.hungteen.pvz.common.entity.EntityGroupHander;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class BeeEntity extends CreatureEntity implements IHasGroup, IHasOwner {
    protected Entity owner;
    protected UUID ownerId;
    protected PVZGroupType groupType;
    private int limitedLifeTicks = 500;
    public BeeEntity(EntityType<? extends CreatureEntity> arg, World arg2) {
        super(arg, arg2);
        this.setNoGravity(true);
        this.groupType = this.getInitialEntityGroup();
        this.moveControl = new FlyingMovementController(this, 20, true);
        this.xpReward = 0;
    }
    public BeeEntity(EntityType<? extends CreatureEntity> type, World worldIn, Entity livingEntityIn) {
        super(type, worldIn);
        this.summonByOwner(livingEntityIn);
    }

    public void shootBee(Vector3d vec, double speed, double angleOffset) {
        this.shootBee(vec.x, vec.y, vec.z, speed, angleOffset);
    }

    public void shootBee(double dx, double dy, double dz, double speed, double angleOffset) {
        double dxz = Math.sqrt(dx * dx + dz * dz);

        double degree = MathHelper.atan2(dz, dx) + Math.toRadians(angleOffset);
        dx = Math.cos(degree) * dxz;
        dz = Math.sin(degree) * dxz;
        double totSpeed = Math.sqrt(dxz * dxz + dy * dy);
        this.setDeltaMovement((new Vector3d(dx / totSpeed, dy / totSpeed, dz / totSpeed)).scale(speed));
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.499999976158142, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 15, true, false, (entity) -> EntityUtil.canSeeEntity(this, entity) && EntityUtil.canTargetEntity(this.getOwnerOrSelf(), entity)));
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
    protected PathNavigator createNavigation(World level) {
        FlyingPathNavigator flyingpathnavigator = new FlyingPathNavigator(this, level);
        flyingpathnavigator.setCanOpenDoors(false);
        flyingpathnavigator.setCanFloat(false);
        flyingpathnavigator.setCanPassDoors(true);
        return flyingpathnavigator;
    }
    public void setLimitedLife(int limitedLifeTicks) {
        this.limitedLifeTicks = limitedLifeTicks;
    }
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.owner = null;
        if (compound.contains("owner", 10)) {
            this.ownerId = NBTUtil.loadUUID(compound.getCompound("owner"));
        }
        if (compound.contains("group_owner_type")) {
            this.groupType = EntityGroupHander.getGroup(compound.getInt("group_owner_type"));
        }
        this.setLimitedLife(compound.getInt("life_ticks"));
    }

    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        if (this.ownerId != null) {
            compound.put("owner", NBTUtil.createUUID(this.ownerId));
        }
        compound.putInt("group_owner_type", this.groupType.ordinal());
        compound.putInt("life_ticks", this.limitedLifeTicks);
    }
    public void setOwner(LivingEntity player) {
        this.owner = player;
    }
    public Entity getOwnerOrSelf() {
        return this.getOwner() == null ? this : this.getOwner();
    }

    @Nullable
    public Entity getOwner() {
        if (EntityUtil.isEntityValid(this.owner) && this.ownerId != null && this.level instanceof ServerWorld) {
            this.owner = ((ServerWorld)this.level).getEntity(this.ownerId);
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
        return PVZGroupType.PLANTS;
    }

    public PVZGroupType getEntityGroupType() {
        return this.groupType;
    }
    public EntitySize getDimensions(Pose poseIn) {
        return EntitySize.scalable(0.4F, 0.4F);
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 1).add(Attributes.FLYING_SPEED, 0.5000000238418579).add(Attributes.KNOCKBACK_RESISTANCE, 5.4002).add(Attributes.ATTACK_DAMAGE, 0.0).add(Attributes.FOLLOW_RANGE, 34.0);
    }


}
