package com.zhilizhan.bhtpvz.client.render.entity.bullet;

import com.hungteen.pvz.client.render.entity.EntityBlockRender;
import com.zhilizhan.bhtpvz.common.entity.bullet.GoldenMelonEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GoldenMelonRender  extends EntityBlockRender<GoldenMelonEntity> {
    public GoldenMelonRender(EntityRenderDispatcher renderManager) {
        super(renderManager);
    }

    public float getScaleByEntity(GoldenMelonEntity entity) {
        return 0.8F;
    }

    public BlockState getBlockByEntity(GoldenMelonEntity entity) {
        return Blocks.GOLD_BLOCK.defaultBlockState();
    }
}
