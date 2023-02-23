package com.github.zhilizhan.bhtpvz.client.render.entity.zombie.add;

import com.github.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz.TargetArrowZombieModel;
import com.github.zhilizhan.bhtpvz.common.entity.zombie.bhtpvz.TargetArrowZombieEntity;

import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;

public class TargetArrowZombieRender extends PVZZombieRender<TargetArrowZombieEntity> {
    public TargetArrowZombieRender(EntityRenderDispatcher rendererManager) {
        super(rendererManager, new TargetArrowZombieModel(), 0.5f);
    }
}
