package com.zhilizhan.bhtpvz.common.entity.bullet;

import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.base.AbstractBossZombieEntity;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.damagesource.BHTPvZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ChorusFruitEntity extends PultBulletEntity {
    public ChorusFruitEntity(EntityType<?> type, Level worldIn) {
        super(type, worldIn);
    }

    public ChorusFruitEntity(Level worldIn, LivingEntity shooter) {
        super(BHTPvZEntityTypes.CHORUS_FRUIT.get(), worldIn, shooter);
    }

    protected void dealDamage(Entity target) {
        PVZEntityDamageSource source = BHTPvZEntityDamageSource.chorusFruit(this, this.getThrower());
        target.hurt(source, this.attackDamage);
        if(!(target instanceof AbstractBossZombieEntity) && target instanceof PVZZombieEntity) {
            Vec3 vector3d = new Vec3(this.getX() - target.getX(), this.getY(0.5) - target.getEyeY(), this.getZ() - target.getZ());
            vector3d = vector3d.normalize();

            double dx = this.getX() + (this.random.nextDouble() - 0.5) * 8.0 - vector3d.x * 4.0;
            double dz = this.getZ() + (this.random.nextDouble() - 0.5) * 8.0 - vector3d.z * 4.0;

            target.hurt(source, this.attackDamage);

            ((PVZZombieEntity) target).randomTeleport(dx,target.getY(),dz,true);
            if (!this.isSilent()) {
                this.level.playSound((Player)null, this.xo, this.yo, this.zo, SoundEvents.ENDERMAN_TELEPORT, this.getSoundSource(), 1.0F, 1.0F);
                target.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
            }
        }

    }

    public EntityDimensions getDimensions(Pose poseIn) {
        return EntityDimensions.scalable(0.6F, 0.6F);
    }
}

