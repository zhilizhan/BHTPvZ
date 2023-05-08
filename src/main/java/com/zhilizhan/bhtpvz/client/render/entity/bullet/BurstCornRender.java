package com.zhilizhan.bhtpvz.client.render.entity.bullet;

import com.hungteen.pvz.client.render.entity.PVZEntityRender;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.client.model.entity.bullet.CornModel;
import com.zhilizhan.bhtpvz.common.entity.bullet.CornEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class BurstCornRender  extends PVZEntityRender<CornEntity> {

    private static final ResourceLocation  BURST_CORN_TEX = new ResourceLocation(BHTPvZ.MOD_ID,("textures/entity/misc/burst_corn.png"));

    public BurstCornRender(EntityRenderDispatcher renderManager) {
        super(renderManager, new CornModel());
    }

    public void render(CornEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(partialTicks, entityIn.yRotO, entityIn.yRot) + 180.0F));
        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(partialTicks, entityIn.xRotO, entityIn.xRot)));
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.popPose();
    }

    protected float getScaleByEntity(CornEntity entity) {
        return 1F;
    }

    public ResourceLocation getTextureLocation(CornEntity entity) {
        return  BURST_CORN_TEX;
    }
}
