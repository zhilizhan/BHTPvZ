package com.zhilizhan.bhtpvz.client.gui.screen;

import com.hungteen.pvz.client.gui.screen.PVZContainerScreen;
import com.hungteen.pvz.client.gui.widget.DisplayField;
import com.hungteen.pvz.common.tileentity.CardFusionTileEntity;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Colors;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.common.container.CardDecompositionContainer;
import com.zhilizhan.bhtpvz.common.network.BHTPvZPacketHandler;
import com.zhilizhan.bhtpvz.common.network.toserver.BHTPvZClickButtonPacket;
import com.zhilizhan.bhtpvz.common.tileentity.CardDecompositionTileEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Arrays;

public class CardDecompositionScreen extends PVZContainerScreen<CardDecompositionContainer> {
    private static final ResourceLocation TEXTURE = BHTPvZ.prefix("textures/gui/container/card_decomposition.png");
    protected Button craftButton;

    public CardDecompositionScreen(CardDecompositionContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.imageWidth = 210;
        this.imageHeight = 225;
        this.tips.add(new DisplayField.TipField(3, 3, Arrays.asList(new TranslationTextComponent("gui.bhtpvz.card_decomposition_table.tip1"), new TranslationTextComponent("gui.bhtpvz.card_decomposition_table.tip2"), new TranslationTextComponent("gui.bhtpvz.card_decomposition_table.tip3"))));
    }

    protected void init() {
        super.init();
        this.craftButton = this.addButton(new Button(this.leftPos + 92, this.topPos + 122, 26, 14, new TranslationTextComponent("gui.bhtpvz.card_decomposition"), (button) -> {
            if (this.craftButton.visible) {
                BHTPvZPacketHandler.CHANNEL.sendToServer(new BHTPvZClickButtonPacket(1, 0, 0));
            }

        }));
        this.craftButton.visible = false;
    }

    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        this.craftButton.visible = this.canCraftNow();
        super.render(stack, mouseX, mouseY, partialTicks);
        final float percent = this.menu.te.array.get(0) * 100.0F / CardDecompositionTileEntity.CRAFT_SUN_AMOUNT;
        StringUtil.drawCenteredScaledString(stack, this.font, (new TranslationTextComponent("block.bhtpvz.card_decomposition_table")).getString(), this.leftPos + this.imageWidth / 2, this.topPos + 8, 0, 1.0F);
        StringUtil.drawCenteredScaledString(stack, this.font, "" + String.format("%.0f%%", percent), this.leftPos + 16, this.topPos + 67, Colors.BLACK, 0.5F);
        StringUtil.drawCenteredScaledString(stack, this.font, "" + String.format("%.0f%%", percent), this.leftPos + 15, this.topPos + 66, Colors.WHITE, 0.8F);
        this.renderTooltip(stack, mouseX, mouseY);
    }

    protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
        stack.pushPose();
        this.minecraft.getTextureManager().bind(TEXTURE);
        this.blit(stack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

        int len = MathUtil.getBarLen(this.menu.te.array.get(0), 10000, 88);
        this.blit(stack, this.leftPos + 7, this.topPos + 113 - len + 1, 210, 0, 16, len);
        stack.popPose();
        super.renderBg(stack, partialTicks, mouseX, mouseY);
    }

    protected boolean canCraftNow() {
        return this.menu.canCraft();
    }
}
