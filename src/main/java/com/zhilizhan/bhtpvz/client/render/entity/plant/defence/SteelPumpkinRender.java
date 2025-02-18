package com.zhilizhan.bhtpvz.client.render.entity.plant.defence;


import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.zhilizhan.bhtpvz.client.model.entity.plant.defence.SteelPumpkinModel;
import com.zhilizhan.bhtpvz.common.entity.plant.defence.SteelPumpkinEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SteelPumpkinRender extends PVZPlantRender<SteelPumpkinEntity> {
	public SteelPumpkinRender(EntityRendererManager rendererManager) {
		super(rendererManager, new SteelPumpkinModel(), 0.5f);
	}

}
