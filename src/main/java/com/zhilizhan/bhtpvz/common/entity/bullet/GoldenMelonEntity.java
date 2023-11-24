package com.zhilizhan.bhtpvz.common.entity.bullet;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.client.particle.ParticleRegister;
import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.client.particle.BHTPvZParticle;
import com.zhilizhan.bhtpvz.common.damagesource.BHTPvZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;

public class GoldenMelonEntity  extends PultBulletEntity {

    private Entity attackEntity = null;

    public GoldenMelonEntity(EntityType<?> type, Level worldIn) {
        super(type, worldIn);
    }

    public GoldenMelonEntity(Level worldIn, LivingEntity shooter) {
        super(BHTPvZEntityTypes.GOLDEN_MELON.get(), worldIn, shooter);
    }


    protected void dealDamage(Entity target) {
        target.hurt(BHTPvZEntityDamageSource.goldenMelon(this, this.getThrower()), this.getAttackDamage());

        this.attackEntity = target;

            for(int i = 0; i < 2; ++i) {
                this.level.addParticle(ParticleRegister.RED_BOMB.get(), this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        for(int i = 0; i < 10; ++i) {
             if (PVZMod.PROXY.getPlayer() != null) {
                 PVZMod.PROXY.getPlayer().level.addParticle(BHTPvZParticle.GOLD_NUGGET.get(), this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
             }
        }
        this.dealSplashDamage();
    }

    protected void onHitBlock() {
        this.dealSplashDamage();
    }

    public void dealSplashDamage() {
        float range = 3.0F;
        EntityUtil.getTargetableEntities(this.getOwnerOrSelf(), EntityUtil.getEntityAABB(this, 3.0, 3.0)).forEach((entity) -> {
            if (!entity.is(this.attackEntity) && this.shouldHit(entity)) {
                PVZEntityDamageSource source;

                    source = BHTPvZEntityDamageSource.goldenMelon(this, this.getThrower());
                    entity.hurt(source, this.getAttackDamage() / 2.0F);
            }

        });

        EntityUtil.playSound(this, (SoundEvent) SoundRegister.MELON_HIT.get());
    }

    public EntityDimensions getDimensions(Pose poseIn) {
        return EntityDimensions.scalable(0.6F, 0.6F);
    }

}
