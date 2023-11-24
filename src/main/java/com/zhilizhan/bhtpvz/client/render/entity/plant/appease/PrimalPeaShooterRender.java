package com.zhilizhan.bhtpvz.client.render.entity.plant.appease;

import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.zhilizhan.bhtpvz.client.model.entity.plant.appease.PrimalPeaShooterModel;
import com.zhilizhan.bhtpvz.common.entity.plant.appease.PrimalPeaShooterEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PrimalPeaShooterRender extends PVZPlantRender<PrimalPeaShooterEntity> {
    public PrimalPeaShooterRender(EntityRenderDispatcher rendererManager) {
        super(rendererManager, new PrimalPeaShooterModel(), 0.4F);
    }
}

