package com.zhilizhan.bhtpvz.common.entity.bullet;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.client.particle.ParticleRegister;
import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.common.entity.misc.drop.CoinEntity;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.client.particle.BHTPvZParticle;
import com.zhilizhan.bhtpvz.common.damagesource.BHTPvZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import net.minecraft.entity.*;
import net.minecraft.world.World;

public class GoldenMelonEntity  extends PultBulletEntity {

    private Entity attackEntity = null;

    public GoldenMelonEntity(EntityType<?> type, World worldIn) {
        super(type, worldIn);
    }

    public GoldenMelonEntity(World worldIn, LivingEntity shooter) {
        super(BHTPvZEntityTypes.GOLDEN_MELON.get(), worldIn, shooter);
    }

    protected void dealDamage(Entity target) {
        if(EntityUtil.isEntityValid(target)) target.hurt(BHTPvZEntityDamageSource.goldenMelon(this, this.getThrower()), this.getAttackDamage());

        this.attackEntity = target;
            if(Math.random()<=0.08 && EntityUtil.isEntityValid(this)){
                CoinEntity.spawnCoin(this.level, this.blockPosition(), CoinEntity.CoinType.GOLD);
            }
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
        EntityUtil.getTargetableEntities(this.getOwnerOrSelf(), EntityUtil.getEntityAABB(this, range, range)).forEach((entity) -> {
            if (!entity.is(this.attackEntity) && this.shouldHit(entity) && EntityUtil.isEntityValid(entity)) {
                PVZEntityDamageSource source;

                    source = BHTPvZEntityDamageSource.goldenMelon(this, this.getThrower());
                    entity.hurt(source, this.getAttackDamage() / 2.0F);
            }

        });

        EntityUtil.playSound(this, SoundRegister.MELON_HIT.get());
    }

    public EntitySize getDimensions(Pose poseIn) {
        return EntitySize.scalable(0.6F, 0.6F);
    }

}
