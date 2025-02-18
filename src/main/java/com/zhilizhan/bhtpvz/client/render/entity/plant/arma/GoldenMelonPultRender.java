package com.zhilizhan.bhtpvz.client.render.entity.plant.arma;

import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.zhilizhan.bhtpvz.client.model.entity.plant.arma.GoldenMelonPultModel;
import com.zhilizhan.bhtpvz.common.entity.plant.arma.GoldenMelonPultEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GoldenMelonPultRender extends PVZPlantRender<GoldenMelonPultEntity> {
    public GoldenMelonPultRender(EntityRendererManager rendererManager) {
        super(rendererManager, new GoldenMelonPultModel(), 0.5F);
    }
}