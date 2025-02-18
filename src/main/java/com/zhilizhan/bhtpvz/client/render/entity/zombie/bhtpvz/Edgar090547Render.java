package com.zhilizhan.bhtpvz.client.render.entity.zombie.bhtpvz;

import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz.Edgar090547Model;
import com.zhilizhan.bhtpvz.common.entity.zombie.Edgar090547Entity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class Edgar090547Render extends PVZZombieRender<Edgar090547Entity> {
	public Edgar090547Render(EntityRendererManager rendererManager) {
		super(rendererManager, new Edgar090547Model<>(), 1.0f);
	}
}
