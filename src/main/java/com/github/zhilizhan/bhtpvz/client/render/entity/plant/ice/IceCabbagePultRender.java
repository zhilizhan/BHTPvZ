package com.github.zhilizhan.bhtpvz.client.render.entity.plant.ice;


import com.github.zhilizhan.bhtpvz.client.model.entity.plant.ice.IceCabbagePultModel;
import com.github.zhilizhan.bhtpvz.common.entity.plant.ice.IceCabbagePultEntity;

import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IceCabbagePultRender extends PVZPlantRender<IceCabbagePultEntity> {
	public IceCabbagePultRender(EntityRenderDispatcher rendererManager) {
		super(rendererManager, new IceCabbagePultModel(), 0.5f);
	}
}
