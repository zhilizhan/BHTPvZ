package com.zhilizhan.bhtpvz.common.Item;

import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.common.Item.superItem.DamsonCrystalSword;
import com.zhilizhan.bhtpvz.common.Item.superItem.Garlic;
import com.zhilizhan.bhtpvz.common.Item.superItem.Hammer;
import com.zhilizhan.bhtpvz.common.Item.tools.BhtpvzArmor;
import com.zhilizhan.bhtpvz.common.Item.tools.BhtpvzTools;
import com.zhilizhan.bhtpvz.common.block.BlockRegistry;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BHTPvZ.MOD_ID);
    //普通物品
    public static final RegistryObject<Item> BETTER_HUNG_TEEN_S_PLANTS_VS_ZONBIES = ITEMS.register("better_hung_teen_s_plants_vs_zombies", ()-> new Item(new Item.Properties().tab(BHTPvZ.BHTPVZ)));//更好的HTPVZ MOD
    public static final RegistryObject<Item> ORIGIN_SPORE= ITEMS.register("origin_spore", ()-> new Item(new Item.Properties().tab(BHTPvZ.BHTPVZ)));//原始孢子
    public static final RegistryObject<Item> MORION_INGOT = ITEMS.register("morion_ingot", ()-> new Item(new Item.Properties().tab(BHTPvZ.BHTPVZ)));//黑晶锭
    public static final RegistryObject<Item> DAMSON_CRYSTAL_INGOT = ITEMS.register("damson_crystal_ingot", ()-> new Item(new Item.Properties().tab(BHTPvZ.BHTPVZ)));//暗紫合晶锭
    public static final RegistryObject<Item> BUTTER = ITEMS.register("butter", ()-> new Item(new Item.Properties().tab(BHTPvZ.BHTPVZ)));//黄油
    //种子
    public static final RegistryObject<Item> PEPPER_SEEDS = ITEMS.register("pepper_seeds", ()-> new BlockItem(BlockRegistry.PEPPER.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ)));//辣椒种子
    //食物
    public static final RegistryObject<Item> CHERRY = ITEMS.register("cherry", ()-> new Item(new Item.Properties().tab(BHTPvZ.BHTPVZ).food(new Food.Builder().nutrition(4).saturationMod(0.3F).build())));//樱桃
    public static final RegistryObject<Item> GARLIC = ITEMS.register("garlic", ()-> new Garlic(new Item.Properties().tab(BHTPvZ.BHTPVZ).food(new Food.Builder().nutrition(2).saturationMod(0.3F).build())));//大蒜
    public static final RegistryObject<Item> STAR_FRUIT = ITEMS.register("star_fruit", ()-> new Item(new Item.Properties().tab(BHTPvZ.BHTPVZ).food(new Food.Builder().nutrition(4).saturationMod(0.3F).build())));//杨桃
    public static final RegistryObject<Item> ANGEL_STAR_FRUIT = ITEMS.register("angel_star_fruit", ()-> new Item(new Item.Properties().tab(BHTPvZ.BHTPVZ).food(new Food.Builder().nutrition(8).saturationMod(1.2F).effect(new EffectInstance(Effects.REGENERATION, 100, 1), 1.0F).build())));//天使杨桃
    public static final RegistryObject<Item> SQUASH_SLICE = ITEMS.register("squash_slice", ()-> new Item(new Item.Properties().tab(BHTPvZ.BHTPVZ).food(new Food.Builder().nutrition(3).saturationMod(0.3F).build())));//倭瓜片
    //工具
    public static final RegistryObject<Item> HAMMER = ITEMS.register("hammer", ()-> new Hammer(BhtpvzTools.HAMMRE,1, -2.4F, new Item.Properties().tab(BHTPvZ.BHTPVZ)));//锤子
    public static final RegistryObject<Item> DAMSON_CRYSTAL_SWORD = ITEMS.register("damson_crystal_sword", ()-> new DamsonCrystalSword(BhtpvzTools.DAMSON_CRYSTAL_2, 3, -2.4F, new Item.Properties().tab(BHTPvZ.BHTPVZ)));//暗紫合金剑
    public static final RegistryObject<Item> DAMSON_CRYSTAL_SHOVEL = ITEMS.register("damson_crystal_shovel", ()-> new ShovelItem(BhtpvzTools.DAMSON_CRYSTAL_2,1.5F,-0.3F, new Item.Properties().tab(BHTPvZ.BHTPVZ)));//暗紫合金铲
    public static final RegistryObject<Item> DAMSON_CRYSTAL_PICKAXE = ITEMS.register("damson_crystal_pickaxe", ()-> new PickaxeItem(BhtpvzTools.DAMSON_CRYSTAL_2, 1, -2.8F, new Item.Properties().tab(BHTPvZ.BHTPVZ)));//暗紫合金镐
    public static final RegistryObject<Item> DAMSON_CRYSTAL_AXE = ITEMS.register("damson_crystal_axe", ()-> new AxeItem(BhtpvzTools.DAMSON_CRYSTAL_2, 5.0F, -3.0F, new Item.Properties().tab(BHTPvZ.BHTPVZ)));//暗紫合金斧
    public static final RegistryObject<Item> DAMSON_CRYSTAL_HOE = ITEMS.register("damson_crystal_hoe", ()-> new HoeItem(BhtpvzTools.DAMSON_CRYSTAL_2, -4, 0.0F, new Item.Properties().tab(BHTPvZ.BHTPVZ)));//暗紫合金锄
    //装备
    public static final RegistryObject<Item> ORIGIN_HELMET = ITEMS.register("origin_helmet", ()-> new ArmorItem(BhtpvzArmor.ORIGIN1, EquipmentSlotType.HEAD, new Item.Properties().tab(BHTPvZ.BHTPVZ)));//原始精华头盔
    public static final RegistryObject<Item> ORIGIN_CHESTPLATE = ITEMS.register("origin_chestplate", ()-> new ArmorItem(BhtpvzArmor.ORIGIN1, EquipmentSlotType.CHEST, new Item.Properties().tab(BHTPvZ.BHTPVZ)));//原始精华胸甲
    public static final RegistryObject<Item> ORIGIN_LEGGINGS = ITEMS.register("origin_leggings", ()-> new ArmorItem(BhtpvzArmor.ORIGIN1, EquipmentSlotType.LEGS, new Item.Properties().tab(BHTPvZ.BHTPVZ)));//原始精华护腿
    public static final RegistryObject<Item> ORIGIN_BOOTS = ITEMS.register("origin_boots", ()-> new ArmorItem(BhtpvzArmor.ORIGIN1, EquipmentSlotType.FEET, new Item.Properties().tab(BHTPvZ.BHTPVZ)));//原始精华靴子
    public static final RegistryObject<Item> DAMSON_CRYSTAL_HELMET = ITEMS.register("damson_crystal_helmet", ()-> new ArmorItem(BhtpvzArmor.DAMSON_CRYSTAL_1, EquipmentSlotType.HEAD, new Item.Properties().tab(BHTPvZ.BHTPVZ)));//暗紫合晶头盔
    public static final RegistryObject<Item> DAMSON_CRYSTAL_CHESTPLATE = ITEMS.register("damson_crystal_chestplate", ()-> new ArmorItem(BhtpvzArmor.DAMSON_CRYSTAL_1, EquipmentSlotType.CHEST, new Item.Properties().tab(BHTPvZ.BHTPVZ)));//暗紫合晶胸甲
    public static final RegistryObject<Item> DAMSON_CRYSTAL_LEGGINGS = ITEMS.register("damson_crystal_leggings", ()-> new ArmorItem(BhtpvzArmor.DAMSON_CRYSTAL_1, EquipmentSlotType.LEGS, new Item.Properties().tab(BHTPvZ.BHTPVZ)));//暗紫合晶护腿
    public static final RegistryObject<Item> DAMSON_CRYSTAL_BOOTS = ITEMS.register("damson_crystal_boots", ()-> new ArmorItem(BhtpvzArmor.DAMSON_CRYSTAL_1, EquipmentSlotType.FEET, new Item.Properties().tab(BHTPvZ.BHTPVZ)));//暗紫合晶靴子
    //方块物品
    public static final RegistryObject<Item> CHERRY_TREE_LEAVES = ITEMS.register("cherry_leaves", ()-> new BlockItem(BlockRegistry.CHERRY_LEAVES.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ)));//樱桃树树叶
    public static final RegistryObject<Item> STAR_FRUIT_LEAVES = ITEMS.register("star_fruit_leaves", ()-> new BlockItem(BlockRegistry.STAR_FRUIT_LEAVES.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ)));//杨桃树树叶
    public static final RegistryObject<Item> CHERRY_TREE_SAPLING = ITEMS.register("cherry_sapling", ()-> new BlockItem(BlockRegistry.CHERRY_SAPLING.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ)));//樱桃树树苗
    public static final RegistryObject<Item> STAR_FRUIT_SAPLING = ITEMS.register("star_fruit_sapling", ()-> new BlockItem(BlockRegistry.STAR_FRUIT_SAPLING.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ)));//杨桃树树苗
    public static final RegistryObject<Item> ORIGIN_MUSHROOM = ITEMS.register("origin_mushroom", ()-> new BlockItem(BlockRegistry.ORIGIN_MUSHROOM.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ)));//原始蘑菇
    public static final RegistryObject<Item> ORIGIN_MUSHROOM_BLOCK = ITEMS.register("origin_mushroom_block", ()-> new BlockItem(BlockRegistry.ORIGIN_MUSHROOM_BLOCK.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ)));//原始蘑菇块
    public static final RegistryObject<Item> QUESTION_MARK_POT = ITEMS.register("question_mark_pot", ()-> new BlockItem(BlockRegistry.QUESTION_MARK_POT.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ)));//问号罐
    public static final RegistryObject<Item> PLANT_POT = ITEMS.register("plant_pot", ()-> new BlockItem(BlockRegistry.PLANT_POT.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ)));//植物罐
    public static final RegistryObject<Item> SQUASH = ITEMS.register("squash", ()-> new BlockItem(BlockRegistry.SQUASH.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ)));//倭瓜
    public static final RegistryObject<Item> MORION_ORE = ITEMS.register("morion_ore", ()-> new BlockItem(BlockRegistry.MORION_ORE.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ)));//黑晶矿
    public static final RegistryObject<Item> MORION_BLOCK = ITEMS.register("morion_block", ()-> new BlockItem(BlockRegistry.MORION_BLOCK.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ)));//黑晶块
    public static final RegistryObject<Item> DAMSON_CRYSTAL_BLOCK = ITEMS.register("damson_crystal_block", ()-> new BlockItem(BlockRegistry.DAMSON_CRYSTAL_BLOCK.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ)));//暗紫合晶块
    public static final RegistryObject<Item> DECOMPOSITION_STAGE = ITEMS.register("decomposition_stage", ()-> new BlockItem(BlockRegistry.DECOMPOSITION_STAGE.get(), new Item.Properties().tab(BHTPvZ.BHTPVZ)));//分解台
}
