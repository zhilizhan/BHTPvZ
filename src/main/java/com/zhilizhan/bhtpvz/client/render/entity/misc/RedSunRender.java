package com.zhilizhan.bhtpvz.client.render.entity.misc;

import com.hungteen.pvz.common.entity.misc.drop.SunEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.common.entity.misc.RedSunEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RedSunRender extends EntityRenderer<RedSunEntity> {
    private static final ResourceLocation RED_SUN_LOCATION = BHTPvZ.prefix("textures/entity/misc/red_sun.png");
    private static final RenderType RENDER_TYPE;

    public RedSunRender(EntityRenderDispatcher p_174110_) {
        super(p_174110_);
        this.shadowRadius = 0.15F;
        this.shadowStrength = 0.75F;
    }

    protected int getBlockLightLevel(SunEntity p_114606_, BlockPos p_114607_) {
        return Mth.clamp(super.getBlockLightLevel((RedSunEntity) p_114606_, p_114607_) + 7, 0, 15);
    }

    public void render(RedSunEntity sun, float p_114600_, float partialTicks, PoseStack stack, MultiBufferSource bufferSource, int p_114604_) {
        stack.pushPose();
        int i = sun.getIcon();
        float x = (float)(i % 2 * 32 + 16) / 64.0F;
        float y = (float)(i / 2 * 32 + 16) / 64.0F;
        float tick = ((float)sun.tickCount + partialTicks) / 20.0F + 0.79F;
        int red = (int)(sun.ColorBase.x + (double)Mth.sin(tick) * sun.ColorChange.x);
        int green = (int)(sun.ColorBase.y + (double)Mth.sin(tick) * sun.ColorChange.y);
        int blue = (int)(sun.ColorBase.z + (double)Mth.sin(tick) * sun.ColorChange.z);
        int tmp = sun.getMaxLiveTick() - sun.tickCount;
        int alpha;
        if (tmp >= 90) {
            alpha = 255;
        } else if (tmp > 10) {
            alpha = 159 - (int)(Mth.sin((float)((double)(tmp + 5) / 3.183)) * 96.0F);
        } else {
            alpha = 127 - (int)(Mth.sin((float)((double)(tmp + 5) / 3.183)) * 127.0F);
        }

        stack.translate(0.0, 0.10000000149011612, 0.0);
        stack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        stack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
        float size = 0.4F;
        stack.scale(0.4F, 0.4F, 0.4F);
        VertexConsumer vertexconsumer = bufferSource.getBuffer(RENDER_TYPE);
        PoseStack.Pose posestack$pose = stack.last();
        Matrix4f matrix4f = posestack$pose.pose();
        Matrix3f matrix3f = posestack$pose.normal();
        vertex(vertexconsumer, matrix4f, matrix3f, -1.0F, -1.0F, red, green, blue, x + 12.01F * Mth.cos(tick) / 64.0F, y + 12.01F * Mth.sin(tick) / 64.0F, p_114604_, alpha);
        vertex(vertexconsumer, matrix4f, matrix3f, 1.0F, -1.0F, red, green, blue, x + 12.01F * Mth.cos(tick + 1.57F) / 64.0F, y + 12.01F * Mth.sin(tick + 1.57F) / 64.0F, p_114604_, alpha);
        vertex(vertexconsumer, matrix4f, matrix3f, 1.0F, 1.0F, red, green, blue, x - 12.01F * Mth.cos(tick) / 64.0F, y - 12.01F * Mth.sin(tick) / 64.0F, p_114604_, alpha);
        vertex(vertexconsumer, matrix4f, matrix3f, -1.0F, 1.0F, red, green, blue, x - 12.01F * Mth.cos(tick + 1.57F) / 64.0F, y - 12.01F * Mth.sin(tick + 1.57F) / 64.0F, p_114604_, alpha);
        stack.popPose();
        super.render((RedSunEntity) sun, p_114600_, partialTicks, stack, bufferSource, p_114604_);
    }

    private static void vertex(VertexConsumer p_114609_, Matrix4f p_114610_, Matrix3f p_114611_, float p_114612_, float p_114613_, int p_114614_, int p_114615_, int p_114616_, float p_114617_, float p_114618_, int p_114619_, int alpha) {
        p_114609_.vertex(p_114610_, p_114612_, p_114613_ + 0.5F, 0.0F).color(p_114614_, p_114615_, p_114616_, alpha).uv(p_114617_, p_114618_).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(p_114619_).normal(p_114611_, 0.0F, 1.0F, 0.0F).endVertex();
    }

    @Override
    public ResourceLocation getTextureLocation(RedSunEntity entity) {
        return null;
    }
    static {
        RENDER_TYPE = RenderType.itemEntityTranslucentCull(RED_SUN_LOCATION);
    }
}
