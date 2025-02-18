package com.zhilizhan.bhtpvz.client.particle;

import com.hungteen.pvz.client.particle.PVZNormalParticle;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;

public class OriginalFumeParticle extends PVZNormalParticle {
    public OriginalFumeParticle(ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(world, x, y, z, xSpeed, ySpeed, zSpeed);
        this.quadSize = 0.3F;
        this.lifetime = this.random.nextInt(5) + 15;
        this.hasPhysics = false;
        this.gravity = 0.0F;
        this.xd /= 10.0;
        this.yd /= 10.0;
        this.zd /= 10.0;
    }

    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite sprite;

        public Factory(IAnimatedSprite sprite) {
            this.sprite = sprite;
        }

        public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            OriginalFumeParticle particle = new OriginalFumeParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.pickSprite(this.sprite);
            return particle;
        }

        private Factory() {
            throw new UnsupportedOperationException("Use the Factory(IAnimatedSprite sprite) constructor");
        }
    }
}
