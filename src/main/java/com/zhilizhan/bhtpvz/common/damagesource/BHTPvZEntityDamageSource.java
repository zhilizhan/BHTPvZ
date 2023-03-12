package com.zhilizhan.bhtpvz.common.damagesource;

import com.hungteen.pvz.common.entity.bullet.itembullet.PeaEntity;
import com.zhilizhan.bhtpvz.common.entity.bullet.itembullet.IceCabbageEntity;

import com.hungteen.pvz.common.misc.PVZEntityDamageSource;

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
}
