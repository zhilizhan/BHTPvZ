package com.zhilizhan.bhtpvz.common.item.token;

import com.hungteen.pvz.common.entity.npc.AbstractDaveEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.entity.plant.defence.SteelPumpkinEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.List;

public class SteelPumpkinToken extends Item {
    public SteelPumpkinToken(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        ItemStack itemstack = context.getItemInHand();
        if (!level.isClientSide && !player.getCooldowns().isOnCooldown(this) && context.getClickedFace() == Direction.UP) {
            if (getDaveCount(player) < 1) {
                SteelPumpkinEntity pumpkin = BHTPvZEntityTypes.STEEL_PUMPKIN.get().create(level);
                EntityUtil.onEntitySpawn(level, pumpkin, pos.above());
                //itemstack.shrink(1);
            }
        }
        return InteractionResult.CONSUME;
    }

    public int getDaveCount(Player player) {
        int range = 1;
        long count = EntityUtil.getFriendlyLivings(player, EntityUtil.getEntityAABB(player, range, range)).stream().filter(entity -> entity instanceof AbstractDaveEntity).count();
        return (int) count;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        player.startUsingItem(usedHand);
        return InteractionResultHolder.success(player.getItemInHand(usedHand));
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        tooltipComponents.add(new TranslatableComponent("tooltip.bhtpvz.steel_pumpkin_token.use").withStyle(ChatFormatting.GOLD));
    }
}
