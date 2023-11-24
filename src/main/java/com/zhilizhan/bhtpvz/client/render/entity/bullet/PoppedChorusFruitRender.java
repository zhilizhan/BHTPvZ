package com.zhilizhan.bhtpvz.client.render.entity.bullet;

import com.hungteen.pvz.client.render.entity.EntityBlockRender;
import com.zhilizhan.bhtpvz.common.entity.bullet.PoppedChorusFruitEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PoppedChorusFruitRender extends EntityBlockRender<PoppedChorusFruitEntity> {
    public PoppedChorusFruitRender(EntityRenderDispatcher renderManager) {
        super(renderManager);
    }

    public float getScaleByEntity(PoppedChorusFruitEntity entity) {
        return 0.5F;
    }

    public BlockState getBlockByEntity(PoppedChorusFruitEntity entity) {
        return Blocks.CHORUS_FLOWER.defaultBlockState();
    }
}