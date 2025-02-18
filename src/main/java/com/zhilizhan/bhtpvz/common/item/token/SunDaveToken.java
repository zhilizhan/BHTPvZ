package com.zhilizhan.bhtpvz.common.item.token;

import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.entity.npc.AbstractDaveEntity;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class SunDaveToken extends AbstractToken {
    public SunDaveToken(Properties properties) {
        super(properties);
    }

    @Override
    protected LivingEntity entity(World level){
        return EntityRegister.SUN_DAVE.get().create(level);
    }
    @Override
    protected int getEntityCount(){
        return 1;
    }
    @Override
    protected int getRangeCount(PlayerEntity player) {
        final int range = 120;
        final long count = EntityUtil.getFriendlyLivings(player, EntityUtil.getEntityAABB(player, range, range)).stream().filter(entity -> entity instanceof AbstractDaveEntity).count();
        return (int)count;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World level, List<ITextComponent> tooltipComponents, ITooltipFlag isAdvanced) {
        tooltipComponents.add(new TranslationTextComponent("tooltip.bhtpvz.sun_token.use").withStyle(TextFormatting.YELLOW));
    }
}
