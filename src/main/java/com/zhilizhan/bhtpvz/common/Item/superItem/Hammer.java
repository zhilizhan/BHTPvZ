package com.zhilizhan.bhtpvz.common.Item.superItem;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.zhilizhan.bhtpvz.common.Item.ItemRegistry;
import com.zhilizhan.bhtpvz.common.Item.tools.BhtpvzTools;
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

    public ActionResultType interactLivingEntity(ItemStack itemStack, PlayerEntity playerEntity, LivingEntity livingEntity, Hand hand) {
        //检查是否有CD
        if (playerEntity.getCooldowns().isOnCooldown(ItemRegistry.HAMMER.get())){
            return ActionResultType.SUCCESS;
        }else if (livingEntity instanceof PVZZombieEntity) {
            //对PVZ僵尸照成伤害 20点
            livingEntity.hurt(DamageSource.MAGIC, 20.0F);
            //减少锤子耐久 5点
            itemStack.hurtAndBreak(5, livingEntity, (p_0) -> {
                p_0.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
            });
            //如果是生存模式添加CD 30tick
            if (!playerEntity.isCreative()) {
                playerEntity.getCooldowns().addCooldown(ItemRegistry.HAMMER.get(), 30);
            }
        }
        return ActionResultType.SUCCESS;
    }
}
