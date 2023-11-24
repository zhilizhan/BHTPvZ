package com.zhilizhan.bhtpvz.common.mixin;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.flame.TorchWoodEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.common.entity.bullet.StonePeaEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = TorchWoodEntity.class,remap = false)
public abstract class TorchWoodEntityMixin extends PVZPlantEntity {
    @Shadow public abstract float getHeatRange();

    public TorchWoodEntityMixin(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    @Inject(method = "heatPeas", at = @At("RETURN"))
    public void heatPeas(CallbackInfo ci) {
        this.level.getEntitiesOfClass(StonePeaEntity.class, EntityUtil.getEntityAABB(this, (double)getHeatRange(), (double)getHeatRange())).forEach((pea) -> {
            pea.heatBy(this);
        });
    }
}
