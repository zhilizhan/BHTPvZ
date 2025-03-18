package com.zhilizhan.bhtpvz.common.entity.plant.arma;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.common.entity.plant.arma.MelonPultEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.zhilizhan.bhtpvz.common.entity.bullet.GoldenMelonEntity;
import com.zhilizhan.bhtpvz.common.impl.BHTPvZSkill;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class GoldenMelonPultEntity extends MelonPultEntity {
    public GoldenMelonPultEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected PultBulletEntity createBullet() {
        return new GoldenMelonEntity(level, this);
    }
    @Override
    public float getAttackDamage() {
        return this.getSkillValue(BHTPvZSkill.MORE_GOLDEN_MELON_DAMAGE);
    }
    @Override
    protected float getLife() {
        return this.getSkillValue(SkillTypes.PLANT_MORE_LIFE)*1.5F;
    }
    @Override
    public IPlantType getPlantType() {
        return BHTPvZPlants.GOLDEN_MELON_PULT;
    }
}
