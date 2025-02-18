package com.zhilizhan.bhtpvz.client.render.layer;

import com.hungteen.pvz.client.model.entity.plant.defence.WallNutModel;
import com.hungteen.pvz.client.render.layer.component.ComponentLayer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.zhilizhan.bhtpvz.common.entity.plant.fire.BlazeWartEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NetherWartLayer extends ComponentLayer<BlazeWartEntity> {

    public NetherWartLayer(IEntityRenderer<BlazeWartEntity, EntityModel<BlazeWartEntity>> entityRendererIn) {
        super(entityRendererIn, new WallNutModel.WallNutArmorModel());
    }

    //地狱庞渲染
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, BlazeWartEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (this.canRender(entity) && entity.hasNetherWart()) {
            BlockRendererDispatcher lv = Minecraft.getInstance().getBlockRenderer();
            BlockState lv2 = Blocks.NETHER_WART.defaultBlockState().getBlockState();
            int m = LivingRenderer.getOverlayCoords(entity, 0.0F);
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.20000000298023224, -0.2999999940395355, 0.5);
            matrixStackIn.scale(-1.0F, -1.0F, 1.0F);
            matrixStackIn.translate(-0.3, -1, -1);

            lv.renderSingleBlock(lv2, matrixStackIn, bufferIn, packedLightIn, m);
            matrixStackIn.popPose();
        }

    }

  

    public boolean canRender(BlazeWartEntity entity) {
        return !entity.isInvisible();
    }

    public ResourceLocation getRenderTexture(BlazeWartEntity entity) {
        return null;
    }
}
