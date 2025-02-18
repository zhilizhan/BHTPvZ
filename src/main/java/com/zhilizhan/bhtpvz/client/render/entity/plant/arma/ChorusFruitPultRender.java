package com.zhilizhan.bhtpvz.client.render.entity.plant.arma;

import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.zhilizhan.bhtpvz.client.model.entity.plant.arma.ChorusFruitPultModel;
import com.zhilizhan.bhtpvz.client.render.layer.eyelayer.ChorusFruitPultEyeLayer;
import com.zhilizhan.bhtpvz.client.render.layer.eyelayer.WartEyeLayer;
import com.zhilizhan.bhtpvz.common.entity.plant.arma.ChorusFruitPultEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChorusFruitPultRender extends PVZPlantRender<ChorusFruitPultEntity> {
    public ChorusFruitPultRender(EntityRendererManager rendererManager) {
        super(rendererManager, new ChorusFruitPultModel(), 0.4f);
    }
    protected void addPlantLayers() {
        super.addPlantLayers();
        this.addLayer(new ChorusFruitPultEyeLayer<>(this));
        this.addLayer(new WartEyeLayer<>(this));
    }
}