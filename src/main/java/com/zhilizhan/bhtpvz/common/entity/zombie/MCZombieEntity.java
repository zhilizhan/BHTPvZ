package com.zhilizhan.bhtpvz.common.entity.zombie;

import com.hungteen.pvz.common.entity.zombie.grass.NormalZombieEntity;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.zhilizhan.bhtpvz.common.impl.zombie.BHTPvZZombies;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class MCZombieEntity extends NormalZombieEntity {
    public MCZombieEntity(EntityType<? extends CreatureEntity> type, World level) {
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
    public int getArmorToughness() {
        return 2;
    }
    @Override
    public ZombieType getZombieType() {
        return BHTPvZZombies.MC_ZOMBIE;
    }

}
