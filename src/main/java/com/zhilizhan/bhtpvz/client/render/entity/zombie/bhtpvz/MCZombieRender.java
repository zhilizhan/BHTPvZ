package com.zhilizhan.bhtpvz.client.render.entity.zombie.bhtpvz;

import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz.MCZombieModel;
import com.zhilizhan.bhtpvz.common.entity.zombie.MCZombieEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MCZombieRender extends PVZZombieRender<MCZombieEntity> {
	public MCZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new MCZombieModel(), 0.5f);
	}
}
