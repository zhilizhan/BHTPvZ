package com.zhilizhan.bhtpvz.common.entity.bullet.itembullet;

import com.hungteen.pvz.common.entity.bullet.AbstractShootBulletEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public abstract class BHTPvZPeaEntity extends AbstractShootBulletEntity implements IRendersAsItem {
    private int power = 0;
    public BHTPvZPeaEntity(EntityType<?> type, World worldIn) {
        super(type,worldIn);
    }
    public BHTPvZPeaEntity(EntityType<?> type, World worldIn, LivingEntity shooter) {
        super(type,worldIn,shooter);
    }

    protected void onImpact(RayTraceResult result) {
        boolean flag = false;
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity target = ((EntityRayTraceResult)result).getEntity();
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
