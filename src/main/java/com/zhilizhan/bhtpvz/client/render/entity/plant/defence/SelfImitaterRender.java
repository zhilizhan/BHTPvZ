package com.zhilizhan.bhtpvz.client.render.entity.plant.defence;

import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.zhilizhan.bhtpvz.client.model.entity.plant.defence.SelfImitaterModel;
import com.zhilizhan.bhtpvz.common.entity.plant.defence.SelfImitaterEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SelfImitaterRender extends PVZPlantRender<SelfImitaterEntity> {
    public SelfImitaterRender(EntityRendererManager rendererManager) {
        super(rendererManager, new SelfImitaterModel(), 0.5F);
    }
}