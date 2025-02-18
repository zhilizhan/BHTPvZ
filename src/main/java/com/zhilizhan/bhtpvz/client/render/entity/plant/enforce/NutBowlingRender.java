package com.zhilizhan.bhtpvz.client.render.entity.plant.enforce;

import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.zhilizhan.bhtpvz.client.model.entity.plant.enforce.NutBowlingModel;
import com.zhilizhan.bhtpvz.common.entity.plant.enforce.NutBowlingEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NutBowlingRender extends PVZPlantRender<NutBowlingEntity> {

    public NutBowlingRender(EntityRendererManager rendererManager) {
        super(rendererManager, new NutBowlingModel(), 0.4f);
    }

    public ResourceLocation getTextureLocation(NutBowlingEntity entity) {
        return PVZPlants.WALL_NUT.getRenderResource();
    }
}
