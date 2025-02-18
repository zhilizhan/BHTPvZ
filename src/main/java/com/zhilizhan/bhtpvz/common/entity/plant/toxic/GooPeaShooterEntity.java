package com.zhilizhan.bhtpvz.common.entity.plant.toxic;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.common.entity.plant.appease.PeaShooterEntity;
import com.zhilizhan.bhtpvz.common.entity.bullet.itembullet.GooPeaEntity;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;


public class GooPeaShooterEntity extends PeaShooterEntity {
    public GooPeaShooterEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }
    protected AbstractBulletEntity createBullet() {
        return new GooPeaEntity(this.level,this);
    }

    public IPlantType getPlantType() {
        return BHTPvZPlants.GOO_PEA_SHOOTER;
    }
}
