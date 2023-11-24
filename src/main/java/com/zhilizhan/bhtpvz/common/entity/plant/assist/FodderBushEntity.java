package com.zhilizhan.bhtpvz.common.entity.plant.assist;

import com.hungteen.pvz.api.interfaces.ICanBeAttracted;
import com.hungteen.pvz.api.interfaces.ICanBeCharmed;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.interfaces.ICanAttract;
import com.zhilizhan.bhtpvz.common.damagesource.BHTPvZEntityDamageSource;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;

public class FodderBushEntity extends PVZPlantEntity implements ICanAttract {

    public FodderBushEntity(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    public boolean canAttract(LivingEntity target) {
        return !(target instanceof ICanBeAttracted) || ((ICanBeAttracted) target).canBeAttractedBy(this);
    }

    public void attract(LivingEntity target) {
        if (target instanceof Mob) {
            ((Mob)target).setTarget(this);
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
                if (entity instanceof LivingEntity) {
                    if(i<33 && !((LivingEntity)entity).hasEffect(MobEffects.WEAKNESS)) {
                        ((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.WEAKNESS, (int) (this.getLife()*8)));
                      }else if(i<66 && ! ((LivingEntity)entity).hasEffect(MobEffects.MOVEMENT_SLOWDOWN)){
                        ((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,(int) (this.getLife()*8)));
                    }
                    PVZEntityDamageSource damageSource = BHTPvZEntityDamageSource.bush(this, this);
                    entity.hurt(damageSource,this.getLife()/4);
                }
            } else if (source.getEntity() instanceof ICanBeCharmed) {
                ((ICanBeCharmed)source.getEntity()).onCharmedBy(this);
            }

            EntityUtil.playSound(this, (SoundEvent) SoundRegister.HYPNO.get());
        }

    public boolean canStartSuperMode() {
        return false;
    }
    public float getLife() {
        return this.getSkillValue(SkillTypes.PLANT_MORE_LIFE);
    }
    public EntityDimensions getDimensions(Pose poseIn) {
        return new EntityDimensions(0.6F, 1.0F, false);
    }

    @Override
    public IPlantType getPlantType() {
        return BHTPvZPlants.FODDER_BUSH;
    }
}
