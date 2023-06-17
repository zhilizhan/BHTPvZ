package com.zhilizhan.bhtpvz.common.entity.zombie.bhtpvz;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.zhilizhan.bhtpvz.common.impl.zombie.BHTPvZZombies;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class RedEdgeZombieEntity extends PVZZombieEntity {

    public RedEdgeZombieEntity(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    protected float getLife() {
        return 74;
    }

    public boolean onFire() {
        return this.canNormalUpdate() && this.getHealth() / this.getMaxHealth() < 0.5F;
    }
    public boolean doHurtTarget(Entity target) {
        if (!super.doHurtTarget(target)) {
            return false;
        } else {
            if (target instanceof LivingEntity) {
               if(this.onFire()){
                  ((LivingEntity)target).setSecondsOnFire(5);
            }}

            return true;
        }
    }

    protected float getModifyAttackDamage(Entity entity, float f) {
        return this.onFire() ? 25 : 5;
    }
    public int getArmorToughness() {
        return 10;
    }
    @Override
    public ZombieType getZombieType() {
        return BHTPvZZombies.RED_EDGE_ZOMBIE;
    }


}
