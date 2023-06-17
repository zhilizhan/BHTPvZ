package com.zhilizhan.bhtpvz.client.render.entity.zombie.bhtpvz;

import com.hungteen.pvz.client.render.entity.zombie.zombotany.AbstractZombotanyRender;
import com.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz.SunFlowerZombieModel;
import com.zhilizhan.bhtpvz.common.entity.zombie.bhtpvz.SunFlowerZombieEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;

public class SunFlowerZombieRender extends AbstractZombotanyRender<SunFlowerZombieEntity> {
    public SunFlowerZombieRender(EntityRenderDispatcher rendererManager) {
        super(rendererManager, new SunFlowerZombieModel(), 0.4F);
    }
}
