package com.zhilizhan.bhtpvz.common.client.render.entity.plant.ice;


import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.zhilizhan.bhtpvz.common.client.model.entity.plant.ice.IceCabbagePultModel;
import com.zhilizhan.bhtpvz.common.entity.plant.ice.IceCabbagePultEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IceCabbagePultRender extends PVZPlantRender<IceCabbagePultEntity> {

	public IceCabbagePultRender(EntityRendererManager rendererManager) {
		super(rendererManager, new IceCabbagePultModel(), 0.5F);
	}


}
