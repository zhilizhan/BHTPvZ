package com.zhilizhan.bhtpvz.common.other;

import com.zhilizhan.bhtpvz.common.block.BHTPvZBlocks;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import net.minecraft.block.ComposterBlock;
import net.minecraft.util.IItemProvider;

public class OtherRegister {

	public static void registerCompostable() {
		registerCompostable(0.3F, BHTPvZBlocks.STARFRUIT_LEAVES.get());
		registerCompostable(0.3F, BHTPvZBlocks.CHERRY_LEAVES.get());
		registerCompostable(0.3F, BHTPvZBlocks.STARFRUIT_SAPLING.get());
		registerCompostable(0.3F, BHTPvZBlocks.CHERRY_SAPLING.get());
		registerCompostable(1.0F, BHTPvZBlocks.PEA_BLOCK.get());
		registerCompostable(1.0F, BHTPvZBlocks.SQUASH.get());
		registerCompostable(0.3F, BHTPvZItems.SQUASH_SLICE.get());
		registerCompostable(0.3F, BHTPvZItems.GOO_PEA.get());
		registerCompostable(0.3F, BHTPvZItems.ICE_CABBAGE.get());
		registerCompostable(0.4F, BHTPvZItems.STARFRUIT.get());
		registerCompostable(0.5F, BHTPvZItems.ANGEL_STARFRUIT.get());
		registerCompostable(0.5F, BHTPvZItems.CHERRY.get());
		registerCompostable(0.3F, BHTPvZItems.CHILI_SEEDS.get());
		registerCompostable(0.5F, BHTPvZItems.GARLIC.get());
	}

	private static void registerCompostable(float chance, IItemProvider itemIn) {
		ComposterBlock.COMPOSTABLES.put(itemIn.asItem(), chance);
	}
	
}
