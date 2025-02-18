package com.zhilizhan.bhtpvz.client.render.entity.plant.enforce;

import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.zhilizhan.bhtpvz.client.model.entity.plant.enforce.RotateRadishModel;
import com.zhilizhan.bhtpvz.common.entity.plant.enforce.RotateRadishEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RotateRadishRender extends PVZPlantRender<RotateRadishEntity> {
    public RotateRadishRender(EntityRendererManager rendererManager) {
        super(rendererManager, new RotateRadishModel(), 0.6f);
    }
}