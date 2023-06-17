package com.zhilizhan.bhtpvz;

import com.ferreusveritas.dynamictrees.api.GatherDataHelper;
import com.ferreusveritas.dynamictrees.api.registry.RegistryHandler;
import com.zhilizhan.bhtpvz.common.block.BHTPvZBlocks;
import com.zhilizhan.bhtpvz.common.effect.BHTPvZMobEffects;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import com.zhilizhan.bhtpvz.common.impl.zombie.BHTPvZZombies;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import com.zhilizhan.bhtpvz.common.world.DecorationGenerate;
import com.zhilizhan.bhtpvz.common.world.biome.BHTPvZBiomes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
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
        //动态的树
        if(ModList.get().isLoaded("dynamictrees")){
        RegistryHandler.setup(MOD_ID);
        bus.addListener(this::gatherData);}
    }

    // 创造物品栏


    public static final CreativeModeTab BHTPVZ = new CreativeModeTab("better_hung_teen_plants_vs_zombies") {
        @OnlyIn(Dist.CLIENT)
        public ItemStack makeIcon() {
            return new ItemStack((ItemLike) BHTPvZItems.CHERRY.get());
        }
    };
    //动态的树
    private void gatherData(final GatherDataEvent event) {
        GatherDataHelper.gatherTagData(MOD_ID, event);
        //GatherDataHelper.gatherLootData(MOD_ID, event);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            BHTPvZBiomes.addBiomeTypes();
            BHTPvZBiomes.addBiomesToGeneration();
        });
    }

}
