package com.zhilizhan.bhtpvz.client.render.entity.zombie.add;

import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz.RedEdgeZombieModel;
import com.zhilizhan.bhtpvz.common.entity.zombie.bhtpvz.RedEdgeZombieEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RedEdgeZombieRender extends PVZZombieRender<RedEdgeZombieEntity> {
    public RedEdgeZombieRender(EntityRenderDispatcher rendererManager) {
        super(rendererManager, new RedEdgeZombieModel(), 1.0f);
    }
}
