package com.zhilizhan.bhtpvz.common.entity.bullet;

import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.WorldUtil;
import com.zhilizhan.bhtpvz.client.particle.BHTPvZParticle;
import com.zhilizhan.bhtpvz.common.damagesource.BHTPvZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.block.Block;
import net.minecraft.block.BushBlock;
import net.minecraft.entity.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;


public class SonicEntity  extends AbstractBulletEntity {

    public SonicEntity(EntityType<?> type, World worldIn) {
        super(type, worldIn);
    }

    public SonicEntity(World worldIn, LivingEntity living) {
        super(BHTPvZEntityTypes.SONIC.get(), worldIn, living);
    }

    protected int getMaxLiveTick() {
        return 20;
    }



    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity target = ((EntityRayTraceResult)result).getEntity();
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
    public void tick() {
        super.tick();
        if (this.level.isClientSide) {
             WorldUtil.spawnRandomSpeedParticle(this.level, BHTPvZParticle.SONIC_BOOM.get(), this.position(), 0.05F);
        }
    }
    private void dealSonicDamage(Entity target) {
        if (!this.level.isClientSide && EntityUtil.isEntityValid(target)) {
            target.hurt(BHTPvZEntityDamageSource.sonic(this, this.getThrower()), this.attackDamage);
            if (target instanceof LivingEntity && Math.random()<=0.75F) {
                ((LivingEntity)target).addEffect(new EffectInstance(Effects.WEAKNESS, 120));
            }
        }
    }

    public EntitySize getDimensions(Pose poseIn) {
        return EntitySize.scalable(0.15F, 0.15F);
    }

    protected float getGravityVelocity() {
        return 0.0F;
    }
}
