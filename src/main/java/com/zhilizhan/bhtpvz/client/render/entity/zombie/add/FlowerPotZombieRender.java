package com.zhilizhan.bhtpvz.client.render.entity.zombie.add;


import com.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz.FlowerPotZombieModel;
import com.zhilizhan.bhtpvz.common.entity.zombie.bhtpvz.FlowerPotZombieEntity;

import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FlowerPotZombieRender extends PVZZombieRender<FlowerPotZombieEntity> {
	public FlowerPotZombieRender(EntityRenderDispatcher rendererManager){
		super(rendererManager, new FlowerPotZombieModel(), 0.5f);
	}
}
