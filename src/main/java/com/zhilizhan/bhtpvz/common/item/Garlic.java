package com.zhilizhan.bhtpvz.common.item;

import com.zhilizhan.bhtpvz.common.effect.BHTPvZMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class Garlic extends BlockItem {
    public Garlic(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if(livingEntity instanceof Player) {
            Player player = (Player) livingEntity;
            if(!level.isClientSide) {
                player.addEffect(new MobEffectInstance(BHTPvZMobEffects.HALITOSIS.get(),300));
            }
        }
        return stack;
    }
}
