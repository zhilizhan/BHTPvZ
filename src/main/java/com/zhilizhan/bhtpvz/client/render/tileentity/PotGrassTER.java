package com.zhilizhan.bhtpvz.client.render.tileentity;


import com.hungteen.pvz.PVZMod;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.zhilizhan.bhtpvz.common.tileentity.PotGrassTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PotGrassTER extends TileEntityRenderer<PotGrassTileEntity> {
    public static final ResourceLocation CHARMED_LOCATION = new ResourceLocation(PVZMod.MOD_ID, "textures/entity/layer/charm.png");
    private final BlockRendererDispatcher blockRenderDispatcher;

    public PotGrassTER(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
        this.blockRenderDispatcher = Minecraft.getInstance().getBlockRenderer();
    }

    public void render(PotGrassTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if (tileEntityIn.getLevel() == null) return;
        if (tileEntityIn.isCharmed()) {
            matrixStackIn.pushPose();
            this.blockRenderDispatcher.renderBatched(
                    tileEntityIn.getBlockState(),
                    tileEntityIn.getBlockPos(),
                    tileEntityIn.getLevel(),
                    matrixStackIn,
                    bufferIn.getBuffer(CHARMED),
                    true,
                    tileEntityIn.getLevel().random
            );
            matrixStackIn.popPose();
        }
    }
    private static final RenderState.TexturingState COMBINED_CHARMED_OFFSET = new RenderState.TexturingState(
            "combined_charmed_offset",
            () -> {
                // 多边形偏移设置 (CUSTOM_POLYGON_OFFSET_LAYERING)
                RenderSystem.polygonOffset(-0.25F, -10.0F);
                RenderSystem.enablePolygonOffset();

                // 附魔光效纹理变换 (GLINT_TEXTURING)
                RenderSystem.matrixMode(5890); // GL_PROJECTION
                RenderSystem.pushMatrix();
                RenderSystem.loadIdentity();
                long time = Util.getMillis() * 4L;
                float offsetX = (float)(time % 110000L) / 110000.0F;
                float offsetY = (float)(time % 30000L) / 30000.0F;
                RenderSystem.translatef(-offsetX, offsetY* 1.2F, 0.0F);
                RenderSystem.rotatef(15.0F, 0.0F, 0.0F, 1.0F);
                RenderSystem.scalef(12.0F, 12.0F, 12.0F);
                RenderSystem.matrixMode(5888); // GL_MODELVIEW
            },
            () -> {
                // 清理附魔光效纹理变换 (逆序)
                RenderSystem.matrixMode(5890); // GL_PROJECTION
                RenderSystem.popMatrix();
                RenderSystem.matrixMode(5888); // GL_MODELVIEW

                // 清理多边形偏移
                RenderSystem.polygonOffset(0.0F, 0.0F);
                RenderSystem.disablePolygonOffset();
            }
    );
    public static final RenderState.TransparencyState CHARMED_TRANSPARENT = new RenderState.TransparencyState("charmed_transparent", () -> {
        RenderSystem.enableBlend();
        // 加法混合但加入alpha控制
        RenderSystem.blendFuncSeparate(
                GlStateManager.SourceFactor.SRC_ALPHA,
                GlStateManager.DestFactor.ONE,
                GlStateManager.SourceFactor.SRC_ALPHA,
                GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA
        );
    }, () -> {
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    });

    private static final RenderType CHARMED = RenderType.create("charmed",DefaultVertexFormats.POSITION_TEX, 7, 256,RenderType.State.builder().setTextureState(new RenderState.TextureState(CHARMED_LOCATION, true, false)).setWriteMaskState(RenderState.COLOR_WRITE).setCullState(new RenderState.CullState(false)).setDepthTestState(new RenderState.DepthTestState("<=", 547)).setTransparencyState(CHARMED_TRANSPARENT).setTexturingState(COMBINED_CHARMED_OFFSET).createCompositeState(false));
}
