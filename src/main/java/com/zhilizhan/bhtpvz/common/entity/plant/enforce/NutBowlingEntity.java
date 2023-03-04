package com.zhilizhan.bhtpvz.common.entity.plant.enforce;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.advancement.trigger.EntityEffectAmountTrigger;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class NutBowlingEntity extends PVZPlantEntity {


    protected IntOpenHashSet hitEntities;
    private static final EntityDataAccessor<Integer> FACING;
    private static final EntityDataAccessor<Integer> DIRECTION;
    private int bowlingTick;
    private int wallTick;
    private boolean playSpawnSound;
    protected int hitCount;


    public NutBowlingEntity(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
        this.bowlingTick = 0;
        this.wallTick = 0;
        this.playSpawnSound = false;
        this.hitCount = 0;
        this.pushthrough = 1.0F;
    }




    public void tick() {
        super.tick();
        if (!this.level.isClientSide) {
            if (this.tickCount <= 1 && !this.playSpawnSound) {
                EntityUtil.playSound(this, SoundRegister.BOWLING.get());
                this.playSpawnSound = true;
                this.ownerPlayer = this.level.getPlayerByUUID((UUID)this.getOwnerUUID().get());
                Direction direction = ownerPlayer.getDirection();
                this.setDirection(direction);
                this.yRot = direction.toYRot();
            }

            if (this.tickCount >= this.getMaxLiveTick()) {
                this.remove();
            }
        }

        this.yRotO = this.yRot;
        this.yRot = this.getDirection().toYRot() + this.getBowlingFacing().offset;
        double angle = (double)this.yRot * Math.PI / 180.0;
        double dx = -Math.sin(angle);
        double dz = Math.cos(angle);
        double speed = this.getBowlingSpeed();
        this.setDeltaMovement(dx * speed, this.getDeltaMovement().y(), dz * speed);
        this.tickRayTrace();
        this.tickMove();
        this.tickCollision();
        if (!this.level.isClientSide) {
            if (this.bowlingTick > 0) {
                --this.bowlingTick;
            }

            if (this.wallTick > 0) {
                --this.wallTick;
            }

            if (this.bowlingTick == 0 && this.horizontalCollision) {
                if (this.wallTick > 0) {
                    this.remove();
                } else {
                    this.wallTick = 15;
                    this.changeDiretion();
                }
            }
        }

    }
    protected void tickMove() {
        Vec3 vec3d = this.getDeltaMovement();
        double d0 = this.getX() + vec3d.x;
        double d1 = this.getY() + vec3d.y;
        double d2 = this.getZ() + vec3d.z;
        float f1;
        if (this.isInWater()) {
            for(int i = 0; i < 4; ++i) {
                this.level.addParticle(ParticleTypes.BUBBLE, d0 - vec3d.x * 0.25, d1 - vec3d.y * 0.25, d2 - vec3d.z * 0.25, vec3d.x, vec3d.y, vec3d.z);
            }

            f1 = 0.8F;
        } else {
            f1 = 1.0F;
        }

        this.setDeltaMovement(vec3d.scale((double)f1));
        if (!this.isNoGravity()) {
            Vec3 vec3d1 = this.getDeltaMovement();
            this.setDeltaMovement(vec3d1.x, vec3d1.y - 0.05f, vec3d1.z);
        }

        this.move(MoverType.SELF, this.getDeltaMovement());
    }
    protected void tickCollision() {
        if (!this.level.isClientSide) {
            if (this.bowlingTick <= 0) {
                List<Entity> list = this.level.getEntitiesOfClass(Entity.class, this.getBoundingBox(), (target) -> {
                    return EntityUtil.canTargetEntity(this.ownerPlayer, target);
                });
                if (!list.isEmpty()) {
                    this.dealDamageTo(list.get(0));
                    this.changeDiretion();
                }

            }
        }
    }


    protected void dealDamageTo(Entity entity) {
        ++this.hitCount;

       

        entity.hurt(PVZEntityDamageSource.normal(this, this.ownerPlayer).setCount(this.hitCount), 30.0F);
        EntityUtil.playSound(this, (SoundEvent)SoundRegister.BOWLING_HIT.get());
        Player player = this.ownerPlayer;
        if (player != null && player instanceof ServerPlayer) {
            EntityEffectAmountTrigger.INSTANCE.trigger((ServerPlayer)player, this, this.hitCount);
        }

    }
    private void tickRayTrace() {
        double rayLen = 3.0;
        double angle = (double)(this.getDirection().toYRot() + this.getBowlingFacing().offset) * Math.PI / 180.0;
        double dx = Math.sin(angle);
        double dz = -Math.cos(angle);
        Vec3 start = this.position();
        Vec3 end = start.add(new Vec3(dx * rayLen, 0.0, dz * rayLen));
        HitResult result = this.level.clip(new ClipContext(start, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
        if (result.getType() != HitResult.Type.MISS) {
            end = result.getLocation();
        }

        EntityHitResult entityRay = this.rayTraceEntities(start, end);
        if (entityRay != null) {
            result = entityRay;
        }

        if (result != null && result.getType() != HitResult.Type.MISS && !ForgeEventFactory.onProjectileImpact(this, result)) {
            this.onImpact(result);
        }

    }

    protected void changeDiretion() {
        if (this.getBowlingFacing() == NutBowlingEntity.BowlingFacings.MID) {
            this.setBowlingFacing(this.random.nextInt(2) == 0 ? NutBowlingEntity.BowlingFacings.LEFT : NutBowlingEntity.BowlingFacings.RIGHT);
        } else if (this.getBowlingFacing() == NutBowlingEntity.BowlingFacings.LEFT) {
            this.setBowlingFacing(NutBowlingEntity.BowlingFacings.RIGHT);
        } else if (this.getBowlingFacing() == NutBowlingEntity.BowlingFacings.RIGHT) {
            this.setBowlingFacing(NutBowlingEntity.BowlingFacings.LEFT);
        }

        this.bowlingTick = 10;
    }



    public void shoot(Player player) {
        Direction direction = player.getDirection();
        this.setDirection(direction);
        this.yRot = direction.toYRot();
    }

    public double getBowlingSpeed() {
        return 0.4;
    }

    protected void addHitEntity(Entity entity) {
        this.hitEntities.addAll(EntityUtil.getOwnerAndPartsID(entity));
    }

    protected boolean shouldHit(Entity target) {
        return EntityUtil.canTargetEntity(this.ownerPlayer, target);
    }

    @Nullable
    protected EntityHitResult rayTraceEntities(Vec3 startVec, Vec3 endVec) {
        return ProjectileUtil.getEntityHitResult(this.level, this, startVec, endVec, this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate(1.0), (entity) -> {
            return entity.isPickable() && this.shouldHit(entity) && (this.hitEntities == null || !this.hitEntities.contains(entity.getId()));
        });
    }

    protected void onImpact(HitResult result) {
    }



    protected int getMaxLiveTick() {
        return 600;
    }


    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("bowling_facings", this.getBowlingFacing().ordinal());
        compound.putInt("bowling_directions", this.getDirection().ordinal());
        compound.putInt("bowling_tick", this.bowlingTick);
        compound.putInt("bowling_hit_count", this.hitCount);
    }
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FACING, NutBowlingEntity.BowlingFacings.MID.ordinal());
        this.entityData.define(DIRECTION, Direction.NORTH.ordinal());
    }
    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("bowling_facings")) {
            this.setBowlingFacing(NutBowlingEntity.BowlingFacings.values()[compound.getInt("bowling_facings")]);
        }

        if (compound.contains("bowling_directions")) {
            this.setDirection(Direction.values()[compound.getInt("bowling_directions")]);
        }

        if (compound.contains("bowling_tick")) {
            this.bowlingTick = compound.getInt("bowling_tick");
        }

        if (compound.contains("bowling_hit_count")) {
            this.hitCount = compound.getInt("bowling_hit_count");
        }

    }

    public Direction getDirection() {
        return Direction.values()[this.entityData.get(DIRECTION)];
    }

    public void setDirection(Direction drt) {
        this.entityData.set(DIRECTION, drt.ordinal());
    }

    public NutBowlingEntity.BowlingFacings getBowlingFacing() {
        return NutBowlingEntity.BowlingFacings.values()[this.entityData.get(FACING)];
    }

    public void setBowlingFacing(NutBowlingEntity.BowlingFacings facing) {
        this.entityData.set(FACING, facing.ordinal());
    }

    @OnlyIn(Dist.CLIENT)
    public boolean shouldRenderAtSqrDistance(double distance) {
        double d0 = this.getBoundingBox().getSize() * 4.0;
        if (Double.isNaN(d0)) {
            d0 = 4.0;
        }

        d0 *= 64.0;
        return distance < d0 * d0;
    }

    @OnlyIn(Dist.CLIENT)
    public void lerpMotion(double x, double y, double z) {
        this.setDeltaMovement(x, y, z);
    }

    public boolean isAttackable() {
        return false;
    }

    static {
        FACING = SynchedEntityData.defineId(NutBowlingEntity.class, EntityDataSerializers.INT);
        DIRECTION = SynchedEntityData.defineId(NutBowlingEntity.class, EntityDataSerializers.INT);
    }

    public  enum BowlingFacings {
        LEFT(-45.0F),
        MID(0.0F),
        RIGHT(45.0F);

        public final float offset;

        BowlingFacings(float offset) {
            this.offset = offset;
        }
    }


    public EntityDimensions getDimensions(Pose poseIn) {
        return EntityDimensions.scalable(1F, 1.2F);
    }

    public IPlantType getPlantType() {
        return BHTPvZPlants.NUT_BOWLING;
    }
}
