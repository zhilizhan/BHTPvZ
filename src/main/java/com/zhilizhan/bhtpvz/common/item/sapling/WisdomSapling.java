package com.zhilizhan.bhtpvz.common.item.sapling;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class WisdomSapling extends AbstractXpSapling {
    public WisdomSapling(Properties properties) {
        super(properties);
    }
    public boolean isFoil(ItemStack stack) {
        return true;
    }
    @Override
    protected int amount(){
        return 500;
    }
    @Override
    public void appendHoverText(ItemStack stack, World level, List<ITextComponent> tooltipComponents, ITooltipFlag isAdvanced) {
        tooltipComponents.add(new TranslationTextComponent("tooltip.bhtpvz.wisdom_sapling.use").withStyle(TextFormatting.GREEN));
    }
}
