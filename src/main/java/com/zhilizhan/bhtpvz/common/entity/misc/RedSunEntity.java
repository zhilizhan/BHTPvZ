package com.zhilizhan.bhtpvz.common.entity.misc;

import com.hungteen.pvz.common.entity.misc.drop.SunEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class RedSunEntity extends SunEntity {
    private Entity following;
    public RedSunEntity(EntityType<? extends Mob> type, Level worldIn) {
        super(type, worldIn);
        this.setAmount(this.getDefaultAmount());
        this.setNoGravity(true);

    }
    public Vec3 ColorBase = new Vec3(240.0, 24.0, 24.0);
    @Override
    protected int getDefaultAmount() {
        return -50;
    }
    public int getIcon() {
        int value = -(this.getAmount());
        return value < 6 ? 0 : (value < 16 ? 1 : (value < 26 ? 2 : 3));
    }
    public void tick() {
        super.tick();
        if (!this.onGround && !this.isInWater()) {
            double speedY = this.getDeltaMovement().y;
            if (speedY > -0.029999999329447746) {
                speedY -= 0.014999999664723873;
            } else {
                speedY = -0.029999999329447746;
            }

            this.setDeltaMovement(this.getDeltaMovement().x * 0.94, speedY, this.getDeltaMovement().z * 0.94);
        } else {
            this.setDeltaMovement(Vec3.ZERO);
        }

        if ((this.tickCount + this.getId()) % (this.following instanceof Player ? 200 : 50) == 0 || this.following != null && this.following.distanceToSqr(this) > 64.0) {
            this.following = this.level.getNearestPlayer(this, 32.0);

        if (this.following != null) {
            Vec3 vector3d = new Vec3(this.following.getX() - this.getX(), this.following.getY() + (double)this.following.getEyeHeight() / 2.0 - this.getY(), this.following.getZ() - this.getZ());
            double d1 = vector3d.lengthSqr();
            if (d1 < 64.0) {
                double d2 = 1.0 - Math.sqrt(d1) / 8.0;
                this.setDeltaMovement(this.getDeltaMovement().add(vector3d.normalize().scale(d2 * d2 * 0.5)));
            }
        }
        }
        if (this.following == null && this.getAmount() < 150 && (this.tickCount + this.getId()) % 50 == 0) {
            List<Entity> list = this.level.getEntities(this, this.getBoundingBox().inflate(2.0, 2.0, 2.0));

            for (Entity entity : list) {
                if (entity instanceof SunEntity && entity.getId() > this.getId()) {
                    this.following = entity;
                    break;
                }
            }
        }


        if (this.controller != null && (this.controller.isSpectator() || !this.controller.isAlive())) {
            this.controller = null;
        }

        if (this.controller == null) {
            this.ColorBase = new Vec3(240.0, 24.0, 24.0);
            this.ColorChange = new Vec3(0.0, 25.0, 15.0);
        }

    }
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes();
    }


}
