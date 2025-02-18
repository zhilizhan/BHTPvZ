package com.zhilizhan.bhtpvz.client.particle;

import com.hungteen.pvz.client.particle.PVZNormalParticle;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;

public class GoldNuggetParticle extends PVZNormalParticle {
    public GoldNuggetParticle(ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(world, x, y, z, xSpeed, ySpeed, zSpeed);
        this.quadSize = 0.2F;
        this.lifetime = this.random.nextInt(20) + 10;
        this.hasPhysics = true;
        this.gravity = 0.1F;
        this.xd = (double)world.random.nextFloat() - 0.5;
        this.yd = (double)(-world.random.nextFloat());
        this.zd = (double)world.random.nextFloat() - 0.5;
    }

    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite sprite;

        public Factory(IAnimatedSprite sprite) {
            this.sprite = sprite;
        }

        public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            GoldNuggetParticle particle = new GoldNuggetParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.pickSprite(this.sprite);
            return particle;
        }

        private Factory() {
            throw new UnsupportedOperationException("Use the Factory(IAnimatedSprite sprite) constructor");
        }
    }
}
