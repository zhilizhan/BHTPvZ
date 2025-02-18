package com.zhilizhan.bhtpvz.client.particle;

import com.zhilizhan.bhtpvz.BHTPvZ;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BHTPvZParticle {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, BHTPvZ.MOD_ID);
    public static final RegistryObject<BasicParticleType> GOLD_NUGGET = PARTICLE_TYPES.register("gold_nugget", () -> {
        return new BasicParticleType(false);
    });
    public static final RegistryObject<BasicParticleType> ORIGINAL_FUME = PARTICLE_TYPES.register("original_fume", () -> {
        return new BasicParticleType(false);
    });
    public static final RegistryObject<BasicParticleType> SONIC_BOOM = PARTICLE_TYPES.register("sonic_boom", () -> {
        return new BasicParticleType(false);
    });
}
