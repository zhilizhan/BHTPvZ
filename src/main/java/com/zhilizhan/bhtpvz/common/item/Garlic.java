package com.zhilizhan.bhtpvz.common.item;

import com.zhilizhan.bhtpvz.common.effect.BHTPvZMobEffects;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class Garlic extends BlockItem {
    public Garlic(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World level, LivingEntity livingEntity) {
        if (!level.isClientSide) {
            if (livingEntity instanceof PlayerEntity) livingEntity.addEffect(new EffectInstance(BHTPvZMobEffects.HALITOSIS.get(), 300));
        }
        return stack;
    }
}
