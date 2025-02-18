package com.zhilizhan.bhtpvz.common.mixin.optional;

import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.client.render.entity.plant.toxic.PuffShroomRender;
import com.hungteen.pvz.common.entity.plant.toxic.PuffShroomEntity;
import com.hungteen.pvz.utils.AnimationUtil;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
@OnlyIn(Dist.CLIENT)
@Mixin(value = PuffShroomRender.class,remap = false)
public abstract class PuffShroomRenderMixin extends PVZPlantRender<PuffShroomEntity> {
    public PuffShroomRenderMixin(EntityRendererManager rendererManager, EntityModel<PuffShroomEntity> entityModelIn, float shadowSizeIn) {
        super(rendererManager, entityModelIn, shadowSizeIn);
    }
    /**
     * This method calculates the scale factor for the PuffShroomEntity based on its properties and state.
     * @param entity The PuffShroomEntity for which the scale factor is calculated
     * @return The calculated scale factor for the entity
     * @author SuSen36
     * @reason none
     */
    @Overwrite
    public float getScaleByEntity(PuffShroomEntity entity) {
        float size = super.getScaleByEntity(entity)-0.3f+entity.getAttackDamage()*0.1f;

        int tick;
        if (entity.isPlantInSuperMode()) {
            tick = entity.getSuperTime() % 10;
            return size + AnimationUtil.upDown((float)tick, 10.0F, 0.05F);
        } else {
            tick = entity.getShootTick() + 10 - entity.getShootCD();
            return tick >= 0 ? size + AnimationUtil.upDown((float)tick, 10.0F, 0.05F) : size;
        }
    }
}
