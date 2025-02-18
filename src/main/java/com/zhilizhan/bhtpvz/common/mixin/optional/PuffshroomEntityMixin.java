package com.zhilizhan.bhtpvz.common.mixin.optional;

import com.hungteen.pvz.common.advancement.trigger.EntityEffectAmountTrigger;
import com.hungteen.pvz.common.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.common.entity.plant.toxic.PuffShroomEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.config.BHTPvZConfig;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = PuffShroomEntity.class,remap = false)
public abstract class PuffshroomEntityMixin extends PlantShooterEntity {
    @Unique
    int  growTime = 8000;
    @Shadow protected abstract boolean canSuperTogether(PuffShroomEntity entity);

    @Shadow public abstract int getMaxSuperCnt();

    public PuffshroomEntityMixin(EntityType <? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }
    @Unique
    protected void growUpTo(int stage) {
        this.setExistTick(growTime * (stage - 1) - 10 - 2);
        EntityUtil.playSound(this, SoundRegister.PLANT_GROW.get());
    }

    @Unique
    public int getDamageInStage(int stage) {
        return stage == 3 ? 2 : (stage == 2 ? 1 : 0);
    }

    @Unique
    public boolean isInGrowStage(int stage) {
        return this.getExistTick() > growTime * (stage - 1);
    }
    /**
     * @author SuSen36
     * @reason 胆小菇长大加攻击
     */
    @Inject(method = "getAttackDamage", at = @At("HEAD"),cancellable = true)
    public void getAttackDamage(CallbackInfoReturnable<Float> cir) {
        if(BHTPvZConfig.COMMON_CONFIG.EntitySettings.PlantSetting.PuffShroomGrow.get()){
        float f = this.getSkillValue(SkillTypes.SPORE_DAMAGE) + getCurrentDamage();
        cir.setReturnValue(f);
        cir.cancel();
      }
    }
    protected int getCurrentDamage() {
        return this.isInGrowStage(3) ? this.getDamageInStage(3) : (this.isInGrowStage(2) ? this.getDamageInStage(2) : this.getDamageInStage(1));
    }

    @Inject(method = "shootBullet", at = @At("HEAD"),cancellable = true)
    public void shootBullet(CallbackInfo ci) {
        if (this.isPlantInSuperMode()) {
            this.performShoot(0.1, 0.0, -0.20000000298023224, this.getExistTick() % 5 == 0, 0.0);
        } else {
            this.performShoot(0.1, 0.0, -0.20000000298023224, this.getAttackTime() == 1, 0.0);
            this.refreshDimensions();
        }
        ci.cancel();
    }

    @Inject(method = "startSuperMode", at = @At("HEAD"),cancellable = true)
    public void startSuperMode(boolean first, CallbackInfo ci) {
        if (first) {
            int cnt = 1;

            for (PuffShroomEntity shroom : this.level.getEntitiesOfClass(PuffShroomEntity.class, EntityUtil.getEntityAABB(this, 20.0, 20.0), this::canSuperTogether)) {
                if (shroom.canStartSuperMode()) {
                    shroom.startSuperMode(false);
                    ++cnt;
                    if (cnt >= this.getMaxSuperCnt()) {
                        break;
                    }
                }
            }

            PlayerEntity player = EntityUtil.getEntityOwner(this.level, this);
            if (player instanceof ServerPlayerEntity) {
                EntityEffectAmountTrigger.INSTANCE.trigger((ServerPlayerEntity)player, this, cnt);
            }
        if (!this.isInGrowStage(3)) {
            this.growUpTo(3);
        }
        }
        ci.cancel();
    }
}
