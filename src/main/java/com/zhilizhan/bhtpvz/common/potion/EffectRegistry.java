package com.zhilizhan.bhtpvz.common.potion;

import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.common.potion.potions.Halitosis;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectRegistry {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, BHTPvZ.MOD_ID);
    public static final RegistryObject<Effect> HALITOSIS = EFFECTS.register("halitosis", ()-> new Halitosis(EffectType.BENEFICIAL, 7259200));//口臭buff
}
