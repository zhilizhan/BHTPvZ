package com.zhilizhan.bhtpvz.common.entity.zombie;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantDefenderEntity;
import com.hungteen.pvz.common.entity.zombie.body.ZombieDropBodyEntity;
import com.hungteen.pvz.common.entity.zombie.zombotany.AbstractZombotanyEntity;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.remove.MetalTypes;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.interfaces.IHasMetal;
import com.zhilizhan.bhtpvz.common.impl.zombie.BHTPvZZombies;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import java.util.Objects;

public class SteelPumpkinZombieEntity extends AbstractZombotanyEntity implements IHasMetal {
    public SteelPumpkinZombieEntity(EntityType<? extends CreatureEntity> type, World level) {
        super(type, level);
    }

    @Override
    protected void initAttributes() {
        super.initAttributes();
        Objects.requireNonNull(this.getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(ZombieUtil.WALK_SLOW);
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
        return entity instanceof PlantDefenderEntity && ((PVZPlantEntity) entity).hasMetal();
    }

    @Override
    public boolean hasMetal() {
        return this.getOuterDefenceLife() > 0;
    }

    public static boolean canTargetPutLadder(Entity target) {
        // Can not put ladder or already has ladder on.
        if(!(target instanceof PVZPlantEntity) || hasLadderOnEntity(target)) {
            return false;
        }
        if(target instanceof PlantDefenderEntity) {
            return true;
        }
        return ((PVZPlantEntity) target).getOuterPlantInfo().isPresent();
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
    }

    @Override
    public float getLife() {
        return 125;
    }

    @Override
    public float getOuterLife() {
        return 175;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource source) {
        return SoundRegister.METAL_HIT.get();
    }
}
