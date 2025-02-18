package com.zhilizhan.bhtpvz.client.render.entity.bullet;

import com.hungteen.pvz.client.render.entity.bullet.BulletRender;
import com.zhilizhan.bhtpvz.common.entity.bullet.itembullet.PopCornEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT)
public class PopCornRender extends BulletRender<PopCornEntity> {
    public PopCornRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    protected float getScaleByEntity(PopCornEntity entity) {
        return 1.2f;
    }
}
