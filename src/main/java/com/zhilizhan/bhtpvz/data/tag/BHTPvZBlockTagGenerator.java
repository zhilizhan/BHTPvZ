package com.zhilizhan.bhtpvz.data.tag;

import com.hungteen.pvz.PVZMod;
import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Comparator;
import java.util.function.Predicate;
//未使用
public class BHTPvZBlockTagGenerator extends BlockTagsProvider{

	public BHTPvZBlockTagGenerator(DataGenerator generatorIn, ExistingFileHelper helper) {
		super(generatorIn, PVZMod.MOD_ID, helper);
	}
	
	@Override
	protected void addTags() {
	}
	
	private Block[] getFilterBlocks(Predicate<Block> predicate) {
		return registry.stream()
				.filter(predicate)
				.sorted(Comparator.comparing(ForgeRegistries.BLOCKS::getKey))
				.toArray(Block[]::new);
	}
	
	@Override
	public String getName() {
		return "Plants vs Zombies block tags";
	}

}
