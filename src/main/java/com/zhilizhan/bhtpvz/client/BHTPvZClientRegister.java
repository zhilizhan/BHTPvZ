package com.zhilizhan.bhtpvz.client;

import com.zhilizhan.bhtpvz.client.particle.BHTPvZParticle;
import com.zhilizhan.bhtpvz.client.particle.GoldNuggetParticle;
import com.zhilizhan.bhtpvz.client.particle.OriginalFumeParticle;
import com.zhilizhan.bhtpvz.client.particle.SonicBoomParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BHTPvZClientRegister {
    public BHTPvZClientRegister() {
    }
    @SubscribeEvent
    public static void registerFactories(ParticleFactoryRegisterEvent event) {
        ParticleManager manager = Minecraft.getInstance().particleEngine;
        manager.register(BHTPvZParticle.GOLD_NUGGET.get(), GoldNuggetParticle.Factory::new);
        manager.register(BHTPvZParticle.ORIGINAL_FUME.get(), OriginalFumeParticle.Factory::new);
        manager.register(BHTPvZParticle.SONIC_BOOM.get(), SonicBoomParticle.Factory::new);
    }

}
