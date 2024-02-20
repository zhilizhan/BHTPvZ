package com.zhilizhan.bhtpvz.client.render.entity.bullet;

import com.hungteen.pvz.client.render.entity.EntityBlockRender;
import com.zhilizhan.bhtpvz.common.entity.bullet.LightBeamEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LightBeamRender extends EntityBlockRender<LightBeamEntity> {
    public LightBeamRender(EntityRenderDispatcher renderManager) {
        super(renderManager);
    }

    public float getScaleByEntity(LightBeamEntity entity) {
        return 0.2F;
    }

    public BlockState getBlockByEntity(LightBeamEntity entity) {
        return Blocks.GRASS_BLOCK.defaultBlockState();
    }
}
