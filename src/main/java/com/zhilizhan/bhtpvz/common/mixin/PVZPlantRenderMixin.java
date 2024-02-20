package com.zhilizhan.bhtpvz.common.mixin;

import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.client.render.layer.PlantLadderLayer;
import com.hungteen.pvz.client.render.layer.PumpkinArmorLayer;
import com.hungteen.pvz.client.render.layer.fullskin.CharmLayer;
import com.hungteen.pvz.client.render.layer.fullskin.EnergyLayer;
import com.hungteen.pvz.client.render.layer.fullskin.HealLightLayer;
import com.hungteen.pvz.client.render.layer.fullskin.SunLightLayer;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.zhilizhan.bhtpvz.client.render.layer.SteelPumpkinArmorLayer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@OnlyIn(Dist.CLIENT)
@Mixin(value = PVZPlantRender.class,remap = false)
public abstract class PVZPlantRenderMixin<T extends PVZPlantEntity> extends MobRenderer<T, EntityModel<T>> {

    public PVZPlantRenderMixin(EntityRenderDispatcher arg, EntityModel<T> arg2, float f) {
        super(arg, arg2, f);
    }
/*
    @Inject(method = "addPlantLayers", at = @At("TAIL"))
    protected void addPlantLayers() {
        this.addLayer(new SteelPumpkinArmorLayer<>(this));
    }*/
/**
 * @author
 * @reason
 */
@Overwrite
        protected void addPlantLayers() {
        this.addLayer(new EnergyLayer<>(this));
        this.addLayer(new CharmLayer<>(this));
        this.addLayer(new PumpkinArmorLayer<>(this));
        this.addLayer(new SunLightLayer<>(this));
        this.addLayer(new HealLightLayer<>(this));
        this.addLayer(new PlantLadderLayer<>(this));
        this.addLayer(new SteelPumpkinArmorLayer<>(this));
    }

}
