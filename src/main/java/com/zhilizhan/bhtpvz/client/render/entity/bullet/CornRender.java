package com.zhilizhan.bhtpvz.client.render.entity.bullet;

import com.hungteen.pvz.client.render.entity.PVZEntityRender;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.zhilizhan.bhtpvz.client.model.entity.bullet.CornModel;
import com.zhilizhan.bhtpvz.common.entity.bullet.CornEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CornRender extends PVZEntityRender<CornEntity> {

    private static final ResourceLocation CORN_TEX = StringUtil.prefix("textures/entity/misc/corn.png");

    public CornRender(EntityRendererManager renderManager) {
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
        return 1F;
    }

    public ResourceLocation getTextureLocation(CornEntity entity) {
        return  CORN_TEX;
    }
}
