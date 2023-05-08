package com.zhilizhan.bhtpvz.common.entity.bullet;

import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.common.damagesource.BHTPvZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.entity.plant.arma.BurstKernelPultEntity;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;

import java.util.Random;

public class BurstCornEntity extends CornEntity {

    private Random random;
    private Entity attackEntity = null;

    public BurstCornEntity(EntityType<?> type, Level worldIn) {
        super(type, worldIn);
    }
    public BurstCornEntity(Level worldIn, LivingEntity shooter) {
        super((EntityType) BHTPvZEntityTypes.BURST_CORN.get(), worldIn);
    }

    private BurstKernelPultEntity pultEntity;


    protected void dealDamage(Entity target) {
            PVZEntityDamageSource source = BHTPvZEntityDamageSource.burst_corn(this, this.getThrower());
            target.setSecondsOnFire(3);
            target.hurt(source, this.getAttackDamage());

        this.attackEntity = target;
        this.dealSplashDamage();
    }

    protected void onHitBlock() {
        this.dealSplashDamage();
    }

    public void dealSplashDamage() {
        float range = 3.0F;
        EntityUtil.getTargetableEntities(this.getOwnerOrSelf(), EntityUtil.getEntityAABB(this, range, range)).forEach((entity) -> {
            if (!entity.is(this.attackEntity) && this.shouldHit(entity)) {
                PVZEntityDamageSource source;
                  source = BHTPvZEntityDamageSource.burst_corn(this, this.getThrower());
                   entity.setSecondsOnFire(3);
                    entity.hurt(source, this.getAttackDamage() / 1.5F);
            }

        });
        for(int i = 0; i < 10; ++i) {
            EntityUtil.spawnParticle(this,1);
        }

        EntityUtil.playSound(this, (SoundEvent) SoundEvents.GENERIC_EXPLODE);
    }


    public EntityDimensions getDimensions(Pose poseIn) {
        return EntityDimensions.scalable(0.6F, 0.6F);
    }


}

