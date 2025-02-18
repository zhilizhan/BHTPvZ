package com.zhilizhan.bhtpvz.client.render.entity.bullet;

import com.hungteen.pvz.client.render.entity.bullet.BulletRender;
import com.zhilizhan.bhtpvz.common.entity.bullet.itembullet.GooPeaEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GooPeaRender extends BulletRender<GooPeaEntity> {
    public GooPeaRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }
    @Override
    protected float getScaleByEntity(GooPeaEntity entity) {
        return 1.4f;
    }
}
