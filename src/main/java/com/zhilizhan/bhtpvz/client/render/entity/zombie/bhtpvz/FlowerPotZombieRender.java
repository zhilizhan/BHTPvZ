package com.zhilizhan.bhtpvz.client.render.entity.zombie.bhtpvz;


import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz.FlowerPotZombieModel;
import com.zhilizhan.bhtpvz.common.entity.zombie.FlowerPotZombieEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FlowerPotZombieRender extends PVZZombieRender<FlowerPotZombieEntity> {
	public FlowerPotZombieRender(EntityRendererManager rendererManager){
		super(rendererManager, new FlowerPotZombieModel(), 0.5f);
	}
}
