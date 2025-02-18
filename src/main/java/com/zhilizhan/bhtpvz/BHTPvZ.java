package com.zhilizhan.bhtpvz;

import com.ferreusveritas.dynamictrees.api.GatherDataHelper;
import com.ferreusveritas.dynamictrees.api.registry.RegistryHandler;
import com.ferreusveritas.dynamictrees.blocks.leaves.LeavesProperties;
import com.ferreusveritas.dynamictrees.blocks.rootyblocks.SoilProperties;
import com.ferreusveritas.dynamictrees.trees.Family;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.zhilizhan.bhtpvz.client.particle.BHTPvZParticle;
import com.zhilizhan.bhtpvz.common.block.BHTPvZBlocks;
import com.zhilizhan.bhtpvz.common.effect.BHTPvZMobEffects;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.event.LivingEvents;
import com.zhilizhan.bhtpvz.common.impl.BHTPvZSkill;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import com.zhilizhan.bhtpvz.common.impl.zombie.BHTPvZZombies;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import com.zhilizhan.bhtpvz.common.item.BHTPvZSpawnEggItem;
import com.zhilizhan.bhtpvz.common.sound.BHTPvZSound;
import com.zhilizhan.bhtpvz.common.world.DecorationGenerate;
import com.zhilizhan.bhtpvz.common.world.biome.BHTPvZBiomes;
import com.zhilizhan.bhtpvz.config.BHTPvZConfig;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;

@Mod(BHTPvZ.MOD_ID)
@Mod.EventBusSubscriber(modid = BHTPvZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class
BHTPvZ {
    public static final String MOD_ID = "bhtpvz";

    // 事件总线
    public BHTPvZ() {
        Pair<BHTPvZConfig.Common, ForgeConfigSpec> specPair = (new ForgeConfigSpec.Builder()).configure(BHTPvZConfig.Common::new);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, specPair.getRight());
        BHTPvZConfig.COMMON_CONFIG = specPair.getLeft();

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus bus2 = MinecraftForge.EVENT_BUS;
        BHTPvZMobEffects.MOB_EFFECTS.register(bus);
        BHTPvZEntityTypes.ENTITY_TYPES.register(bus);
        BHTPvZItems.ITEMS.register(bus);
        BHTPvZBlocks.BLOCKS.register(bus);
        BHTPvZBiomes.BIOMES.register(bus);
        BHTPvZParticle.PARTICLE_TYPES.register(bus);
        BHTPvZPlants.register();
        BHTPvZZombies.register();
        BHTPvZSkill.SkillType.register();
        bus.addListener(this::commonSetup);
        //MinecraftForge.EVENT_BUS.register(bus);


        bus2.register(LivingEvents.class);
        bus2.addListener(EventPriority.HIGH, DecorationGenerate::addOresToBiomes);
        bus2.addListener(EventPriority.HIGH, DecorationGenerate::addTreesToBiomes);
        bus2.addListener(EventPriority.HIGH, DecorationGenerate::addBlocksToBiomes);
        BHTPvZSound.SOUNDS.register(bus);
        
        
        //动态的树
        if(ModList.get().isLoaded("dynamictrees")){
        RegistryHandler.setup(MOD_ID);
        bus.addListener(this::gatherData);
        }
    }

    // 创造物品栏
    public static final ItemGroup BHTPVZ = new ItemGroup("better_hung_teen_plants_vs_zombies") {
        @Nonnull
        @OnlyIn(Dist.CLIENT)
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(BHTPvZItems.CHERRY.get());
        }

    };

    //动态的树
    public void gatherData(GatherDataEvent event) {
        GatherDataHelper.gatherAllData("bhtpvz", event, SoilProperties.REGISTRY, Family.REGISTRY, Species.REGISTRY, LeavesProperties.REGISTRY);
    }

    //初始化刷怪蛋（颜色）
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPostRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
        BHTPvZSpawnEggItem.initUnaddedEggs();
    }
    public static ResourceLocation prefix(String a) {
        return new ResourceLocation("bhtpvz", a);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            BHTPvZBiomes.addBiomeTypes();
            BHTPvZBiomes.addBiomesToGeneration();
        });
    }
   
}
