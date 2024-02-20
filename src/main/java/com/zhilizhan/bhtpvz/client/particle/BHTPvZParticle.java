package com.zhilizhan.bhtpvz.client.particle;

import com.zhilizhan.bhtpvz.BHTPvZ;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BHTPvZParticle {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, BHTPvZ.MOD_ID);
    public static final RegistryObject<SimpleParticleType> GOLD_NUGGET = PARTICLE_TYPES.register("gold_nugget", () -> {
        return new SimpleParticleType(false);
    });
    public static final RegistryObject<SimpleParticleType> ORIGINAL_FUME = PARTICLE_TYPES.register("original_fume", () -> {
        return new SimpleParticleType(false);
    });
    public static final RegistryObject<SimpleParticleType> SONIC_BOOM = PARTICLE_TYPES.register("sonic_boom", () -> {
        return new SimpleParticleType(false);
    });
}
