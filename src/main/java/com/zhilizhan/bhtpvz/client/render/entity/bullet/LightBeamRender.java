package com.zhilizhan.bhtpvz.client.render.entity.bullet;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.zhilizhan.bhtpvz.common.entity.bullet.LightBeamEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class LightBeamRender extends EntityRenderer<LightBeamEntity> {
    public LightBeamRender(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public void render(LightBeamEntity entity, float entityYaw, float partialTick, MatrixStack matrixStack, IRenderTypeBuffer bufferSource, int packedLight) {
        List<Vector3d> trails = entity.getTrails();
        if (trails.isEmpty()) return;

        int trailColor = entity.getTrailColor();
        float r = (float)((trailColor >> 16) & 0xFF) / 255.0F;
        float g = (float)((trailColor >> 8) & 0xFF) / 255.0F;
        float b = (float)(trailColor & 0xFF) / 255.0F;
        float alpha = 0.8f;

        matrixStack.pushPose();
        Matrix4f matrix = matrixStack.last().pose();
        IVertexBuilder buffer = bufferSource.getBuffer(RenderType.lightning());
        Vector3d entityPos = entity.position();

        for (int i = 1; i < trails.size(); i++) {
            Vector3d prevPos = trails.get(i-1).subtract(entityPos);
            Vector3d currentPos = trails.get(i).subtract(entityPos);

            Vector3d dir = currentPos.subtract(prevPos);
            if (dir.lengthSqr() > 0.001) {
                dir = dir.normalize();

                Vector3d up = new Vector3d(0, 1, 0);
                Vector3d right = dir.cross(up);
                if (right.lengthSqr() < 0.001) {
                    up = new Vector3d(1, 0, 0);
                    right = dir.cross(up);
                }
                right = right.normalize();
                Vector3d forward = dir.cross(right).normalize();

                float width0 = 0.15f * (i-1) / trails.size();
                float width1 = 0.15f * i / trails.size();

                // 计算圆形截面的8个顶点（圆柱体效果）
                for (int j = 0; j < 8; j++) {
                    float angle = j * (float)Math.PI * 2 / 8;
                    float nextAngle = (j+1) * (float)Math.PI * 2 / 8;

                    // 前一截面的两个点
                    Vector3d prevPoint1 = prevPos.add(right.scale(width0 * MathHelper.cos(angle)))
                            .add(forward.scale(width0 * MathHelper.sin(angle)));
                    Vector3d prevPoint2 = prevPos.add(right.scale(width0 * MathHelper.cos(nextAngle)))
                            .add(forward.scale(width0 * MathHelper.sin(nextAngle)));

                    // 当前截面的两个点
                    Vector3d currentPoint1 = currentPos.add(right.scale(width1 * MathHelper.cos(angle)))
                            .add(forward.scale(width1 * MathHelper.sin(angle)));
                    Vector3d currentPoint2 = currentPos.add(right.scale(width1 * MathHelper.cos(nextAngle)))
                            .add(forward.scale(width1 * MathHelper.sin(nextAngle)));

                    // 绘制四边形（构成圆柱体的一个面）
                    buffer.vertex(matrix, (float)prevPoint1.x, (float)prevPoint1.y, (float)prevPoint1.z)
                            .color(r, g, b, alpha).endVertex();
                    buffer.vertex(matrix, (float)prevPoint2.x, (float)prevPoint2.y, (float)prevPoint2.z)
                            .color(r, g, b, alpha).endVertex();
                    buffer.vertex(matrix, (float)currentPoint2.x, (float)currentPoint2.y, (float)currentPoint2.z)
                            .color(r, g, b, alpha).endVertex();
                    buffer.vertex(matrix, (float)currentPoint1.x, (float)currentPoint1.y, (float)currentPoint1.z)
                            .color(r, g, b, alpha).endVertex();
                }
            }
        }
        matrixStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(LightBeamEntity entity) {
        return AtlasTexture.LOCATION_BLOCKS;
    }
}
