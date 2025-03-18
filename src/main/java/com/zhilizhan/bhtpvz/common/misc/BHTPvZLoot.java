package com.zhilizhan.bhtpvz.common.misc;

import com.zhilizhan.bhtpvz.BHTPvZ;
import net.minecraft.util.ResourceLocation;

public class BHTPvZLoot {
    public static final ResourceLocation EDGAR_090547 = getEntityLootTable("edgar_090547");
    public static final ResourceLocation FLOWER_POT_ZOMBIE = getEntityLootTable("flower_pot_zombie");
    public static final ResourceLocation TARGET_ARROW_ZOMBIE = getEntityLootTable("target_arrow_zombie");
    public static final ResourceLocation MC_ZOMBIE = getEntityLootTable("mc_zombie");
    public static final ResourceLocation STEEL_PUMPKIN_ZOMBIE = getEntityLootTable("steel_pumpkin_zombie");
    public static final ResourceLocation SUN_FLOWER_ZOMBIE = getEntityLootTable("sun_flower_zombie");

    public static ResourceLocation getEntityLootTable(String name) {
        return BHTPvZ.prefix("entities/" + name);
    }

}
