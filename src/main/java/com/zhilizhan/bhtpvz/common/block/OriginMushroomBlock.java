package com.zhilizhan.bhtpvz.common.block;

import net.minecraft.block.MushroomBlock;

public class OriginMushroomBlock extends MushroomBlock {
    public OriginMushroomBlock(Properties arg) {
        super(arg);
    }
    /*
    public boolean growMushroom(ServerWorld level, BlockPos pos, BlockState state, Random random) {
        level.removeBlock(pos, false);
        ConfiguredFeature<?, ?> configuredfeature = BHTPvZFeatures.ORGIGIN_MUSHROOM;

        if (configuredfeature.place(level, level.getChunkSource().getGenerator(), random, pos)) {
            return true;
        } else {
            level.setBlock(pos, state, 3);
            return false;
        }
    }
     */
}
