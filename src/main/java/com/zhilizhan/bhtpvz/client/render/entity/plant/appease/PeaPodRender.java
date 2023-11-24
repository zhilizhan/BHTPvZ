package com.zhilizhan.bhtpvz.client.render.entity.plant.appease;

import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.zhilizhan.bhtpvz.client.model.entity.plant.appease.PeaPodModel;
import com.zhilizhan.bhtpvz.common.entity.plant.appease.PeaPodEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PeaPodRender extends PVZPlantRender<PeaPodEntity> {

    public PeaPodRender(EntityRenderDispatcher arg) {
        super(arg, new PeaPodModel(), 0.6F);
    }

}

