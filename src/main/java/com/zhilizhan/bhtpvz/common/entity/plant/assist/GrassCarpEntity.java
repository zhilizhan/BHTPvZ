package com.zhilizhan.bhtpvz.common.entity.plant.assist;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.common.impl.BHTPvZSkill;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class GrassCarpEntity extends PVZPlantEntity {
    private static final int EFFECT_CD = 200;
    public GrassCarpEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new SwimGoal(this));
    }

    public void normalPlantTick() {
        super.normalPlantTick();

        if (!this.level.isClientSide && this.getExistTick() % EFFECT_CD == 0) {
            this.giveHealToFriendly();
        }
    }
    private void giveHealToFriendly() {
        float range = this.getEffectRange();
        EntityUtil.getFriendlyLivings(this, EntityUtil.getEntityAABB(this, range, range)).forEach((entity) -> {

            if (entity.getHealth()<entity.getMaxHealth()) {
                entity.heal(this.getAttackDamage());
            }
            if (entity instanceof GrassCarpEntity && entity!=this) {
                entity.kill();
            }

        });
    }

    public float getAttackDamage() {
        return this.getSkillValue(BHTPvZSkill.GRASS_CARP_DAMAGE);
    }

    public float getEffectRange() {
        return this.getSkillValue(BHTPvZSkill.GRASS_CARP_HEAL_RANGE);
    }
    public EntitySize getDimensions(@Nonnull Pose poseIn) {
        return EntitySize.scalable(0.8F, 0.8F);
    }

    @Override
    public IPlantType getPlantType() {
        return BHTPvZPlants.GRASS_CARP;
    }
}