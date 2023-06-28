package com.zhilizhan.bhtpvz.common.mixin.optional;

import com.hungteen.pvz.common.advancement.trigger.EntityEffectAmountTrigger;
import com.hungteen.pvz.common.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.common.entity.plant.toxic.PuffShroomEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Iterator;

@Mixin(value = PuffShroomEntity.class,remap = false)
public abstract class PuffshroomEntityMixin extends PlantShooterEntity {

     int   growTime = 1000;
    @Shadow protected abstract boolean canSuperTogether(PuffShroomEntity entity);

    @Shadow public abstract int getMaxSuperCnt();

    public PuffshroomEntityMixin(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }
    protected void growUpTo(int stage) {
        this.setExistTick(growTime * (stage - 1) - 10 - 2);
        EntityUtil.playSound(this, (SoundEvent) SoundRegister.PLANT_GROW.get());
    }

    public int getDamageInStage(int stage) {
        return stage == 3 ? 2 : (stage == 2 ? 1 : 0);
    }

    public boolean isInGrowStage(int stage) {
        return this.getExistTick() > growTime * (stage - 1);
    }
    @Overwrite
    public float getAttackDamage() {
        return this.getSkillValue(SkillTypes.SPORE_DAMAGE)+getCurrentDamage();

    }
    protected int getCurrentDamage() {
        return this.isInGrowStage(3) ? this.getDamageInStage(3) : (this.isInGrowStage(2) ? this.getDamageInStage(2) : this.getDamageInStage(1));
    }
    @Overwrite
    public void shootBullet() {
        if (this.isPlantInSuperMode()) {
            this.performShoot(0.1, 0.0, -0.20000000298023224, this.getExistTick() % 5 == 0, 0.0);
        } else {
            this.performShoot(0.1, 0.0, -0.20000000298023224, this.getAttackTime() == 1, 0.0);
            this.refreshDimensions();
        }

    }
    @Overwrite
    public void startSuperMode(boolean first) {
        if (first) {
            int cnt = 1;
            boolean range = true;
            Iterator var4 = this.level.getEntitiesOfClass(PuffShroomEntity.class, EntityUtil.getEntityAABB(this, 20.0, 20.0), (shroomx) -> {
                return this.canSuperTogether(shroomx);
            }).iterator();

            while(var4.hasNext()) {
                PuffShroomEntity shroom = (PuffShroomEntity)var4.next();
                if (shroom.canStartSuperMode()) {
                    shroom.startSuperMode(false);
                    ++cnt;
                    if (cnt >= this.getMaxSuperCnt()) {
                        break;
                    }
                }
            }

            Player player = EntityUtil.getEntityOwner(this.level, this);
            if (player != null && player instanceof ServerPlayer) {
                EntityEffectAmountTrigger.INSTANCE.trigger((ServerPlayer)player, this, cnt);
            }
        if (!this.isInGrowStage(3)) {
            this.growUpTo(3);
        }
        }
    }
}
