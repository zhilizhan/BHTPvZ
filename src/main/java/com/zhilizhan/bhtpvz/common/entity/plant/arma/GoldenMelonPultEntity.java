package com.zhilizhan.bhtpvz.common.entity.plant.arma;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.common.entity.plant.arma.MelonPultEntity;
import com.zhilizhan.bhtpvz.common.entity.bullet.GoldenMelonEntity;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class GoldenMelonPultEntity extends MelonPultEntity {
    public GoldenMelonPultEntity(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    protected PultBulletEntity createBullet() {
        return new GoldenMelonEntity(level, this);
    }
    public IPlantType getPlantType() {
        return BHTPvZPlants.GOLDEN_MELON_PULT;
    }
}
