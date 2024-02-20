package com.zhilizhan.bhtpvz.client.render.entity.bullet;

import com.hungteen.pvz.client.render.entity.EntityBlockRender;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.zhilizhan.bhtpvz.common.entity.bullet.DragonFireEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DragonFireRender extends EntityBlockRender<DragonFireEntity> {
    public DragonFireRender(EntityRenderDispatcher renderManager) {
        super(renderManager);
    }
    public void render(DragonFireEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        float scale = this.getScaleByEntity(entityIn);
        matrixStackIn.scale(scale, scale, scale);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F));
        matrixStackIn.popPose();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    public float getScaleByEntity(DragonFireEntity entity) {
        return 0.8F;
    }

    public BlockState getBlockByEntity(DragonFireEntity entity) {
        return Blocks.FIRE.defaultBlockState();
    }
}
