package com.zhilizhan.bhtpvz.common.entity.bullet;

import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.common.damagesource.BHTPvZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.entity.plant.arma.BurstKernelPultEntity;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;

public class CornEntity extends PultBulletEntity {
    private Entity attackEntity = null;

    public CornEntity(EntityType<?> type, Level worldIn) {
        super(type, worldIn);
    }

    public CornEntity(Level worldIn, LivingEntity shooter) {
        super((EntityType) BHTPvZEntityTypes.CORN.get(), worldIn, shooter);
    }
    private BurstKernelPultEntity pultEntity;


    protected void dealDamage(Entity target) {
        target.hurt(BHTPvZEntityDamageSource.corn(this, this.getThrower()), this.getAttackDamage());

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
                PVZEntityDamageSource source = BHTPvZEntityDamageSource.corn(this, this.getThrower());
                    entity.hurt(source, this.getAttackDamage()*1.5f);

            }

        });

        EntityUtil.playSound(this, (SoundEvent) SoundEvents.ARROW_HIT);
    }


    public EntityDimensions getDimensions(Pose poseIn) {
        return EntityDimensions.scalable(0.6F, 0.6F);
    }
}

