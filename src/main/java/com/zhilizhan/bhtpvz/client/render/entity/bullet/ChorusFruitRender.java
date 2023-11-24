package com.zhilizhan.bhtpvz.client.render.entity.bullet;

import com.hungteen.pvz.client.render.entity.EntityBlockRender;
import com.zhilizhan.bhtpvz.common.entity.bullet.ChorusFruitEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChorusFruitRender extends EntityBlockRender<ChorusFruitEntity> {
    public ChorusFruitRender(EntityRenderDispatcher renderManager) {
        super(renderManager);
    }

    public float getScaleByEntity(ChorusFruitEntity entity) {
        return 0.75F;
    }

    public BlockState getBlockByEntity(ChorusFruitEntity entity) {
        return Blocks.CHORUS_PLANT.defaultBlockState();
    }
}