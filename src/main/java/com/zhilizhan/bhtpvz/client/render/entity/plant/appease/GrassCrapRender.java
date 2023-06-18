package com.zhilizhan.bhtpvz.client.render.entity.plant.appease;

import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.zhilizhan.bhtpvz.client.model.entity.plant.appease.GrassCarpModel;
import com.zhilizhan.bhtpvz.common.entity.plant.appease.GrassCarpEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GrassCrapRender extends PVZPlantRender<GrassCarpEntity> {

    public GrassCrapRender(EntityRenderDispatcher arg) {
        super(arg, new GrassCarpModel(), 0.6F);
    }

}
