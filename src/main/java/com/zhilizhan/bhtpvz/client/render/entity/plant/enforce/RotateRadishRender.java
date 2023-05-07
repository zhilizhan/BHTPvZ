package com.zhilizhan.bhtpvz.client.render.entity.plant.enforce;

import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.zhilizhan.bhtpvz.client.model.entity.plant.enforce.RotateRadishModel;
import com.zhilizhan.bhtpvz.common.entity.plant.enforce.RotateRadishEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RotateRadishRender extends PVZPlantRender<RotateRadishEntity> {
    public RotateRadishRender(EntityRenderDispatcher rendererManager) {
        super(rendererManager, new RotateRadishModel(), 1.0f);
    }
}