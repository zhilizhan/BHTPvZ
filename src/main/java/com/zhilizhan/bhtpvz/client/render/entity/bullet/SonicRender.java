package com.zhilizhan.bhtpvz.client.render.entity.bullet;

import com.hungteen.pvz.client.render.entity.EntityBlockRender;
import com.zhilizhan.bhtpvz.common.entity.bullet.SonicEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SonicRender  extends EntityBlockRender<SonicEntity> {
    public SonicRender(EntityRenderDispatcher renderManager) {
        super(renderManager);
    }

    public float getScaleByEntity(SonicEntity entity) {
        return 0.2F;
    }

    public BlockState getBlockByEntity(SonicEntity entity) {
        return Blocks.NOTE_BLOCK.defaultBlockState();
    }
}
