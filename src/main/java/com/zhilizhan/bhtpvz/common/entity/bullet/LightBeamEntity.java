package com.zhilizhan.bhtpvz.common.entity.bullet;

import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.damagesource.BHTPvZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class LightBeamEntity extends AbstractBulletEntity {

    public LightBeamEntity(EntityType<?> type, Level worldIn) {
        super(type, worldIn);
    }
    public LightBeamEntity(Level worldIn, LivingEntity shooter) {
        super((EntityType) BHTPvZEntityTypes.LIGHT_BEAM.get(), worldIn, shooter);
    }
    @Override
    protected int getMaxLiveTick() {
        return 40;
    }
    @Override
    protected void onImpact(HitResult hitResult) {
        boolean flag = false;
        if (hitResult.getType() == net.minecraft.world.phys.HitResult.Type.ENTITY) {
            Entity target = ((EntityHitResult)hitResult).getEntity();
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

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return EntityDimensions.scalable(0.5f, 0.5f);
    }

}
