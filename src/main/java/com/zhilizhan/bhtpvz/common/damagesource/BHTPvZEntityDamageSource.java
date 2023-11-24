package com.zhilizhan.bhtpvz.common.damagesource;

import com.hungteen.pvz.common.entity.bullet.itembullet.PeaEntity;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.entity.bullet.*;
import com.zhilizhan.bhtpvz.common.entity.bullet.itembullet.*;
import com.zhilizhan.bhtpvz.common.entity.plant.assist.FodderBushEntity;
import com.zhilizhan.bhtpvz.common.entity.plant.electric.LightningReedEntity;
import com.zhilizhan.bhtpvz.common.entity.plant.electric.MagnifyingGrassEntity;
import net.minecraft.world.entity.Entity;

public class BHTPvZEntityDamageSource {

    //冰卷心菜投手伤害
    public static PVZEntityDamageSource iceCabbage(IceCabbageEntity iceCabbage, Entity indirectEntity) {
        return (new PVZEntityDamageSource("ice_cabbage", iceCabbage, indirectEntity).setParabola().setIceDamage());
    }
    //火焰豌豆伤害
    public static PVZEntityDamageSource firePea(PeaEntity pea, Entity indirectEntity) {
        return (new PVZEntityDamageSource("fire_pea", pea, indirectEntity).setAppease().setFlameDamage());
    }
    //紫颂果伤害
    public static PVZEntityDamageSource chorusFruit(ChorusFruitEntity pea, Entity shooter) {
         return (new PVZEntityDamageSource("chorus_fruit", pea, shooter)).setParabola();
    }
    //爆裂紫颂果伤害
    public static PVZEntityDamageSource poppedChorusFruit(PoppedChorusFruitEntity pea, Entity shooter) {
        return (PVZEntityDamageSource)(new PVZEntityDamageSource("popped_chorus_fruit", pea, shooter)).setParabola().setExplosion();
    }
    //玉米伤害
    public static PVZEntityDamageSource corn(CornEntity pea, Entity shooter) {
        return (new PVZEntityDamageSource("corn", pea, shooter)).setParabola();
    }
    //爆裂玉米伤害
    public static PVZEntityDamageSource burst_corn(BurstCornEntity pea, Entity shooter) {
        return (PVZEntityDamageSource) (new PVZEntityDamageSource("burst_corn", pea, shooter)).setParabola().setFlameDamage().setExplosion();
    }
    //爆米花伤害
    public static PVZEntityDamageSource popCorn(PopCornEntity pea, Entity shooter) {
        return (PVZEntityDamageSource)(new PVZEntityDamageSource("pop_corn", pea, shooter)).setParabola().setExplosion();
    }
    //鱼豆
    public static PVZEntityDamageSource fishPea(FishPeaEntity pea, Entity shooter) {
        return (PVZEntityDamageSource)(new PVZEntityDamageSource("fish_pea", pea, shooter));
    }
    //冰结豌豆
    public static PVZEntityDamageSource icePea(IcePeaEntity pea, Entity shooter) {
        return (PVZEntityDamageSource)(new PVZEntityDamageSource("ice_pea", pea, shooter)).setIceDamage();
    }
    //音波
    public static PVZEntityDamageSource sonic(SonicEntity pea, Entity shooter) {
        return (new PVZEntityDamageSource("sonic", pea, shooter)).setAppease().setThroughDamage();
    }
    //起源孢子
    public static PVZEntityDamageSource originFume(OriginFumeEntity pea, Entity shooter) {
        return (new PVZEntityDamageSource("origin_fume", pea, shooter)).setAppease().setThroughDamage();
    }
    //炮灰灌
    public static PVZEntityDamageSource bush(FodderBushEntity bush, Entity shooter) {
        return (new PVZEntityDamageSource("bush", bush, shooter)).setEatDamage().setThroughDamage();
    }
    //金西瓜
    public static PVZEntityDamageSource goldenMelon(GoldenMelonEntity melon, Entity shooter) {
        return (new PVZEntityDamageSource("golden_melon", melon, shooter)).setParabola();
    }
    //闪电芦苇
    public static PVZEntityDamageSource lightningBeam(LightningReedEntity beam, Entity shooter) {
        return (new PVZEntityDamageSource("lightning_beam", beam, shooter));
    }
    //光束
    public static PVZEntityDamageSource lightBeam(LightBeamEntity beam, Entity shooter) {
        return (new PVZEntityDamageSource("light_beam", beam, shooter));
    }
    //棱镜草
    public static PVZEntityDamageSource magnifyingGrass(MagnifyingGrassEntity entity, Entity shooter) {
        return (new PVZEntityDamageSource("magnifying_grass", entity, shooter));
    }
    //原始豌豆
    public static PVZEntityDamageSource stonePea(StonePeaEntity pea, Entity shooter) {
        return (new PVZEntityDamageSource("stone_pea", pea, shooter)).setAppease();
    }
    //熔岩原始豌豆
    public static PVZEntityDamageSource magmaPea(StonePeaEntity pea, Entity shooter) {
        return (new PVZEntityDamageSource("magma_pea", pea, shooter)).setAppease();
    }
}
