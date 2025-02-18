package com.zhilizhan.bhtpvz.client.render.entity.plant.arma;

import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.zhilizhan.bhtpvz.client.model.entity.plant.arma.BurstKernelPultModel;
import com.zhilizhan.bhtpvz.common.entity.plant.arma.BurstKernelPultEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BurstKernelPultRender extends PVZPlantRender<BurstKernelPultEntity> {
    public BurstKernelPultRender(EntityRendererManager rendererManager) {
        super(rendererManager, new BurstKernelPultModel(), 0.7f);
    }
}