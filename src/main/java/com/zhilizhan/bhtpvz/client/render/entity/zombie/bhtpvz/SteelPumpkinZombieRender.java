package com.zhilizhan.bhtpvz.client.render.entity.zombie.bhtpvz;

import com.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz.SteelPumpkinZombieModel;
import com.zhilizhan.bhtpvz.common.entity.zombie.bhtpvz.SteelPumpkinZombieEntity;

import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SteelPumpkinZombieRender extends PVZZombieRender<SteelPumpkinZombieEntity> {
    public SteelPumpkinZombieRender(EntityRenderDispatcher rendererManager) {
        super(rendererManager, new SteelPumpkinZombieModel(), 1.0f);
    }
}
