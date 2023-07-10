package com.zhilizhan.bhtpvz.common.entity.bullet;

import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.base.AbstractBossZombieEntity;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.utils.WorldUtil;
import com.zhilizhan.bhtpvz.common.damagesource.BHTPvZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;

public class ChorusFruitEntity extends PultBulletEntity {
    public ChorusFruitEntity(EntityType<?> type, Level worldIn) {
        super(type, worldIn);
    }

    public ChorusFruitEntity(Level worldIn, LivingEntity shooter) {
        super((EntityType) BHTPvZEntityTypes.CHORUS_FRUIT.get(), worldIn, shooter);
    }

    protected void dealDamage(Entity target) {
        PVZEntityDamageSource source = BHTPvZEntityDamageSource.chorusFruit(this, this.getThrower());
        target.hurt(source, this.attackDamage);
        if(!(target instanceof AbstractBossZombieEntity) && target instanceof PVZZombieEntity) {
            target.setDeltaMovement(0, 0, 2);}
            target.hurt(source, this.attackDamage);
        if (this.level.isClientSide) {
            WorldUtil.spawnRandomSpeedParticle(this.level, ParticleTypes.END_ROD, this.position(), 0.015F);
        }

    }

    public EntityDimensions getDimensions(Pose poseIn) {
        return EntityDimensions.scalable(0.6F, 0.6F);
    }
}

