package com.zhilizhan.bhtpvz.client.render.layer;

import com.hungteen.pvz.client.model.entity.plant.defence.WallNutModel;
import com.hungteen.pvz.client.render.layer.component.ComponentLayer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.zhilizhan.bhtpvz.common.entity.plant.fire.BlazeWartEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NetherWartLayer extends ComponentLayer<BlazeWartEntity> {

    public NetherWartLayer(RenderLayerParent<BlazeWartEntity, EntityModel<BlazeWartEntity>> entityRendererIn) {
        super(entityRendererIn, new WallNutModel.WallNutArmorModel());
    }

    //地狱庞渲染
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, BlazeWartEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (this.canRender(entity)) {
            BlockRenderDispatcher lv = Minecraft.getInstance().getBlockRenderer();
            BlockState lv2 = Blocks.NETHER_WART.defaultBlockState().getBlockState();
            int m = LivingEntityRenderer.getOverlayCoords(entity, 0.0F);
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.20000000298023224, -0.3499999940395355, 0.5);
            matrixStackIn.scale(-1.0F, -1.0F, 1.0F);
            matrixStackIn.translate(-0.3, -1, -1);

            lv.renderSingleBlock(lv2, matrixStackIn, bufferIn, packedLightIn, m);
            matrixStackIn.popPose();
            matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(-78.0F));
        }

    }

  

    public boolean canRender(BlazeWartEntity entity) {
        return !entity.isInvisible();
    }

    public ResourceLocation getRenderTexture(BlazeWartEntity entity) {
        return null;
    }
}
