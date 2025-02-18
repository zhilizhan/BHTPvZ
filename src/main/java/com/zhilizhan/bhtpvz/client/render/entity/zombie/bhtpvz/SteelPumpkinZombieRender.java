package com.zhilizhan.bhtpvz.client.render.entity.zombie.bhtpvz;

import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz.SteelPumpkinZombieModel;
import com.zhilizhan.bhtpvz.common.entity.zombie.SteelPumpkinZombieEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SteelPumpkinZombieRender extends PVZZombieRender<SteelPumpkinZombieEntity> {
    public SteelPumpkinZombieRender(EntityRendererManager rendererManager) {
        super(rendererManager, new SteelPumpkinZombieModel(), 0.6f);
    }
}
