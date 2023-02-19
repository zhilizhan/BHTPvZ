package com.zhilizhan.bhtpvz.common.client.render.entity.zombie.add;

import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.zhilizhan.bhtpvz.common.client.model.entity.zombie.add.TargetArrowZombieModel;
import com.zhilizhan.bhtpvz.common.entity.zombie.add.TargetArrowZombieEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class TargetArrowZombieRender extends PVZZombieRender<TargetArrowZombieEntity> {
    public TargetArrowZombieRender(EntityRendererManager rendererManager) {
        super(rendererManager, new TargetArrowZombieModel(), 0.5F);
    }
}