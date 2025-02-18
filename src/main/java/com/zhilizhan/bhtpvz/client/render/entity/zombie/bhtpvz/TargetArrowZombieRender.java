package com.zhilizhan.bhtpvz.client.render.entity.zombie.bhtpvz;

import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz.TargetArrowZombieModel;
import com.zhilizhan.bhtpvz.common.entity.zombie.TargetArrowZombieEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class TargetArrowZombieRender extends PVZZombieRender<TargetArrowZombieEntity> {
    public TargetArrowZombieRender(EntityRendererManager rendererManager) {
        super(rendererManager, new TargetArrowZombieModel(), 0.5f);
    }
}
