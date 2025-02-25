package com.zhilizhan.bhtpvz.data.tag;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.entity.PVZEntityClassifications;
import com.hungteen.pvz.common.misc.tag.PVZEntityTypeTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.EntityTypeTagsProvider;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Comparator;
import java.util.function.Predicate;

public class BHTPVZEntityTypeTagGenerator extends EntityTypeTagsProvider{

	public BHTPVZEntityTypeTagGenerator(DataGenerator generatorIn, ExistingFileHelper helper) {
		super(generatorIn, PVZMod.MOD_ID, helper);
	}
	
	@Override
	protected void addTags() {
		/* pvz tags */
		
		// for plant entities.
		this.tag(PVZEntityTypeTags.PVZ_PLANTS)
				.add(getFilterTypes(type -> type.getCategory() == PVZEntityClassifications.PVZ_PLANT))
				.add(EntityRegister.CRAZY_DAVE.get());

		// for zombie entities.
		this.tag(PVZEntityTypeTags.PVZ_ZOMBIES)
				.add(getFilterTypes(type -> type.getCategory() == PVZEntityClassifications.PVZ_ZOMBIE));

	}
	
	@SuppressWarnings("unused")
	private EntityType<?>[] getFilterTypes(Predicate<EntityType<?>> predicate) {
		return registry.stream()
				.filter(predicate)
				.sorted(Comparator.comparing(ForgeRegistries.ENTITIES::getKey))
				.toArray(EntityType<?>[]::new);
	}
	
	@Override
	public String getName() {
		return "Plants vs Zombies entity type tags";
	}

}
