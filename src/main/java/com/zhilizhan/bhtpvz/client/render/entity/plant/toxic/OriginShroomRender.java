package com.zhilizhan.bhtpvz.client.render.entity.plant.toxic;

import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.AnimationUtil;
import com.zhilizhan.bhtpvz.client.model.entity.plant.toxic.OriginShroomModel;
import com.zhilizhan.bhtpvz.common.entity.plant.toxic.OriginShroomEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OriginShroomRender extends PVZPlantRender<OriginShroomEntity> {

    public OriginShroomRender(EntityRendererManager rendererManager) {
        super(rendererManager, new OriginShroomModel(), 0.75f);
    }
    public float getScaleByEntity(OriginShroomEntity entity) {
        float changeSize = 0.05F;
        float size = super.getScaleByEntity(entity);
        boolean T;
        int tick;
        if (entity.isPlantInSuperMode()) {
            T = true;
            tick = entity.getSuperTime() % 10;
            return size + AnimationUtil.upDown((float)tick, 10.0F, 0.05F);
        } else {
            T = true;
            tick = entity.getShootTick() + 10 - entity.getShootCD();
            return tick >= 0 ? size + AnimationUtil.upDown((float)tick, 10.0F, 0.05F) : size;
        }
    }
}
