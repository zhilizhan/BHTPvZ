package com.zhilizhan.bhtpvz.common.item.sapling;

import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

public abstract class AbstractXpSapling extends Item {
    public AbstractXpSapling(Properties arg) {
        super(arg);
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.startUsingItem(handIn);
        return ActionResult.success(playerIn.getItemInHand(handIn));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World level, LivingEntity livingEntity) {
        if(livingEntity instanceof PlayerEntity) {
            if(!level.isClientSide) {
                PlayerEntity player = (PlayerEntity) livingEntity;
                player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
                    int amount = amount();
                    {
                        l.getPlayerData().addResource(Resources.TREE_XP, amount);
                        PlayerUtil.playClientSound(player, SoundEvents.EXPERIENCE_ORB_PICKUP);
                        if(!player.isCreative()) {
                            stack.shrink(1);
                        }}
                });
            }
        }
        return stack;
    }

    protected int amount(){
        return 200;
    }

    @Override
    public void appendHoverText(ItemStack stack, World level, List<ITextComponent> tooltipComponents, ITooltipFlag isAdvanced) {
        tooltipComponents.add(new TranslationTextComponent("tooltip.bhtpvz.xp_sapling.use").withStyle(TextFormatting.GREEN));
    }

    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.EAT;
    }
    @Override
    public int getUseDuration(ItemStack stack) {
        return 50;
    }
}
