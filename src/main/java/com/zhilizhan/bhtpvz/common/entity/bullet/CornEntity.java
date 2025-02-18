package com.zhilizhan.bhtpvz.common.entity.bullet;

import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.common.damagesource.BHTPvZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import net.minecraft.entity.*;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import javax.annotation.Nonnull;


public class CornEntity extends PultBulletEntity {
    private Entity attackEntity = null;

    public CornEntity(EntityType<?> type, World worldIn) {
        super(type, worldIn);
    }

    public CornEntity(World worldIn, LivingEntity shooter) {
        super(BHTPvZEntityTypes.CORN.get(), worldIn, shooter);
    }

    @Override
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

        EntityUtil.playSound(this, SoundEvents.ARROW_HIT);
    }


    @Nonnull
    public EntitySize getDimensions(@Nonnull Pose poseIn) {
        return EntitySize.scalable(0.6F, 0.6F);
    }
}

