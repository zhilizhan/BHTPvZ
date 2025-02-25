package com.zhilizhan.bhtpvz.data.tag;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.block.ores.EssenceOreBlock;
import com.hungteen.pvz.common.impl.RankTypes;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.item.material.TemplateCardItem;
import com.hungteen.pvz.common.item.spawn.card.PlantCardItem;
import com.hungteen.pvz.common.item.spawn.card.SummonCardItem;
import com.hungteen.pvz.common.misc.tag.PVZItemTags;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import com.zhilizhan.bhtpvz.data.BHTPVZBlockModelGenerator;
import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Comparator;
import java.util.function.Predicate;

public class BHTPVZItemTagGenerator extends ItemTagsProvider{

	public BHTPVZItemTagGenerator(DataGenerator generatorIn, BlockTagsProvider provider, ExistingFileHelper helper) {
		super(generatorIn, provider, PVZMod.MOD_ID, helper);
	}
	
	@Override
	protected void addTags() {
		//mc tag

		//forge tag
		this.tag(PVZItemTags.PEAS).add(BHTPvZItems.GOO_PEA.get());
		this.tag(PVZItemTags.CHILIPEPPERS).add(ItemRegister.PEPPER.get());
		this.tag(PVZItemTags.CABBAGES).add(BHTPvZItems.ICE_CABBAGE.get());
		//this.tag(PVZItemTags.AMETHYST_INGOTS).add(ItemRegister.AMETHYST_INGOT.get());
		//this.tag(PVZItemTags.AMETHYST_ORES).add(BlockRegister.AMETHYST_ORE.get().asItem());
		this.tag(PVZItemTags.BLACK_MATERIALS).add(BHTPvZItems.DAMSON_CRYSTAL_INGOT.get());
		//pvz tag
		for (Item item : ForgeRegistries.ITEMS) {
			if (item instanceof SummonCardItem) {
				if (item instanceof PlantCardItem) {
					this.tag(PVZItemTags.PLANT_CARDS).add(item);
					if (((SummonCardItem) item).isEnjoyCard) {
						this.tag(PVZItemTags.PLANT_ENJOY_CARDS).add(item);
					} else {
						this.tag(PVZItemTags.PLANT_SUMMON_CARDS).add(item);
					}
				}

			}// else if (item instanceof BlockItem) {
			//	Block b = ((BlockItem) item).getBlock();
			//	if (b instanceof EssenceOreBlock) {
			//		this.tag(PVZItemTags.ESSENCE_ORES).add(item);
			//	}
			//}
		}
	}
	
	@Override
	public String getName() {
		return "Plants vs Zombies item tags";
	}

}
