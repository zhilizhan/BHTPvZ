package com.zhilizhan.bhtpvz.client.render.entity.plant.fire;

import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.zhilizhan.bhtpvz.client.model.entity.plant.fire.CaramelKernelPultModel;
import com.zhilizhan.bhtpvz.common.entity.plant.arma.CaramelKernelPultEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CaramelKernelPultRender extends PVZPlantRender<CaramelKernelPultEntity> {
    public CaramelKernelPultRender(EntityRendererManager rendererManager) {
        super(rendererManager, new CaramelKernelPultModel(), 0.7f);
    }
}