package com.zhilizhan.bhtpvz.client.render.layer.eyelayer;

import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.common.entity.plant.fire.BlazeWartEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WartEyeLayer<T extends LivingEntity, M extends EntityModel<T>> extends PVZEyeLayer<T, M> {
    public WartEyeLayer(RenderLayerParent<T, M> entityRendererIn) {
        super(entityRendererIn);
    }

    protected boolean canRender(T entity) {
        if(entity instanceof BlazeWartEntity){
        return  !entity.isInvisible() && ((BlazeWartEntity)entity).hasNetherWart();
        }
        return false;
    }

    protected ResourceLocation getResourceLocation(T entity) {
        return BHTPvZ.prefix("textures/entity/layer/eyes/wart_eyes.png");
    }
}
