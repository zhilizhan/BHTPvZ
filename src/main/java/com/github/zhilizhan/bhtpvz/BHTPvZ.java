package com.github.zhilizhan.bhtpvz;

import com.github.zhilizhan.bhtpvz.common.block.BHTPvZBlocks;
import com.github.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.github.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import com.github.zhilizhan.bhtpvz.common.impl.zombie.BHTPvZZombies;
import com.github.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import com.github.zhilizhan.bhtpvz.common.effect.BHTPvZMobEffects;
import com.github.zhilizhan.bhtpvz.common.world.biome.BHTPvZBiomes;
import com.github.zhilizhan.bhtpvz.common.world.DecorationGenerate;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BHTPvZ.MOD_ID)
@Mod.EventBusSubscriber(modid = BHTPvZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BHTPvZ {
    public static final String MOD_ID = "bhtpvz";

    // 事件总线
    public BHTPvZ() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BHTPvZItems.ITEMS.register(bus);
        BHTPvZBlocks.BLOCKS.register(bus);
        BHTPvZMobEffects.MOB_EFFECTS.register(bus);
        BHTPvZEntityTypes.ENTITY_TYPES.register(bus);
        BHTPvZBiomes.BIOMES.register(bus);
        BHTPvZPlants.register();
        BHTPvZZombies.register();
        bus.addListener(this::commonSetup);
        IEventBus bus2 = MinecraftForge.EVENT_BUS;
        bus2.addListener(EventPriority.HIGH, DecorationGenerate::addOresToBiomes);
        bus2.addListener(EventPriority.HIGH, DecorationGenerate::addTreesToBiomes);
        bus2.addListener(EventPriority.HIGH, DecorationGenerate::addBlocksToBiomes);
    }

    // 创造物品栏
    public static final CreativeModeTab BHTPVZ = new CreativeModeTab("better_hung_teen_s_plants_vs_zombies") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(BHTPvZItems.CHERRY.get());
        }
    };

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            BHTPvZBiomes.addBiomeTypes();
            BHTPvZBiomes.addBiomesToGeneration();
        });
    }
}
