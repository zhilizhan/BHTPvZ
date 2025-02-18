package com.zhilizhan.bhtpvz.common.item;

import com.zhilizhan.bhtpvz.BHTPvZ;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class DamsonCrystalShove extends ShovelItem {
    public DamsonCrystalShove() {
        super(BHTPvZTier.DAMSON_CRYSTAL,1.5f,-0.3f, new Properties().tab(BHTPvZ.BHTPVZ));
    }
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> textComponents, ITooltipFlag tooltipFlag) {
        super.appendHoverText(stack, world, textComponents, tooltipFlag);
        textComponents.add((new TranslationTextComponent("tooltip.pvz.origin_shovel")).withStyle(TextFormatting.DARK_GREEN));
    }
}
