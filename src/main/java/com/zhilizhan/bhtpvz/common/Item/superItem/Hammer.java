package com.zhilizhan.bhtpvz.common.Item.superItem;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.zhilizhan.bhtpvz.common.Item.tools.BhtpvzTools;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;

public class Hammer extends SwordItem {
    public Hammer(BhtpvzTools hammre, int i, float v, Properties properties) {
        super(hammre, i, v, properties);
    }

    public ActionResultType interactLivingEntity(ItemStack p_111207_1_, PlayerEntity p_111207_2_, LivingEntity p_111207_3_, Hand p_111207_4_) {
        if (p_111207_3_ instanceof PVZZombieEntity) {
            p_111207_3_.hurt(DamageSource.MAGIC, 15.0F);
            p_111207_1_.hurtAndBreak(5, p_111207_3_, (p_0) -> {
                p_0.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
            });
        }
        return ActionResultType.SUCCESS;
    }
}
