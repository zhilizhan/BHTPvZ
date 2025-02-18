package com.zhilizhan.bhtpvz.client.render.entity.plant.appease;

import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.zhilizhan.bhtpvz.client.model.entity.plant.appease.BeeShooterModel;
import com.zhilizhan.bhtpvz.common.entity.plant.appease.BeeShooterEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BeeShooterRender extends PVZPlantRender<BeeShooterEntity> {
    public BeeShooterRender(EntityRendererManager rendererManager) {
        super(rendererManager, new BeeShooterModel(), 0.4F);
    }
}