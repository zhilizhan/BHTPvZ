package com.zhilizhan.bhtpvz.common.item;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.item.spawn.card.PlantCardItem;
import com.hungteen.pvz.utils.enums.Colors;
import com.mojang.datafixers.util.Pair;
import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.common.block.BHTPvZBlocks;
import com.zhilizhan.bhtpvz.common.entity.BHTPvZEntityTypes;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import com.zhilizhan.bhtpvz.common.item.sapling.WisdomSapling;
import com.zhilizhan.bhtpvz.common.item.sapling.XpSapling;
import com.zhilizhan.bhtpvz.common.item.token.DaveToken;
import com.zhilizhan.bhtpvz.common.item.token.SteelPumpkinToken;
import com.zhilizhan.bhtpvz.common.item.token.SunDaveToken;
import com.zhilizhan.bhtpvz.common.sound.BHTPvZSound;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BHTPvZItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BHTPvZ.MOD_ID);

    // 普通物品
    public static final RegistryObject<Item> ORIGIN_SPORE = ITEMS.register("origin_spore", ()-> new BlockItem(BHTPvZBlocks.ORIGIN_MUSHROOM.get(),new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 原始孢子
    public static final RegistryObject<Item> MORION_INGOT = ITEMS.register("morion_ingot", ()-> new Item(new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 黑晶锭
    public static final RegistryObject<Item> DAMSON_CRYSTAL_INGOT = ITEMS.register("damson_crystal_ingot", ()-> new Item(new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 暗紫合晶锭
    public static final RegistryObject<Item> BUTTER = ITEMS.register("butter", ()-> new Item(new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 黄油
    public static final RegistryObject<Item> POT_GRASS = ITEMS.register("pot_grass", ()-> new Item(new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 罐子草
    // 种子
    public static final RegistryObject<Item> CHILI_SEEDS = ITEMS.register("chili_seeds", ()-> new BlockItem(BHTPvZBlocks.CHILI.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 辣椒种子

    // 食物
    public static final RegistryObject<Item> CHERRY = ITEMS.register("cherry", ()-> new Item(new Item.Properties().tab(BHTPvZ.BHTPVZ).food(new Food.Builder().nutrition(4).saturationMod(0.3F).build()))); // 樱桃
    public static final RegistryObject<Item> GARLIC = ITEMS.register("garlic", ()-> new Garlic(BHTPvZBlocks.GARLIC.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ).food(new Food.Builder().nutrition(2).saturationMod(0.3F)/*.effect(new EffectInstance(BHTPvZEffects.HALITOSIS.get(), 400, 1), 1.0F)*/.build()))); // 大蒜
    public static final RegistryObject<Item> STARFRUIT = ITEMS.register("starfruit", ()-> new Item(new Item.Properties().tab(BHTPvZ.BHTPVZ).food(new Food.Builder().nutrition(4).saturationMod(0.3F).build()))); // 杨桃
    public static final RegistryObject<Item> ANGEL_STARFRUIT = ITEMS.register("angel_starfruit", ()-> new Item(new Item.Properties().tab(BHTPvZ.BHTPVZ).food(new Food.Builder().nutrition(8).saturationMod(1.2F).effect(new EffectInstance(Effects.REGENERATION, 200, 1), 1.0F).build()))); // 天使杨桃
    public static final RegistryObject<Item> SQUASH_SLICE = ITEMS.register("squash_slice", ()-> new Item(new Item.Properties().tab(BHTPvZ.BHTPVZ).food(new Food.Builder().nutrition(3).saturationMod(0.3F).build()))); //倭瓜片
    public static final RegistryObject<Item> ICE_CABBAGE = ITEMS.register("ice_cabbage", ()-> new Item(new Item.Properties().tab(BHTPvZ.BHTPVZ).food(new Food.Builder().nutrition(4).saturationMod(0.5F).effect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 80, 1), 1.0F).build()))); // 冰卷心菜
    public static final RegistryObject<Item> GOO_PEA = ITEMS.register("goo_pea", ()-> new Item(new Item.Properties().tab(BHTPvZ.BHTPVZ).food(new Food.Builder().nutrition(3).saturationMod(0.5F).effect(new EffectInstance(Effects.POISON, 40, 0), 1.0F).build()))); // 毒豌豆

    // 功能性食物
    public static final RegistryObject<Item> XP_SAPLING = ITEMS.register("xp_sapling", ()-> new XpSapling(new Item.Properties().tab(BHTPvZ.BHTPVZ).food(new Food.Builder().nutrition(4).saturationMod(0.5F).effect(new EffectInstance(Effects.LUCK, 1000, 1), 1.0F).build()))); //经验树苗
    public static final RegistryObject<Item> WISDOM_SAPLING = ITEMS.register("wisdom_sapling", ()-> new WisdomSapling(new Item.Properties().tab(BHTPvZ.BHTPVZ).food(new Food.Builder().nutrition(6).saturationMod(0.5F).effect(new EffectInstance(Effects.LUCK, 2000, 3), 1.0F).build()))); //智慧树苗
    public static final RegistryObject<Item> STEEL_COIN = ITEMS.register("steel_coin", ()-> new SteelCoin(new Item.Properties().tab(BHTPvZ.BHTPVZ))); //钢镚
    public static final RegistryObject<Item> CHLOROPHYLL = ITEMS.register("chlorophyll", ()-> new Chlorophyll(new Item.Properties().tab(BHTPvZ.BHTPVZ).food(new Food.Builder().nutrition(4).saturationMod(0.5F).effect(new EffectInstance(Effects.LUCK, 1000, 1), 1.0F).build()))); //钢镚

    // 工具
    public static final RegistryObject<Item> HAMMER = ITEMS.register("hammer", ()-> new Hammer(new Item.Properties().tab(BHTPvZ.BHTPVZ).durability(300))); //锤子
    public static final RegistryObject<Item> DAMSON_CRYSTAL_SWORD = ITEMS.register("damson_crystal_sword", DamsonCrystalSword::new); // 暗紫合金剑
    public static final RegistryObject<Item> DAMSON_CRYSTAL_SHOVEL = ITEMS.register("damson_crystal_shovel", DamsonCrystalShove::new); // 暗紫合金铲
    public static final RegistryObject<Item> DAMSON_CRYSTAL_PICKAXE = ITEMS.register("damson_crystal_pickaxe", ()-> new PickaxeItem(BHTPvZTier.DAMSON_CRYSTAL, 1, -2.8f, new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 暗紫合金镐
    public static final RegistryObject<Item> DAMSON_CRYSTAL_AXE = ITEMS.register("damson_crystal_axe", ()-> new AxeItem(BHTPvZTier.DAMSON_CRYSTAL, 5.0f, -3.0f, new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 暗紫合金斧
    public static final RegistryObject<Item> DAMSON_CRYSTAL_HOE = ITEMS.register("damson_crystal_hoe", ()-> new HoeItem(BHTPvZTier.DAMSON_CRYSTAL, -4, 0.0f, new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 暗紫合金锄

    // 装备
    public static final RegistryObject<Item> ORIGIN_HELMET = ITEMS.register("origin_helmet", ()-> new ArmorItem(BHTPvZArmorMaterial.ORIGIN, EquipmentSlotType.HEAD, new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 原始精华头盔
    public static final RegistryObject<Item> ORIGIN_CHESTPLATE = ITEMS.register("origin_chestplate", ()-> new ArmorItem(BHTPvZArmorMaterial.ORIGIN, EquipmentSlotType.CHEST, new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 原始精华胸甲
    public static final RegistryObject<Item> ORIGIN_LEGGINGS = ITEMS.register("origin_leggings", ()-> new ArmorItem(BHTPvZArmorMaterial.ORIGIN, EquipmentSlotType.LEGS, new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 原始精华护腿
    public static final RegistryObject<Item> ORIGIN_BOOTS = ITEMS.register("origin_boots", ()-> new ArmorItem(BHTPvZArmorMaterial.ORIGIN, EquipmentSlotType.FEET, new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 原始精华靴子
    public static final RegistryObject<Item> DAMSON_CRYSTAL_HELMET = ITEMS.register("damson_crystal_helmet", ()-> new ArmorItem(BHTPvZArmorMaterial.DAMSON_CRYSTAL, EquipmentSlotType.HEAD, new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 暗紫合晶头盔
    public static final RegistryObject<Item> DAMSON_CRYSTAL_CHESTPLATE = ITEMS.register("damson_crystal_chestplate", ()-> new ArmorItem(BHTPvZArmorMaterial.DAMSON_CRYSTAL, EquipmentSlotType.CHEST, new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 暗紫合晶胸甲
    public static final RegistryObject<Item> DAMSON_CRYSTAL_LEGGINGS = ITEMS.register("damson_crystal_leggings", ()-> new ArmorItem(BHTPvZArmorMaterial.DAMSON_CRYSTAL, EquipmentSlotType.LEGS, new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 暗紫合晶护腿
    public static final RegistryObject<Item> DAMSON_CRYSTAL_BOOTS = ITEMS.register("damson_crystal_boots", ()-> new ArmorItem(BHTPvZArmorMaterial.DAMSON_CRYSTAL, EquipmentSlotType.FEET, new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 暗紫合晶靴子

    // 僵尸刷怪蛋
    public static final RegistryObject<BHTPvZSpawnEggItem> EDGAR_090547_SPAWN_EGG = registerSpawnEgg("edgar_090547_spawn_egg", BHTPvZEntityTypes.EDGAR_090547, Colors.ZOMBIE_COLOR, new Item.Properties().tab(BHTPvZ.BHTPVZ)); // 埃德加-090547刷怪蛋
    public static final RegistryObject<BHTPvZSpawnEggItem> FLOWER_POT_ZOMBIE_SPAWN_EGG = registerSpawnEgg("flower_pot_zombie_spawn_egg", BHTPvZEntityTypes.FLOWER_POT_ZOMBIE, Colors.ZOMBIE_COLOR, new Item.Properties().tab(BHTPvZ.BHTPVZ)); // 花盆僵尸刷怪蛋
    public static final RegistryObject<BHTPvZSpawnEggItem> AIRBORNE_ZOMBIE_SPAWN_EGG = registerSpawnEgg("airborne_zombie_spawn_egg", BHTPvZEntityTypes.AIRBORNE_ZOMBIE, Colors.ZOMBIE_COLOR, new Item.Properties().tab(BHTPvZ.BHTPVZ)); // 空降僵尸刷怪蛋
    public static final RegistryObject<BHTPvZSpawnEggItem> MC_ZOMBIE_SPAWN_EGG = registerSpawnEgg("mc_zombie_spawn_egg", BHTPvZEntityTypes.MC_ZOMBIE, Colors.ZOMBIE_COLOR, new Item.Properties().tab(BHTPvZ.BHTPVZ)); // MC僵尸刷怪蛋
    public static final RegistryObject<BHTPvZSpawnEggItem> STEEL_PUMPKIN_ZOMBIE_SPAWN_EGG = registerSpawnEgg("steel_pumpkin_zombie_spawn_egg", BHTPvZEntityTypes.STEEL_PUMPKIN_ZOMBIE, Colors.ZOMBIE_COLOR, new Item.Properties().tab(BHTPvZ.BHTPVZ)); // 钢南瓜僵尸刷怪蛋
    public static final RegistryObject<BHTPvZSpawnEggItem> TARGET_ARROW_ZOMBIE_SPAWN_EGG = registerSpawnEgg("target_arrow_zombie_spawn_egg", BHTPvZEntityTypes.TARGET_ARROW_ZOMBIE, Colors.ZOMBIE_COLOR, new Item.Properties().tab(BHTPvZ.BHTPVZ)); // 箭靶僵尸刷怪蛋
    public static final RegistryObject<BHTPvZSpawnEggItem> RED_EDGE_ZOMBIE_SPAWN_EGG = registerSpawnEgg("red_edge_zombie_spawn_egg", BHTPvZEntityTypes.RED_EDGE_ZOMBIE, Colors.ZOMBIE_COLOR, new Item.Properties().tab(BHTPvZ.BHTPVZ)); // 红刀僵尸刷怪蛋
    public static final RegistryObject<BHTPvZSpawnEggItem> MJ_ZOMBIE_SPAWN_EGG = registerSpawnEgg("mj_zombie_spawn_egg", BHTPvZEntityTypes.MJ_ZOMBIE, Colors.ZOMBIE_COLOR, new Item.Properties().tab(BHTPvZ.BHTPVZ)); // 舞王僵尸刷怪蛋
    public static final RegistryObject<BHTPvZSpawnEggItem> DANCER_BACKUP_ZOMBIE_SPAWN_EGG = registerSpawnEgg("dancer_backup_zombie_spawn_egg", BHTPvZEntityTypes.DANCER_BACKUP_ZOMBIE, Colors.ZOMBIE_COLOR, new Item.Properties().tab(BHTPvZ.BHTPVZ)); // 伴舞舞僵尸刷怪蛋
    public static final RegistryObject<BHTPvZSpawnEggItem> RED_FLOWER_ZOMBIE_SPAWN_EGG = registerSpawnEgg("sun_flower_zombie_spawn_egg", BHTPvZEntityTypes.SUN_FLOWER_ZOMBIE, Colors.ZOMBIE_COLOR, new Item.Properties().tab(BHTPvZ.BHTPVZ)); // 向日葵僵尸刷怪蛋
    public static final RegistryObject<BHTPvZSpawnEggItem> CHOMPER_ZOMBIE_SPAWN_EGG = registerSpawnEgg("chomper_zombie_spawn_egg", BHTPvZEntityTypes.CHOMPER_ZOMBIE, Colors.ZOMBIE_COLOR, new Item.Properties().tab(BHTPvZ.BHTPVZ)); // 大嘴花僵尸刷怪蛋


    //普通生物刷怪蛋
    public static final RegistryObject<BHTPvZSpawnEggItem> ORIGIN_MOOB_SPAWN_EGG = registerSpawnEgg("origin_moob_spawn_egg", BHTPvZEntityTypes.ORIGIN_MOOB, Colors.APPEASE_COLOR, new Item.Properties().tab(BHTPvZ.BHTPVZ)); // 原始蘑菇牛刷怪蛋
    public static final RegistryObject<BHTPvZSpawnEggItem> TOXIC_MOOB_SPAWN_EGG = registerSpawnEgg("toxic_moob_spawn_egg", BHTPvZEntityTypes.TOXIC_MOOB, Colors.TOXIC_COLOR, new Item.Properties().tab(BHTPvZ.BHTPVZ)); // 毒蘑菇牛刷怪蛋

    // 生成工具
    public static final RegistryObject<Item> DAVE_TOKEN = ITEMS.register("dave_token", () -> new DaveToken(new Item.Properties().tab(BHTPvZ.BHTPVZ).stacksTo(1))); // 戴夫标志
    public static final RegistryObject<Item> SUN_DAVE_TOKEN = ITEMS.register("sun_dave_token", () -> new SunDaveToken(new Item.Properties().tab(BHTPvZ.BHTPVZ).stacksTo(1))); // 阳光戴夫标志
    public static final RegistryObject<Item> STEEL_PUMPKIN_TOKEN = ITEMS.register("steel_pumpkin_token", () -> new SteelPumpkinToken(new Item.Properties().tab(BHTPvZ.BHTPVZ).stacksTo(1))); // 钢南瓜标志

    // 植物卡
    public static final RegistryObject<PlantCardItem> STEEL_PUMPKIN_CARD = registerCard(BHTPvZPlants.STEEL_PUMPKIN, false); // 钢南瓜卡
    public static final RegistryObject<PlantCardItem> STEEL_PUMPKIN_ENJOY_CARD = registerCard(BHTPvZPlants.STEEL_PUMPKIN, true); // 钢南瓜体验卡
    public static final RegistryObject<PlantCardItem> ICE_CABBAGE_PULT_CARD = registerCard(BHTPvZPlants.ICE_CABBAGE_PULT, false); // 冰卷心菜投手卡
    public static final RegistryObject<PlantCardItem> ICE_CABBAGE_PULT_ENJOY_CARD = registerCard(BHTPvZPlants.ICE_CABBAGE_PULT, true); // 冰卷心菜投手体验卡
    public static final RegistryObject<PlantCardItem> FIRE_PEASHOOTER_CARD = registerCard(BHTPvZPlants.FIRE_PEASHOOTER, false); // 火焰豌豆卡
    public static final RegistryObject<PlantCardItem> FIRE_PEASHOOTER_ENJOY_CARD = registerCard(BHTPvZPlants.FIRE_PEASHOOTER, true); // 火焰豌豆体验卡
    public static final RegistryObject<PlantCardItem> NUT_BOWLING_CARD = registerCard(BHTPvZPlants.NUT_BOWLING, false); // 坚果卡
    public static final RegistryObject<PlantCardItem> NUT_BOWLING_ENJOY_CARD = registerCard(BHTPvZPlants.NUT_BOWLING, true); // 坚果体验卡
    public static final RegistryObject<PlantCardItem> WATER_POT_CARD = registerCard(BHTPvZPlants.WATER_POT, false); // 水盆卡
    public static final RegistryObject<PlantCardItem> WATER_POT_ENJOY_CARD = registerCard(BHTPvZPlants.WATER_POT, true); // 水盆体验卡
    public static final RegistryObject<PlantCardItem> CHORUS_FRUIT_PULT_CARD = registerCard(BHTPvZPlants.CHORUS_FRUIT_PULT, false); // 紫颂果投手卡
    public static final RegistryObject<PlantCardItem> CHORUS_FRUIT_PULT_ENJOY_CARD = registerCard(BHTPvZPlants.CHORUS_FRUIT_PULT, true); // 紫颂果投手体验卡
    public static final RegistryObject<PlantCardItem> ROTATE_RADISH_CARD = registerCard(BHTPvZPlants.ROTATE_RADISH, false); // 转转萝卜卡
    public static final RegistryObject<PlantCardItem> ROTATE_RADISH_ENJOY_CARD = registerCard(BHTPvZPlants.ROTATE_RADISH, true); // 转转萝卜体验卡
    public static final RegistryObject<PlantCardItem> BURST_KERNEL_PULT_CARD = registerCard(BHTPvZPlants.BURST_KERNEL_PULT, false); // 爆裂玉米投手卡
    public static final RegistryObject<PlantCardItem> BURST_KERNEL_PULT_ENJOY_CARD = registerCard(BHTPvZPlants.BURST_KERNEL_PULT, true); // 爆裂玉米投手体验卡
    public static final RegistryObject<PlantCardItem> BLAZE_WART_CARD = registerCard(BHTPvZPlants.BLAZE_WART, false); // 烈焰疣卡
    public static final RegistryObject<PlantCardItem> BLAZE_WART_ENJOY_CARD = registerCard(BHTPvZPlants.BLAZE_WART, true); // 烈焰疣体验卡
    public static final RegistryObject<PlantCardItem> GRASS_CARP_CARD = registerCard(BHTPvZPlants.GRASS_CARP, false); // 草鱼卡
    public static final RegistryObject<PlantCardItem> GRASS_CARP_ENJOY_CARD = registerCard(BHTPvZPlants.GRASS_CARP, true); // 草鱼体验卡
    public static final RegistryObject<PlantCardItem> POT_GRASS_CARD = registerCard(BHTPvZPlants.POT_GRASS, false); // 罐子草卡
    public static final RegistryObject<PlantCardItem> POT_GRASS_ENJOY_CARD = registerCard(BHTPvZPlants.POT_GRASS, true); // 罐子草体验卡
    public static final RegistryObject<PlantCardItem> SELF_IMITATER_CARD = registerCard(BHTPvZPlants.SELF_IMITATER, false); // 自己模仿者卡
    public static final RegistryObject<PlantCardItem> SELF_IMITATER_ENJOY_CARD = registerCard(BHTPvZPlants.SELF_IMITATER, true); // 自己模仿者体验卡
    public static final RegistryObject<PlantCardItem> RE_ICEPEA_CARD = registerCard(BHTPvZPlants.RE_ICEPEA, false); // 双发寒冰卡
    public static final RegistryObject<PlantCardItem> RE_ICEPEA_ENJOY_CARD = registerCard(BHTPvZPlants.RE_ICEPEA, true); // 双发寒冰体验卡
    public static final RegistryObject<PlantCardItem> PEAPOD_CARD = registerCard(BHTPvZPlants.PEA_POD, false); // 豌豆荚卡
    public static final RegistryObject<PlantCardItem> PEAPOD_ENJOY_CARD = registerCard(BHTPvZPlants.PEA_POD, true); // 豌豆荚体验卡
    public static final RegistryObject<PlantCardItem> SCULK_SHROOM_CARD = registerCard(BHTPvZPlants.SCULK_SHROOM, false); // 音爆菇卡
    public static final RegistryObject<PlantCardItem> SCULK_SHROOM_ENJOY_CARD = registerCard(BHTPvZPlants.SCULK_SHROOM, true); // 音爆菇体验卡
    public static final RegistryObject<PlantCardItem> ORIGIN_SHROOM_CARD = registerCard(BHTPvZPlants.ORIGIN_SHROOM, false); // 原始蘑菇卡
    public static final RegistryObject<PlantCardItem> ORIGIN_SHROOM_ENJOY_CARD = registerCard(BHTPvZPlants.ORIGIN_SHROOM, true); // 原始蘑菇体验卡
    public static final RegistryObject<PlantCardItem> BEE_SHOOTER_CARD = registerCard(BHTPvZPlants.BEE_SHOOTER, false); // 蜜蜂射手卡
    public static final RegistryObject<PlantCardItem> BEE_SHOOTER_ENJOY_CARD = registerCard(BHTPvZPlants.BEE_SHOOTER, true); // 蜜蜂射手体验卡
    public static final RegistryObject<PlantCardItem> FODDER_BUSH_CARD = registerCard(BHTPvZPlants.FODDER_BUSH, false); // 炮灰灌卡
    public static final RegistryObject<PlantCardItem> FODDER_BUSH_ENJOY_CARD = registerCard(BHTPvZPlants.FODDER_BUSH, true); // 炮灰灌体验卡
    public static final RegistryObject<PlantCardItem> GOLDEN_MELON_PULT_CARD = registerCard(BHTPvZPlants.GOLDEN_MELON_PULT, false); // 金西瓜卡
    public static final RegistryObject<PlantCardItem> GOLDEN_MELON_PULT_ENJOY_CARD = registerCard(BHTPvZPlants.GOLDEN_MELON_PULT, true); // 金西瓜体验卡
    public static final RegistryObject<PlantCardItem> PRIMAL_PEA_SHOOTER_CARD = registerCard(BHTPvZPlants.PRIMAL_PEA_SHOOTER, false); // 原始豌豆卡
    public static final RegistryObject<PlantCardItem> PRIMAL_PEA_SHOOTER_ENJOY_CARD = registerCard(BHTPvZPlants.PRIMAL_PEA_SHOOTER, true); // 原始豌豆体验卡
    public static final RegistryObject<PlantCardItem> GOO_PEA_SHOOTER_CARD = registerCard(BHTPvZPlants.GOO_PEA_SHOOTER, false); // 毒液豌豆卡
    public static final RegistryObject<PlantCardItem> GOO_PEA_SHOOTER_ENJOY_CARD = registerCard(BHTPvZPlants.GOO_PEA_SHOOTER, true); // 毒液豌豆体验卡
    public static final RegistryObject<PlantCardItem> MAGNIFYING_GRASS_CARD = registerCard(BHTPvZPlants.MAGNIFYING_GRASS, false); // 棱镜草卡
    public static final RegistryObject<PlantCardItem> MAGNIFYING_GRASS_ENJOY_CARD = registerCard(BHTPvZPlants.MAGNIFYING_GRASS, true); // 棱镜草体验卡

    //唱片
    public static final RegistryObject<MusicDiscItem> POOL_DISC = ITEMS.register("pool_disc", () -> new MusicDiscItem(0, BHTPvZSound.POOL, (new Item.Properties()).stacksTo(1).tab(BHTPvZ.BHTPVZ).rarity(Rarity.RARE)));
    public static final RegistryObject<MusicDiscItem> BOWLING_MUSIC_DISC = ITEMS.register("bowling_music_disc", () -> new MusicDiscItem(0, BHTPvZSound.BOWLING_MUSIC, (new Item.Properties()).stacksTo(1).tab(BHTPvZ.BHTPVZ).rarity(Rarity.RARE)));
    // 方块物品
    public static final RegistryObject<Item> CHERRY_TREE_LEAVES = ITEMS.register("cherry_leaves", ()-> new BlockItem(BHTPvZBlocks.CHERRY_LEAVES.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 樱桃树树叶
    public static final RegistryObject<Item> STARFRUIT_LEAVES = ITEMS.register("starfruit_leaves", ()-> new BlockItem(BHTPvZBlocks.STARFRUIT_LEAVES.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 杨桃树树叶
    public static final RegistryObject<Item> CHERRY_TREE_SAPLING = ITEMS.register("cherry_sapling", ()-> new BlockItem(BHTPvZBlocks.CHERRY_SAPLING.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 樱桃树树苗
    public static final RegistryObject<Item> STARFRUIT_SAPLING = ITEMS.register("starfruit_sapling", ()-> new BlockItem(BHTPvZBlocks.STARFRUIT_SAPLING.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 杨桃树树苗
    public static final RegistryObject<Item> ORIGIN_MUSHROOM_BLOCK = ITEMS.register("origin_mushroom_block", ()-> new BlockItem(BHTPvZBlocks.ORIGIN_MUSHROOM_BLOCK.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 原始蘑菇块
    public static final RegistryObject<Item> QUESTION_MARK_POT = ITEMS.register("question_mark_pot", ()-> new BlockItem(BHTPvZBlocks.QUESTION_MARK_POT.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 问号罐
    public static final RegistryObject<Item> PLANT_POT = ITEMS.register("plant_pot", ()-> new BlockItem(BHTPvZBlocks.PLANT_POT.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 植物罐
    public static final RegistryObject<Item> WATER_POT = ITEMS.register("water_pot", ()-> new BlockItem(BHTPvZBlocks.WATER_POT.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 水盆
    public static final RegistryObject<Item> SQUASH = ITEMS.register("squash", ()-> new BlockItem(BHTPvZBlocks.SQUASH.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 倭瓜
    public static final RegistryObject<Item> MORION_ORE = ITEMS.register("morion_ore", ()-> new BlockItem(BHTPvZBlocks.MORION_ORE.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 黑晶矿
    public static final RegistryObject<Item> MORION_BLOCK = ITEMS.register("morion_block", ()-> new BlockItem(BHTPvZBlocks.MORION_BLOCK.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 黑晶块
    public static final RegistryObject<Item> DAMSON_CRYSTAL_BLOCK = ITEMS.register("damson_crystal_block", ()-> new BlockItem(BHTPvZBlocks.DAMSON_CRYSTAL_BLOCK.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 暗紫合晶块
    public static final RegistryObject<Item> DECOMPOSITION_STAGE = ITEMS.register("decomposition_stage", ()-> new BlockItem(BHTPvZBlocks.DECOMPOSITION_STAGE.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // 分解台
    public static final RegistryObject<BlockItem> SCREEN_DOOR = ITEMS.register("screen_door",() -> new BlockItem(BHTPvZBlocks.SCREEN_DOOR.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ)));  // 铁网门
    public static final RegistryObject<BlockItem> PEA_BLOCK = ITEMS.register("pea_block",() -> new BlockItem(BHTPvZBlocks.PEA_BLOCK.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ)));  // 压缩豌豆块
    public static final RegistryObject<Item> TOXIC_SHROOM_BLOCK = ITEMS.register("toxic_shroom_block", ()-> new BlockItem(BHTPvZBlocks.TOXIC_SHROOM_BLOCK.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ))); // PVZ毒蘑菇块

    // 刷怪蛋注册方法
    private static RegistryObject<BHTPvZSpawnEggItem> registerSpawnEgg(String name, RegistryObject<? extends EntityType<?>> entityType, Pair<Integer, Integer> color, Item.Properties tab) {
        return ITEMS.register(name, () -> new BHTPvZSpawnEggItem(entityType, color.getFirst(), color.getSecond(), tab));
    }

    private static RegistryObject<PlantCardItem> registerCard(IPlantType plant, boolean is){
        String name = plant.toString();
        if(is) {
            name = name + "_enjoy";
        }
        name = name + "_card";
        return ITEMS.register(name, () -> new PlantCardItem(plant, is));
    }
}
