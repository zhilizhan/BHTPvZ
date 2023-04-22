package com.zhilizhan.bhtpvz.common.entity.plant.assist;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class WaterPotEntity extends PVZPlantEntity {
    public WaterPotEntity(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    public IPlantType getPlantType() {
        return BHTPvZPlants.WATER_POT;
    }

    public int getSuperTimeLength() {
        return 0;
    }
}