package com.zhilizhan.bhtpvz.client.render.entity.plant.assist;

import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.zhilizhan.bhtpvz.client.model.entity.plant.assist.WaterPotModel;
import com.zhilizhan.bhtpvz.common.entity.plant.assist.WaterPotEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

 @OnlyIn(Dist.CLIENT)
public class WaterPotRender extends PVZPlantRender<WaterPotEntity> {
    public WaterPotRender(EntityRenderDispatcher rendererManager) {
        super(rendererManager, new WaterPotModel(), 0.5F);
    }
}
