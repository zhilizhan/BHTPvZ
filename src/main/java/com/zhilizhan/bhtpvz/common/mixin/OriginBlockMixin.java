package com.zhilizhan.bhtpvz.common.mixin;

import com.hungteen.pvz.api.types.IEssenceType;
import com.hungteen.pvz.common.block.cubes.OriginBlock;
import com.hungteen.pvz.common.impl.EssenceTypes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(value = OriginBlock.class,remap = false)
public class OriginBlockMixin {
    @Shadow @Final private static Map<Block, IEssenceType> BLOCK_TO_ESSENCE;

    @Inject(
            method = "updateRadiationMap",
            at = @At("TAIL")
    )
    private static void injectElectric(CallbackInfo ci) {
        BLOCK_TO_ESSENCE.put(Blocks.IRON_BLOCK, EssenceTypes.ELECTRIC);
    }
}
