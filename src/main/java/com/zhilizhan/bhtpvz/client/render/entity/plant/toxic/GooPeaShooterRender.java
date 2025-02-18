package com.zhilizhan.bhtpvz.client.render.entity.plant.toxic;

import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.zhilizhan.bhtpvz.client.model.entity.plant.toxic.GooPeaShooterModel;
import com.zhilizhan.bhtpvz.common.entity.plant.toxic.GooPeaShooterEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GooPeaShooterRender extends PVZPlantRender<GooPeaShooterEntity> {
    public GooPeaShooterRender(EntityRendererManager rendererManager) {
        super(rendererManager, new GooPeaShooterModel(), 0.4F);
    }
}

