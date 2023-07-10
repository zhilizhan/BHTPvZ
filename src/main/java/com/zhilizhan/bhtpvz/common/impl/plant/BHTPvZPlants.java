package com.zhilizhan.bhtpvz.common.impl.plant;

import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.client.model.entity.plant.defence.WallNutModel;
import com.hungteen.pvz.client.model.entity.plant.ice.SnowPeaModel;
import com.hungteen.pvz.common.impl.CoolDowns;
import com.hungteen.pvz.common.impl.EssenceTypes;
import com.hungteen.pvz.common.impl.RankTypes;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.impl.plant.PlantType;
import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.client.model.entity.plant.appease.GrassCarpModel;
import com.zhilizhan.bhtpvz.client.model.entity.plant.arma.BurstKernelPultModel;
import com.zhilizhan.bhtpvz.client.model.entity.plant.arma.ChorusFruitPultModel;
import com.zhilizhan.bhtpvz.client.model.entity.plant.defence.SelfImitaterModel;
import com.zhilizhan.bhtpvz.client.model.entity.plant.defence.SteelPumpkinModel;
import com.zhilizhan.bhtpvz.client.model.entity.plant.enforce.RotateRadishModel;
import com.zhilizhan.bhtpvz.client.model.entity.plant.fire.BlazeWartModel;
import com.zhilizhan.bhtpvz.client.model.entity.plant.fire.FirePeashooterModel;
import com.zhilizhan.bhtpvz.client.model.entity.plant.ice.IceCabbagePultModel;
import com.zhilizhan.bhtpvz.common.block.BHTPvZBlocks;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.entity.plant.defence.SteelPumpkinEntity;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class BHTPvZPlants extends PlantType {
    private static final List<IPlantType> LIST = new ArrayList<>();

    //钢南瓜
    public static final IPlantType STEEL_PUMPKIN = new BHTPvZPlants("steel_pumpkin", new PlantFeatures()
            .cost(225).requiredLevel(35)
            .cd(CoolDowns.VERY_SLOW).rank(RankTypes.BLUE).essence(EssenceTypes.DEFENCE)
            .entityType(() -> BHTPvZEntityTypes.STEEL_PUMPKIN.get())
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
            .commonSkill(Collections.singleton(SkillTypes.MORE_CABBAGE_DAMAGE)));
    //坚果保龄球
    public static final IPlantType NUT_BOWLING = new BHTPvZPlants("nut_bowling", new PlantFeatures()
            .cost(50).requiredLevel(15)
            .cd(CoolDowns.FAST).rank(RankTypes.BLUE).essence(EssenceTypes.ENFORCE)
            .entityType(() -> BHTPvZEntityTypes.NUT_BOWLING.get())
            .summonCard(() -> BHTPvZItems.NUT_BOWLING_CARD.get())
            .enjoyCard(() -> BHTPvZItems.NUT_BOWLING_ENJOY_CARD.get())
            .plantModel(() -> WallNutModel::new).scale(0.95F));
    //火焰豌豆
    public static final IPlantType FIRE_PEASHOOTER = new BHTPvZPlants("fire_peashooter", new PlantFeatures()
            .cost(175).requiredLevel(17)
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
            .plantBlock(BHTPvZBlocks.WATER_POT));

    //紫颂果
    public static final IPlantType CHORUS_FRUIT_PULT = new BHTPvZPlants("chorus_fruit_pult", new PlantFeatures()
            .cost(175).requiredLevel(21)
            .cd(CoolDowns.VERY_FAST).rank(RankTypes.PURPLE).essence(EssenceTypes.ARMA)
            .entityType(() -> BHTPvZEntityTypes.CHORUS_FRUIT_PULT.get())
            .summonCard(() -> BHTPvZItems.CHORUS_FRUIT_PULT_CARD.get())
            .enjoyCard(() -> BHTPvZItems.CHORUS_FRUIT_PULT_ENJOY_CARD.get())
            .plantModel(() -> ChorusFruitPultModel::new).scale(0.95F)
            .commonSkill(Collections.singletonList(SkillTypes.MORE_KERNEL_DAMAGE)));
    //转转萝卜
    public static final IPlantType ROTATE_RADISH = new BHTPvZPlants("rotate_radish", new PlantFeatures()
            .cost(150).requiredLevel(20)
            .cd(CoolDowns.FAST).rank(RankTypes.BLUE).essence(EssenceTypes.ENFORCE)
            .entityType(() -> BHTPvZEntityTypes.ROTATE_RADISH.get())
            .summonCard(() -> BHTPvZItems.ROTATE_RADISH_CARD.get())
            .enjoyCard(() -> BHTPvZItems.ROTATE_RADISH_ENJOY_CARD.get())
            .plantModel(() -> RotateRadishModel::new).scale(0.95F)
            .commonSkill(Collections.singletonList(SkillTypes.MORE_SWING_DAMAGE)));

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
            .plantModel(() -> BlazeWartModel::new).scale(0.95f)
            .commonSkill(Collections.singletonList(SkillTypes.MORE_SWING_DAMAGE)));
    //草鱼

    public static final IPlantType GRASS_CARP = new BHTPvZPlants("grass_carp", new PlantFeatures()
            .cost(225).requiredLevel(23)
            .cd(CoolDowns.FAST).rank(RankTypes.GREEN).essence(EssenceTypes.APPEASE)
            .entityType(() -> BHTPvZEntityTypes.GRASS_CARP.get()).isWaterPlant()
            .summonCard(() -> BHTPvZItems.GRASS_CARP_CARD.get())
            .enjoyCard(() -> BHTPvZItems.GRASS_CARP_ENJOY_CARD.get())
            .plantModel(() -> GrassCarpModel::new).scale(1.4f)
            .commonSkill(Collections.singletonList(SkillTypes.PEA_DAMAGE)));
    //罐子草

    public static final IPlantType POT_GRASS = new BHTPvZPlants("pot_grass", new PlantFeatures()
            .cost(75).requiredLevel(9)
            .cd(CoolDowns.SLOW).rank(RankTypes.GREEN).essence(EssenceTypes.ASSIST)
            .summonCard(() -> BHTPvZItems.POT_GRASS_CARD.get())
            .enjoyCard(() -> BHTPvZItems.POT_GRASS_ENJOY_CARD.get())
            .plantBlock(BHTPvZBlocks.POT_GRASS));
    //自己模仿者
    public static final IPlantType SELF_IMITATER = new BHTPvZPlants("self_imitater", new PlantFeatures()
            .cost(125).requiredLevel(16)
            .cd(CoolDowns.SLOW).rank(RankTypes.PURPLE).essence(EssenceTypes.DEFENCE)
            .entityType(() -> BHTPvZEntityTypes.SELF_IMITATER.get())
            .summonCard(() -> BHTPvZItems.SELF_IMITATER_CARD.get())
            .enjoyCard(() -> BHTPvZItems.SELF_IMITATER_ENJOY_CARD.get())
            .plantModel(() -> SelfImitaterModel::new).scale(1.1f).commonSkill(Collections.singletonList(SkillTypes.MORE_GARLIC_LIFE)));

    public static final IPlantType RE_ICEPEA = new BHTPvZPlants("re_icepea", new PlantFeatures()
            .cost(275).requiredLevel(22)
            .cd(CoolDowns.SLOW).rank(RankTypes.BLUE).essence(EssenceTypes.ICE)
            .entityType(() -> BHTPvZEntityTypes.RE_ICEPEA.get())
            .summonCard(() -> BHTPvZItems.RE_ICEPEA_CARD.get())
            .enjoyCard(() -> BHTPvZItems.RE_ICEPEA_ENJOY_CARD.get())
            .plantModel(() -> SnowPeaModel::new).scale(1.1f).commonSkill(Collections.singletonList(SkillTypes.PEA_DAMAGE)));


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
