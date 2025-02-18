package com.zhilizhan.bhtpvz.client.particle;

import com.hungteen.pvz.client.particle.PVZNormalParticle;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;

public class SonicBoomParticle  extends PVZNormalParticle {

    public SonicBoomParticle(ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, IAnimatedSprite spriteSet) {
        super(world, x, y, z, xSpeed, ySpeed, zSpeed);
        this.quadSize = 0.25F;
        this.lifetime = 25;
        this.hasPhysics = false;
        this.gravity = 0.0F;
        this.xd /= 10.0;
        this.yd /= 10.0;
        this.zd /= 10.0;
        this.setSpriteFromAge(spriteSet);
    }

    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite sprite;

        public Factory(IAnimatedSprite sprite) {
            this.sprite = sprite;
        }

        public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            SonicBoomParticle particle = new SonicBoomParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.sprite);
            particle.pickSprite(this.sprite);

            return particle;
        }

        private Factory() {
            throw new UnsupportedOperationException("Use the Factory(IAnimatedSprite sprite) constructor");
        }
    }
}
