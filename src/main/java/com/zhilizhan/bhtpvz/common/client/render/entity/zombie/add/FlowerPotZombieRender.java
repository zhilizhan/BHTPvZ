package com.zhilizhan.bhtpvz.common.client.render.entity.zombie.add;


import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.zhilizhan.bhtpvz.common.client.model.entity.zombie.add.FlowerPotZombieModel;
import com.zhilizhan.bhtpvz.common.entity.zombie.add.FlowerPotZombieEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FlowerPotZombieRender extends PVZZombieRender<FlowerPotZombieEntity> {
	
	public FlowerPotZombieRender(EntityRendererManager rendererManager){
		super(rendererManager, new FlowerPotZombieModel(), 0.5F);
	}

}
