package com.zhilizhan.bhtpvz.common.mixin;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingRenderer.class)
public abstract class LivingEntityRenderMixin<T extends LivingEntity, M extends EntityModel<T>> extends EntityRenderer<T> implements IEntityRenderer<T, M> {
    protected LivingEntityRenderMixin(EntityRendererManager manager) {
        super(manager);
    }

    @Inject(method = "getFlipDegrees", at = @At("HEAD"), cancellable = true)
    protected void getFlipDegrees(T livingEntity, CallbackInfoReturnable<Float> cir) {
        boolean flag = livingEntity instanceof PVZPlantEntity && ((PVZPlantEntity)livingEntity).isNoAi();
        cir.setReturnValue(flag ? 0.0F : 90.0F);
        cir.cancel();
    }
}
