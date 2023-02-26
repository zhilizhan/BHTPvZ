package com.zhilizhan.bhtpvz.common.impl.plant;

import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.client.model.entity.plant.defence.SteelPumpkinModel;
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

    //Steel Pumpkin
    public static final IPlantType STEEL_PUMPKIN = new BHTPvZPlants("steel_pumpkin", new PlantFeatures().cost(225).requiredLevel(35).cd(CoolDowns.SLOW).rank(RankTypes.BLUE).essence(EssenceTypes.DEFENCE).entityType(BHTPvZEntityTypes.STEEL_PUMPKIN::get).summonCard(BHTPvZItems.STEEL_PUMPKIN_CARD).enjoyCard(BHTPvZItems.STEEL_PUMPKIN_ENJOY_CARD).plantModel(() -> SteelPumpkinModel::new).scale(1.1f));

    // Ice Cabbage
    public static final IPlantType ICE_CABBAGE_PULT = new BHTPvZPlants("ice_cabbage_pult", new PlantFeatures().cost(175).requiredLevel(17).cd(CoolDowns.VERY_FAST).rank(RankTypes.BLUE).essence(EssenceTypes.ICE).entityType(BHTPvZEntityTypes.ICE_CABBAGE_PULT::get).summonCard(BHTPvZItems.ICE_CABBAGE_PULT_CARD).enjoyCard(BHTPvZItems.ICE_CABBAGE_PULT_ENJOY_CARD).plantModel(() -> IceCabbagePultModel::new).scale(0.95f).commonSkill(Collections.singletonList(SkillTypes.MORE_CABBAGE_DAMAGE)));

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
