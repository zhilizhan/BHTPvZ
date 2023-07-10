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
            this.RuleSettings.CanSpawnDefaultMonster = builder.translation("config.bhtpvz.rule.spawn_monster").comment("if turn to false, there will have no monster of other monsters spawn in overworld except bhtpvz zombies.").define("CanSpawnDefaultMonster", true);
            this.RuleSettings.AllZombieGiveXP = builder.translation("config.bhtpvz.rule.all_zombie_give_xp").comment("if turn to false, only invasion zombies will give tree xp.").define("AllZombieGiveXP", false);
            this.RuleSettings.DespawnOwnedEntityRange = builder.translation("config.bhtpvz.rule.despawn_owned_entity_range").comment("plants summoned by players will be naturally despawned if the distance to player's spawn point is farther than this number.").defineInRange("DespawnOwnedEntityRange", 1000, -1, 100000);
            this.RuleSettings.GiveBeginnerReward = builder.translation("config.bhtpvz.rule.beginner_reward").comment("If you set it true, you will get some basic plantcards when you first join world.").define("GiveBeginnerReward", false);
            this.RuleSettings.AllowNaturalTurnOrigin = builder.translation("config.bhtpvz.rule.turn_origin").comment("If turn to false, saplings no longer to grow to Origin Ore naturally, except there is a block above it.").define("AllowNaturalTurnOrigin", true);
            this.RuleSettings.TeamAttack = builder.translation("config.bhtpvz.rule.team_attack").comment("if turn to true, when plant's owner is in a team, the plant will attack the entity from other team(include players).").define("PlantAttackTeam", false);
            this.RuleSettings.LimitPlantCount = builder.translation("config.bhtpvz.rule.plant_count").comment("how many plants can you place in 30 x 30 area without increasing cost.").defineInRange("LimitPlantCount", 50, 10, 1000);
            this.RuleSettings.NeedUnlockToPlant = builder.translation("config.bhtpvz.rule.unlock_plant").comment("if on, plant needs to be unlocked before planting.").define("NeedUnlockToPlant", true);
            this.RuleSettings.KeepSunWhenDie = builder.translation("config.bhtpvz.rule.keep_sun").comment("if turn to true, player will keep its sun after death.").define("KeepSunWhenDie", false);
            this.RuleSettings.MaxDamageLimit = builder.translation("config.bhtpvz.rule.max_damage").comment("how many damage could plants and zombies deal to other living entity.").defineInRange("MaxDamageLimit", 20, 0, 100000);
            builder.pop();
            builder.comment("Settings about world.").push("World Settings");
            builder.comment("Settings about the biome gen.").push("Biome Settings");
            this.WorldSettings.GenZenGardenChance = builder.translation("config.bhtpvz.world.zen_garden_chance").comment("the gen chance of Zen Garden biome(the larger the more chance to see it).").defineInRange("GenZenGardenChance", 20, 1, 10000);
            builder.pop();
            builder.comment("Settings about the structure gen.").push("Structure Settings");
            this.WorldSettings.DaveVillaDistance = builder.translation("config.bhtpvz.world.dave_villa_distance").comment("the distance value between dave villa.").defineInRange("DaveVillaDistance", 40, 1, 1000);
            this.WorldSettings.BucketHouseDistance = builder.translation("config.bhtpvz.world.bucket_house_distance").comment("the distance value between bucket house.").defineInRange("BucketHouseDistance", 36, 1, 1000);
            this.WorldSettings.DolphinHouseDistance = builder.translation("config.bhtpvz.world.dolphin_house_distance").comment("the distance value between dolphin house.").defineInRange("DolphinHouseDistance", 32, 1, 1000);
            this.WorldSettings.GraveHouseDistance = builder.translation("config.bhtpvz.world.grave_house_distance").comment("the distance value between grave house.").defineInRange("GraveHouseDistance", 28, 1, 1000);
            this.WorldSettings.SunTempleDistance = builder.translation("config.bhtpvz.world.sun_temple_distance").comment("the distance value between sun temple.").defineInRange("SunTempleDistance", 36, 1, 1000);
            this.WorldSettings.YetiHouseDistance = builder.translation("config.bhtpvz.world.yeti_house_distance").comment("the distance value between yeti house.").defineInRange("YetiHouseDistance", 28, 1, 1000);
            builder.pop();
            builder.comment("Settings about the ore gen.").push("Ore Settings");
            this.WorldSettings.GenOriginOreChance = builder.translation("config.bhtpvz.world.origin_ore_chance").comment("the gen chance of origin ore in overworld(the larger the more chance to see it).").defineInRange("GenOriginOreChance", 5, 1, 10000);
            this.WorldSettings.GenAmethystOreChance = builder.translation("config.bhtpvz.world.amethyst_ore_chance").comment("the gen chance of amethyst ore in the end(the larger the more chance to see it).").defineInRange("GenAmethystOreChance", 15, 1, 10000);
            this.WorldSettings.GenLunarStoneChance = builder.translation("config.bhtpvz.world.lunar_stone_chance").comment("the gen chance of lunar stone in overworld(the larger the more chance to see it).").defineInRange("GenLunarStoneChance", 30, 1, 10000);
            builder.pop();
            builder.comment("The Spawn Weight of entity.").push("EntitySpawnWeight");
            this.WorldSettings.LavaZombieSpawnWeight = builder.translation("config.bhtpvz.world.lava_zombie_weight").comment("spawn weight of LavaZombie at nether.").defineInRange("LavaZombieSpawnWeight", 10, 1, 200);
            this.WorldSettings.GigaTombStoneSpawnWeight = builder.translation("config.bhtpvz.world.giga_tomb_weight").comment("spawn weight of GigaTombStone at night in overworld.").defineInRange("GigaTombStoneSpawnWeight", 5, 1, 200);
            this.WorldSettings.YetiZombieSpawnWeight = builder.translation("config.bhtpvz.world.yeti_zombie_weight").comment("spawn weight of YetiZombie in overworld when thunder.").defineInRange("YetiZombieSpawnWeight", 2, 1, 200);
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
            this.BlockSettings.OriginEffectChance = builder.translation("config.bhtpvz.block.origin_effect_chance").comment("The chance to get Essence Ore from Origin Block's effect").defineInRange("OriginEffectChance", 0.25, 0.0, 1.0);
            this.BlockSettings.SaplingTurnChance = builder.translation("config.bhtpvz.block.sapling_turn_chance").comment("The chance when sapling turn to origin ore").defineInRange("SaplingTurnChance", 0.15, 0.0, 1.0);
            this.BlockSettings.AmethystAngerChance = builder.translation("config.bhtpvz.block.amethyst_anger_chance").comment("The chance to anger nearby endermen when break amethyst ore").defineInRange("AmethystAngerChance", 0.4, 0.0, 1.0);
            builder.comment("Setting about break blocks.").push("Break Block Setting");
            this.BlockSettings.PeaDropChance = builder.translation("config.bhtpvz.block.pea_drop_chance").comment("the drop chance of pea when you break grass").defineInRange("PeaDropChance", 0.05, 0.0, 1.0);
            this.BlockSettings.CabbageDropChance = builder.translation("config.bhtpvz.block.cabbage_drop_chance").comment("the drop chance of cabbage when you break grass").defineInRange("CabbageDropChance", 0.025, 0.0, 1.0);
            builder.pop();
            builder.pop();
            builder.comment("Settings about items.").push("Item Settings");
            this.ItemSettings.JackBoxSurpriseChance = builder.translation("config.bhtpvz.item.jack_surprise_chance").comment("The chance when player got a surprise when use jack box.the bigger the value is,the lower chance you get.(more specificly 1/x)").defineInRange("JackBoxSurpriseChance", 10, 1, 1000000);
            builder.pop();
        }

        public static class ItemSettings {
            public ForgeConfigSpec.IntValue JackBoxSurpriseChance;

            public ItemSettings() {
            }
        }

        public static class BlockSettings {
            public ForgeConfigSpec.DoubleValue OriginEffectChance;
            public ForgeConfigSpec.DoubleValue SaplingTurnChance;
            public ForgeConfigSpec.DoubleValue PeaDropChance;
            public ForgeConfigSpec.DoubleValue CabbageDropChance;
            public ForgeConfigSpec.DoubleValue AmethystAngerChance;

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
            public ForgeConfigSpec.IntValue GenZenGardenChance;
            public ForgeConfigSpec.IntValue DaveVillaDistance;
            public ForgeConfigSpec.IntValue BucketHouseDistance;
            public ForgeConfigSpec.IntValue DolphinHouseDistance;
            public ForgeConfigSpec.IntValue GraveHouseDistance;
            public ForgeConfigSpec.IntValue SunTempleDistance;
            public ForgeConfigSpec.IntValue YetiHouseDistance;
            public ForgeConfigSpec.IntValue GenAmethystOreChance;
            public ForgeConfigSpec.IntValue GenLunarStoneChance;
            public ForgeConfigSpec.IntValue GenOriginOreChance;

            public ForgeConfigSpec.IntValue LavaZombieSpawnWeight;
            public ForgeConfigSpec.IntValue GigaTombStoneSpawnWeight;
            public ForgeConfigSpec.IntValue YetiZombieSpawnWeight;

            public WorldSettings() {
            }
        }

        public static class RuleSettings {
            public ForgeConfigSpec.BooleanValue CanSpawnDefaultMonster;
            public ForgeConfigSpec.BooleanValue AllZombieGiveXP;
            public ForgeConfigSpec.IntValue DespawnOwnedEntityRange;
            public ForgeConfigSpec.BooleanValue GiveBeginnerReward;
            public ForgeConfigSpec.BooleanValue AllowNaturalTurnOrigin;
            public ForgeConfigSpec.BooleanValue TeamAttack;
            public ForgeConfigSpec.IntValue LimitPlantCount;
            public ForgeConfigSpec.BooleanValue NeedUnlockToPlant;
            public ForgeConfigSpec.BooleanValue KeepSunWhenDie;
            public ForgeConfigSpec.IntValue MaxDamageLimit;

            public RuleSettings() {
            }
        }



    }
}