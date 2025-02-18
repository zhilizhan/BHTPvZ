package com.zhilizhan.bhtpvz.common.util;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.utils.AlgorithmUtil;

public class BHTPVZUtils {
    public final static int STEEL_PUMPKIN_FLAG = 5;//

    public static boolean getSteelPumpkin(PVZPlantEntity plant) {
        return AlgorithmUtil.BitOperator.hasBitOne(plant.getPAZState(), STEEL_PUMPKIN_FLAG);
    }

    public static void setSteelPumpkin(PVZPlantEntity plant,boolean flag) {
        AlgorithmUtil.BitOperator.setBit(plant.getPAZState(), STEEL_PUMPKIN_FLAG, flag);
    }
}
