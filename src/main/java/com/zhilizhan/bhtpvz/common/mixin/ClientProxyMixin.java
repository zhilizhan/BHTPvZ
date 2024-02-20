package com.zhilizhan.bhtpvz.common.mixin;

import com.hungteen.pvz.client.ClientProxy;
import com.hungteen.pvz.client.render.layer.fullskin.ColdLayer;
import com.zhilizhan.bhtpvz.client.render.layer.GooPoisonLayer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.hungteen.pvz.client.ClientProxy.MC;

@Mixin(value = ClientProxy.class,remap = false)
public class ClientProxyMixin {
    @Inject(method = "postInit", at = @At("TAIL"))
    private void postInit(CallbackInfo ci) {
     this.addLayersForRender();
    }

    @Unique
    private void addLayersForRender() {
        MC.getEntityRenderDispatcher().renderers.values().forEach((r) -> {
            if (r instanceof LivingEntityRenderer) {
                ((LivingEntityRenderer)r).addLayer(new GooPoisonLayer((LivingEntityRenderer)r));
                ((LivingEntityRenderer)r).addLayer(new ColdLayer((LivingEntityRenderer)r));
            }
        });
    }
}
