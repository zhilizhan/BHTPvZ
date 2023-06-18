package com.zhilizhan.bhtpvz.client.render.entity.bullet;

import com.hungteen.pvz.client.render.entity.bullet.BulletRender;
import com.zhilizhan.bhtpvz.common.entity.bullet.itembullet.FishPeaEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FishPeRender extends BulletRender<FishPeaEntity> {
	public FishPeRender(EntityRenderDispatcher renderManagerIn) {
        super(renderManagerIn);
    }
        @Override
        protected float getScaleByEntity(FishPeaEntity entity) {
        return 0.5f;
    }
}

