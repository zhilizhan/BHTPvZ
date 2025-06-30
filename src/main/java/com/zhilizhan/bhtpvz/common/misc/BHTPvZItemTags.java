package com.zhilizhan.bhtpvz.common.misc;

import com.hungteen.pvz.utils.StringUtil;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class BHTPvZItemTags {
    public static final ITag.INamedTag<Item> STARFRUIT = forgeTag("crops/starfruit");
    public static final ITag.INamedTag<Item> CHERRY = forgeTag("crops/cherry");
    public static final ITag.INamedTag<Item> GARLIC = forgeTag("crops/garlic");
    public static final ITag.INamedTag<Item> CHILI_SEEDS = forgeTag("seeds/chilipepper");

    private static ITag.INamedTag<Item> pvzTag(String name) {
        return ItemTags.createOptional(StringUtil.prefix(name));
    }

    private static ITag.INamedTag<Item> forgeTag(String name) {
        return ItemTags.createOptional(new ResourceLocation("forge", name));
    }

    private static ITag.INamedTag<Item> mcTag(String name) {
        return ItemTags.createOptional(new ResourceLocation(name));
    }
}
