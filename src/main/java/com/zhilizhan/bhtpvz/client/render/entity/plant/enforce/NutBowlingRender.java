package com.zhilizhan.bhtpvz.client.render.entity.plant.enforce;

import com.hungteen.pvz.client.model.entity.plant.defence.WallNutModel;
import com.hungteen.pvz.common.entity.plant.defence.WallNutEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.zhilizhan.bhtpvz.common.entity.plant.enforce.NutBowlingEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NutBowlingRender <T extends NutBowlingEntity> extends EntityRenderer<T> {
    protected final EntityModel<WallNutEntity> model = new WallNutModel();

    public NutBowlingRender(EntityRenderDispatcher renderManager) {
        super(renderManager);
    }

    public void render(T entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.pushPose();
        matrixStackIn.scale(-1.0F, -1.0F, 1.0F);
        float f = this.getRenderSize(entityIn);
        matrixStackIn.scale(f, f, f);
        matrixStackIn.translate(0.0, -0.0, 0.0);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(partialTicks, entityIn.yRotO, entityIn.yRot) + 0.0F));
        matrixStackIn.mulPose(Vector3f.XN.rotationDegrees((float)(-entityIn.tickCount * 15)));
        VertexConsumer ivertexbuilder = bufferIn.getBuffer(this.model.renderType(this.getTextureLocation(entityIn)));
        this.model.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStackIn.popPose();
    }

    protected float getRenderSize(T entity) {
        return 1.0F;
    }

    public ResourceLocation getTextureLocation(T entity) {
        return PVZPlants.WALL_NUT.getRenderResource();
    }
}
