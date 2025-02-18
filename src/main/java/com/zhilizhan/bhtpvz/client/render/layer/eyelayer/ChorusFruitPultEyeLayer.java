package com.zhilizhan.bhtpvz.client.render.layer.eyelayer;

import com.zhilizhan.bhtpvz.BHTPvZ;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public class ChorusFruitPultEyeLayer<T extends LivingEntity, M extends EntityModel<T>> extends AbstractEyesLayer<T, M> {

    public ChorusFruitPultEyeLayer(IEntityRenderer<T, M> entityRendererIn) {
        super(entityRendererIn);
    }

    private static final RenderType CHORUS_FRUIT_PULT_EYES = RenderType.eyes(BHTPvZ.prefix("textures/entity/layer/eyes/chorus_fruit_pult_eyes.png"));

    @Override
    public RenderType renderType() {
        return CHORUS_FRUIT_PULT_EYES;
    }
}
