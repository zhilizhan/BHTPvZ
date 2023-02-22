package com.zhilizhan.bhtpvz.common.item.superItem;

import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.entity.npc.AbstractDaveEntity;
import com.hungteen.pvz.common.entity.npc.SunDaveEntity;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class SunDaveToken extends Item {
    public SunDaveToken(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        World world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        ItemStack itemstack = context.getItemInHand();
        ///////////////////////////


        if (!world.isClientSide && !player.getCooldowns().isOnCooldown(this) && context.getClickedFace() == Direction.UP) {

            if (getDaveCount(player) < 1) {
                SunDaveEntity dave = EntityRegister.SUN_DAVE.get().create(world);

                EntityUtil.onEntitySpawn(world, dave, pos.above());

                itemstack.shrink(1);

            }
        }
        return super.useOn(context);
    }

    public int getDaveCount(PlayerEntity player) {
        final int range = 120;
        final long count = EntityUtil.getFriendlyLivings(player, EntityUtil.getEntityAABB(player, range, range))
                .stream().filter(entity -> entity instanceof AbstractDaveEntity).count();

        return (int)count;
    }
    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.bhtpvz.sun_token.use").withStyle(TextFormatting.YELLOW));
    }
}
