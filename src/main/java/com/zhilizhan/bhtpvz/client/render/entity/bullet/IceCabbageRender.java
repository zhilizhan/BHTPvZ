package com.zhilizhan.bhtpvz.client.render.entity.bullet;

import com.zhilizhan.bhtpvz.common.entity.bullet.itembullet.IceCabbageEntity;

import com.hungteen.pvz.client.render.entity.bullet.BulletRender;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
@OnlyIn(Dist.CLIENT)
public class IceCabbageRender extends BulletRender<IceCabbageEntity> {
	public IceCabbageRender(EntityRenderDispatcher renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	protected float getScaleByEntity(IceCabbageEntity entity) {
		return 1.4f;
	}
}
