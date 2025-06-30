package com.zhilizhan.bhtpvz.client;

import com.zhilizhan.bhtpvz.client.particle.BHTPvZParticle;
import com.zhilizhan.bhtpvz.client.particle.GoldNuggetParticle;
import com.zhilizhan.bhtpvz.client.particle.OriginalFumeParticle;
import com.zhilizhan.bhtpvz.client.particle.SonicBoomParticle;
import com.zhilizhan.bhtpvz.client.render.tileentity.PotGrassTER;
import com.zhilizhan.bhtpvz.common.tileentity.BHTPvZTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

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

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientSetUpEvent(FMLClientSetupEvent event) {
        event.getMinecraftSupplier().get().tell(() -> {
            ClientRegistry.bindTileEntityRenderer(BHTPvZTileEntity.POT_GRASS.get(), PotGrassTER::new);
        });
    }
}
