package com.zhilizhan.bhtpvz.client.render.entity.bullet;

import com.zhilizhan.bhtpvz.common.entity.bullet.SonicEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SonicRender extends EntityRenderer<SonicEntity> {
    public SonicRender(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public ResourceLocation  getTextureLocation(SonicEntity entity) {
        return null;
    }
}
