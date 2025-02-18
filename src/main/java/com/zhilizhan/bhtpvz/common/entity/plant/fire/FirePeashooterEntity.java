package com.zhilizhan.bhtpvz.common.entity.plant.fire;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.common.entity.bullet.itembullet.PeaEntity;
import com.hungteen.pvz.common.entity.plant.appease.PeaShooterEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.utils.MathUtil;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.world.World;

public class FirePeashooterEntity extends PeaShooterEntity {
    public FirePeashooterEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public void shootBullet() {
        if (this.isPlantInSuperMode()) {
            int cnt = this.getSuperShootCount();

            for(int i = 0; i < cnt; ++i) {
                float offset = MathUtil.getRandomFloat(this.getRandom()) / 3.0F;
                float offsetH = MathUtil.getRandomFloat(this.getRandom()) / 3.0F;
                this.performShoot(0.2, offset, offsetH, this.getExistTick() % 10 == 0, 0.0);
            }
        } else {
            this.performShoot(0.2, 0.0, 0.0, this.getAttackTime() == 1, 0.0);
        }

    }

    protected AbstractBulletEntity createBullet() {
        return new PeaEntity(this.level, this, this.getShootType(), this.getShootState());
    }

    public float getAttackDamage() {
        return this.getSkillValue(SkillTypes.PEA_DAMAGE);
    }

    public int getSuperShootCount() {
        return MathUtil.getRandomMinMax(this.getRandom(), 1, 2);
    }

    protected PeaEntity.Type getShootType() {
        return PeaEntity.Type.NORMAL;
    }

    protected PeaEntity.State getShootState() {
        return PeaEntity.State.FIRE;
    }

    public void startShootAttack() {
        this.setAttackTime(1);
    }

    public int getSuperTimeLength() {
        return 100;
    }

    public EntitySize getDimensions(Pose poseIn) {
        return EntitySize.scalable(0.7F, 1.3F);
    }

    public IPlantType getPlantType() {
        return BHTPvZPlants.FIRE_PEASHOOTER;
    }
}
