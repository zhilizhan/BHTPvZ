package com.zhilizhan.bhtpvz.data.loot;

import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.item.ItemRegister;
import com.zhilizhan.bhtpvz.common.misc.BHTPvZLoot;
import net.minecraft.data.loot.EntityLootTables;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.LootTable.Builder;
import net.minecraft.loot.conditions.RandomChanceWithLooting;
import net.minecraft.loot.functions.LootingEnchantBonus;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;

import java.util.function.BiConsumer;

public class BHTPVZEntityLootTables extends EntityLootTables {

	@Override
	public void accept(BiConsumer<ResourceLocation, Builder> t) {
		t.accept(BHTPvZLoot.EDGAR_090547, getZombieLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
					    .add(ItemLootEntry.lootTableItem(ItemRegister.ZOMBIE_DOLL.get()))
						.when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.01F, 0.01F))
		).withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(ItemRegister.POLE.get()))
						.when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.05F, 0.02F))
				)
		);
		t.accept(BHTPvZLoot.FLOWER_POT_ZOMBIE, getZombieLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(BlockRegister.FLOWER_POT.get()))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.025F, 0.02F))
		));
		t.accept(BHTPvZLoot.TARGET_ARROW_ZOMBIE, getZombieLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.TARGET_ARROW.get()))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.025F, 0.01F))
		));
		t.accept(BHTPvZLoot.MC_ZOMBIE, getZombieLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(Items.GRASS_BLOCK))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.025F, 0.01F))
		));
		t.accept(BHTPvZLoot.STEEL_PUMPKIN_ZOMBIE, getZombieLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(BlockRegister.STEEL_LADDER.get()))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.025F, 0.01F))
		));
		t.accept(BHTPvZLoot.SUN_FLOWER_ZOMBIE, getZombieLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(Items.SUNFLOWER))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.025F, 0.02F))
		));

	}
	
	private static Builder getLootTable() {
		return LootTable.lootTable();
	}
	
	private static Builder getRottenFleshLootTable() {
		return LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				.add(ItemLootEntry.lootTableItem(Items.ROTTEN_FLESH))
				.apply(SetCount.setCount(RandomValueRange.between(0.0F, 2.0F)))
				.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))
				.when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.15F, 0.01F)))

				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(ItemRegister.REAL_BRAIN.get()))
						.when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.0001F, 0.0002F))
				);
	}
	
	private static Builder getZombieLootTable() {
		return getRottenFleshLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				.add(ItemLootEntry.lootTableItem(ItemRegister.CORN_SEEDS.get()))
				.add(ItemLootEntry.lootTableItem(Items.SUNFLOWER))
				.add(ItemLootEntry.lootTableItem(ItemRegister.NUT.get()))
				.when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.001F, 0.01F)));
	}

}
