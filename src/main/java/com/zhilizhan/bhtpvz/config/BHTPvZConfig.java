package com.zhilizhan.bhtpvz.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class BHTPvZConfig {
    public static BHTPvZConfig.Common COMMON_CONFIG;

    public static class Common {
        public BHTPvZConfig.Common.RuleSettings RuleSettings = new BHTPvZConfig.Common.RuleSettings();
        public BHTPvZConfig.Common.WorldSettings WorldSettings = new BHTPvZConfig.Common.WorldSettings();
        public BHTPvZConfig.Common.EntitySettings EntitySettings = new BHTPvZConfig.Common.EntitySettings();
        public BHTPvZConfig.Common.BlockSettings BlockSettings = new BHTPvZConfig.Common.BlockSettings();
        public BHTPvZConfig.Common.ItemSettings ItemSettings = new BHTPvZConfig.Common.ItemSettings();

        public Common(ForgeConfigSpec.Builder builder) {
            builder.comment("Settings about global rules.").push("Rule Settings");
            builder.pop();
            builder.comment("Settings about world.").push("World Settings");
            builder.comment("Settings about the biome gen.").push("Biome Settings");
            builder.pop();
            builder.comment("Settings about the structure gen.").push("Structure Settings");
             builder.pop();
            builder.comment("Settings about the ore gen.").push("Ore Settings");
            builder.pop();
            builder.comment("The Spawn Weight of entity.").push("EntitySpawnWeight");
            builder.pop();
            builder.pop();
            builder.comment("Settings about entities.").push("Entity Settings");
            builder.comment("Settings about players.").push("Player Settings");
            this.EntitySettings.PlayerInitialGroup = builder.translation("config.bhtpvz.player.initial_group").comment("Player Initial Group When they join the world for the first time(-2 means other monsters, -1 means zombies, 0 means neutral creatures, 1 means plants and 2 means other guards).").defineInRange("PlayerInitialGroup", 1, -2, 2);
            this.EntitySettings.PlayerBaseSunAmount = builder.translation("config.bhtpvz.player.initial_sun").comment("players' base sun amount, it will increase when tree maxLevel increasing.").defineInRange("PlayerInitialSun", 800, 100, 10000);
            builder.pop();
            builder.comment("Settings about zombies.").push("Zombie Settings");
            this.EntitySettings.ZombieSetting.ZombieHurtAmount = builder.translation("config.bhtpvz.zombie.zombie_hurt_amount").comment("zombie hurt amount of multiple.").defineInRange("ZombieHurtAmount", 1.5, 0.1,5);
            builder.pop();
            builder.comment("Settings about plants.").push("Plant Settings");
            this.EntitySettings.PlantSetting.ScaredyShroomSurrender = builder.translation("config.bhtpvz.plant.scaredy_shroom_surrender").comment("If it‘s true,scaredyshroom will surrender.").define("ScaredyShroomSurrender", true);
            this.EntitySettings.PlantSetting.PuffShroomGrow = builder.translation("config.bhtpvz.plant.puffshroom_grow").comment("If it‘s true,puffshroom will growup.").define("PuffShroomGrow",true);
            this.EntitySettings.PlantSetting.SteelPumpkinPeace = builder.translation("config.bhtpvz.plant.steel_pumpkin_peace").comment("If it‘s true,steel_pumpkin with plant will not be attacked.").define("SteelPumpkinPeace",true);
            this.EntitySettings.PlantSetting.MyceliumSleep = builder.translation("config.bhtpvz.plant.mycelium_sleep").comment("If it‘s false,mushrooms sleep on mycelium.").define("MyceliumSleep",true);

            builder.pop();
            builder.comment("The Max live time for Entity like sun.").push("EntityLiveTime");
            this.EntitySettings.EntityLiveTick.PVZDamageAmount = builder.translation("config.bhtpvz.entity.pvz_damage_amount").comment("pvz entity damage of amount to vanilla entity.").defineInRange("PVZDamageAmount", 0.5, 0.1, 3);
            this.EntitySettings.EntityLiveTick.ElementBallLiveTick = builder.translation("config.bhtpvz.entity.element_ball_live_tick").comment("how many ticks can element ball entity live.").defineInRange("ElementBallLiveTick", 600, 1, 1000000);
            builder.pop();
            builder.pop();
            builder.comment("Settings about blocks.").push("Block Settings");
            builder.comment("Setting about break blocks.").push("Break Block Setting");
            builder.pop();
            builder.pop();
            builder.comment("Settings about items.").push("Item Settings");
            builder.pop();
        }

        public static class ItemSettings {


            public ItemSettings() {
            }
        }

        public static class BlockSettings {

            public BlockSettings() {
            }
        }

        public static class EntitySettings {
            public BHTPvZConfig.Common.EntitySettings.EntityLiveTick EntityLiveTick = new BHTPvZConfig.Common.EntitySettings.EntityLiveTick();
            public BHTPvZConfig.Common.EntitySettings.ZombieSetting ZombieSetting = new BHTPvZConfig.Common.EntitySettings.ZombieSetting();
            public BHTPvZConfig.Common.EntitySettings.PlantSetting PlantSetting = new BHTPvZConfig.Common.EntitySettings.PlantSetting();
            public ForgeConfigSpec.IntValue PlayerInitialGroup;
            public ForgeConfigSpec.IntValue PlayerBaseSunAmount;

            public EntitySettings() {
            }

            public static class EntityLiveTick {

                public ForgeConfigSpec.DoubleValue PVZDamageAmount;
                public ForgeConfigSpec.IntValue ElementBallLiveTick;

                public EntityLiveTick() {
                }
            }

            public static class PlantSetting {
                public ForgeConfigSpec.BooleanValue ScaredyShroomSurrender;
                public ForgeConfigSpec.BooleanValue PuffShroomGrow;
                public ForgeConfigSpec.BooleanValue SteelPumpkinPeace;
                public ForgeConfigSpec.BooleanValue MyceliumSleep;
                public PlantSetting() {
                }
            }

            public static class ZombieSetting {
                public ForgeConfigSpec.DoubleValue ZombieHurtAmount;

                public ZombieSetting() {
                }
            }
        }

        public static class WorldSettings {


            public WorldSettings() {
            }
        }

        public static class RuleSettings {

            public RuleSettings() {
            }
        }



    }
}