package com.github.zhilizhan.bhtpvz.common.item;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Tiers;

public class Hammer extends TieredItem {
    public Hammer(Properties properties) {
        super(Tiers.WOOD, properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity interactionTarget, InteractionHand usedHand) {
        //检查是否有CD
        if (player.getCooldowns().isOnCooldown(BHTPvZItems.HAMMER.get())){
            //如果有CD则结束方法
            return InteractionResult.FAIL;
        } else if (interactionTarget instanceof PVZZombieEntity) {
            //如果没有CD就执行一下语句
            //对PVZ僵尸照成20点伤害
            interactionTarget.hurt(DamageSource.MAGIC, 20.0F);
            //减少锤子5点耐久
            stack.hurtAndBreak(5, interactionTarget, (arg) -> arg.broadcastBreakEvent(player.getUsedItemHand()));
            //如果是生存模式添加30tick的CD
            if (!player.isCreative()) {
                player.getCooldowns().addCooldown(BHTPvZItems.HAMMER.get(), 30);
            }
        }
        return InteractionResult.SUCCESS;
    }
}
