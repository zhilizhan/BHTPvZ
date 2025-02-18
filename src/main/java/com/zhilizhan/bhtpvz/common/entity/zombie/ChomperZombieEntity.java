package com.zhilizhan.bhtpvz.common.entity.zombie;

import com.hungteen.pvz.common.entity.zombie.zombotany.AbstractZombotanyEntity;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.common.impl.zombie.BHTPvZZombies;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class ChomperZombieEntity extends AbstractZombotanyEntity {
    private static final DataParameter<Integer> REST_TICK = EntityDataManager.defineId(ChomperZombieEntity.class, DataSerializers.INT);
    public static final int ATTACK_ANIM_CD = 30;
    public ChomperZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    protected void initAttributes() {
        super.initAttributes();
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.25);
    }

    public void normalZombieTick() {
        super.normalZombieTick();
        if (!this.level.isClientSide && this.getTarget() != null) {
            this.lookControl.setLookAt(this.getTarget(), 30.0F, 30.0F);
        }
        if (this.isResting()) {
            this.setRestTick(this.getRestTick() - 1);
            return;
        }
        if (!this.level.isClientSide &&this.getRestTick()<=0 &&this.getTarget()!=null&& this.distanceTo(this.getTarget())<=3) {
            if (this.getAttackTime() < 15) {

                if (!EntityUtil.isEntityValid(this.getTarget())) {
                    this.setAttackTime(0);
                    return;
                }

                this.setAttackTime(this.getAttackTime() + 1);
                if (this.getAttackTime() == 15) {
                    this.performAttack();
                }
            } else {
                this.setAttackTime(this.getAttackTime() + 1);
                if (this.getAttackTime() > ATTACK_ANIM_CD) {
                    this.setAttackTime(0);
                }
            }
        }
    }
    protected void performAttack() {
        LivingEntity target = this.getTarget();

           if(EntityUtil.isEntityValid(target))this.doHurtTarget(target);
           this.setRestTick(this.getRestCD());

        EntityUtil.playSound(this, SoundRegister.BIG_CHOMP.get());
    }
    public int getRestCD() {
        return 800;
    }
    public boolean isResting() {
        return this.getRestTick() > 0;
    }

    public int getRestTick() {
        return this.entityData.get(REST_TICK);
    }

    public void setRestTick(int tick) {
        this.entityData.set(REST_TICK, tick);
    }
    @Override
    protected float getModifyAttackDamage(Entity entity, float f) {
        return !this.isResting() ? this.getEatDamage()*10 : this.getEatDamage();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(REST_TICK, 0);
    }
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("rest_tick")) {
            this.setRestTick(compound.getInt("rest_tick"));
        }

    }
    public float getEatDamage() {
        return 5.0F;
    }
    public float getLife() {
        return 44.0F;
    }
    public ZombieType getZombieType() {
        return BHTPvZZombies.CHOMPER_ZOMBIE;
    }


}
