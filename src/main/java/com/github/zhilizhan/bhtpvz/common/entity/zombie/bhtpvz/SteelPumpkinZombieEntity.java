package com.github.zhilizhan.bhtpvz.common.entity.zombie.bhtpvz;

import com.github.zhilizhan.bhtpvz.common.impl.zombie.BHTPvZZombies;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantDefenderEntity;
import com.hungteen.pvz.common.entity.zombie.base.DefenceZombieEntity;
import com.hungteen.pvz.common.entity.zombie.body.ZombieDropBodyEntity;
import com.hungteen.pvz.common.entity.zombie.part.PVZHealthPartEntity;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.remove.MetalTypes;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.interfaces.IHasMetal;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

public class SteelPumpkinZombieEntity extends DefenceZombieEntity implements IHasMetal {
    public SteelPumpkinZombieEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override
    public void resetParts() {
        removeParts();
        this.part = new PVZHealthPartEntity(this, 1f, 1.7f);
        this.part.setOwner(this);
    }

    @Override
    protected void initAttributes() {
        super.initAttributes();
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.WALK_SLOW);
    }

    @Override
    public ZombieType getZombieType() {
        return BHTPvZZombies.STEEL_PUMPKIN_ZOMBIE;
    }

    @Override
    public MetalTypes getMetalType() {
        return MetalTypes.LADDER;
    }

    private static boolean hasLadderOnEntity(Entity entity) {
        if(! (entity instanceof PVZPlantEntity)) {
            return false;
        }
        return ((PVZPlantEntity) entity).hasMetal();
    }

    @Override
    public boolean hasMetal() {
        return this.getOuterDefenceLife() > 0;
    }

    public static boolean canTargetPutLadder(Entity target) {
        // Can not put ladder or already has ladder on.
        if(! (target instanceof PVZPlantEntity plant) || hasLadderOnEntity(target)) {
            return false;
        }
        if(target instanceof PlantDefenderEntity) {
            return true;
        }
        return plant.getOuterPlantInfo().isPresent();
    }

    public void putLadderOn(Entity entity) {
        if(entity instanceof PVZPlantEntity) {
            ((PVZPlantEntity) entity).increaseMetal();
        }
        this.decreaseMetal();
    }

    @Override
    public boolean doHurtTarget(Entity entityIn) {
        if(!level.isClientSide) {
            this.heal(20);
        }
        if(this.hasMetal() && canTargetPutLadder(entityIn)) {
            this.putLadderOn(entityIn);
        }
        return super.doHurtTarget(entityIn);
    }

    @Override
    public boolean canLostHand() {
        return super.canLostHand() && ! this.hasMetal();
    }

    @Override
    protected void setBodyStates(ZombieDropBodyEntity body) {
        super.setBodyStates(body);
        body.setHandDefence(this.hasMetal());
    }

    @Override
    public void decreaseMetal() {
        this.setOuterDefenceLife(0);
    }

    @Override
    public void increaseMetal() {
        this.setOuterDefenceLife(this.getOuterLife());
        this.resetParts();
    }

    @Override
    public float getLife() {
        return 100;
    }

    @Override
    public float getOuterLife() {
        return 225;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource source) {
        return SoundRegister.METAL_HIT.get();
    }
}
