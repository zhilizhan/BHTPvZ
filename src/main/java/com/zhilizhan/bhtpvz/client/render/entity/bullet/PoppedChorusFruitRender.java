package com.zhilizhan.bhtpvz.client.render.entity.bullet;

import com.hungteen.pvz.client.render.entity.EntityBlockRender;
import com.zhilizhan.bhtpvz.common.entity.bullet.PoppedChorusFruitEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PoppedChorusFruitRender extends EntityBlockRender<PoppedChorusFruitEntity> {
    public PoppedChorusFruitRender(EntityRendererManager renderManager) {
        super(renderManager);
    }

    public float getScaleByEntity(PoppedChorusFruitEntity entity) {
        return 0.5F;
    }

    public BlockState getBlockByEntity(PoppedChorusFruitEntity entity) {
        return Blocks.CHORUS_FLOWER.defaultBlockState();
    }
}