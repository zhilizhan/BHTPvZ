package com.zhilizhan.bhtpvz.client.render.entity.bullet;

import com.hungteen.pvz.client.render.entity.EntityBlockRender;
import com.zhilizhan.bhtpvz.common.entity.bullet.ChorusFruitEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChorusFruitRender extends EntityBlockRender<ChorusFruitEntity> {
    public ChorusFruitRender(EntityRendererManager renderManager) {
        super(renderManager);
    }

    public float getScaleByEntity(ChorusFruitEntity entity) {
        return 0.625F;
    }

    public BlockState getBlockByEntity(ChorusFruitEntity entity) {
        return Blocks.CHORUS_PLANT.defaultBlockState();
    }
}