package com.zhilizhan.bhtpvz.common.mixin;

import com.hungteen.pvz.common.entity.ai.goal.ZombieBreakPlantBlockGoal;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.zhilizhan.bhtpvz.common.block.BHTPvZBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PVZZombieEntity.class,remap = false)
public class PVZZombieEntityMixin {
    @Inject(method = "registerAttackGoals", at = @At("TAIL"))
    protected void registerAttackGoals(CallbackInfo ci) {
        this.getSelf().goalSelector.addGoal(6, new ZombieBreakPlantBlockGoal(BHTPvZBlocks.WATER_POT.get(), this.getSelf() , 1F, 10));
    }
    @Unique
    private PVZZombieEntity getSelf() {
        return (PVZZombieEntity) (Object) this;
    }
}
