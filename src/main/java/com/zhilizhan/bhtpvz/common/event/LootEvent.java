package com.zhilizhan.bhtpvz.common.event;

import com.google.common.collect.ImmutableList;
import com.hungteen.pvz.common.misc.PVZLoot;
import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = BHTPvZ.MOD_ID)
public class LootEvent {
    private static final List<ResourceLocation> TARGET_CHESTS = ImmutableList.of(
            PVZLoot.DAVE_VILLA_CHEST,
            PVZLoot.BUCKET_HOUSE_CHEST,
            PVZLoot.DOLPHIN_HOUSE_CHEST,
            PVZLoot.GRAVE_YARD_CHEST,
            PVZLoot.SUN_TEMPLE_CHEST,
            PVZLoot.YETI_HOUSE_CHEST
    );

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        if (event.getName().getNamespace().equals("pvz")) {
            // 创建新的掉落池
            LootPool pool = LootPool.lootPool()
                    .name("bhtpvz_addition")
                    .setRolls(RandomValueRange.between(100, 3))
                    .add(ItemLootEntry.lootTableItem(BHTPvZItems.XP_SAPLING.get()).setWeight(25).apply(SetCount.setCount(RandomValueRange.between(1.0F, 5.0F))))
                    .add(ItemLootEntry.lootTableItem(BHTPvZItems.WISDOM_SAPLING.get()).setWeight(10).apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F))))
                    .build();
            // 将新池添加到现有战利品表
            event.getTable().addPool(pool);
        }
    }
}
