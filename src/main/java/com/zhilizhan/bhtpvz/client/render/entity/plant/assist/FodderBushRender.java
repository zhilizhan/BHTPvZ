package com.zhilizhan.bhtpvz.client.render.entity.plant.assist;// Made with Blockbench 4.8.1
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.zhilizhan.bhtpvz.client.model.entity.plant.assist.FodderBushModel;
import com.zhilizhan.bhtpvz.common.entity.plant.assist.FodderBushEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FodderBushRender extends PVZPlantRender<FodderBushEntity> {
	public FodderBushRender(EntityRendererManager rendererManager) {
		super(rendererManager, new FodderBushModel(), 0.5F);
	}
}