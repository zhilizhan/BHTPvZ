package com.zhilizhan.bhtpvz;
import com.zhilizhan.bhtpvz.common.Item.ItemRegistry;

import com.zhilizhan.bhtpvz.common.block.BlockRegistry;
import com.zhilizhan.bhtpvz.common.world.biome.BiomeRegistry;
import com.zhilizhan.bhtpvz.common.world.biome.DecorationGenerate;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(BHTPvZ.MOD_ID)
public class BHTPvZ {
    public static final String MOD_ID = "bhtpvz";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public BHTPvZ() {
        IEventBus bus = MinecraftForge.EVENT_BUS;

        ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BlockRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BiomeRegistry.BIOMES.register(FMLJavaModLoadingContext.get().getModEventBus());
        bus.addListener(EventPriority.HIGH, DecorationGenerate::addOresToBiomes);
        bus.addListener(EventPriority.HIGH, DecorationGenerate::addTreesToBiomes);
        bus.addListener(EventPriority.HIGH, DecorationGenerate::addBlocksToBiomes);
        bus.register(BiomeRegistry.class);
    }

    public static final ItemGroup BHTPVZ = new ItemGroup("better_hung_teen_s_plants_vs_zombies") {
        @Override
        public ItemStack makeIcon() {return new ItemStack(ItemRegistry.BETTER_HUNG_TEEN_S_PLANTS_VS_ZONBIES.get());}
    };
}
