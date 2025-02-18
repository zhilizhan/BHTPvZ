package com.zhilizhan.bhtpvz.common.entity.bullet.itembullet;

import com.hungteen.pvz.common.entity.bullet.itembullet.PVZItemBulletEntity;
import com.hungteen.pvz.common.entity.plant.toxic.GloomShroomEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.WorldUtil;
import com.zhilizhan.bhtpvz.client.particle.BHTPvZParticle;
import com.zhilizhan.bhtpvz.common.damagesource.BHTPvZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.block.Block;
import net.minecraft.block.BushBlock;
import net.minecraft.entity.*;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public class OriginFumeEntity extends PVZItemBulletEntity {


    public OriginFumeEntity(EntityType<?> type, World worldIn) {
        super(type, worldIn);
    }

    public OriginFumeEntity(World worldIn, LivingEntity living) {
        super(BHTPvZEntityTypes.ORIGIN_FUME.get(), worldIn, living);
    }

    public void tick() {
        super.tick();
        if (this.level.isClientSide) {
            int cnt = Math.max(2, Math.min(5, this.getMaxLiveTick() / this.tickCount));

            for(int i = 0; i < cnt; ++i) {
                WorldUtil.spawnRandomSpeedParticle(this.level, BHTPvZParticle.ORIGINAL_FUME.get(), this.position(), 0.05F);
            }
        }

    }

    protected int getMaxLiveTick() {
        return this.getThrower() instanceof GloomShroomEntity ? 3 : 10;
    }


    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity target = ((EntityRayTraceResult)result).getEntity();
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
        if (!this.checkLive(result)) {
            this.remove();
        }
    }

    protected boolean checkLive(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.BLOCK) {
            Block block = this.level.getBlockState(((BlockRayTraceResult)result).getBlockPos()).getBlock();
            return block instanceof BushBlock;
        } else {
            return true;
        }
    }

    private void dealFumeDamage(Entity target) {
        target.hurt(BHTPvZEntityDamageSource.originFume(this, this.getThrower()), this.attackDamage);
        if (!this.level.isClientSide) {
            if(target instanceof PVZZombieEntity && random.nextInt(3)==0) {
                ((LivingEntity) target).addEffect(new EffectInstance(Effects.WEAKNESS, 80));
            }
        }

    }

    @Nonnull
    public EntitySize getDimensions(@Nonnull Pose poseIn) {
        return EntitySize.scalable(0.25F, 0.25F);
    }

    protected float getGravityVelocity() {
        return 0.0015F;
    }

    @Nonnull
    public ItemStack getItem() {
        return new ItemStack(BHTPvZItems.ORIGIN_SPORE.get());
    }

}
