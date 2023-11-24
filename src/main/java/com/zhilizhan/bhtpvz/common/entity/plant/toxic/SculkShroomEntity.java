package com.zhilizhan.bhtpvz.common.entity.plant.toxic;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.common.entity.plant.toxic.FumeShroomEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.common.entity.bullet.SonicEntity;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;

public class SculkShroomEntity extends FumeShroomEntity {

    public SculkShroomEntity(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    public void shootBullet() {
        float now = this.yHeadRot;

        for(int i = 0; i < 8; ++i) {
            this.shootByAngle(now, 0.25F);
            now += 45.0F;
        }

        EntityUtil.playSound(this, (SoundEvent) SoundEvents.LODESTONE_HIT);
    }

    protected AbstractBulletEntity createBullet() {
        SonicEntity sonic = new SonicEntity(this.level, this);
        sonic.setGlowing(true);
        return sonic;
    }

    public void startShootAttack() {
        this.setAttackTime(1);
    }

    public int getSuperTimeLength() {
        return 100;
    }

    public int getShootCD() {
        return 40;
    }

    public float getAttackDamage() {
        return this.getSkillValue(SkillTypes.SPORE_DAMAGE)*1.5F;
    }

    public float getShootRange() {
        return 24.0F;
    }

    public EntityDimensions getDimensions(Pose poseIn) {
        return EntityDimensions.scalable(0.9F, 0.8F);
    }

    public IPlantType getPlantType() {
        return BHTPvZPlants.SCULK_SHROOM;
    }
}

