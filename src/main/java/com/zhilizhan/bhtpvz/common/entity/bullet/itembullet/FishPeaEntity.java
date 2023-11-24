package com.zhilizhan.bhtpvz.common.entity.bullet.itembullet;

import com.hungteen.pvz.common.entity.bullet.itembullet.PeaEntity;
import com.hungteen.pvz.common.item.ItemRegister;
import com.zhilizhan.bhtpvz.common.damagesource.BHTPvZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class FishPeaEntity extends PeaEntity implements ItemSupplier {
    public FishPeaEntity(EntityType<?> type, Level worldIn) {
        super(type, worldIn);
    }
    public FishPeaEntity(Level worldIn) {
        super(BHTPvZEntityTypes.FISH_PEA.get(), worldIn);
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
    private void dealPeaDamage(Entity target) {
        float damage = this.getAttackDamage();

        target.hurt(BHTPvZEntityDamageSource.fishPea(this, this.getThrower()), damage);

    }

    @Override
    public EntityDimensions getDimensions(Pose poseIn) {
        return EntityDimensions.scalable(0.4f, 0.4f);
    }

    @Override
    protected int getMaxLiveTick() {
        return 30;
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(ItemRegister.PEA.get());
    }
}
