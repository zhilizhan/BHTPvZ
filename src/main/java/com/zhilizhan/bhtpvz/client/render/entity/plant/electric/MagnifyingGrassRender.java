package com.zhilizhan.bhtpvz.client.render.entity.plant.electric;

import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.EntityUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.zhilizhan.bhtpvz.client.model.entity.plant.electric.MagnifyingGrassModel;
import com.zhilizhan.bhtpvz.common.entity.plant.electric.MagnifyingGrassEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MagnifyingGrassRender  extends PVZPlantRender<MagnifyingGrassEntity> {
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
    public boolean shouldRender(MagnifyingGrassEntity entity, ClippingHelper p_225626_2_, double p_225626_3_, double p_225626_5_, double p_225626_7_) {
        if (super.shouldRender(entity, p_225626_2_, p_225626_3_, p_225626_5_, p_225626_7_)) {
            return true;
        } else {
            if (entity.hasActiveAttackTarget()) {
                LivingEntity lvt_9_1_ = entity.getActiveAttackTarget();
                if (lvt_9_1_ != null) {
                    Vector3d lvt_10_1_ = this.getPosition(lvt_9_1_, (double)lvt_9_1_.getBbHeight() * 0.5, 1.0F);
                    Vector3d lvt_11_1_ = this.getPosition(entity, (double)entity.getEyeHeight(), 1.0F);
                    return p_225626_2_.isVisible(new AxisAlignedBB(lvt_11_1_.x, lvt_11_1_.y, lvt_11_1_.z, lvt_10_1_.x, lvt_10_1_.y, lvt_10_1_.z));
                }
            }
            return false;
        }
    }

    @Override
    public void render(MagnifyingGrassEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
        LivingEntity target = entity.getActiveAttackTarget();
        if (EntityUtil.isEntityValid(target) && entity.isInSuperState()) {
            float eyeHeight = entity.getEyeHeight() * 0.75F;
            matrixStack.pushPose();
            matrixStack.translate(0.0, eyeHeight, 0.0);

            Vector3d targetPos = this.getPosition(target, target.getBbHeight() * 0.5, partialTicks);
            Vector3d entityPos = this.getPosition(entity, eyeHeight, partialTicks);
            Vector3d direction = targetPos.subtract(entityPos);

            float length = (float)direction.length() + 1.0F;
            direction = direction.normalize();

            float pitch = (float)Math.acos(direction.y);
            float yaw = (float)Math.atan2(direction.z, direction.x);
            matrixStack.mulPose(Vector3f.YP.rotationDegrees((1.5707964F - yaw) * 57.295776F));
            matrixStack.mulPose(Vector3f.XP.rotationDegrees(pitch * 57.295776F));

            float beamAngle = -0.075F;
            int red = 255, green = 255, blue = 128;

            // 计算8个方向的顶点(确保全角度兼容)
            float[][] vertices = new float[8][2];
            for (int i = 0; i < 8; i++) {
                float angle = beamAngle + i * (float)Math.PI / 4;
                vertices[i][0] = MathHelper.cos(angle) * (i % 2 == 0 ? 0.282F : 0.2F);
                vertices[i][1] = MathHelper.sin(angle) * (i % 2 == 0 ? 0.282F : 0.2F);
            }

            IVertexBuilder vertexBuilder = buffer.getBuffer(RenderType.lightning());
            MatrixStack.Entry matrixEntry = matrixStack.last();
            Matrix4f pose = matrixEntry.pose();
            Matrix3f normal = matrixEntry.normal();

            // 绘制光束主体(8边形，确保全角度)
            float endWidth = -1.0F;
            float beamLength = length * 2.5F + endWidth;

            for (int i = 0; i < 8; i++) {
                int next = (i + 1) % 8;
                vertex(vertexBuilder, pose, normal,
                        vertices[i][0], length, vertices[i][1],
                        red, green, blue,
                        i % 2 == 0 ? 0.4999F : 0.0F, beamLength);
                vertex(vertexBuilder, pose, normal,
                        vertices[i][0], 0.0F, vertices[i][1],
                        red, green, blue,
                        i % 2 == 0 ? 0.4999F : 0.0F, endWidth);
                vertex(vertexBuilder, pose, normal,
                        vertices[next][0], 0.0F, vertices[next][1],
                        red, green, blue,
                        next % 2 == 0 ? 0.4999F : 0.0F, endWidth);
                vertex(vertexBuilder, pose, normal,
                        vertices[next][0], length, vertices[next][1],
                        red, green, blue,
                        next % 2 == 0 ? 0.4999F : 0.0F, beamLength);
            }

            float flicker = entity.tickCount % 2 == 0 ? 0.5F : 0.0F;

            // 绘制光束末端特效(4个顶点)
            for (int i = 0; i < 4; i++) {
                float u = i < 2 ? 0.5F + flicker : flicker;
                float v = i % 2 == 0 ? 0.5F : 1.0F;
                vertex(vertexBuilder, pose, normal,
                        vertices[i*2][0], length, vertices[i*2][1],
                        red, green, blue, v, u);
            }
            matrixStack.popPose();
        }
    }

    private static void vertex(IVertexBuilder arg, Matrix4f arg2, Matrix3f arg3, float f, float g, float h, int i, int j, int k, float l, float m) {
        arg.vertex(arg2, f, g, h).color(i, j, k, 255).uv(l, m).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(arg3, 0.0F, 1.0F, 0.0F).endVertex();
    }
}