package com.zhilizhan.bhtpvz.common.impl.plant;

import com.hungteen.pvz.api.types.ICardPlacement;
import com.hungteen.pvz.common.misc.tag.PVZBlockTags;
import net.minecraft.block.Blocks;

public class BHTPvZPlacements  {
    public BHTPvZPlacements() {
    }
    public static final ICardPlacement SOUL_SAND = (block) -> {
        return block.is(PVZBlockTags.PLANT_SUIT_BLOCKS) || block.is(Blocks.SOUL_SAND);
    };
    public static final ICardPlacement END_STONE = (block) -> {
        return block.is(PVZBlockTags.PLANT_SUIT_BLOCKS) || block.is(Blocks.END_STONE);
    };
}
