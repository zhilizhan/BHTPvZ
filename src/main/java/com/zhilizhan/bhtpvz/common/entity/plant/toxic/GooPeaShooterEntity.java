package com.zhilizhan.bhtpvz.common.entity.plant.toxic;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.common.entity.plant.appease.PeaShooterEntity;
import com.zhilizhan.bhtpvz.common.entity.bullet.itembullet.GooPeaEntity;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class GooPeaShooterEntity extends PeaShooterEntity {
    public GooPeaShooterEntity(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    protected AbstractBulletEntity createBullet() {
        return new GooPeaEntity(this.level,this);
    }
    public IPlantType getPlantType() {
        return BHTPvZPlants.GOO_PEA_SHOOTER;
    }
}
