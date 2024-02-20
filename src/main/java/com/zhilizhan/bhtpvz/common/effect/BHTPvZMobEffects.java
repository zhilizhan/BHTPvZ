package com.zhilizhan.bhtpvz.common.effect;

import com.zhilizhan.bhtpvz.BHTPvZ;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.UUID;

public class BHTPvZMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, BHTPvZ.MOD_ID);

    public static final UUID POISON_EFFECT_UUID = UUID.fromString("102aca6f-08ad-b606-6839-8da6d5d10a67");


    public static final RegistryObject<MobEffect> HALITOSIS = MOB_EFFECTS.register("halitosis", () -> new BHTPvZMobEffect(MobEffectCategory.BENEFICIAL, 7259200));// 口臭buff
    public static final RegistryObject<MobEffect> GOO_POISON = MOB_EFFECTS.register("goo_poison", () -> new BHTPvZMobEffect(MobEffectCategory.HARMFUL, 15350204).addAttributeModifier(Attributes.MOVEMENT_SPEED,
            POISON_EFFECT_UUID.toString(), -0.01f, AttributeModifier.Operation.MULTIPLY_TOTAL));// 毒素buff
}