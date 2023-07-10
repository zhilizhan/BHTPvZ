package com.zhilizhan.bhtpvz.common.entity.plant.ice;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.common.entity.bullet.itembullet.PeaEntity;
import com.hungteen.pvz.common.entity.plant.ice.SnowPeaEntity;
import com.zhilizhan.bhtpvz.common.entity.bullet.itembullet.IcePeaEntity;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class ReIcePeaEntity extends SnowPeaEntity {
     public ReIcePeaEntity(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }
    @Override
    public void startShootAttack() {
        this.setAttackTime(2);
    }

    protected AbstractBulletEntity createBullet() {
         if(this.getAttackTime()==1) {
           return  new PeaEntity(this.level, this, this.getShootType(), this.getShootState());
         }
         return new IcePeaEntity(this.level,this);
    }
    public IPlantType getPlantType() {
        return BHTPvZPlants.RE_ICEPEA;
    }
}
