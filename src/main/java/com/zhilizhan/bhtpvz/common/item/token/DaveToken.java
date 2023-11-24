package com.zhilizhan.bhtpvz.common.item.token;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

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
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        tooltipComponents.add(new TranslatableComponent("tooltip.bhtpvz.token.use").withStyle(ChatFormatting.GREEN));
    }
}
