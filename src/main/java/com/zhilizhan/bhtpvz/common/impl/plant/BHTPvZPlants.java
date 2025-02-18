package com.zhilizhan.bhtpvz.common.impl.plant;

import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.client.model.entity.plant.defence.WallNutModel;
import com.hungteen.pvz.client.model.entity.plant.ice.SnowPeaModel;
import com.hungteen.pvz.common.impl.*;
import com.hungteen.pvz.common.impl.plant.PlantType;
import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.client.model.entity.plant.appease.BeeShooterModel;
import com.zhilizhan.bhtpvz.client.model.entity.plant.appease.PeaPodModel;
import com.zhilizhan.bhtpvz.client.model.entity.plant.appease.PrimalPeaShooterModel;
import com.zhilizhan.bhtpvz.client.model.entity.plant.arma.BurstKernelPultModel;
import com.zhilizhan.bhtpvz.client.model.entity.plant.arma.ChorusFruitPultModel;
import com.zhilizhan.bhtpvz.client.model.entity.plant.arma.GoldenMelonPultModel;
import com.zhilizhan.bhtpvz.client.model.entity.plant.assist.FodderBushModel;
import com.zhilizhan.bhtpvz.client.model.entity.plant.assist.GrassCarpModel;
import com.zhilizhan.bhtpvz.client.model.entity.plant.defence.SelfImitaterModel;
import com.zhilizhan.bhtpvz.client.model.entity.plant.defence.SteelPumpkinModel;
import com.zhilizhan.bhtpvz.client.model.entity.plant.electric.MagnifyingGrassModel;
import com.zhilizhan.bhtpvz.client.model.entity.plant.enforce.RotateRadishModel;
import com.zhilizhan.bhtpvz.client.model.entity.plant.fire.BlazeWartModel;
import com.zhilizhan.bhtpvz.client.model.entity.plant.fire.FirePeashooterModel;
import com.zhilizhan.bhtpvz.client.model.entity.plant.ice.IceCabbagePultModel;
import com.zhilizhan.bhtpvz.client.model.entity.plant.toxic.GooPeaShooterModel;
import com.zhilizhan.bhtpvz.client.model.entity.plant.toxic.OriginShroomModel;
import com.zhilizhan.bhtpvz.client.model.entity.plant.toxic.SculkShroomModel;
import com.zhilizhan.bhtpvz.common.block.BHTPvZBlocks;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.entity.plant.defence.SteelPumpkinEntity;
import com.zhilizhan.bhtpvz.common.impl.BHTPvZSkill;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.hungteen.pvz.common.impl.plant.PVZPlants.MELON_PULT;

public final class BHTPvZPlants extends PlantType {
    private static final List<IPlantType> LIST = new ArrayList<>();

    //钢南瓜
    public static final IPlantType STEEL_PUMPKIN = new BHTPvZPlants("steel_pumpkin", new PlantFeatures()
            .cost(225).requiredLevel(35)
            .cd(CoolDowns.VERY_SLOW).rank(RankTypes.BLUE).essence(EssenceTypes.DEFENCE)
            .entityType(() -> BHTPvZEntityTypes.STEEL_PUMPKIN.get()).isShroomPlant()
            .summonCard(() -> BHTPvZItems.STEEL_PUMPKIN_CARD.get())
            .enjoyCard(() -> BHTPvZItems.STEEL_PUMPKIN_ENJOY_CARD.get())
            .plantModel(() -> SteelPumpkinModel::new).scale(1F)
            .outerPlant(SteelPumpkinEntity.SteelPumpkinInfo::new)
            .commonSkill(Collections.emptyList())
    );
    //冰卷心菜
    public static final IPlantType ICE_CABBAGE_PULT = new BHTPvZPlants("ice_cabbage_pult", new PlantFeatures()
            .cost(175).requiredLevel(17)
            .cd(CoolDowns.VERY_FAST).rank(RankTypes.BLUE).essence(EssenceTypes.ICE)
            .entityType(() -> BHTPvZEntityTypes.ICE_CABBAGE_PULT.get())
            .summonCard(() -> BHTPvZItems.ICE_CABBAGE_PULT_CARD.get())
            .enjoyCard(() -> BHTPvZItems.ICE_CABBAGE_PULT_ENJOY_CARD.get())
            .plantModel(() -> IceCabbagePultModel::new).scale(0.95F)
            .commonSkill(Arrays.asList(SkillTypes.MORE_CABBAGE_DAMAGE)));
    //坚果保龄球
    public static final IPlantType NUT_BOWLING = new BHTPvZPlants("nut_bowling", new PlantFeatures()
            .cost(50).requiredLevel(15)
            .cd(CoolDowns.FAST).rank(RankTypes.BLUE).essence(EssenceTypes.ENFORCE)
            .entityType(() -> BHTPvZEntityTypes.NUT_BOWLING.get())
            .summonCard(() -> BHTPvZItems.NUT_BOWLING_CARD.get())
            .enjoyCard(() -> BHTPvZItems.NUT_BOWLING_ENJOY_CARD.get())
            .plantModel(() -> WallNutModel::new).scale(0.95F)
            .placement(Placements.ANY));
    //火焰豌豆
    public static final IPlantType FIRE_PEASHOOTER = new BHTPvZPlants("fire_peashooter", new PlantFeatures()
            .cost(225).requiredLevel(17)
            .cd(CoolDowns.VERY_FAST).rank(RankTypes.GREEN).essence(EssenceTypes.FLAME)
            .entityType(() -> BHTPvZEntityTypes.FIRE_PEASHOOTER.get())
            .summonCard(() -> BHTPvZItems.FIRE_PEASHOOTER_CARD.get())
            .enjoyCard(() -> BHTPvZItems.FIRE_PEASHOOTER_ENJOY_CARD.get())
            .plantModel(() -> FirePeashooterModel::new).scale(1.0F)
            .commonSkill(Collections.singletonList(SkillTypes.PEA_DAMAGE)));

    //水盆
    public static final IPlantType WATER_POT = new BHTPvZPlants("water_pot", new PlantFeatures()
            .cost(25).requiredLevel(9)
            .cd(CoolDowns.HUGE_FAST).rank(RankTypes.GREEN).essence(EssenceTypes.ASSIST)
            .summonCard(() -> BHTPvZItems.WATER_POT_CARD.get())
            .enjoyCard(() -> BHTPvZItems.WATER_POT_ENJOY_CARD.get())
            .plantBlock(BHTPvZBlocks.WATER_POT)
            .placement(Placements.ANY));

    //紫颂果
    public static final IPlantType CHORUS_FRUIT_PULT = new BHTPvZPlants("chorus_fruit_pult", new PlantFeatures()
            .cost(175).requiredLevel(21)
            .cd(CoolDowns.VERY_FAST).rank(RankTypes.PURPLE).essence(EssenceTypes.ARMA)
            .entityType(() -> BHTPvZEntityTypes.CHORUS_FRUIT_PULT.get())
            .summonCard(() -> BHTPvZItems.CHORUS_FRUIT_PULT_CARD.get())
            .enjoyCard(() -> BHTPvZItems.CHORUS_FRUIT_PULT_ENJOY_CARD.get())
            .plantModel(() -> ChorusFruitPultModel::new).scale(0.95F).placement(BHTPvZPlacements.END_STONE)
            .cdSkill(Collections.singletonList(SkillTypes.FAST_CD)));
    //转转萝卜
    public static final IPlantType ROTATE_RADISH = new BHTPvZPlants("rotate_radish", new PlantFeatures()
            .cost(150).requiredLevel(20)
            .cd(CoolDowns.FAST).rank(RankTypes.BLUE).essence(EssenceTypes.ENFORCE)
            .entityType(() -> BHTPvZEntityTypes.ROTATE_RADISH.get())
            .summonCard(() -> BHTPvZItems.ROTATE_RADISH_CARD.get())
            .enjoyCard(() -> BHTPvZItems.ROTATE_RADISH_ENJOY_CARD.get())
            .plantModel(() -> RotateRadishModel::new).scale(0.95F)
            .commonSkill(Arrays.asList(SkillTypes.MORE_SWING_DAMAGE,SkillTypes.PLANT_MORE_LIFE)));

    //爆裂玉米投手
    public static final IPlantType BURST_KERNEL_PULT = new BHTPvZPlants("burst_kernel_pult", new PlantFeatures()
            .cost(125).requiredLevel(15)
            .cd(CoolDowns.FAST).rank(RankTypes.GREEN).essence(EssenceTypes.ARMA)
            .entityType(() -> BHTPvZEntityTypes.BURST_KERNEL_PULT.get())
            .summonCard(() -> BHTPvZItems.BURST_KERNEL_PULT_CARD.get())
            .enjoyCard(() -> BHTPvZItems.BURST_KERNEL_PULT_ENJOY_CARD.get())
            .plantModel(() -> BurstKernelPultModel::new).scale(0.95f)
            .commonSkill(Collections.singletonList(SkillTypes.MORE_KERNEL_DAMAGE)));
    //烈焰庞
    public static final IPlantType BLAZE_WART = new BHTPvZPlants("blaze_wart", new PlantFeatures()
            .cost(175).requiredLevel(19)
            .cd(CoolDowns.FAST).rank(RankTypes.GREEN).essence(EssenceTypes.FLAME)
            .entityType(() -> BHTPvZEntityTypes.BLAZE_WART.get())
            .summonCard(() -> BHTPvZItems.BLAZE_WART_CARD.get())
            .enjoyCard(() -> BHTPvZItems.BLAZE_WART_ENJOY_CARD.get())
            .plantModel(() -> BlazeWartModel::new).scale(0.95f).placement(BHTPvZPlacements.SOUL_SAND)
            .commonSkill(Collections.singletonList(BHTPvZSkill.WART_HEAL_CD)));
    //草鱼
    public static final IPlantType GRASS_CARP = new BHTPvZPlants("grass_carp", new PlantFeatures()
            .cost(225).requiredLevel(23)
            .cd(CoolDowns.LITTLE_SLOW).rank(RankTypes.PURPLE).essence(EssenceTypes.ASSIST)
            .entityType(() -> BHTPvZEntityTypes.GRASS_CARP.get()).isWaterPlant()
            .summonCard(() -> BHTPvZItems.GRASS_CARP_CARD.get())
            .enjoyCard(() -> BHTPvZItems.GRASS_CARP_ENJOY_CARD.get())
            .plantModel(() -> GrassCarpModel::new).scale(1.4f)
            .commonSkill(Collections.singletonList(SkillTypes.PEA_DAMAGE)));
    //罐子草
    public static final IPlantType POT_GRASS = new BHTPvZPlants("pot_grass", new PlantFeatures()
            .cost(100).requiredLevel(9)
            .cd(CoolDowns.SLOW).rank(RankTypes.GREEN).essence(EssenceTypes.ASSIST)
            .summonCard(() -> BHTPvZItems.POT_GRASS_CARD.get())
            .enjoyCard(() -> BHTPvZItems.POT_GRASS_ENJOY_CARD.get())
            .plantBlock(BHTPvZBlocks.POT_GRASS));
    //自己模仿者
    public static final IPlantType SELF_IMITATER = new BHTPvZPlants("self_imitater", new PlantFeatures()
            .cost(175).requiredLevel(16)
            .cd(CoolDowns.SLOW).rank(RankTypes.PURPLE).essence(EssenceTypes.DEFENCE)
            .entityType(() -> BHTPvZEntityTypes.SELF_IMITATER.get())
            .summonCard(() -> BHTPvZItems.SELF_IMITATER_CARD.get())
            .enjoyCard(() -> BHTPvZItems.SELF_IMITATER_ENJOY_CARD.get())
            .plantModel(() -> SelfImitaterModel::new).scale(1.1f).commonSkill(Collections.singletonList(BHTPvZSkill.IMITATER_CHANCE)));
    //冰结射手
    public static final IPlantType RE_ICEPEA = new BHTPvZPlants("re_icepea", new PlantFeatures()
            .cost(275).requiredLevel(22)
            .cd(CoolDowns.VERY_FAST).rank(RankTypes.BLUE).essence(EssenceTypes.ICE)
            .entityType(() -> BHTPvZEntityTypes.RE_ICEPEA.get())
            .summonCard(() -> BHTPvZItems.RE_ICEPEA_CARD.get())
            .enjoyCard(() -> BHTPvZItems.RE_ICEPEA_ENJOY_CARD.get())
            .plantModel(() -> SnowPeaModel::new).scale(1.1f).commonSkill(Collections.singletonList(SkillTypes.PEA_DAMAGE)));
    //豌豆荚
    public static final IPlantType PEA_POD = new BHTPvZPlants("peapod", new PlantFeatures()
            .cost(125).requiredLevel(25)
            .cd(CoolDowns.VERY_FAST).rank(RankTypes.GREEN).essence(EssenceTypes.APPEASE)
            .entityType(() -> BHTPvZEntityTypes.PEA_POD.get())
            .summonCard(() -> BHTPvZItems.PEAPOD_CARD.get())
            .enjoyCard(() -> BHTPvZItems.PEAPOD_ENJOY_CARD.get())
            .plantModel(() -> PeaPodModel::new).scale(1.1f).commonSkill(Collections.singletonList(SkillTypes.PEA_DAMAGE)));
   //音爆菇
    public static final IPlantType SCULK_SHROOM = new BHTPvZPlants("sculk_shroom", new PlantFeatures()
            .cost(175).requiredLevel(29).isShroomPlant()
            .cd(CoolDowns.FAST).rank(RankTypes.BLACK).essence(EssenceTypes.TOXIC)
            .entityType(() -> BHTPvZEntityTypes.SCULK_SHROOM.get())
            .summonCard(() -> BHTPvZItems.SCULK_SHROOM_CARD.get())
            .enjoyCard(() -> BHTPvZItems.SCULK_SHROOM_ENJOY_CARD.get())
            .plantModel(() -> SculkShroomModel::new).scale(1.4f).commonSkill(Collections.singletonList(SkillTypes.SPORE_DAMAGE)));
   //原始蘑菇
    public static final IPlantType ORIGIN_SHROOM = new BHTPvZPlants("origin_shroom", new PlantFeatures()
            .cost(150).requiredLevel(18).isShroomPlant()
            .cd(CoolDowns.FAST).rank(RankTypes.GREEN).essence(EssenceTypes.TOXIC)
            .entityType(() -> BHTPvZEntityTypes.ORIGIN_SHROOM.get())
            .summonCard(() -> BHTPvZItems.ORIGIN_SHROOM_CARD.get())
            .enjoyCard(() -> BHTPvZItems.ORIGIN_SHROOM_ENJOY_CARD.get())
            .plantModel(() -> OriginShroomModel::new).scale(0.9f).commonSkill(Collections.singletonList(SkillTypes.SPORE_DAMAGE)));

    //蜜蜂射手
    public static final IPlantType BEE_SHOOTER = new BHTPvZPlants("bee_shooter", new PlantFeatures()
            .cost(175).requiredLevel(21)
            .cd(CoolDowns.VERY_FAST).rank(RankTypes.GREEN).essence(EssenceTypes.APPEASE)
            .entityType(() -> BHTPvZEntityTypes.BEE_SHOOTER.get())
            .summonCard(() -> BHTPvZItems.BEE_SHOOTER_CARD.get())
            .enjoyCard(() -> BHTPvZItems.BEE_SHOOTER_ENJOY_CARD.get())
            .plantModel(() -> BeeShooterModel::new).scale(1.0F)
            .commonSkill(Collections.singletonList(SkillTypes.PEA_DAMAGE)));

    //炮灰灌
    public static final IPlantType FODDER_BUSH = new BHTPvZPlants("fodder_bush", new PlantFeatures()
            .cost(0).requiredLevel(8)
            .cd(CoolDowns.SUPER_FAST).rank(RankTypes.WHITE).essence(EssenceTypes.ASSIST)
            .entityType(() -> BHTPvZEntityTypes.FODDER_BUSH.get())
            .summonCard(() -> BHTPvZItems.FODDER_BUSH_CARD.get())
            .enjoyCard(() -> BHTPvZItems.FODDER_BUSH_ENJOY_CARD.get())
            .plantModel(() -> FodderBushModel::new).scale(1.0F)
            .commonSkill(Collections.singletonList(SkillTypes.PLANT_MORE_LIFE)));

    //金西瓜
    public static final IPlantType GOLDEN_MELON_PULT = new BHTPvZPlants("golden_melon_pult", new PlantFeatures()
            .cost(625).requiredLevel(30)
            .cd(CoolDowns.VERY_SLOW).rank(RankTypes.GOLD).essence(EssenceTypes.ARMA)
            .entityType(() -> BHTPvZEntityTypes.GOLDEN_MELON_PULT.get())
            .summonCard(() -> BHTPvZItems.GOLDEN_MELON_PULT_CARD.get())
            .enjoyCard(() -> BHTPvZItems.GOLDEN_MELON_PULT_ENJOY_CARD.get())
            .plantModel(() -> GoldenMelonPultModel::new).scale(1.0F).upgradeFrom(() -> MELON_PULT)
            .commonSkill(Collections.singletonList(SkillTypes.MORE_MELON_DAMAGE)));
    //原始豌豆
    public static final IPlantType PRIMAL_PEA_SHOOTER = new BHTPvZPlants("primal_pea_shooter", new PlantFeatures()
            .cost(175).requiredLevel(27)
            .cd(CoolDowns.VERY_FAST).rank(RankTypes.BLUE).essence(EssenceTypes.APPEASE)
            .entityType(() -> BHTPvZEntityTypes.PRIMAL_PEA_SHOOTER.get())
            .summonCard(() -> BHTPvZItems.PRIMAL_PEA_SHOOTER_CARD.get())
            .enjoyCard(() -> BHTPvZItems.PRIMAL_PEA_SHOOTER_ENJOY_CARD.get())
            .plantModel(() -> PrimalPeaShooterModel::new).scale(1.0F)
            .commonSkill(Collections.singletonList(SkillTypes.PEA_DAMAGE)));
    //毒液豌豆
    public static final IPlantType GOO_PEA_SHOOTER = new BHTPvZPlants("goo_pea_shooter", new PlantFeatures()
            .cost(175).requiredLevel(28)
            .cd(CoolDowns.VERY_FAST).rank(RankTypes.BLUE).essence(EssenceTypes.TOXIC)
            .entityType(() -> BHTPvZEntityTypes.GOO_PEA_SHOOTER.get())
            .summonCard(() -> BHTPvZItems.GOO_PEA_SHOOTER_CARD.get())
            .enjoyCard(() -> BHTPvZItems.GOO_PEA_SHOOTER_ENJOY_CARD.get())
            .plantModel(() -> GooPeaShooterModel::new).scale(1.0F)
            .commonSkill(Collections.singletonList(SkillTypes.PEA_DAMAGE)));

    //棱镜草
    public static final IPlantType MAGNIFYING_GRASS = new BHTPvZPlants("magnifying_grass", new PlantFeatures()
            .cost(50).requiredLevel(42)
            .cd(CoolDowns.VERY_FAST).rank(RankTypes.BLUE).essence(EssenceTypes.ELECTRIC)
            .entityType(() -> BHTPvZEntityTypes.MAGNIFYING_GRASS.get())
            .summonCard(() -> BHTPvZItems.MAGNIFYING_GRASS_CARD.get())
            .enjoyCard(() -> BHTPvZItems.MAGNIFYING_GRASS_ENJOY_CARD.get())
            .plantModel(() -> MagnifyingGrassModel::new).scale(1.3F)
            .commonSkill(Arrays.asList(BHTPvZSkill.LIGHT_BEAM_DAMAGE,BHTPvZSkill.MAGNIFYING_GRASS_DAMAGE)));

    public static void register() {
        PVZAPI.get().registerPlantTypes(LIST);
    }

    private BHTPvZPlants(String name, PlantFeatures features) {
        super(name, features);
        LIST.add(this);
    }
    @Override
    public String getModID() {
        return BHTPvZ.MOD_ID;
    }

    @Override
    public int getSortPriority() {
        return 80;
    }

    @Override
    public String getCategoryName() {
        return "bhtpvz";
    }
}
