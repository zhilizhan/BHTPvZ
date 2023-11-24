package com.zhilizhan.bhtpvz.common.item.token;

import com.hungteen.pvz.common.entity.npc.AbstractDaveEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class SteelPumpkinToken extends AbstractToken {
    public SteelPumpkinToken(Properties properties) {
        super(properties);
    }

    @Override
    protected LivingEntity entity(Level level){
        return BHTPvZEntityTypes.STEEL_PUMPKIN.get().create(level);
    }
    @Override
    public int getRangeCount(Player player) {
        int range = 1;
        long count = EntityUtil.getFriendlyLivings(player, EntityUtil.getEntityAABB(player, range, range)).stream().filter(entity -> entity instanceof AbstractDaveEntity).count();
        return (int) count;
    }
     @Override
     protected int getEntityCount(){
         return 1;
     }
    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        tooltipComponents.add(new TranslatableComponent("tooltip.bhtpvz.steel_pumpkin_token.use").withStyle(ChatFormatting.GOLD));
    }
}
