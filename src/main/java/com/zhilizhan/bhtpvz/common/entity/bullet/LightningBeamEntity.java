package com.zhilizhan.bhtpvz.common.entity.bullet;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.Iterator;
import java.util.List;

public class LightningBeamEntity extends LightningBolt {
    private int life;
    public long seed;
    private int flashes;

    public LightningBeamEntity(EntityType<? extends LightningBolt> arg, Level arg2) {
        super(arg, arg2);
    }

    @Override
    public void tick() {
        if (this.life == 1) {
            this.level.playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.WEATHER, 10000.0F, 0.8F + this.random.nextFloat() * 0.2F);
            this.level.playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundEvents.LIGHTNING_BOLT_IMPACT, SoundSource.WEATHER, 2.0F, 0.5F + this.random.nextFloat() * 0.2F);
        }

        --this.life;
        if (this.life < 0) {
            if (this.flashes == 0) {
                this.remove();
            } else if (this.life < -this.random.nextInt(10)) {
                --this.flashes;
                this.life = 1;
                this.seed = this.random.nextLong();
            }
        }

        if (this.life >= 0) {
            if (!(this.level instanceof ServerLevel)) {
                this.level.setSkyFlashTime(2);
            } else  {
                double ran = 3.0;
                List<Entity> list = this.level.getEntities(this, new AABB(this.getX() - ran, this.getY() - ran, this.getZ() - ran, this.getX() + 1.0, this.getY() +ran, this.getZ() + ran), Entity::isAlive);
                Iterator var4 = list.iterator();

                while(var4.hasNext()) {
                    Entity entity = (Entity)var4.next();
                        entity.hurt(DamageSource.LIGHTNING_BOLT, 1);
                    }
            }
        }
        if (!this.level.isClientSide) {
            this.setSharedFlag(6, this.isGlowing());
        }

        this.baseTick();

    }

}
