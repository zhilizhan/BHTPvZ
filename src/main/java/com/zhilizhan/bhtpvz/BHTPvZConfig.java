package com.zhilizhan.bhtpvz;

import net.minecraftforge.common.ForgeConfigSpec;

public class BHTPvZConfig {
    public static BHTPvZConfig.Common COMMON_CONFIG;
    public static class Common {
        public BHTPvZConfig.Common.WorldSettings WorldSettings = new BHTPvZConfig.Common.WorldSettings();

        public Common(ForgeConfigSpec.Builder builder) {
            this.WorldSettings.NightBiomeChance = builder.translation("config.bhtpvz.world.night_biome_chance.").comment("the gen chance of Night biome(the larger the more chance to see it).").defineInRange("NightBiomeChance", 20, 1, 10000);
        }

        public static class WorldSettings {
            public ForgeConfigSpec.IntValue NightBiomeChance;
        }
    }
}
