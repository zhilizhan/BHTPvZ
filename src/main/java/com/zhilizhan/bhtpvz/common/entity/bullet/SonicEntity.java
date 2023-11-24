package com.zhilizhan.bhtpvz.common.entity.bullet;

import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.zhilizhan.bhtpvz.common.damagesource.BHTPvZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class SonicEntity  extends AbstractBulletEntity {

    public SonicEntity(EntityType<?> type, Level worldIn) {
        super(type, worldIn);
    }

    public SonicEntity(Level worldIn, LivingEntity living) {
        super(BHTPvZEntityTypes.SONIC.get(), worldIn, living);
    }

    protected int getMaxLiveTick() {
        return 20;
    }

    public ItemStack getItem() {
        return null;
    }

    protected void onImpact(HitResult result) {
        boolean flag = false;
        if (result.getType() == HitResult.Type.ENTITY) {
            Entity target = ((EntityHitResult)result).getEntity();
            if (this.shouldHit(target)) {
                target.invulnerableTime = 0;
                this.dealSonicDamage(target);
                if (this.hitEntities == null) {
                    this.hitEntities = new IntOpenHashSet();
                }

                this.addHitEntity(target);
            }
        }

        this.level.broadcastEntityEvent(this, (byte)3);
        if (flag || !this.checkLive(result)) {
            this.remove();
        }

    }

    protected boolean checkLive(HitResult result) {
        if (result.getType() == HitResult.Type.BLOCK) {
            Block block = this.level.getBlockState(((BlockHitResult)result).getBlockPos()).getBlock();
            return block instanceof BushBlock;
        } else {
            return true;
        }
    }

    private void dealSonicDamage(Entity target) {
        if (!this.level.isClientSide ) {
            target.hurt(BHTPvZEntityDamageSource.sonic(this, this.getThrower()), this.attackDamage);
            if (target instanceof LivingEntity) {
                ((LivingEntity) target).addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 120));
            }
        }
    }

    public EntityDimensions getDimensions(Pose poseIn) {
        return EntityDimensions.scalable(0.15F, 0.15F);
    }

    protected float getGravityVelocity() {
        return 0.0001F;
    }
}
