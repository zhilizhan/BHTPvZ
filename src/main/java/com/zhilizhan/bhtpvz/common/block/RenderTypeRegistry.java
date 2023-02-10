package com.zhilizhan.bhtpvz.common.block;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RenderTypeRegistry {
    @SubscribeEvent
    public static void onRenderTypeRegistry(FMLClientSetupEvent event){
        event.enqueueWork(()-> RenderTypeLookup.setRenderLayer(BlockRegistry.CHERRY_SAPLING.get(), RenderType.cutout()));//樱桃树树苗渲染
        event.enqueueWork(()-> RenderTypeLookup.setRenderLayer(BlockRegistry.STAR_FRUIT_SAPLING.get(), RenderType.cutout()));//杨桃树树苗渲染
        event.enqueueWork(()-> RenderTypeLookup.setRenderLayer(BlockRegistry.ORIGIN_MUSHROOM.get(), RenderType.cutout()));//原始蘑菇渲染
        event.enqueueWork(()-> RenderTypeLookup.setRenderLayer(BlockRegistry.PEPPER.get(), RenderType.cutout()));//辣椒作物渲染
        event.enqueueWork(()-> RenderTypeLookup.setRenderLayer(BlockRegistry.GARLIC.get(), RenderType.cutout()));//大蒜作物渲染
    }
}
