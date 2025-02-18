package com.zhilizhan.bhtpvz.common.entity.bullet;

import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.damagesource.BHTPvZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import net.minecraft.entity.*;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class LightBeamEntity extends AbstractBulletEntity {

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

    @Nonnull
    @Override
    public EntitySize getDimensions(@Nonnull Pose pose) {
        return EntitySize.scalable(0.2f, 0.2f);
    }

}
