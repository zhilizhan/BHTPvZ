package com.zhilizhan.bhtpvz.common.impl.plant;

import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.client.model.entity.plant.defence.SteelPumpkinModel;
import com.zhilizhan.bhtpvz.client.model.entity.plant.fire.FirePeashooterModel;
import com.zhilizhan.bhtpvz.client.model.entity.plant.ice.IceCabbagePultModel;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;

import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.impl.CoolDowns;
import com.hungteen.pvz.common.impl.EssenceTypes;
import com.hungteen.pvz.common.impl.RankTypes;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.impl.plant.PlantType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class BHTPvZPlants extends PlantType {
    private static final List<IPlantType> LIST = new ArrayList<>();

    //钢南瓜
    public static final IPlantType STEEL_PUMPKIN = new BHTPvZPlants("steel_pumpkin", new PlantFeatures().cost(225).requiredLevel(35).cd(CoolDowns.SLOW).rank(RankTypes.BLUE).essence(EssenceTypes.DEFENCE).entityType(BHTPvZEntityTypes.STEEL_PUMPKIN::get).summonCard(BHTPvZItems.STEEL_PUMPKIN_CARD).enjoyCard(BHTPvZItems.STEEL_PUMPKIN_ENJOY_CARD).plantModel(() -> SteelPumpkinModel::new).scale(1.1f));

    //冰卷心菜
    public static final IPlantType ICE_CABBAGE_PULT = new BHTPvZPlants("ice_cabbage_pult", new PlantFeatures().cost(175).requiredLevel(17).cd(CoolDowns.VERY_FAST).rank(RankTypes.BLUE).essence(EssenceTypes.ICE).entityType(BHTPvZEntityTypes.ICE_CABBAGE_PULT::get).summonCard(BHTPvZItems.ICE_CABBAGE_PULT_CARD).enjoyCard(BHTPvZItems.ICE_CABBAGE_PULT_ENJOY_CARD).plantModel(() -> IceCabbagePultModel::new).scale(0.95f).commonSkill(Collections.singletonList(SkillTypes.MORE_CABBAGE_DAMAGE)));

    // 坚果保龄球
    public static final IPlantType NUT_BOWLING = new BHTPvZPlants("nut_bowling", new PlantFeatures().cost(50).requiredLevel(15).cd(CoolDowns.NORMAL).rank(RankTypes.BLUE).essence(EssenceTypes.ENFORCE).entityType(BHTPvZEntityTypes.NUT_BOWLING::get).summonCard(BHTPvZItems.NUT_BOWLING_CARD).enjoyCard(BHTPvZItems.NUT_BOWLING_ENJOY_CARD).scale(0.95f));

    //火焰豌豆
    public static final IPlantType FIRE_PEASHOOTER = new BHTPvZPlants("fire_peashooter", new PlantFeatures().cost(175).requiredLevel(17).cd(CoolDowns.VERY_FAST).rank(RankTypes.GREEN).essence(EssenceTypes.FLAME).entityType(BHTPvZEntityTypes.FIRE_PEASHOOTER::get).summonCard(BHTPvZItems.FIRE_PEASHOOTER_CARD).enjoyCard(BHTPvZItems.FIRE_PEASHOOTER_ENJOY_CARD).plantModel(() -> FirePeashooterModel::new).scale(1.0f).commonSkill(Collections.singletonList(SkillTypes.HEAT_PEA_RANGE)));
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