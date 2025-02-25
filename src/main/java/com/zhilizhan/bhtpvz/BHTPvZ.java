package com.zhilizhan.bhtpvz;

import com.ferreusveritas.dynamictrees.api.GatherDataHelper;
import com.ferreusveritas.dynamictrees.api.registry.RegistryHandler;
import com.ferreusveritas.dynamictrees.blocks.leaves.LeavesProperties;
import com.ferreusveritas.dynamictrees.blocks.rootyblocks.SoilProperties;
import com.ferreusveritas.dynamictrees.trees.Family;
import com.ferreusveritas.dynamictrees.trees.Species;

import com.zhilizhan.bhtpvz.client.particle.BHTPvZParticle;
import com.zhilizhan.bhtpvz.common.block.BHTPvZBlocks;
import com.zhilizhan.bhtpvz.common.container.BHTPvZContainer;
import com.zhilizhan.bhtpvz.common.effect.BHTPvZMobEffects;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.event.LivingEvents;
import com.zhilizhan.bhtpvz.common.impl.BHTPvZSkill;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import com.zhilizhan.bhtpvz.common.impl.zombie.BHTPvZZombies;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import com.zhilizhan.bhtpvz.common.item.BHTPvZSpawnEggItem;
import com.zhilizhan.bhtpvz.common.network.BHTPvZPacketHandler;
import com.zhilizhan.bhtpvz.common.sound.BHTPvZSound;
import com.zhilizhan.bhtpvz.common.tileentity.BHTPvZTileEntity;
import com.zhilizhan.bhtpvz.common.world.DecorationGenerate;
import com.zhilizhan.bhtpvz.common.world.biome.BHTPvZBiomes;
import com.zhilizhan.bhtpvz.config.BHTPvZConfig;
import com.zhilizhan.bhtpvz.data.ItemModelGenerator;
import com.zhilizhan.bhtpvz.data.loot.LootTableGenerator;
import com.zhilizhan.bhtpvz.data.recipe.RecipeGenerator;
import com.zhilizhan.bhtpvz.data.tag.BHTPVZBlockTagGenerator;
import com.zhilizhan.bhtpvz.data.tag.BHTPVZEntityTypeTagGenerator;
import com.zhilizhan.bhtpvz.data.tag.BHTPVZItemTagGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
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
        BHTPvZTileEntity.TILE_ENTITY_TYPES.register(bus);
        BHTPvZContainer.CONTAINER_TYPES.register(bus);
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
        bus.addListener(this::gatherData);
        
        //动态的树
        if(ModList.get().isLoaded("dynamictrees")){
            RegistryHandler.setup(MOD_ID);
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

    public void gatherData(GatherDataEvent event) {

        ExistingFileHelper helper = event.getExistingFileHelper();
        if(event.includeServer()) {
            //for tags.
            BHTPVZBlockTagGenerator generator = new BHTPVZBlockTagGenerator(event.getGenerator(), helper);
            event.getGenerator().addProvider(generator);
            event.getGenerator().addProvider(new BHTPVZItemTagGenerator(event.getGenerator(), generator, helper));
            event.getGenerator().addProvider(new BHTPVZEntityTypeTagGenerator(event.getGenerator(), helper));
            //for recipes.
            event.getGenerator().addProvider(new RecipeGenerator(event.getGenerator()));
            //for loot tables.
            event.getGenerator().addProvider(new LootTableGenerator(event.getGenerator()));
        }
        if(event.includeClient()) {
            ///for language
//			event.getGenerator().addProvider(new CNLanguageGenerator(ev.getGenerator()));
//			event.getGenerator().addProvider(new USLanguageGenerator(ev.getGenerator()));
            //for item model
            event.getGenerator().addProvider(new ItemModelGenerator(event.getGenerator(), helper));
//			event.getGenerator().addProvider(new BlockModelGenerator(ev.getGenerator(), helper));
            //for block state
//			event.getGenerator().addProvider(new BlockStateGenerator(ev.getGenerator(), helper));
        }
        if(ModList.get().isLoaded("dynamictrees")){
            GatherDataHelper.gatherAllData("bhtpvz", event, SoilProperties.REGISTRY, Family.REGISTRY, Species.REGISTRY, LeavesProperties.REGISTRY);
        }
    }

    //初始化刷怪蛋（颜色）
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPostRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
        BHTPvZSpawnEggItem.initUnaddedEggs();
    }
    public static ResourceLocation prefix(String a) {
        return new ResourceLocation(MOD_ID, a);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            BHTPvZBiomes.addBiomeTypes();
            BHTPvZBiomes.addBiomesToGeneration();
            BHTPvZPacketHandler.init();
        });
    }

}
