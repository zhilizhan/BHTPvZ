package com.github.zhilizhan.bhtpvz.client.render.entity.plant.defence;


import com.github.zhilizhan.bhtpvz.client.model.entity.plant.defence.SteelPumpkinModel;
import com.github.zhilizhan.bhtpvz.common.entity.plant.defence.SteelPumpkinEntity;

import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SteelPumpkinRender extends PVZPlantRender<SteelPumpkinEntity> {
	public SteelPumpkinRender(EntityRenderDispatcher rendererManager) {
		super(rendererManager, new SteelPumpkinModel(), 1.1f);
	}
}
