package com.zhilizhan.bhtpvz.common.entity.plant.ice;

import com.hungteen.pvz.api.interfaces.IIceEffect;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.common.entity.bullet.itembullet.PeaEntity;
import com.hungteen.pvz.common.entity.plant.appease.PeaShooterEntity;
import com.hungteen.pvz.common.entity.plant.ice.SnowPeaEntity;
import com.hungteen.pvz.common.potion.EffectRegister;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.entity.EntityType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

import java.util.Optional;

public class ReIcePeaEntity extends SnowPeaEntity implements IIceEffect{
    private static final int FROZEN_TICK = 10;
    public ReIcePeaEntity(EntityType<? extends PeaShooterEntity> type, World level) {
        super(type, level);
    }
    @Override
    public void startShootAttack() {
        this.setAttackTime(2);
    }

    protected AbstractBulletEntity createBullet() {
         return new PeaEntity(this.level, this, this.getShootType(), this.getShootState());
    }
    @Override
    public Optional<EffectInstance> getFrozenEffect() {
        return this.getRandom().nextBoolean() ? Optional.of(new EffectInstance(EffectRegister.FROZEN_EFFECT.get(), FROZEN_TICK, 2,false,false)):Optional.empty();
    }
    protected PeaEntity.State getShootState() {
        return PeaEntity.State.ICE;
    }

    public IPlantType getPlantType() {
        return BHTPvZPlants.RE_ICEPEA;
    }
}
