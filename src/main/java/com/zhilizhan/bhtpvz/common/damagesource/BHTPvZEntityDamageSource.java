package com.zhilizhan.bhtpvz.common.damagesource;

import com.hungteen.pvz.common.entity.bullet.itembullet.PeaEntity;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.entity.bullet.ChorusFruitEntity;
import com.zhilizhan.bhtpvz.common.entity.bullet.PoppedChorusFruitEntity;
import com.zhilizhan.bhtpvz.common.entity.bullet.itembullet.IceCabbageEntity;
import net.minecraft.world.entity.Entity;

public class BHTPvZEntityDamageSource {
    //冰卷心菜投手伤害
    public static PVZEntityDamageSource iceCabbage(IceCabbageEntity iceCabbage, Entity indirectEntity) {
        return (new PVZEntityDamageSource("ice_cabbage", iceCabbage, indirectEntity).setIceDamage());
    }

    //火焰豌豆伤害
    public static PVZEntityDamageSource firePea(PeaEntity pea, Entity indirectEntity) {
        return (new PVZEntityDamageSource("fire_pea", pea, indirectEntity).setIceDamage());
    }
    //
    public static PVZEntityDamageSource chorusFruit(ChorusFruitEntity pea, Entity shooter) {
         return (new PVZEntityDamageSource("chorus_fruit", pea, shooter)).setParabola();
    }

    public static PVZEntityDamageSource poppedChorusFruit(PoppedChorusFruitEntity pea, Entity shooter) {
        return (PVZEntityDamageSource)(new PVZEntityDamageSource("popped_chorus_fruit", pea, shooter)).setParabola().setExplosion();
    }
}
