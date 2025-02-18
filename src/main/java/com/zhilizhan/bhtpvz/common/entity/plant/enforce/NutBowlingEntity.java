package com.zhilizhan.bhtpvz.common.entity.plant.enforce;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.advancement.trigger.EntityEffectAmountTrigger;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantCloserEntity;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class NutBowlingEntity extends PlantCloserEntity {

    protected IntOpenHashSet hitEntities = new IntOpenHashSet();
    private static final DataParameter<Integer>  FACING = EntityDataManager.defineId(NutBowlingEntity.class, DataSerializers.INT);

    private static final DataParameter<Integer>  DIRECTION = EntityDataManager.defineId(NutBowlingEntity.class, DataSerializers.INT);

    private int bowlingTick;
    private int wallTick;
    private boolean playSpawnSound;
    protected int hitCount;


    public NutBowlingEntity(EntityType<? extends PVZPlantEntity> type, World worldIn) {
        super(type, worldIn);
        this.bowlingTick = 0;
        this.wallTick = 0;
        this.playSpawnSound = false;
        this.hitCount = 0;
        this.pushthrough = 1.0F;
        this.isImmuneToWeak =true;
        this.canCollideWithPlant = false;
    }

    @Override
    public void performAttack(LivingEntity livingEntity) {

    }
    @Override
    public void normalPlantTick() {
        super.normalPlantTick();
        if (!this.level.isClientSide) {
            if (this.tickCount <=20 && !this.playSpawnSound) {
                EntityUtil.playSound(this, SoundRegister.BOWLING.get());
                this.playSpawnSound = true;
                if (this.getOwnerUUID().isPresent())
                    this.ownerPlayer = this.level.getPlayerByUUID(this.getOwnerUUID().orElse(null));
                if (this.ownerPlayer != null) {
                    Direction direction = ownerPlayer.getDirection();
                    this.setDirection(direction);
                }
            }else if (this.tickCount > 0) {
                this.yRot = this.getDirection().toYRot() + this.getBowlingFacing().offset;
                if (this.tickCount >= this.getMaxLiveTick()) {
                    this.remove();
                }

                this.yRotO = this.yRot;

                double angle = (double) this.yRot * Math.PI / 180.0;
                double dx = -Math.sin(angle);
                double dz = Math.cos(angle);
                this.setDeltaMovement(dx, this.getDeltaMovement().y, dz);
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
        }

    }
    protected void tickMove() {
        Vector3d vec3d = this.getDeltaMovement();
        double dx = vec3d.x;
        double dy = vec3d.y;
        double dz = vec3d.z;
        // 生成水中粒子效果
        if (this.isInWater()) {
            for (int i = 0; i < 3; ++i) {
                double particleX = this.getX() - dx * 0.25;  // 使用实时坐标值进行计算
                double particleY = this.getY() - vec3d.y * 0.25;
                double particleZ = this.getZ() - dz * 0.25;
                this.level.addParticle(ParticleTypes.BUBBLE, particleX, particleY, particleZ, dx, vec3d.y, dz);
            }
        }
        if (!this.isNoGravity()) {

            if(this.horizontalCollision&&this.isOneBlockHighObstacle()){
                dy+=0.25;
            }

            // 根据当前速度动态调整水平移动速度
            double speedFactor = 1.0F;
            double horizontalSpeed = Math.sqrt(dx * dx + dz * dz);
            if (Math.abs(horizontalSpeed) > 1.0) {
                double ratio = speedFactor / horizontalSpeed;
                dx *= ratio;
                dz *= ratio;
            }

            // 限制速度范围在 [Math.abs(dd), 0.35] 内
            dx = Math.signum(dx) * Math.min(Math.abs(dx), 0.35);
            dz = Math.signum(dz) * Math.min(Math.abs(dz), 0.35);

            this.setDeltaMovement(dx, dy, dz);
        }

        this.move(MoverType.SELF, this.getDeltaMovement());
    }
    protected void tickCollision() {
        if (!this.level.isClientSide) {
            if (this.bowlingTick <= 0) {
                List<Entity> list = this.level.getEntitiesOfClass(Entity.class, this.getBoundingBox(), (target) -> EntityUtil.canTargetEntity(this.ownerPlayer, target));
                if (!list.isEmpty()) {
                    this.dealDamageTo(list.get(0));
                    this.changeDiretion();
                }
            }
        }
    }
    protected boolean isOneBlockHighObstacle() {
        World world = this.level;
        BlockPos pos = this.blockPosition();  // 获取实体当前坐标
        Direction facing = this.getDirection();  // 假定为正面所朝方向

        // 前方的方块位置
        BlockPos frontBlockPos = pos.offset(facing.getNormal());
        // 前方上方一格的方块位置
        BlockPos frontUpBlockPos = frontBlockPos.above();

        // 检查前方的方块是否为实体障碍
        boolean isFrontBlockSolid = !world.getBlockState(frontBlockPos).isAir();
        // 检查前方上方的方块是否为非障碍（如空气）
        boolean isFrontUpBlockPassable = world.getBlockState(frontUpBlockPos).isAir();

        return isFrontBlockSolid && isFrontUpBlockPassable;
    }

    protected void dealDamageTo(Entity entity) {
        ++this.hitCount;

        entity.hurt(PVZEntityDamageSource.normal(this, this.ownerPlayer).setCount(this.hitCount), 30.0F);
        EntityUtil.playSound(this, SoundRegister.BOWLING_HIT.get());
        PlayerEntity player = this.ownerPlayer;
        if (player instanceof ServerPlayerEntity) {
            EntityEffectAmountTrigger.INSTANCE.trigger((ServerPlayerEntity)player, this, this.hitCount);
        }

    }
    private void tickRayTrace() {
        double rayLen = 3.0;
        double angle = (double)(this.getDirection().toYRot() + this.getBowlingFacing().offset) * Math.PI / 180.0;
        double dx = Math.sin(angle);
        double dz = -Math.cos(angle);
        Vector3d start = this.position();
        Vector3d end = start.add(new Vector3d(dx * rayLen, 0.0, dz * rayLen));
        RayTraceResult result = this.level.clip(new RayTraceContext(start, end, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this));
        if (result.getType() != RayTraceResult.Type.MISS) {
            end = result.getLocation();
        }

        this.rayTraceEntities(start, end);

    }

    protected void changeDiretion() {
        if (this.getBowlingFacing() == NutBowlingEntity.BowlingFacings.MID) {
            this.setBowlingFacing(this.random.nextInt(2) == 0 ? NutBowlingEntity.BowlingFacings.LEFT : NutBowlingEntity.BowlingFacings.RIGHT);
        } else if (this.getBowlingFacing() == NutBowlingEntity.BowlingFacings.LEFT) {
            this.setBowlingFacing(NutBowlingEntity.BowlingFacings.RIGHT);
        } else if (this.getBowlingFacing() == NutBowlingEntity.BowlingFacings.RIGHT) {
            this.setBowlingFacing(NutBowlingEntity.BowlingFacings.LEFT);
        }

        this.bowlingTick = 15;
    }


    protected boolean shouldHit(Entity target) {
        return EntityUtil.canTargetEntity(this.ownerPlayer, target);
    }


    protected void rayTraceEntities(Vector3d startVec, Vector3d endVec) {
        ProjectileHelper.getEntityHitResult(this.level, this, startVec, endVec, this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate(1.0), (entity) -> entity.isPickable() && this.shouldHit(entity) && (this.hitEntities == null || !this.hitEntities.contains(entity.getId())));
    }


    protected int getMaxLiveTick() {
        return 300;
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
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
    public void readAdditionalSaveData(CompoundNBT compound) {
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
    public boolean isAttackable() {
        return false;
    }
   // protected float getStandingEyeHeight(Pose pose, EntityDimensions dimension) {
   //     return 0.1f;
   // }

    public  enum BowlingFacings {
        LEFT(-45.0F),
        MID(0.0F),
        RIGHT(45.0F);

        public final float offset;

        BowlingFacings(float offset) {
            this.offset = offset;
        }
    }

    public EntitySize getDimensions(Pose poseIn) {
        return EntitySize.scalable(1F, 1.2F);
    }

    public IPlantType getPlantType() {
        return BHTPvZPlants.NUT_BOWLING;
    }
}
