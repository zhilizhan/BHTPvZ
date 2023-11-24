package com.zhilizhan.bhtpvz.common.entity.plant.defence;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.plant.base.PlantDefenderEntity;
import com.hungteen.pvz.common.item.spawn.card.PlantCardItem;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.WorldUtil;
import com.hungteen.pvz.utils.enums.PAZAlmanacs;
import com.mojang.datafixers.util.Pair;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;

import java.util.Arrays;
import java.util.List;

public class SelfImitaterEntity extends PlantDefenderEntity {
    protected int ImitaterChance = 5;
    public SelfImitaterEntity(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }
    @Override
    public float getLife() {
        if(ImitaterChance>0){
            return 350-this.getImitaterChance()*10;
        }
        return 300;
    }
    @Override
    public float getSuperLife() {
        return this.getLife();
    }
    protected int getSuperBonusChance() {
        return 3;
    }
    public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
        super.addAlmanacEntries(list);
        list.addAll(Arrays.asList(Pair.of(PAZAlmanacs.HEALTH, this.getLife()), Pair.of(PAZAlmanacs.AGAIN_CHANCE, this.getSuperBonusChance())));
    }
    public boolean isImitating() {
        return this.canNormalUpdate() && this.getHealth() / this.getMaxHealth() < 0.1F;
    }
    public boolean startImitat() {
        return this.canNormalUpdate() && this.getHealth() / this.getMaxHealth() < 0.2F;
    }

    protected void normalPlantTick() {
        if (isImitating()&&ImitaterChance>0) {
            if (!this.level.isClientSide) {
                EntityUtil.playSound(this, (SoundEvent) SoundRegister.WAKE_UP.get());
                this.imitate();
            }else { WorldUtil.spawnRandomSpeedParticle(this.level, ParticleTypes.EXPLOSION, this.position(), 0.01F);}
        }
    }
    public void imitate() {
        if(this.getImitaterChance()>0&&!this.level.isClientSide){
        this.getOwnerPlayer().ifPresent((player) -> {
            PlantCardItem.handlePlantEntity(player,this.getPlantType(), BHTPvZItems.SELF_IMITATER_CARD.get().getDefaultInstance(), this.blockPosition(), (plantEntity) -> {
                PlantUtil.copyPlantData(plantEntity, this);
                ((SelfImitaterEntity)plantEntity).setImitaterChance(this.getImitaterChance()-1);
                if (this.getVehicle() != null) {
                    this.stopRiding();
                    plantEntity.startRiding(this.getVehicle());
                }

                if (this.getTarget() != null) {
                    plantEntity.setTarget(this.getTarget());
                }
                 this.remove();
            });
        });}
    }

    public int getImitaterChance(){return this.ImitaterChance;}
    public void setImitaterChance(int ImitaterChance){this.ImitaterChance=ImitaterChance;}
    public void startSuperMode(boolean first) {
        super.startSuperMode(first);
        if (!this.level.isClientSide) {
            this.setImitaterChance(this.getImitaterChance()+ this.getSuperBonusChance());
        }

    }
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("imitater_chance")) {
            this.ImitaterChance = compound.getInt("imitater_chance");
        }

    }
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("imitater_chance", this.ImitaterChance);
    }
    @Override
    public EntityDimensions getDimensions(Pose poseIn) {
        return EntityDimensions.scalable(0.7F, 1.25F);
    }
    @Override
    public IPlantType getPlantType() {
        return BHTPvZPlants.SELF_IMITATER;
    }
}
