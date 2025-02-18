package com.zhilizhan.bhtpvz.client.render.entity.zombie.bhtpvz;

import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz.AirborneZombieModel;
import com.zhilizhan.bhtpvz.common.entity.zombie.AirborneZombieEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.world.LightType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AirborneZombieRender extends PVZZombieRender<AirborneZombieEntity> {

	public AirborneZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new AirborneZombieModel(), 0);
	}

	@Override
	public void render(AirborneZombieEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		final boolean hasLine = entity.hasLine();
		if (hasLine) this.renderLine(entity, partialTicks, matrixStackIn, bufferIn);
	}

	private void renderLine(AirborneZombieEntity entityLivingIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn) {
		matrixStackIn.pushPose();
		double d0 = MathHelper.lerp(partialTicks * 0.5f, 0, 0) * ((float)Math.PI / 180f);
		double d1 = MathHelper.lerp(partialTicks * 0.5f, 0, 0) * ((float)Math.PI / 180f);
		double d2 = Math.cos(d0);
		double d3 = Math.sin(d0);
		double d4 = Math.sin(d1);
		double d5 = Math.cos(d1);
		double d6 = entityLivingIn.getX() - d2 * 0.7d - d3 * 0.5d * d5;
		double d7 = entityLivingIn.getY()+50 - d4 * 0.5d - 0.25d;
		double d8 = entityLivingIn.getZ() - d3 * 0.7d + d2 * 0.5d * d5;
		double d9 = (double)(MathHelper.lerp(partialTicks, entityLivingIn.yBodyRot, entityLivingIn.yBodyRotO) * ((float)Math.PI / 180f)) + (Math.PI / 2d);
		d2 = Math.cos(d9) * (double)entityLivingIn.getBbWidth() * 0.4d;
		d3 = Math.sin(d9) * (double)entityLivingIn.getBbWidth() * 0.4d;
		double d10 = MathHelper.lerp(partialTicks, entityLivingIn.xo, entityLivingIn.getX()) + d2;
		double d11 = MathHelper.lerp(partialTicks, entityLivingIn.yo, entityLivingIn.getY());
		double d12 = MathHelper.lerp(partialTicks, entityLivingIn.zo, entityLivingIn.getZ()) + d3;
		matrixStackIn.translate(d2, -(1.0f - (double)entityLivingIn.getBbHeight()), d3);
		float f = (float)(d6 - d10);
		float f1 = (float)(d7 - d11);
		float f2 = (float)(d8 - d12);
		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.leash());
		Matrix4f matrix4f = matrixStackIn.last().pose();
		float f4 = MathHelper.fastInvSqrt(f * f + f2 * f2) * 0.025f / 2.0f;
		float f5 = f2 * f4;
		float f6 = f * f4;
		int i = this.getBlockLightLevel(entityLivingIn, new BlockPos(entityLivingIn.getEyePosition(partialTicks)));
		int j = entityLivingIn.level.getBrightness(LightType.BLOCK, new BlockPos(entityLivingIn.getEyePosition(partialTicks)));
		int k = entityLivingIn.level.getBrightness(LightType.SKY, new BlockPos(entityLivingIn.getEyePosition(partialTicks)));
		int l = entityLivingIn.level.getBrightness(LightType.SKY, new BlockPos(entityLivingIn.getEyePosition(partialTicks)));
		renderSide(ivertexbuilder, matrix4f, f, f1, f2, i, j, k, l, 0.025f, 0.025f, f5, f6);
		renderSide(ivertexbuilder, matrix4f, f, f1, f2, i, j, k, l, 0.025f, 0.0f, f5, f6);
		matrixStackIn.popPose();
	}
}
