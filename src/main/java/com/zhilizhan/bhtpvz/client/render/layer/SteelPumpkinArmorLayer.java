package com.zhilizhan.bhtpvz.client.render.layer;

import com.hungteen.pvz.client.render.layer.component.ComponentLayer;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.zhilizhan.bhtpvz.client.model.entity.plant.defence.SteelPumpkinModel;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import com.zhilizhan.bhtpvz.common.util.BHTPVZUtils;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;

public class SteelPumpkinArmorLayer <T extends PVZPlantEntity> extends ComponentLayer<T> {
    public SteelPumpkinArmorLayer(IEntityRenderer<T, EntityModel<T>> entityRendererIn) {
        super(entityRendererIn, new SteelPumpkinModel.SteelPumpkinArmorModel<>());
    }

    public boolean canRender(T entity) {
        return entity.getOuterDefenceLife()> 0 && BHTPVZUtils.getSteelPumpkin(entity);
    }
    public ResourceLocation getRenderTexture(T plant) {
        return BHTPvZPlants.STEEL_PUMPKIN.getRenderResource();
    }
}

