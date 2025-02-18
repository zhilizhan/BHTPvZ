package com.zhilizhan.bhtpvz.client.render.layer.eyelayer;

import com.zhilizhan.bhtpvz.BHTPvZ;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WartEyeLayer<T extends LivingEntity, M extends EntityModel<T>> extends AbstractEyesLayer<T, M> {
    public WartEyeLayer(IEntityRenderer<T, M> entityRendererIn) {
        super(entityRendererIn);
    }
    private static final RenderType WART_EYES = RenderType.eyes(BHTPvZ.prefix("textures/entity/layer/eyes/wart_eyes.png"));

    @Override
    public RenderType renderType() {
        return WART_EYES;
    }
}
