package com.zhilizhan.bhtpvz.common.entity.plant.arma;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.common.entity.plant.arma.MelonPultEntity;
import com.hungteen.pvz.common.potion.EffectRegister;
import com.zhilizhan.bhtpvz.common.entity.bullet.GoldenMelonEntity;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class GoldenMelonPultEntity extends MelonPultEntity {
    public GoldenMelonPultEntity(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    public int getColdLvl() {
        return 7;
    }

    public int getColdTick() {
        return 80;
    }

    public Optional<MobEffectInstance> getColdEffect() {
        return Optional.ofNullable(new MobEffectInstance((MobEffect) EffectRegister.COLD_EFFECT.get(), this.getColdTick(), this.getColdLvl(), false, false));
    }

    public Optional<MobEffectInstance> getFrozenEffect() {
        return Optional.empty();
    }

    @Override
    protected PultBulletEntity createBullet() {
        return new GoldenMelonEntity(level, this);
    }
    public IPlantType getPlantType() {
        return BHTPvZPlants.GOLDEN_MELON_PULT;
    }
}
