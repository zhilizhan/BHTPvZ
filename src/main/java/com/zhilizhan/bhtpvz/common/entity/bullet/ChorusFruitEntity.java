package com.zhilizhan.bhtpvz.common.entity.bullet;

import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.base.AbstractBossZombieEntity;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.common.damagesource.BHTPvZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import net.minecraft.entity.*;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class ChorusFruitEntity extends PultBulletEntity {
    public ChorusFruitEntity(EntityType<?> type, World worldIn) {
        super(type, worldIn);
    }

    public ChorusFruitEntity(World worldIn, LivingEntity shooter) {
        super(BHTPvZEntityTypes.CHORUS_FRUIT.get(), worldIn, shooter);
    }

    protected void dealDamage(Entity target) {
        PVZEntityDamageSource source = BHTPvZEntityDamageSource.chorusFruit(this, this.getThrower());
        target.hurt(source, this.attackDamage);
        if(!(target instanceof AbstractBossZombieEntity) && target instanceof PVZZombieEntity) {
            Vector3d forwardVector = target.getLookAngle(); // 获取僵尸当前的朝向
            Vector3d backwardVector = forwardVector.scale(-1); // 取反方向

            double dx = target.getX() + backwardVector.x * 8; // 可以调整8到需要的距离
            double dy = target.getY();
            double dz = target.getZ() + backwardVector.z * 8; // 同上
            target.hurt(source, this.attackDamage);
            PVZZombieEntity zombie = (PVZZombieEntity) target;
            if(EntityUtil.isEntityValid(target)) zombie.randomTeleport(dx,dy,dz,true);
            if (!this.isSilent()) {
                this.level.playSound(null, this.xo, this.yo, this.zo, SoundEvents.ENDERMAN_TELEPORT, this.getSoundSource(), 1.0F, 1.0F);
                target.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
            }
        }

    }

    public EntitySize getDimensions(Pose poseIn) {
        return EntitySize.scalable(0.6F, 0.6F);
    }
}

