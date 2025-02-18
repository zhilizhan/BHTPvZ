package com.zhilizhan.bhtpvz.client.render.entity.bullet;

import com.hungteen.pvz.client.render.entity.bullet.BulletRender;
import com.zhilizhan.bhtpvz.common.entity.bullet.itembullet.OriginFumeEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OriginFumeRender  extends BulletRender<OriginFumeEntity> {
    public OriginFumeRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    protected float getScaleByEntity(OriginFumeEntity entity) {
        return 1.0F;
    }
}
