package com.zhilizhan.bhtpvz.common.entity.plant.toxic;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.common.entity.plant.toxic.FumeShroomEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.common.entity.bullet.SonicEntity;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class SculkShroomEntity extends FumeShroomEntity {

    public SculkShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public void shootBullet() {
        float now = this.yHeadRot;

        for(int i = 0; i < 8; ++i) {
            this.shootByAngle(now, 0.25F);
            now += 45.0F;
        }

        EntityUtil.playSound(this, SoundEvents.GENERIC_EXPLODE);
    }

    protected AbstractBulletEntity createBullet() {
        SonicEntity sonic = new SonicEntity(this.level, this);
        sonic.setInvisible(true);
        return sonic;
    }

    public void startShootAttack() {
        this.setAttackTime(1);
    }

    public int getSuperTimeLength() {
        return 100;
    }

    public int getShootCD() {
        return 60;
    }

    public float getAttackDamage() {
        return this.getSkillValue(SkillTypes.SPORE_DAMAGE)*1.5F;
    }

    public float getShootRange() {
        return 24.0F;
    }

    public EntitySize getDimensions(Pose poseIn) {
        return EntitySize.scalable(0.9F, 0.75F);
    }

    public IPlantType getPlantType() {
        return BHTPvZPlants.SCULK_SHROOM;
    }
}

