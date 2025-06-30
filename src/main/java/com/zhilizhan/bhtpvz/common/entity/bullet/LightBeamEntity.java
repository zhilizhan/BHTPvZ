package com.zhilizhan.bhtpvz.common.entity.bullet;

import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.misc.BHTPvZEntityDamageSource;
import net.minecraft.entity.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

public class LightBeamEntity extends AbstractBulletEntity {
    private static final DataParameter<Integer> DATA_TRAIL_COLOR = EntityDataManager.defineId(LightBeamEntity.class, DataSerializers.INT);

    private final List<Vector3d> trails = new LinkedList<>();
    protected Vector3d posO = Vector3d.ZERO;

    public LightBeamEntity(EntityType<?> type, World worldIn) {
        super(type, worldIn);
    }
    public LightBeamEntity(World worldIn, LivingEntity shooter) {
        super(BHTPvZEntityTypes.LIGHT_BEAM.get(), worldIn, shooter);
    }
    @Override
    protected int getMaxLiveTick() {
        return 40;
    }

    @Override
    public void tick() {
        if (this.level.isClientSide && this.tickCount > 1) {
            if (trails.isEmpty()) {
                trails.add(this.position());
            }
            trails.add(this.position());
            if (trails.size() > 4 || posO == this.position() && !trails.isEmpty()) {
                trails.remove(0);
            }
            posO = this.position();
        }
        super.tick();
    }

    @Override
    protected void onImpact(RayTraceResult hitResult) {
        boolean flag = false;
        if (hitResult.getType() == RayTraceResult.Type.ENTITY) {
            Entity target = ((EntityRayTraceResult)hitResult).getEntity();
            if (this.shouldHit(target)) {
                target.invulnerableTime = 0;
                this.dealDamage(target);
                flag = true;
            }
        }

        this.level.broadcastEntityEvent(this, (byte)3);
        if (flag || !this.checkLive(hitResult)) {
            this.remove();
        }
    }

    private void dealDamage(Entity target) {
        float damage = this.getAttackDamage();
        PVZEntityDamageSource source = BHTPvZEntityDamageSource.lightBeam(this, this.getThrower());
        target.hurt(source, damage);
    }

    protected void defineSynchedData() {
        this.entityData.define(DATA_TRAIL_COLOR, 0xFFFFFF);
    }

    public void setTrailColor(int color) {
        entityData.set(DATA_TRAIL_COLOR, color);
    }

    public int getTrailColor() {
        return entityData.get(DATA_TRAIL_COLOR);
    }

    public void addAdditionalSaveData(CompoundNBT tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("TrailColor", getTrailColor());
    }

    public void readAdditionalSaveData(CompoundNBT tag) {
        super.readAdditionalSaveData(tag);
        setTrailColor(tag.getInt("TrailColor"));
    }

    public List<Vector3d> getTrails() {
        return trails;
    }

    @Nonnull
    @Override
    public EntitySize getDimensions(@Nonnull Pose pose) {
        return EntitySize.scalable(0.2f, 0.2f);
    }

}
