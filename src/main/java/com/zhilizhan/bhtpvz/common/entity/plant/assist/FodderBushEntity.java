package com.zhilizhan.bhtpvz.common.entity.plant.assist;

import com.hungteen.pvz.api.interfaces.ICanBeAttracted;
import com.hungteen.pvz.api.interfaces.IHasWheel;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.interfaces.ICanAttract;
import com.zhilizhan.bhtpvz.common.damagesource.BHTPvZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.entity.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class FodderBushEntity extends PVZPlantEntity implements ICanAttract {

    public FodderBushEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public boolean canAttract(LivingEntity target) {
        return !(target instanceof ICanBeAttracted) || ((ICanBeAttracted) target).canBeAttractedBy(this);
    }

    public void attract(LivingEntity target) {
        if (target instanceof MobEntity) {
           if(EntityUtil.isEntityValid(target)) ((MobEntity)target).setTarget(this);
        }
    }

    public float getAttractRange() {
        return 3.5F;
    }

    public void die(DamageSource source) {
        super.die(source);

        if (!this.level.isClientSide  && source instanceof PVZEntityDamageSource && ((PVZEntityDamageSource)source).isEatDamage()) {
                int i = random.nextInt(100);
                Entity entity = source.getEntity();
            if (entity instanceof IHasWheel && i<23) {
                ((IHasWheel)entity).spikeWheelBy(this);
                this.kill();
            }else if (entity instanceof LivingEntity) {
                    LivingEntity living = (LivingEntity) entity;
                    if(i<33 && EntityUtil.isEntityValid(living) && !living.hasEffect(Effects.WEAKNESS)) {
                        living.addEffect(new EffectInstance(Effects.WEAKNESS, (int) (this.getLife()*8)));
                      }else if(i<66 && ! living.hasEffect(Effects.MOVEMENT_SLOWDOWN)){
                        living.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN,(int) (this.getLife()*8)));
                    }
                    PVZEntityDamageSource damageSource = BHTPvZEntityDamageSource.bush(this, this);
                living.hurt(damageSource,this.getLife()/4);
                }
            }

            EntityUtil.playSound(this, SoundRegister.HYPNO.get());
        }

    public boolean canStartSuperMode() {
        return false;
    }
    public float getLife() {
        return this.getSkillValue(SkillTypes.PLANT_MORE_LIFE);
    }
    public EntitySize getDimensions(@Nonnull Pose poseIn) {
        return new EntitySize(0.6F, 1.0F, false);
    }

    @Override
    public IPlantType getPlantType() {
        return BHTPvZPlants.FODDER_BUSH;
    }
}
