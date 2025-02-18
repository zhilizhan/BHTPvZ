package com.zhilizhan.bhtpvz.common.item.token;

import com.hungteen.pvz.common.entity.npc.AbstractDaveEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class SteelPumpkinToken extends AbstractToken {
    public SteelPumpkinToken(Properties properties) {
        super(properties);
    }

    @Override
    protected LivingEntity entity(World level){
        return BHTPvZEntityTypes.STEEL_PUMPKIN.get().create(level);
    }
    @Override
    public int getRangeCount(PlayerEntity player) {
        int range = 1;
        long count = EntityUtil.getFriendlyLivings(player, EntityUtil.getEntityAABB(player, range, range)).stream().filter(entity -> entity instanceof AbstractDaveEntity).count();
        return (int) count;
    }
     @Override
     protected int getEntityCount(){
         return 1;
     }
    @Override
    public void appendHoverText(ItemStack stack, World level, List<ITextComponent> tooltipComponents, ITooltipFlag isAdvanced) {
        tooltipComponents.add(new TranslationTextComponent("tooltip.bhtpvz.steel_pumpkin_token.use").withStyle(TextFormatting.GOLD));
    }
}
