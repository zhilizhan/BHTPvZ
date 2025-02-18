package com.zhilizhan.bhtpvz.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class BHTPvZConfig {
    public static BHTPvZConfig.Common COMMON_CONFIG;

    public static class Common {
        public BHTPvZConfig.Common.EntitySettings EntitySettings = new BHTPvZConfig.Common.EntitySettings();

        public Common(ForgeConfigSpec.Builder builder) {

            builder.comment("Settings about zombies.").push("Zombie Settings");
            this.EntitySettings.ZombieSetting.ZombieHurtAmount = builder.translation("config.bhtpvz.zombie.zombie_hurt_amount").comment("zombie hurt amount of multiple.").defineInRange("ZombieHurtAmount", 1.0, 0.1, 5);
            this.EntitySettings.ZombieSetting.GigaTombstoneCanBeAttack = builder.translation("config.bhtpvz.plant.gaia_tombstone_can_be_attack").comment("If it‘s true,plant will Attack GigaTombstone.").define("GigaTombstoneCanBeAttack", false);

            builder.pop();

            builder.comment("Settings about plants.").push("Plant Settings");
            this.EntitySettings.PlantSetting.ScaredyShroomSurrender = builder.translation("config.bhtpvz.plant.scaredy_shroom_surrender").comment("If it‘s true,scaredyshroom will surrender.").define("ScaredyShroomSurrender", false);
            this.EntitySettings.PlantSetting.PuffShroomGrow = builder.translation("config.bhtpvz.plant.puffshroom_grow").comment("If it‘s true,puffshroom will growup.").define("PuffShroomGrow", false);
            this.EntitySettings.PlantSetting.MyceliumSleep = builder.translation("config.bhtpvz.plant.mycelium_sleep").comment("If it‘s false,mushrooms sleep on mycelium.").define("MyceliumSleep", false);
            builder.pop();

            builder.comment("Settings about pvzDamage.").push("EntityLiveTime");
            this.EntitySettings.EntityLiveTick.PVZDamageAmount = builder.translation("config.bhtpvz.entity.pvz_damage_amount").comment("pvz entity damage of amount to vanilla entity.").defineInRange("PVZDamageAmount", 1.0, 0.1, 3);
            builder.pop();

        }



        public static class EntitySettings {
            public BHTPvZConfig.Common.EntitySettings.EntityLiveTick EntityLiveTick = new BHTPvZConfig.Common.EntitySettings.EntityLiveTick();
            public BHTPvZConfig.Common.EntitySettings.ZombieSetting ZombieSetting = new BHTPvZConfig.Common.EntitySettings.ZombieSetting();
            public BHTPvZConfig.Common.EntitySettings.PlantSetting PlantSetting = new BHTPvZConfig.Common.EntitySettings.PlantSetting();

            public EntitySettings() {
            }

            public static class EntityLiveTick {

                public ForgeConfigSpec.DoubleValue PVZDamageAmount;

                public EntityLiveTick() {
                }
            }

            public static class PlantSetting {
                public ForgeConfigSpec.BooleanValue ScaredyShroomSurrender;
                public ForgeConfigSpec.BooleanValue PuffShroomGrow;
                public ForgeConfigSpec.BooleanValue MyceliumSleep;

                public PlantSetting() {
                }
            }

            public static class ZombieSetting {
                public ForgeConfigSpec.DoubleValue ZombieHurtAmount;
                public ForgeConfigSpec.BooleanValue GigaTombstoneCanBeAttack;

                public ZombieSetting() {
                }
            }
        }

    }
}