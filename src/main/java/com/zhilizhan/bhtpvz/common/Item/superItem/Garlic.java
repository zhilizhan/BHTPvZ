package com.zhilizhan.bhtpvz.common.Item.superItem;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;

public class Garlic extends BlockItem {

    public Garlic(Block p_i48527_1_, Properties p_i48527_2_) {
        super(p_i48527_1_, p_i48527_2_);
    }

    public ActionResultType interactLivingEntity(ItemStack p_111207_1_, PlayerEntity p_111207_2_, LivingEntity p_111207_3_, Hand p_111207_4_) {
        if(p_111207_3_ instanceof PVZZombieEntity){
            ((PVZZombieEntity) p_111207_3_).checkAndAddPotionEffect(new EffectInstance(Effects.POISON, 120, 0));
            p_111207_1_.shrink(1);
        }
        return ActionResultType.SUCCESS;
    }
}
