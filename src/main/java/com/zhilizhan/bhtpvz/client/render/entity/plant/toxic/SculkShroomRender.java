package com.zhilizhan.bhtpvz.client.render.entity.plant.toxic;

import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.AnimationUtil;
import com.zhilizhan.bhtpvz.client.model.entity.plant.toxic.SculkShroomModel;
import com.zhilizhan.bhtpvz.common.entity.plant.toxic.SculkShroomEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SculkShroomRender extends PVZPlantRender<SculkShroomEntity> {

    public SculkShroomRender(EntityRendererManager rendererManager) {
        super(rendererManager, new SculkShroomModel(), 0.75f);
    }
    public float getScaleByEntity(SculkShroomEntity entity) {
        float changeSize = 0.1F;
        float size = super.getScaleByEntity(entity);
        int T;
        int tick;
        if (entity.isPlantInSuperMode()) {
            T = entity.getShootCD();
            tick = entity.getSuperTime() % T;
            return size + AnimationUtil.upDown((float)tick, (float)T, 0.1F);
        } else {
            T = entity.getShootCD();
            tick = entity.getShootTick() + T - entity.getShootCD();
            return tick >= 0 ? size + AnimationUtil.upDown((float)tick, (float)T, 0.1F) : size;
        }
    }
}
