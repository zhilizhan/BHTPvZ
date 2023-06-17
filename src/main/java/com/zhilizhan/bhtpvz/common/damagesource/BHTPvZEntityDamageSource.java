package com.zhilizhan.bhtpvz.common.damagesource;

import com.hungteen.pvz.common.entity.bullet.itembullet.PeaEntity;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.entity.bullet.BurstCornEntity;
import com.zhilizhan.bhtpvz.common.entity.bullet.ChorusFruitEntity;
import com.zhilizhan.bhtpvz.common.entity.bullet.CornEntity;
import com.zhilizhan.bhtpvz.common.entity.bullet.PoppedChorusFruitEntity;
import com.zhilizhan.bhtpvz.common.entity.bullet.itembullet.IceCabbageEntity;
import com.zhilizhan.bhtpvz.common.entity.bullet.itembullet.PopCornEntity;
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
    //紫颂果伤害
    public static PVZEntityDamageSource chorusFruit(ChorusFruitEntity pea, Entity shooter) {
         return (new PVZEntityDamageSource("chorus_fruit", pea, shooter)).setParabola();
    }
    //爆裂紫颂果伤害
    public static PVZEntityDamageSource poppedChorusFruit(PoppedChorusFruitEntity pea, Entity shooter) {
        return (PVZEntityDamageSource)(new PVZEntityDamageSource("popped_chorus_fruit", pea, shooter)).setParabola().setExplosion();
    }
    public static PVZEntityDamageSource corn(CornEntity pea, Entity shooter) {
        return (new PVZEntityDamageSource("corn", pea, shooter)).setParabola();
    }

    public static PVZEntityDamageSource burst_corn(BurstCornEntity pea, Entity shooter) {
        return (PVZEntityDamageSource) (new PVZEntityDamageSource("burst_corn", pea, shooter)).setParabola().setFlameDamage().setExplosion();
    }
    //爆米花伤害
    public static PVZEntityDamageSource popCorn(PopCornEntity pea, Entity shooter) {
        return (PVZEntityDamageSource)(new PVZEntityDamageSource("pop_corn", pea, shooter)).setParabola().setExplosion();
    }
}
