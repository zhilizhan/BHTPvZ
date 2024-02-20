package com.zhilizhan.bhtpvz.client.render.layer.eyelayer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class PVZEyeLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    protected RenderLayerParent<T, M> entityRender;
    protected EntityModel<T> entityModel;
    protected float scale = 1.0F;

    public PVZEyeLayer(RenderLayerParent<T, M> entityRendererIn) {
        super(entityRendererIn);
        this.entityRender = entityRendererIn;
        this.entityModel = this.entityRender.getModel();
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (this.canRender(entitylivingbaseIn)) {
            matrixStackIn.pushPose();
            matrixStackIn.scale(this.scale, this.scale, this.scale);
            VertexConsumer ivertexbuilder = bufferIn.getBuffer(RenderType.energySwirl(this.getResourceLocation(entitylivingbaseIn), 0, 0));
            this.entityModel.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 0.5F, 0.5F, 0.5F, 1.0F);
            matrixStackIn.popPose();
        }

    }

    protected abstract boolean canRender(T var1);

    protected abstract ResourceLocation getResourceLocation(T var1);
}
