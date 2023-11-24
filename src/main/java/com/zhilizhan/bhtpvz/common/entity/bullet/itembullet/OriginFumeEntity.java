package com.zhilizhan.bhtpvz.common.entity.bullet.itembullet;

import com.hungteen.pvz.common.entity.bullet.itembullet.PVZItemBulletEntity;
import com.hungteen.pvz.common.entity.plant.toxic.GloomShroomEntity;
import com.hungteen.pvz.utils.WorldUtil;
import com.zhilizhan.bhtpvz.client.particle.BHTPvZParticle;
import com.zhilizhan.bhtpvz.common.damagesource.BHTPvZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class OriginFumeEntity extends PVZItemBulletEntity {


    public OriginFumeEntity(EntityType<?> type, Level worldIn) {
        super(type, worldIn);
    }

    public OriginFumeEntity(Level worldIn, LivingEntity living) {
        super((EntityType) BHTPvZEntityTypes.ORIGIN_FUME.get(), worldIn, living);
    }

    public void tick() {
        super.tick();
        if (this.level.isClientSide) {
            int cnt = Math.max(2, Math.min(5, this.getMaxLiveTick() / this.tickCount));

            for(int i = 0; i < cnt; ++i) {
                WorldUtil.spawnRandomSpeedParticle(this.level, (ParticleOptions) BHTPvZParticle.ORIGINAL_FUME.get(), this.position(), 0.05F);
            }
        }

    }

    protected int getMaxLiveTick() {
        return this.getThrower() instanceof GloomShroomEntity ? 3 : 10;
    }


    protected void onImpact(HitResult result) {
        boolean flag = false;
        if (result.getType() == HitResult.Type.ENTITY) {
            Entity target = ((EntityHitResult)result).getEntity();
            if (this.shouldHit(target)) {
                target.invulnerableTime = 0;
                this.dealFumeDamage(target);
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

    private void dealFumeDamage(Entity target) {
        target.hurt(BHTPvZEntityDamageSource.originFume(this, this.getThrower()), this.attackDamage);
        if (!this.level.isClientSide) {
            ((LivingEntity)target).addEffect(new MobEffectInstance(MobEffects.POISON,120,2));
            ((LivingEntity)target).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,60));

        }

    }

    public EntityDimensions getDimensions(Pose poseIn) {
        return EntityDimensions.scalable(0.25F, 0.25F);
    }

    protected float getGravityVelocity() {
        return 0.0015F;
    }

    public ItemStack getItem() {
        return new ItemStack((ItemLike) BHTPvZItems.ORIGIN_SPORE.get());
    }

}
