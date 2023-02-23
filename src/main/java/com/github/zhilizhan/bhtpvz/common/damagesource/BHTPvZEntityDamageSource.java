package com.github.zhilizhan.bhtpvz.common.damagesource;

import com.github.zhilizhan.bhtpvz.common.entity.bullet.itembullet.IceCabbageEntity;

import com.hungteen.pvz.common.misc.PVZEntityDamageSource;

import net.minecraft.world.entity.Entity;

public class BHTPvZEntityDamageSource {
    public static PVZEntityDamageSource iceCabbage(IceCabbageEntity iceCabbage, Entity indirectEntity) {
        return (new PVZEntityDamageSource("ice_cabbage", iceCabbage, indirectEntity).setIceDamage());
    }
}
