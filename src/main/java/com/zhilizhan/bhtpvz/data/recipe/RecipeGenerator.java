package com.zhilizhan.bhtpvz.data.recipe;

import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.api.types.IRankType;
import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.item.spawn.card.PlantCardItem;
import com.hungteen.pvz.common.misc.tag.PVZItemTags;
import com.hungteen.pvz.data.recipe.FragmentRecipeBuilder;
import com.hungteen.pvz.data.recipe.FusionRecipeBuilder;
import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.data.ForgeRecipeProvider;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class RecipeGenerator extends ForgeRecipeProvider{

	public RecipeGenerator(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
		//special cards.
		registerSpecialCard(consumer, BHTPvZItems.STEEL_PUMPKIN_CARD.get(), Items.PUMPKIN, BlockRegister.STEEL_LADDER.get().asItem());
		//summon card
		registerCommonCard(consumer, BHTPvZItems.STEEL_PUMPKIN_CARD.get(), BHTPvZItems.STEEL_PUMPKIN_TOKEN.get());
		registerCommonCard(consumer, BHTPvZItems.ICE_CABBAGE_PULT_CARD.get(), BHTPvZItems.ICE_CABBAGE.get());
		registerCommonCard(consumer, BHTPvZItems.FIRE_PEASHOOTER_CARD.get(), ItemRegister.FLAME_PEA.get());
		registerCommonCard(consumer, BHTPvZItems.NUT_BOWLING_CARD.get(), PVZItemTags.NUTS);
		registerCommonCard(consumer, BHTPvZItems.WATER_POT_CARD.get(), BHTPvZItems.WATER_POT.get());
		registerCommonCard(consumer, BHTPvZItems.CHORUS_FRUIT_PULT_CARD.get(), Items.CHORUS_FRUIT);
		registerCommonCard(consumer, BHTPvZItems.ROTATE_RADISH_CARD.get(), Items.CARROT);
		registerCommonCard(consumer, BHTPvZItems.CARAMEL_KERNEL_PULT_CARD.get(), PVZItemTags.CORNS);
		registerCommonCard(consumer, BHTPvZItems.BLAZE_WART_CARD.get(), Items.NETHER_WART);
		registerCommonCard(consumer, BHTPvZItems.POT_GRASS_CARD.get(), BHTPvZItems.POT_GRASS.get());
		registerCommonCard(consumer, BHTPvZItems.SELF_IMITATER_CARD.get(), Items.BAKED_POTATO);
		registerCommonCard(consumer, BHTPvZItems.BEE_SHOOTER_CARD.get(), Items.HONEYCOMB);
		registerCommonCard(consumer, BHTPvZItems.FODDER_BUSH_CARD.get(), Items.SWEET_BERRIES);
		registerCommonCard(consumer, BHTPvZItems.PRIMAL_PEA_SHOOTER_CARD.get(), BHTPvZItems.PEA_BLOCK.get());
		registerCommonCard(consumer, BHTPvZItems.GOO_PEA_SHOOTER_CARD.get(), BHTPvZItems.GOO_PEA.get());
		registerCommonCard(consumer, BHTPvZItems.MAGNIFYING_GRASS_CARD.get(), Items.GLASS);

		//smelt
		//registerStoneSmelting(consumer, BHTPVZBlocks.AMETHYST_ORE.get(), BHTPVZItems.AMETHYST_INGOT.get(), 1.4F, 250, "amethyst_ingot");
		//registerFoodSmelting(consumer, BHTPVZItems.FAKE_BRAIN.get(), BHTPVZItems.COOKED_BRAIN.get(), 0.4F, 200, "cooked_brain");

		//fragment splice
		PVZAPI.get().getPlants().forEach(p -> {
			registerFragment(consumer, p);
		});

		//fusion recipe
		registerFusion(consumer, Arrays.asList(
				ItemRegister.MELON_PULT_CARD.get(),
				ItemRegister.GOLD_MAGNET_CARD.get()
				), BHTPvZItems.GOLDEN_MELON_PULT_CARD.get());

		registerFusion(consumer, Arrays.asList(
				ItemRegister.CABBAGE_PULT_CARD.get(),
				ItemRegister.ICEBERG_LETTUCE_CARD.get()
		), BHTPvZItems.ICE_CABBAGE_PULT_CARD.get());

		registerFusion(consumer, Arrays.asList(
				ItemRegister.PEA_SHOOTER_CARD.get(),
				ItemRegister.PEA_SHOOTER_CARD.get(),
				ItemRegister.PEA_SHOOTER_CARD.get(),
				ItemRegister.PEA_SHOOTER_CARD.get(),
				ItemRegister.PEA_SHOOTER_CARD.get()
		), BHTPvZItems.PEAPOD_CARD.get());

		registerFusion(consumer, Arrays.asList(
				ItemRegister.SNOW_PEA_CARD.get(),
				ItemRegister.REPEATER_CARD.get()
		), BHTPvZItems.RE_ICEPEA_CARD.get());

	}

	private void registerFusion(Consumer<IFinishedRecipe> consumer, List<IItemProvider> list, Item result) {
		final ItemStack stack = new ItemStack(result);
		final com.hungteen.pvz.data.recipe.FusionRecipeBuilder builder = FusionRecipeBuilder.shapeless(result);
		list.forEach(i -> builder.requires(i));
		if(result instanceof PlantCardItem){
			builder.requires(((PlantCardItem) result).plantType.getRank().getCardTag());
		}
		builder.save(consumer, BHTPvZ.prefix("card_fusion/" + result.getRegistryName().getPath()));
	}

	private void registerFragment(Consumer<IFinishedRecipe> consumer, IPlantType type) {
		type.getSummonCard().ifPresent(card -> {
			if(type.getEnjoyCard().isPresent()) {
				FragmentRecipeBuilder.shaped(card)
					.pattern("AAAAA")
					.pattern("ABBBA")
					.pattern("ABCBA")
					.pattern("ABBBA")
					.pattern("AAAAA")
					.define('A', type.getEssence().getEssenceItem())
					.define('B', type.getEnjoyCard().get())
					.define('C', type.getRank().getCardTag())
					.unlockedBy("has_essence", has(type.getEssence().getEssenceItem()))
					.save(consumer, BHTPvZ.prefix("fragment_splice/" + type.toString() + "_card"));
			}
			
		});
	}
	
	private void registerStoneSmelting(Consumer<IFinishedRecipe> consumer, IItemProvider input, IItemProvider item, float xp, int time, String name) {
		CookingRecipeBuilder.smelting(Ingredient.of(input), item, xp, time).unlockedBy("has_input", has(input)).save(consumer, BHTPvZ.prefix("smelting/" + name + "_from_smelting"));
		CookingRecipeBuilder.blasting(Ingredient.of(input), item, xp, time).unlockedBy("has_input", has(input)).save(consumer, BHTPvZ.prefix("smelting/" + name + "_from_blasting"));
	}
	
	private void registerFoodSmelting(Consumer<IFinishedRecipe> consumer, IItemProvider input, IItemProvider item, float xp, int time, String name) {
		CookingRecipeBuilder.smelting(Ingredient.of(input), item, xp, time).unlockedBy("has_input", has(input)).save(consumer, BHTPvZ.prefix("smelting/" + name));
		CookingRecipeBuilder.cooking(Ingredient.of(input), item, xp, time, IRecipeSerializer.SMOKING_RECIPE).unlockedBy("has_input", has(input)).save(consumer, BHTPvZ.prefix("smelting/" + name + "_from_smoking"));
		CookingRecipeBuilder.cooking(Ingredient.of(input), item, xp, time, IRecipeSerializer.CAMPFIRE_COOKING_RECIPE).unlockedBy("has_input", has(input)).save(consumer, BHTPvZ.prefix("smelting/" + name + "_from_campfire_cooking"));
	}
	
	private void registerCommonCard(Consumer<IFinishedRecipe> consumer, PlantCardItem result, Item crop) {
		final Item essence = result.plantType.getEssence().getEssenceItem();
		final ITag.INamedTag<Item> rankCard = result.plantType.getRank().getCardTag();
	    ShapedRecipeBuilder.shaped(result)
				.pattern("AAA")
				.pattern("ABA")
				.pattern("ACA")
				.define('A', essence)
				.define('B', crop)
				.define('C', rankCard)
				.unlockedBy("has_essence", has(essence))
				.save(consumer, BHTPvZ.prefix("card/" + result.plantType.toString().toLowerCase() + "_card"));
	}
	private void registerSpecialCard(Consumer<IFinishedRecipe> consumer, PlantCardItem result, Item crop ,Item specialItem) {
		final Item essence = result.plantType.getEssence().getEssenceItem();
		final ITag.INamedTag<Item> rankCard = result.plantType.getRank().getCardTag();
		ShapedRecipeBuilder.shaped(result)
				.pattern("ADA")
				.pattern("ABA")
				.pattern("ACA")
				.define('A', essence)
				.define('B', crop)
				.define('C', rankCard)
				.define('D', specialItem)
				.unlockedBy("has_essence", has(essence))
				.save(consumer, BHTPvZ.prefix("card/" + "special_" + result.plantType.toString().toLowerCase() + "_card"));
	}
	
	private void registerCommonCard(Consumer<IFinishedRecipe> consumer, PlantCardItem result, ITag.INamedTag<Item> crop) {
		final Item essence = result.plantType.getEssence().getEssenceItem();
		final ITag.INamedTag<Item> rankCard = result.plantType.getRank().getCardTag();
		if(rankCard != null){
			ShapedRecipeBuilder.shaped(result)
					.pattern("AAA")
					.pattern("ABA")
					.pattern("ACA")
					.define('A', essence)
					.define('B', crop)
					.define('C', rankCard)
					.unlockedBy("has_essence", has(essence))
					.save(consumer, BHTPvZ.prefix("card/" +  result.plantType.toString().toLowerCase() + "_card"));
		}
	}

	private void registerTemplateCard(Consumer<IFinishedRecipe> consumer, IRankType type) {
		final Item rankCard = type.getTemplateCard();
		final ITag.INamedTag<Item> material = type.getMaterial();
		final Item origin = ItemRegister.ORIGIN_ESSENCE.get();
		if (material != null) {
			ShapedRecipeBuilder.shaped(rankCard)
					.pattern("AAA")
					.pattern("ABA")
					.pattern("AAA")
					.define('A', material)
					.define('B', origin)
					.unlockedBy("has_origin", has(origin))
					.save(consumer, BHTPvZ.prefix("card/template/" + type.getName() + "_card"));
		}
	}
	
}
