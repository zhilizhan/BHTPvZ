package com.zhilizhan.bhtpvz.common.entity.plant.defence;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.plant.base.PlantDefenderEntity;
import com.hungteen.pvz.common.item.spawn.card.PlantCardItem;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.WorldUtil;
import com.hungteen.pvz.utils.enums.PAZAlmanacs;
import com.mojang.datafixers.util.Pair;
import com.zhilizhan.bhtpvz.common.impl.BHTPvZSkill;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public class SelfImitaterEntity extends PlantDefenderEntity {
    protected int ImitaterChance = (int) this.getSkillValue(BHTPvZSkill.IMITATER_CHANCE);
    public SelfImitaterEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }
    @Override
    public float getLife() {
        if(ImitaterChance>0){
            return 100+this.getImitaterChance()*10;
        }
        return 100;
    }
    @Override
    public float getSuperLife() {
        return this.getLife()/1.5F;
    }
    protected int getSuperBonusChance() {
        return 3;
    }
    public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
        super.addAlmanacEntries(list);
        list.addAll(Arrays.asList(Pair.of(PAZAlmanacs.HEALTH, this.getLife()), Pair.of(PAZAlmanacs.AGAIN_CHANCE, this.getSuperBonusChance())));
    }
    public boolean isImitating() {
        return this.canNormalUpdate() && this.getHealth() / this.getMaxHealth() <= 0.2F;
    }

    public boolean hurt(DamageSource source, float amount) {

        if (isImitating()&&ImitaterChance>0) {
            if (!this.level.isClientSide) {
                EntityUtil.playSound(this, SoundRegister.WAKE_UP.get());
                if(source instanceof PVZEntityDamageSource  && !((PVZEntityDamageSource)source).isCrushDamage()) {
                    this.imitate();
                }
            }else WorldUtil.spawnRandomSpeedParticle(this.level, ParticleTypes.EXPLOSION, this.position(), 0.01F);
        }
        return  super.hurt(source,amount);
    }
    public void imitate() {
        if(this.getImitaterChance()>0&&!this.level.isClientSide){
        this.getOwnerPlayer().ifPresent((player) -> PlantCardItem.handlePlantEntity(player,this.getPlantType(), BHTPvZItems.SELF_IMITATER_CARD.get().getDefaultInstance(), this.blockPosition(), (plantEntity) -> {
            PlantUtil.copyPlantData(plantEntity, this);
            if(plantEntity instanceof SelfImitaterEntity)((SelfImitaterEntity)plantEntity).setImitaterChance(this.getImitaterChance()-1);
            if (this.getVehicle() != null) {
                this.stopRiding();
                plantEntity.startRiding(this.getVehicle());
            }

            if (this.getTarget() != null) {
                plantEntity.setTarget(this.getTarget());
            }
             this.remove();
        }));}
    }

    public int getImitaterChance(){return this.ImitaterChance;}

    public void setImitaterChance(int ImitaterChance){this.ImitaterChance=ImitaterChance;}

    public void startSuperMode(boolean first) {
        super.startSuperMode(first);
        if (!this.level.isClientSide) {
            this.setImitaterChance(this.getImitaterChance()+ this.getSuperBonusChance());
        }
    }
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("imitater_chance")) {
            this.ImitaterChance = compound.getInt("imitater_chance");
        }

    }
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("imitater_chance", this.ImitaterChance);
    }
    @Override
    public EntitySize getDimensions(Pose poseIn) {
        return EntitySize.scalable(0.7F, 1.25F);
    }
    @Override
    public IPlantType getPlantType() {
        return BHTPvZPlants.SELF_IMITATER;
    }
}
