package com.zhilizhan.bhtpvz.common.potion.potions;

import com.hungteen.pvz.utils.EffectUtil;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;

public class Halitosis extends Effect {
    public Halitosis(EffectType effectType, int color) {
        super(effectType, color);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (livingEntity instanceof PlayerEntity) {
            float range = 2.0F;
            EntityUtil.getFriendlyLivings(livingEntity, EntityUtil.getEntityAABB(livingEntity, range, range)).forEach((entity) -> {
                entity.addEffect(EffectUtil.viewEffect(Effects.POISON, 40, 0));
            });
        }
    }
}
