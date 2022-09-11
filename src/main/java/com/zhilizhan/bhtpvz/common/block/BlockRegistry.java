package com.zhilizhan.bhtpvz.common.block;

import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.common.block.plants.PepperBlock;
import com.zhilizhan.bhtpvz.common.block.superBlock.PlantPot;
import com.zhilizhan.bhtpvz.common.block.superBlock.QuestionMarkPot;
import com.zhilizhan.bhtpvz.common.block.trees.CherryTree;
import com.zhilizhan.bhtpvz.common.block.trees.StarFruitTree;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BHTPvZ.MOD_ID);
    public static final RegistryObject<Block> CHERRY_LEAVES = BLOCKS.register("cherry_leaves", ()-> new LeavesBlock(AbstractBlock.Properties.copy(Blocks.OAK_LEAVES)));//樱桃树树叶
    public static final RegistryObject<Block> STAR_FRUIT_LEAVES = BLOCKS.register("star_fruit_leaves", ()-> new LeavesBlock(AbstractBlock.Properties.copy(Blocks.OAK_LEAVES)));//杨桃树树叶
    public static final RegistryObject<Block> CHERRY_SAPLING = BLOCKS.register("cherry_sapling", ()-> new SaplingBlock(new CherryTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));//樱桃树树苗
    public static final RegistryObject<Block> STAR_FRUIT_SAPLING = BLOCKS.register("star_fruit_sapling", ()-> new SaplingBlock(new StarFruitTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));//杨桃树树苗
    public static final RegistryObject<Block> ORIGIN_MUSHROOM = BLOCKS.register("origin_mushroom", ()-> new MushroomBlock(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.COLOR_GREEN).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).hasPostProcess(BlockRegistry::always)));//原始蘑菇
    public static final RegistryObject<Block> ORIGIN_MUSHROOM_BLOCK = BLOCKS.register("origin_mushroom_block", ()-> new Block(AbstractBlock.Properties.of(Material.STONE).harvestLevel(2).strength(2.0F, 6.0F).sound(SoundType.STONE)));//原始蘑菇块
    public static final RegistryObject<Block> QUESTION_MARK_POT = BLOCKS.register("question_mark_pot", ()-> new QuestionMarkPot(AbstractBlock.Properties.of(Material.STONE).sound(SoundType.STONE)));//问号罐
    public static final RegistryObject<Block> PLANT_POT = BLOCKS.register("plant_pot", ()-> new PlantPot(AbstractBlock.Properties.of(Material.STONE).sound(SoundType.STONE)));//植物罐
    public static final RegistryObject<Block> SQUASH = BLOCKS.register("squash", ()-> new MelonBlock(AbstractBlock.Properties.of(Material.VEGETABLE, MaterialColor.COLOR_GREEN).strength(1.0F).sound(SoundType.WOOD)));//倭瓜
    public static final RegistryObject<Block> MORION_ORE = BLOCKS.register("morion_ore", ()-> new OreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).harvestLevel(3).strength(3.0F, 3.0F)));//黑晶矿
    public static final RegistryObject<Block> MORION_BLOCK = BLOCKS.register("morion_block", ()-> new Block(AbstractBlock.Properties.of(Material.METAL).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).harvestLevel(3).strength(5.0F, 6.0F).sound(SoundType.METAL)));//黑晶块
    public static final RegistryObject<Block> DAMSON_CRYSTAL_BLOCK = BLOCKS.register("damson_crystal_block", ()-> new Block(AbstractBlock.Properties.of(Material.METAL).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).harvestLevel(3).strength(5.0F, 6.0F).sound(SoundType.METAL)));//暗紫合晶块
    public static final RegistryObject<Block> PEPPER = BLOCKS.register("pepper", ()-> new PepperBlock(AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP)));//辣椒作物
    public static final RegistryObject<Block> DECOMPOSITION_STAGE = BLOCKS.register("decomposition_stage", ()-> new Block(AbstractBlock.Properties.of(Material.STONE).harvestLevel(2).strength(2.0F, 6.0F).sound(SoundType.STONE)));//分解台

    private static boolean always(BlockState p_1, IBlockReader p_2, BlockPos p_3) {return true;}
}
