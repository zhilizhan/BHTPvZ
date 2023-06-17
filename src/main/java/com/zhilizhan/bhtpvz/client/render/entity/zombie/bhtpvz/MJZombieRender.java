package com.zhilizhan.bhtpvz.client.render.entity.zombie.bhtpvz;

import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.client.render.layer.DancerLightLayer;
import com.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz.MJZombieModel;
import com.zhilizhan.bhtpvz.common.entity.zombie.bhtpvz.MJZombieEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MJZombieRender extends PVZZombieRender<MJZombieEntity> {
    public MJZombieRender(EntityRenderDispatcher rendererManager) {
        super(rendererManager, new MJZombieModel(), 0.5F);
    }

    protected void addZombieLayers() {
        super.addZombieLayers();
        this.addLayer(new DancerLightLayer(this));
    }
}

