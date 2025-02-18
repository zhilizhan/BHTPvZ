package com.zhilizhan.bhtpvz.client.render.entity.bullet;

import com.hungteen.pvz.client.render.entity.EntityBlockRender;
import com.zhilizhan.bhtpvz.common.block.BHTPvZBlocks;
import com.zhilizhan.bhtpvz.common.entity.bullet.itembullet.StonePeaEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StonePeaRender extends EntityBlockRender<StonePeaEntity> {
    public StonePeaRender(EntityRendererManager renderManager) {
        super(renderManager);
    }

    public float getScaleByEntity(StonePeaEntity entity) {
        return 0.4F;
    }

    public BlockState getBlockByEntity(StonePeaEntity entity) {
        return entity.getPeaState()== StonePeaEntity.State.NORMAL? BHTPvZBlocks.PEA_BLOCK.get().defaultBlockState():entity.getPeaState()== StonePeaEntity.State.STONE?Blocks.COBBLESTONE.defaultBlockState(): Blocks.MAGMA_BLOCK.defaultBlockState();
    }
}
