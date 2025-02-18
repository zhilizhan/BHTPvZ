package com.zhilizhan.bhtpvz.common.item.token;

import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.entity.npc.AbstractDaveEntity;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;


public abstract class AbstractToken extends Item {
    public AbstractToken(Properties arg) {
        super(arg);
    }
    @Override
    public ActionResult<ItemStack> use(World level, @Nonnull PlayerEntity player, Hand usedHand) {
        player.startUsingItem(usedHand);
        return ActionResult.success(player.getItemInHand(usedHand));
    }
    @Override
    public ActionResultType useOn(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        World level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        ItemStack itemstack = context.getItemInHand();
        if (!level.isClientSide && !(player != null && player.getCooldowns().isOnCooldown(this)) && context.getClickedFace() == Direction.UP) {
            if (getRangeCount(player) < getEntityCount()) {
                EntityUtil.onEntitySpawn(level, entity(level), pos.above());
                itemstack.shrink(1);
            }
        }
        return ActionResultType.CONSUME;
    }

    protected LivingEntity entity(World level){
        return EntityRegister.CRAZY_DAVE.get().create(level);
    }

    protected int getEntityCount(){
        return 0;
    }
    protected int getRangeCount(PlayerEntity player) {
        int range = 60;
        long count = EntityUtil.getFriendlyLivings(player, EntityUtil.getEntityAABB(player, range, range)).stream().filter(entity -> entity instanceof AbstractDaveEntity).count();
        return (int) count;
    }
}
