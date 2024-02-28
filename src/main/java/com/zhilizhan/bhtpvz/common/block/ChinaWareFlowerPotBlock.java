package com.zhilizhan.bhtpvz.common.block;

import com.hungteen.pvz.common.block.special.FlowerPotBlock;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ChinaWareFlowerPotBlock extends FlowerPotBlock {
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!level.isClientSide && player.getMainHandItem().getItem()== BHTPvZItems.HAMMER.get()) {
            level.removeBlock(pos, false);
           if (!player.isCreative()) {
                player.getCooldowns().addCooldown(BHTPvZItems.HAMMER.get(), 30);
               if(player instanceof ServerPlayer)player.getMainHandItem().hurt(5,level.random, (ServerPlayer) player);
           }
        }
        return InteractionResult.SUCCESS;
    }
}
