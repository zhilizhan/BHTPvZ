package com.zhilizhan.bhtpvz.client.render.entity.plant.fire;

import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.zhilizhan.bhtpvz.client.model.entity.plant.fire.FirePeashooterModel;
import com.zhilizhan.bhtpvz.common.entity.plant.fire.FirePeashooterEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FirePeashooterRender extends PVZPlantRender<FirePeashooterEntity> {
    public FirePeashooterRender(EntityRendererManager rendererManager) {
        super(rendererManager, new FirePeashooterModel(), 0.4f);
    }
}
