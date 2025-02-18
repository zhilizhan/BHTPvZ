package com.zhilizhan.bhtpvz.common.entity.bullet;

import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.common.damagesource.BHTPvZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import net.minecraft.entity.*;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class PoppedChorusFruitEntity extends PultBulletEntity {

    private Entity attackEntity = null;

    public PoppedChorusFruitEntity(EntityType<?> type, World worldIn) {
        super(type, worldIn);
    }

    public PoppedChorusFruitEntity(World worldIn, LivingEntity shooter) {
        super(BHTPvZEntityTypes.POPPED_CHORUS_FRUIT.get(), worldIn, shooter);
    }

    protected void dealDamage(Entity target) {

        target.hurt(BHTPvZEntityDamageSource.poppedChorusFruit(this, this.getThrower()), this.getAttackDamage());

        this.attackEntity = target;
        this.dealSplashDamage();
    }

    protected void onHitBlock() {
        this.dealSplashDamage();
    }

    public void dealSplashDamage() {
        float range = 2.5F;
        EntityUtil.getTargetableEntities(this.getOwnerOrSelf(), EntityUtil.getEntityAABB(this, range, range)).forEach((entity) -> {
            if (!entity.is(this.attackEntity) && this.shouldHit(entity) && EntityUtil.isEntityValid(entity)) {
                PVZEntityDamageSource source = BHTPvZEntityDamageSource.poppedChorusFruit(this, this.getThrower());
                entity.hurt(source, this.getAttackDamage() / 2.0F);
            }

        EntityUtil.playSound(this, SoundEvents.CHORUS_FLOWER_DEATH);
    });
    }

    public EntitySize getDimensions(Pose poseIn) {
        return EntitySize.scalable(0.6F, 0.6F);
    }

}
