package com.zhilizhan.bhtpvz.common.entity.bullet.itembullet;

import com.hungteen.pvz.common.entity.bullet.AbstractShootBulletEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public abstract class BHTPvZPeaEntity extends AbstractShootBulletEntity implements ItemSupplier {
    private int power = 0;
    public BHTPvZPeaEntity(EntityType<?> type, Level worldIn) {
        super(type,worldIn);
    }
    public BHTPvZPeaEntity(EntityType<?> type, Level worldIn, LivingEntity shooter) {
        super(type,worldIn,shooter);
    }

    protected void onImpact(HitResult result) {
        boolean flag = false;
        if (result.getType() == net.minecraft.world.phys.HitResult.Type.ENTITY) {
            Entity target = ((EntityHitResult)result).getEntity();
            if (this.shouldHit(target)) {
                target.invulnerableTime = 0;
                this.dealPeaDamage(target);
                flag = true;
            }
        }

        this.level.broadcastEntityEvent(this, (byte)3);
        if (flag || !this.checkLive(result)) {
            this.remove();
        }

    }
    @Override
    public float getAttackDamage() {
       return attackDamage *= (1 + this.power * 1.0f / 4);
    }
    @Override
    protected int getMaxLiveTick() {
        return 40;
    }

     abstract void dealPeaDamage(Entity target);

    public void setPower(int level) {
        this.power=level;
    }
}
