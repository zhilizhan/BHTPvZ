package com.zhilizhan.bhtpvz.common.entity.zombie.bhtpvz;

import com.zhilizhan.bhtpvz.common.impl.zombie.BHTPvZZombies;

import com.hungteen.pvz.common.entity.zombie.grass.NormalZombieEntity;
import com.hungteen.pvz.common.impl.zombie.ZombieType;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class MCZombieEntity extends NormalZombieEntity {
    public MCZombieEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override
    public float getLife() {
        return 20;
    }

    @Override
    protected float getWaterSlowDown() {
        return 0.91f;
    }

    @Override
    public ZombieType getZombieType() {
        return BHTPvZZombies.MC_ZOMBIE;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.ARMOR, 2.0);
    }
}
