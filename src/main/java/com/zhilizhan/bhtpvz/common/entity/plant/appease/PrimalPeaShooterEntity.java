package com.zhilizhan.bhtpvz.common.entity.plant.appease;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.common.entity.plant.appease.PeaShooterEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.utils.MathUtil;
import com.zhilizhan.bhtpvz.common.entity.bullet.StonePeaEntity;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class PrimalPeaShooterEntity extends PeaShooterEntity {
    public PrimalPeaShooterEntity(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }
    @Override
    protected AbstractBulletEntity createBullet() {
        return new StonePeaEntity(this.level, this,this.getStoneState());
    }
    @Override
    public float getAttackDamage() {
        return this.getSkillValue(SkillTypes.PEA_DAMAGE);
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

    public int getSuperShootCount() {
        return MathUtil.getRandomMinMax(this.getRandom(), 1, 2);
    }

    protected StonePeaEntity.State getStoneState() {
        return this.isPlantInSuperMode()? StonePeaEntity.State.FIRE: StonePeaEntity.State.NORMAL;
    }

    public IPlantType getPlantType() {
        return BHTPvZPlants.PRIMAL_PEA_SHOOTER;
    }
}
