package com.zhilizhan.bhtpvz.client.render.entity.bullet;

import com.hungteen.pvz.client.render.entity.bullet.BulletRender;
import com.zhilizhan.bhtpvz.common.entity.bullet.itembullet.IcePeaEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IcePeaRender extends BulletRender<IcePeaEntity> {
	public IcePeaRender(EntityRenderDispatcher renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	protected float getScaleByEntity(IcePeaEntity entity) {
		return 1.4f;
	}
}
