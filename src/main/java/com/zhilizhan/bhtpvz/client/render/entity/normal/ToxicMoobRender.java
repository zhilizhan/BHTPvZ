package com.zhilizhan.bhtpvz.client.render.entity.normal;


import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.client.render.layer.ToxicMushroomLayer;
import com.zhilizhan.bhtpvz.common.entity.normal.ToxicMoobEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ToxicMoobRender extends MobRenderer<ToxicMoobEntity, CowModel<ToxicMoobEntity>> {
    private static final ResourceLocation TOXIC_MOOB = BHTPvZ.prefix("textures/entity/normal/toxic_moob.png");


    public ToxicMoobRender(EntityRendererManager arg) {
        super(arg, new CowModel<>(), 0.7F);
        this.addLayer(new ToxicMushroomLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(ToxicMoobEntity entity) {
        return TOXIC_MOOB;
    }
}
