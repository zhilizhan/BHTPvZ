package com.zhilizhan.bhtpvz.common.Item.tools;

import com.zhilizhan.bhtpvz.common.Item.ItemRegistry;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public enum BhtpvzTools implements IItemTier {
    HAMMRE(300, 6.0F, 2.0F, 2, 14,()->{
        return Ingredient.of(ItemTags.PLANKS);
    }),
    DAMSON_CRYSTAL_2(2031, 9.0F, 4.0F, 4, 15,()->{
        return Ingredient.of(ItemRegistry.DAMSON_CRYSTAL_INGOT.get());
    });

    private int uses;
    private float speed;
    private float attackDamageBonus;
    private int level;
    private int enchantmentValue;
    private LazyValue<Ingredient> repairIngredient;

    BhtpvzTools(int uses, float speed, float attackDamageBonus, int level, int enchantmentValue, Supplier<Ingredient> repairIngredient){
        this.uses = uses;
        this.speed = speed;
        this.attackDamageBonus = attackDamageBonus;
        this.level = level;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = new LazyValue<>(repairIngredient);
    }

    public int getUses() {return this.uses;}

    public float getSpeed() {return this.speed;}

    public float getAttackDamageBonus() {return this.attackDamageBonus;}

    public int getLevel() {return this.level;}

    public int getEnchantmentValue() {return this.enchantmentValue;}

    public Ingredient getRepairIngredient() {return this.repairIngredient.get();}
}
