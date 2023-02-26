package com.zhilizhan.bhtpvz.client.render.entity.zombie.add;

import com.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz.Edgar090547Model;
import com.zhilizhan.bhtpvz.common.entity.zombie.bhtpvz.Edgar090547Entity;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class Edgar090547Render extends PVZZombieRender<Edgar090547Entity> {
	public Edgar090547Render(EntityRenderDispatcher rendererManager) {
		super(rendererManager, new Edgar090547Model<>(), 1.0f);
	}
}
