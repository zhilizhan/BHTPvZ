package com.zhilizhan.bhtpvz.common.item.token;

import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.entity.npc.AbstractDaveEntity;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SunDaveToken extends AbstractToken {
    public SunDaveToken(Properties properties) {
        super(properties);
    }

    @Override
    protected LivingEntity entity(Level level){
        return EntityRegister.SUN_DAVE.get().create(level);
    }
    @Override
    protected int getEntityCount(){
        return 1;
    }
    @Override
    protected int getRangeCount(Player player) {
        final int range = 120;
        final long count = EntityUtil.getFriendlyLivings(player, EntityUtil.getEntityAABB(player, range, range)).stream().filter(entity -> entity instanceof AbstractDaveEntity).count();
        return (int)count;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        tooltipComponents.add(new TranslatableComponent("tooltip.bhtpvz.sun_token.use").withStyle(ChatFormatting.YELLOW));
    }
}
