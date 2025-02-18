package com.zhilizhan.bhtpvz.common.entity.plant.toxic;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.common.entity.plant.toxic.FumeShroomEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.zhilizhan.bhtpvz.common.entity.bullet.itembullet.OriginFumeEntity;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class OriginShroomEntity extends FumeShroomEntity {
    public OriginShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }
    protected AbstractBulletEntity createBullet() {
        return new OriginFumeEntity(this.level,this);
    }
    @Override
    protected boolean shouldPlantRegularSleep() {
        return this.level.isNight();
    }
    public float getAttackDamage() {
        return this.getSkillValue(SkillTypes.SPORE_DAMAGE)*0.8F;
    }
    public IPlantType getPlantType() {
        return BHTPvZPlants.ORIGIN_SHROOM;
    }
}
