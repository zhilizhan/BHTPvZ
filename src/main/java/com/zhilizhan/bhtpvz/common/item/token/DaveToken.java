package com.zhilizhan.bhtpvz.common.item.token;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class DaveToken extends AbstractToken {
    public DaveToken(Properties properties) {
        super(properties);
    }
    @Override
    protected int getEntityCount(){
        return 1;
    }
    @Override
    public void appendHoverText(ItemStack stack, World level, List<ITextComponent> tooltipComponents, ITooltipFlag isAdvanced) {
        tooltipComponents.add(new TranslationTextComponent("tooltip.bhtpvz.token.use").withStyle(TextFormatting.GREEN));
    }
}
