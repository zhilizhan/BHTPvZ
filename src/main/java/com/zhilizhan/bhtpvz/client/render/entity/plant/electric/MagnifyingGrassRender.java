package com.zhilizhan.bhtpvz.client.render.entity.plant.electric;

import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.zhilizhan.bhtpvz.client.model.entity.plant.electric.MagnifyingGrassModel;
import com.zhilizhan.bhtpvz.common.entity.plant.electric.MagnifyingGrassEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MagnifyingGrassRender  extends PVZPlantRender<MagnifyingGrassEntity> {
    private static final RenderType BEAM_RENDER_TYPE= RenderType.lightning();
    public MagnifyingGrassRender(EntityRendererManager rendererManager) {
        super(rendererManager, new MagnifyingGrassModel(), 0.5f);
    }

    private Vector3d getPosition(LivingEntity livingEntity, double d, float f) {
        double e = MathHelper.lerp(f, livingEntity.xOld, livingEntity.getX());
        double g = MathHelper.lerp(f, livingEntity.yOld, livingEntity.getY()) + d;
        double h = MathHelper.lerp(f, livingEntity.zOld, livingEntity.getZ());
        return new Vector3d( e,g,h);
    }
    @Override
    public void render(MagnifyingGrassEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
        LivingEntity lv = entity.getActiveAttackTarget();
        if (lv != null) {
            float j = (float)entity.level.getGameTime() + partialTicks;
            float k = j * 0.5F % 1.0F;
            float l = entity.getEyeHeight();
            matrixStack.pushPose();
            matrixStack.translate(0.0, l, 0.0);
            Vector3d lv2 = this.getPosition(lv, (double)lv.getBbHeight() * 0.5, partialTicks);
            Vector3d lv3 = this.getPosition(entity, l, partialTicks);
            Vector3d lv4 = lv2.subtract(lv3);
            float m = (float)(lv4.length() + 1.0);
            lv4 = lv4.normalize();
            float n = (float)Math.acos(lv4.y);
            float o = (float)Math.atan2(lv4.z, lv4.x);
            matrixStack.mulPose(Vector3f.YP.rotationDegrees((1.5707964F - o) * 57.295776F));
            matrixStack.mulPose(Vector3f.XP.rotationDegrees(n * 57.295776F));
            float q = j * 0.05F * -1.5F;
            float r = 1;
            int s = 64 + (int)(r * 191.0F);
            int t = 64 + (int)(r * 191.0F);
            int u = 128 - (int)(r * 64.0F);

            float x = MathHelper.cos(q + 2.3561945F) * 0.282F;
            float y = MathHelper.sin(q + 2.3561945F) * 0.282F;
            float z = MathHelper.cos(q + 0.7853982F) * 0.282F;
            float aa = MathHelper.sin(q + 0.7853982F) * 0.282F;
            float ab = MathHelper.cos(q + 3.926991F) * 0.282F;
            float ac = MathHelper.sin(q + 3.926991F) * 0.282F;
            float ad = MathHelper.cos(q + 5.4977875F) * 0.282F;
            float ae = MathHelper.sin(q + 5.4977875F) * 0.282F;
            float af = MathHelper.cos(q + 3.1415927F) * 0.2F;
            float ag = MathHelper.sin(q + 3.1415927F) * 0.2F;
            float ah = MathHelper.cos(q + 0.0F) * 0.2F;
            float ai = MathHelper.sin(q + 0.0F) * 0.2F;
            float aj = MathHelper.cos(q + 1.5707964F) * 0.2F;
            float ak = MathHelper.sin(q + 1.5707964F) * 0.2F;
            float al = MathHelper.cos(q + 4.712389F) * 0.2F;
            float am = MathHelper.sin(q + 4.712389F) * 0.2F;
            float aq = -1.0F + k;
            float ar = m * 2.5F + aq;
            IVertexBuilder lv5 = buffer.getBuffer(BEAM_RENDER_TYPE);
            MatrixStack.Entry lv6 = matrixStack.last();
            Matrix4f lv7 = lv6.pose();
            Matrix3f lv8 = lv6.normal();
            vertex(lv5, lv7, lv8, af, m, ag, s, t, u, 0.4999F, ar);
            vertex(lv5, lv7, lv8, af, 0.0F, ag, s, t, u, 0.4999F, aq);
            vertex(lv5, lv7, lv8, ah, 0.0F, ai, s, t, u, 0.0F, aq);
            vertex(lv5, lv7, lv8, ah, m, ai, s, t, u, 0.0F, ar);
            vertex(lv5, lv7, lv8, aj, m, ak, s, t, u, 0.4999F, ar);
            vertex(lv5, lv7, lv8, aj, 0.0F, ak, s, t, u, 0.4999F, aq);
            vertex(lv5, lv7, lv8, al, 0.0F, am, s, t, u, 0.0F, aq);
            vertex(lv5, lv7, lv8, al, m, am, s, t, u, 0.0F, ar);
            float as = 0.0F;
            if (entity.tickCount % 2 == 0) {
                as = 0.5F;
            }

            vertex(lv5, lv7, lv8, x, m, y, s, t, u, 0.5F, as + 0.5F);
            vertex(lv5, lv7, lv8, z, m, aa, s, t, u, 1.0F, as + 0.5F);
            vertex(lv5, lv7, lv8, ad, m, ae, s, t, u, 1.0F, as);
            vertex(lv5, lv7, lv8, ab, m, ac, s, t, u, 0.5F, as);
            matrixStack.popPose();
        }
    }
    private static void vertex(IVertexBuilder arg, Matrix4f arg2, Matrix3f arg3, float f, float g, float h, int i, int j, int k, float l, float m) {
        arg.vertex(arg2, f, g, h).color(i, j, k, 255).uv(l, m).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(arg3, 0.0F, 1.0F, 0.0F).endVertex();
    }
}