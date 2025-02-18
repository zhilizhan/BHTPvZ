package com.zhilizhan.bhtpvz.common.mixin.optional;

import com.hungteen.pvz.api.interfaces.ICanBeAttracted;
import com.hungteen.pvz.common.entity.zombie.custom.GigaTombStoneEntity;
import com.hungteen.pvz.common.entity.zombie.grass.AbstractTombStoneEntity;
import com.hungteen.pvz.utils.interfaces.ICanAttract;
import com.zhilizhan.bhtpvz.common.entity.ai.TombStoneAttractGoal;
import com.zhilizhan.bhtpvz.config.BHTPvZConfig;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GigaTombStoneEntity.class)
public abstract class GigaTombStoneEntityMixin extends AbstractTombStoneEntity  implements ICanAttract {
    public GigaTombStoneEntityMixin(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }
    @Override
    public boolean canBeTargetBy(LivingEntity living) {
         return BHTPvZConfig.COMMON_CONFIG.EntitySettings.ZombieSetting.GigaTombstoneCanBeAttack.get();
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TombStoneAttractGoal(this, this, 20));
    }
    @Override
    public boolean canAttract(LivingEntity entity) {
        if (entity instanceof ICanBeAttracted && !((ICanBeAttracted)entity).canBeAttractedBy(this)|| !BHTPvZConfig.COMMON_CONFIG.EntitySettings.ZombieSetting.GigaTombstoneCanBeAttack.get()) {
            return false;
        } else {
            return this.getSensing().canSee(entity);
        }
    }
    @Override
    public void attract(LivingEntity target) {
        if (target instanceof MobEntity && !(((MobEntity)target).getTarget() instanceof ICanAttract)) {
            ((MobEntity)target).setTarget(this);
        }
        if (target instanceof ICanBeAttracted) {
            ((ICanBeAttracted)target).attractBy(this);
        }

    }
    @Override
    public float getAttractRange() {
        return 15.0F;
    }

}
