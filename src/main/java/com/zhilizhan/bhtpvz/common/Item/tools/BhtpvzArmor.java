package com.zhilizhan.bhtpvz.common.Item.tools;

import com.hungteen.pvz.common.item.ItemRegister;
import com.zhilizhan.bhtpvz.common.Item.ItemRegistry;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

import java.util.function.Supplier;

public enum BhtpvzArmor implements IArmorMaterial {
    ORIGIN1("origin1", 33, new int[]{3, 6, 8, 3}, 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, ()->{
        return Ingredient.of(ItemRegister.ORIGIN_INGOT.get());
    }),
    DAMSON_CRYSTAL_1("damson_crystal_1", 37, new int[]{3, 6, 8, 3}, 15, SoundEvents.ARMOR_EQUIP_DIAMOND, 3.0F, 0.1F, ()->{
        return Ingredient.of(ItemRegistry.DAMSON_CRYSTAL_INGOT.get());
    });

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyValue<Ingredient> repairIngredient;

    BhtpvzArmor(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue, SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtections;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyValue<>(repairIngredient);
    }

    public int getDurabilityForSlot(EquipmentSlotType p_200896_1_) {
        return HEALTH_PER_SLOT[p_200896_1_.getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlotType p_200902_1_) {return this.slotProtections[p_200902_1_.getIndex()];}

    public int getEnchantmentValue() {return this.enchantmentValue;}

    public SoundEvent getEquipSound() {return this.sound;}

    public Ingredient getRepairIngredient() {return this.repairIngredient.get();}

    public String getName() {return this.name;}

    public float getToughness() {return this.toughness;}

    public float getKnockbackResistance() {return this.knockbackResistance;}
}
