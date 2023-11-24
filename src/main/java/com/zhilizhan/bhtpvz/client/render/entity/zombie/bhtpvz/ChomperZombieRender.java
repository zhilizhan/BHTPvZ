package com.zhilizhan.bhtpvz.client.render.entity.zombie.bhtpvz;

import com.hungteen.pvz.client.render.entity.zombie.zombotany.AbstractZombotanyRender;
import com.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz.ChomperZombieModel;
import com.zhilizhan.bhtpvz.common.entity.zombie.bhtpvz.ChomperZombieEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChomperZombieRender  extends AbstractZombotanyRender<ChomperZombieEntity> {
    public ChomperZombieRender(EntityRenderDispatcher rendererManager) {
        super(rendererManager, new ChomperZombieModel(), 0.4F);
    }
}
