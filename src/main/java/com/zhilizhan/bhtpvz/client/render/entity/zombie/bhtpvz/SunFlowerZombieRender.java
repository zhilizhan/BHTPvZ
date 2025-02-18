package com.zhilizhan.bhtpvz.client.render.entity.zombie.bhtpvz;

import com.hungteen.pvz.client.render.entity.zombie.zombotany.AbstractZombotanyRender;
import com.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz.SunFlowerZombieModel;
import com.zhilizhan.bhtpvz.common.entity.zombie.SunFlowerZombieEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SunFlowerZombieRender extends AbstractZombotanyRender<SunFlowerZombieEntity> {
    public SunFlowerZombieRender(EntityRendererManager rendererManager) {
        super(rendererManager, new SunFlowerZombieModel(), 0.4F);
    }
}
