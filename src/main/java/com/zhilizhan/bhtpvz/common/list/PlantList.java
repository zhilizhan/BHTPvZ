package com.zhilizhan.bhtpvz.common.list;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.utils.others.WeightList;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;

public class PlantList {
    public static final WeightList<IPlantType> PLANT = new WeightList<>();

    static {
        PLANT.addItem(BHTPvZPlants.CHORUS_FRUIT_PULT, 100);
        PLANT.addItem(BHTPvZPlants.ICE_CABBAGE_PULT, 90);
        PLANT.addItem(BHTPvZPlants.FIRE_PEASHOOTER, 70);
        PLANT.addItem(BHTPvZPlants.ROTATE_RADISH, 100);
        PLANT.addItem(BHTPvZPlants.STEEL_PUMPKIN, 100);
        PLANT.addItem(BHTPvZPlants.BLAZE_WART, 80);
        PLANT.addItem(PVZPlants.SUN_FLOWER, 90);
        PLANT.addItem(PVZPlants.WALL_NUT, 70);
        PLANT.addItem(PVZPlants.SNOW_PEA, 80);
        PLANT.addItem(PVZPlants.CHERRY_BOMB, 85);
        PLANT.addItem(PVZPlants.MELON_PULT, 70);
        PLANT.addItem(PVZPlants.JALAPENO, 85);
        PLANT.addItem(PVZPlants.ICE_SHROOM, 85);
    }
}
