package com.zhilizhan.bhtpvz.common.entity.plant.appease;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.common.entity.plant.appease.PeaShooterEntity;
import com.zhilizhan.bhtpvz.common.entity.bullet.itembullet.StonePeaEntity;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class PrimalPeaShooterEntity extends PeaShooterEntity {

    public PrimalPeaShooterEntity(EntityType<? extends PeaShooterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected AbstractBulletEntity createBullet() {
        return new StonePeaEntity(this.level, this,this.getStoneState());
    }

    @Override
    public void shootBullet() {
        if (this.isPlantInSuperMode()){
            if(this.tickCount%10==0) {this.performShoot(0.2, 0.0, -0.15, true, 0.0);}//1
        } else {
            this.performShoot(0.2, 0.0, -0.15, this.getAttackTime() == 1, 0.0);
        }
    }
    public int getSuperTimeLength() {
        return 40;
    }
    public void startShootAttack() {
        this.setAttackTime(1);
    }


    protected StonePeaEntity.State getStoneState() {
        return this.isPlantInSuperMode()? StonePeaEntity.State.FIRE: StonePeaEntity.State.NORMAL;
    }

    public IPlantType getPlantType() {
        return BHTPvZPlants.PRIMAL_PEA_SHOOTER;
    }
}
