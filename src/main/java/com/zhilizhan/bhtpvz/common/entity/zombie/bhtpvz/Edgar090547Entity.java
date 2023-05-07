package com.zhilizhan.bhtpvz.common.entity.zombie.bhtpvz;

import com.zhilizhan.bhtpvz.common.impl.zombie.BHTPvZZombies;

import com.hungteen.pvz.common.entity.plant.spear.SpikeRockEntity;
import com.hungteen.pvz.common.entity.zombie.roof.GargantuarEntity;
import com.hungteen.pvz.common.impl.zombie.ZombieType;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

import net.minecraftforge.event.ForgeEventFactory;

public class Edgar090547Entity extends GargantuarEntity {
    public Edgar090547Entity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        this.setIsWholeBody();
    }

    @Override
    public ZombieType getZombieType() {
        return BHTPvZZombies.EDGAR_090547;
    }

    @Override
    public float getLife() {
        return 300.0f;
    }

    @Override
    public boolean canPAZTarget(Entity target) {
        if (target instanceof SpikeRockEntity) {
            return true;
        }
        return super.canPAZTarget(target);
    }

    @Override
    public EntityDimensions getDimensions(Pose poseIn) {
        if (this.isMiniZombie()) {
            return EntityDimensions.scalable(0.3f, 1.0f);
        }
        return EntityDimensions.scalable(0.4f, 2.0f);
    }

    // Explosion
     @Override
    protected void dropAllDeathLoot(DamageSource damageSourceIn) {
        Explosion.BlockInteraction mode = ForgeEventFactory.getMobGriefingEvent(this.level, this) ? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.NONE;
        this.level.explode(this, getX(), getY(), getZ(), 3.5f, Explosion.BlockInteraction.NONE);
        super.dropAllDeathLoot(damageSourceIn);
    }
}
