package com.zhilizhan.bhtpvz.client.render.layer;

import com.hungteen.pvz.client.render.layer.component.ComponentLayer;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.zhilizhan.bhtpvz.client.model.entity.plant.defence.SteelPumpkinModel;
import com.zhilizhan.bhtpvz.common.entity.plant.defence.SteelPumpkinEntity;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;

public class SteelPumpkinArmorLayer <T extends PVZPlantEntity> extends ComponentLayer<T> {
    public SteelPumpkinArmorLayer(RenderLayerParent<T, EntityModel<T>> entityRendererIn) {
        super(entityRendererIn, new SteelPumpkinModel.SteelPumpkinArmorModel<>());
    }

    public boolean canRender(T entity) {
        return entity.getOuterDefenceLife()> SteelPumpkinEntity.SteelPumpkinInfo.NORMAL_PUMPKIN_LIFE;
    }
    public ResourceLocation getRenderTexture(T plant) {
        return BHTPvZPlants.STEEL_PUMPKIN.getRenderResource();
    }
}

