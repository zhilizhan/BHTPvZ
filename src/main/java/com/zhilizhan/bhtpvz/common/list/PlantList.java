package com.zhilizhan.bhtpvz.common.list;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.impl.plant.CustomPlants;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.utils.others.WeightList;

public class PlantList {
    public static final WeightList<IPlantType> PLANT = new WeightList<>();
    public static final WeightList<IPlantType> WATER_PLANT = new WeightList<>();

    static {

        PLANT.addItem(PVZPlants.MELON_PULT, 100);
        PLANT.addItem(PVZPlants.SUN_FLOWER, 90);
        PLANT.addItem(PVZPlants.WALL_NUT, 70);
        PLANT.addItem(PVZPlants.SNOW_PEA, 80);
        PLANT.addItem(PVZPlants.CHERRY_BOMB, 85);
        PLANT.addItem(PVZPlants.MELON_PULT, 70);
        PLANT.addItem(PVZPlants.JALAPENO, 85);
        //水生植物
        WATER_PLANT.addItem(PVZPlants.CAT_TAIL, 70);
        WATER_PLANT.addItem(CustomPlants.WATER_GUARD, 90);
    }
}
