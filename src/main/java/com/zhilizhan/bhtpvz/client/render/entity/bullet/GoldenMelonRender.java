package com.zhilizhan.bhtpvz.client.render.entity.bullet;

import com.hungteen.pvz.client.render.entity.EntityBlockRender;
import com.zhilizhan.bhtpvz.common.entity.bullet.GoldenMelonEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GoldenMelonRender  extends EntityBlockRender<GoldenMelonEntity> {
    public GoldenMelonRender(EntityRendererManager renderManager) {
        super(renderManager);
    }

    public float getScaleByEntity(GoldenMelonEntity entity) {
        return 0.8F;
    }

    public BlockState getBlockByEntity(GoldenMelonEntity entity) {
        return Blocks.GOLD_BLOCK.defaultBlockState();
    }
}
