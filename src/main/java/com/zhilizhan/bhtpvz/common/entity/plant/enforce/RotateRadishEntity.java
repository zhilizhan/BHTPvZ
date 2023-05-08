package com.zhilizhan.bhtpvz.common.entity.plant.enforce;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.ai.goal.target.PVZNearestTargetGoal;
import com.hungteen.pvz.common.entity.plant.base.PlantCloserEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.PAZAlmanacs;
import com.mojang.datafixers.util.Pair;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

import java.util.Arrays;
import java.util.List;

public class RotateRadishEntity extends PlantCloserEntity {

    public RotateRadishEntity(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    protected void registerGoals() {
        super.registerGoals();
       this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, false, false, 3.0F, 3.0F));
    }
    @Override
    public float getLife() {
        return 80;
    }

    public void focusOnTarget(LivingEntity target1) {
        super.focusOnTarget(target1);
        if (this.getAttackTime() == 1) {
            float range = 2.0F;
            EntityUtil.getTargetableEntities(this, EntityUtil.getEntityAABB(this, range, range)).forEach((target) -> {
                target.setDeltaMovement(target.getDeltaMovement().add(0.0, 0.0, -2.0));
            });
            for(int i = 0; i < 3; ++i) {
                EntityUtil.spawnParticle(this, 7);
            }

            EntityUtil.playSound(this, (SoundEvent)SoundRegister.SWING.get());
        }

    }

    public void performAttack(LivingEntity target1) {
        for(int i = 0; i < 3; ++i) {
            EntityUtil.spawnParticle(this, 7);
        }

        float range = 3.0F;
        EntityUtil.getTargetableEntities(this, EntityUtil.getEntityAABB(this, range, range)).forEach((target) -> {
            target.hurt(PVZEntityDamageSource.normal(this), this.getAttackDamage());
            target.setDeltaMovement(target.getDeltaMovement().add(0.0, 0.0, -2.0));
        });
        EntityUtil.playSound(this, (SoundEvent)SoundRegister.SWING.get());
    }

    //大招
    protected void normalPlantTick() {
        super.normalPlantTick();
        if (!this.level.isClientSide && this.isPlantInSuperMode() && this.getSuperTime() % 5 == 0) {
            float range = 5.0F;
            EntityUtil.getTargetableEntities(this, EntityUtil.getEntityAABB(this, range, range)).forEach((target) -> {
                target.hurt(PVZEntityDamageSource.normal(this), this.getAttackDamage() * 5.0F);
                EntityUtil.spawnParticle(target, 7);
                EntityUtil.playSound(this, (SoundEvent)SoundRegister.SWING.get());
            });
        }

    }


    public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
        super.addAlmanacEntries(list);
        list.addAll(Arrays.asList(Pair.of(PAZAlmanacs.ATTACK_DAMAGE, this.getAttackDamage()), Pair.of(PAZAlmanacs.ATTACK_CD, this.getAttackCD())));
    }

    public int getAttackCD() {
        return 40;
    }
    public float getAttackDamage() {
        return this.getSkillValue(SkillTypes.MORE_SWING_DAMAGE)/1.5F;
    }

    public int getSuperTimeLength() {
        return 120;
    }
    public IPlantType getPlantType() {
        return BHTPvZPlants.ROTATE_RADISH;

}}

