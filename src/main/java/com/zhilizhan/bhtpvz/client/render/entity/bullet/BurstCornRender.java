package com.zhilizhan.bhtpvz.client.render.entity.bullet;

import com.hungteen.pvz.client.render.entity.PVZEntityRender;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.client.model.entity.bullet.CornModel;
import com.zhilizhan.bhtpvz.common.entity.bullet.CornEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class BurstCornRender  extends PVZEntityRender<CornEntity> {

    private static final ResourceLocation BURST_CORN_TEX = new ResourceLocation(BHTPvZ.MOD_ID,("textures/entity/misc/burst_corn.png"));

    public BurstCornRender(EntityRendererManager renderManager) {
        super(renderManager, new CornModel());
    }

    public void render(CornEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.yRotO, entityIn.yRot) + 180.0F));
        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.xRotO, entityIn.xRot)));
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.popPose();
    }

    protected float getScaleByEntity(CornEntity entity) {
        return 0.75F;
    }

    public ResourceLocation getTextureLocation(CornEntity entity) {
        return  BURST_CORN_TEX;
    }
}
