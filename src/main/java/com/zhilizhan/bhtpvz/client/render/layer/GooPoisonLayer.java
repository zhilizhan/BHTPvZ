package com.zhilizhan.bhtpvz.client.render.layer;

import com.hungteen.pvz.client.render.layer.fullskin.PVZFullSkinLayer;
import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.common.effect.BHTPvZMobEffects;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class GooPoisonLayer <T extends LivingEntity, M extends EntityModel<T>> extends PVZFullSkinLayer<T, M> {
    public GooPoisonLayer(RenderLayerParent<T, M> entityRendererIn) {
        super(entityRendererIn);
    }

    protected boolean canRender(T entity) {
        return !entity.isInvisible()&&isEntityPoison(entity) ;
    }
    public static boolean isEntityPoison(LivingEntity entity) {
        return entity.getAttribute(Attributes.MOVEMENT_SPEED).getModifier(BHTPvZMobEffects.POISON_EFFECT_UUID) != null;
    }
    protected ResourceLocation getResourceLocation(T entity) {
        return BHTPvZ.prefix("textures/entity/layer/goo_poison.png");
    }
}
