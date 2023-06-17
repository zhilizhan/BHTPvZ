package com.zhilizhan.bhtpvz.client.render.entity.plant.arma;

import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.zhilizhan.bhtpvz.client.model.entity.plant.arma.ChorusFruitPultModel;
import com.zhilizhan.bhtpvz.common.entity.plant.arma.ChorusFruitPultEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChorusFruitPultRender extends PVZPlantRender<ChorusFruitPultEntity> {
    public ChorusFruitPultRender(EntityRenderDispatcher rendererManager) {
        super(rendererManager, new ChorusFruitPultModel(), 0.4f);
    }
}