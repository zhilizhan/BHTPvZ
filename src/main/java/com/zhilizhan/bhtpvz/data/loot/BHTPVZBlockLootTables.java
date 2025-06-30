package com.zhilizhan.bhtpvz.data.loot;


import com.hungteen.pvz.common.item.ItemRegister;
import com.zhilizhan.bhtpvz.common.block.BHTPvZBlocks;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.CropsBlock;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable.Builder;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.loot.conditions.TableBonus;
import net.minecraftforge.fml.RegistryObject;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BHTPVZBlockLootTables extends BlockLootTables {

	private final Set<Block> knownBlocks = new HashSet<>();
    private static final ILootCondition.IBuilder HAS_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item()
			.hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
//	private static final ILootCondition.IBuilder HAS_NO_SILK_TOUCH = HAS_SILK_TOUCH.invert();
	private static final ILootCondition.IBuilder HAS_SHEARS = MatchTool
			.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
	private static final ILootCondition.IBuilder HAS_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(HAS_SILK_TOUCH);
	private static final ILootCondition.IBuilder HAS_NO_SHEARS_OR_SILK_TOUCH = HAS_SHEARS_OR_SILK_TOUCH.invert();
	private static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[] { 0.05F, 0.0625F, 0.083333336F, 0.1F };
//	private static final float[] JUNGLE_LEAVES_SAPLING_CHANGES = new float[]{0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F};

	@Override
	protected void addTables() {
		//无loot表
		final Set<Block> noLootBlocks = new HashSet<>(Arrays.asList(
				BHTPvZBlocks.WATER_POT.get(),BHTPvZBlocks.POT_GRASS.get(),BHTPvZBlocks.QUESTION_MARK_POT.get(),BHTPvZBlocks.PLANT_POT.get(),BHTPvZBlocks.SQUASH.get(),BHTPvZBlocks.SCREEN_DOOR.get()
			    ));

		// 矿石
		this.add(BHTPvZBlocks.MORION_ORE.get(), (block) -> {
			return createOreDrop(block, BHTPvZBlocks.MORION_ORE.get().asItem());
		});

		// 农作物
        ILootCondition.IBuilder tmpBuilder = getAgeBuilder(BHTPvZBlocks.CHILI.get(), 3);
		this.add(BHTPvZBlocks.CHILI.get(),
				createCropDrops(BHTPvZBlocks.CHILI.get(), ItemRegister.PEPPER.get(), tmpBuilder));
		tmpBuilder = getAgeBuilder(BHTPvZBlocks.GARLIC.get(), 3);
		this.add(BHTPvZBlocks.GARLIC.get(),
				createCropDrops(BHTPvZBlocks.GARLIC.get(), BHTPvZItems.GARLIC.get(), tmpBuilder));

		// 树叶
		this.add(BHTPvZBlocks.CHERRY_LEAVES.get(), (block) -> {
			return createLeavesDrops(block, BHTPvZBlocks.CHERRY_SAPLING.get(), BHTPvZItems.CHERRY.get(), NORMAL_LEAVES_SAPLING_CHANCES);
		});
		this.add(BHTPvZBlocks.STARFRUIT_LEAVES.get(), (block) -> {
			return createLeavesDrops(block, BHTPvZBlocks.STARFRUIT_SAPLING.get(), BHTPvZItems.STARFRUIT.get(), NORMAL_LEAVES_SAPLING_CHANCES);
		});

		// 其他方块掉落其本身
		BHTPvZBlocks.BLOCKS.getEntries().stream()
				.map(RegistryObject::get)
				.filter(block -> !noLootBlocks.contains(block))
				.filter(block -> !knownBlocks.contains(block)) // 避免重复
				.forEach(this::dropSelf);
	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		return this.knownBlocks;
	}

	@Override
	protected void add(Block p_218507_1_, Builder p_218507_2_) {
		super.add(p_218507_1_, p_218507_2_);
		this.knownBlocks.add(p_218507_1_);
	}

	private static Builder createCropDrops(Block block, Item crops, ILootCondition.IBuilder bb) {
		return createCropDrops(block, crops, block.asItem(), bb);
	}

	private ILootCondition.IBuilder getAgeBuilder(Block block, int age) {
		return BlockStateProperty.hasBlockStateProperties(block)
				.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropsBlock.AGE, age));
	}

	protected static Builder createLeavesDrops(Block p_218526_0_, Block p_218526_1_,
			Item drop, float... p_218526_2_) {
		return createLeavesDrops(p_218526_0_, p_218526_1_, p_218526_2_)
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).when(HAS_NO_SHEARS_OR_SILK_TOUCH)
						.add(applyExplosionCondition(p_218526_0_, ItemLootEntry.lootTableItem(drop))
								.when(TableBonus.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.01F, 0.015F,
										0.02F, 0.025F, 0.03F))));
	}

}
