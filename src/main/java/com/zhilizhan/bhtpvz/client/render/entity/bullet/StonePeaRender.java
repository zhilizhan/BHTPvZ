package com.zhilizhan.bhtpvz.client.render.entity.bullet;

import com.hungteen.pvz.client.render.entity.EntityBlockRender;
import com.zhilizhan.bhtpvz.common.block.BHTPvZBlocks;
import com.zhilizhan.bhtpvz.common.entity.bullet.StonePeaEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StonePeaRender extends EntityBlockRender<StonePeaEntity> {
    public StonePeaRender(EntityRenderDispatcher renderManager) {
        super(renderManager);
    }

    public float getScaleByEntity(StonePeaEntity entity) {
        return 0.4F;
    }

    public BlockState getBlockByEntity(StonePeaEntity entity) {
        return entity.getPeaState()== StonePeaEntity.State.NORMAL? BHTPvZBlocks.PEA_BLOCK.get().defaultBlockState():entity.getPeaState()== StonePeaEntity.State.STONE?Blocks.COBBLESTONE.defaultBlockState():Blocks.MAGMA_BLOCK.defaultBlockState();
    }
}
