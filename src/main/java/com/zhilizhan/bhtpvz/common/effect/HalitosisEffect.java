package com.zhilizhan.bhtpvz.common.effect;

import com.hungteen.pvz.common.potion.PVZEffect;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;

import java.util.List;

public class HalitosisEffect extends PVZEffect {
    public HalitosisEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if(livingEntity.hasEffect(BHTPvZMobEffects.HALITOSIS.get())&& livingEntity.isAlive()) {
            float range = 5.0F;
            List<LivingEntity> entities = EntityUtil.getTargetableLivings(livingEntity, EntityUtil.getEntityAABB(livingEntity, range, range));
            entities.forEach(entity -> entity.addEffect(new EffectInstance(Effects.POISON, 60)));
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 20 == 0;
    }
}
