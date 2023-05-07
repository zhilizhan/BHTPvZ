package com.zhilizhan.bhtpvz.client.model.entity.plant.defence;

import com.hungteen.pvz.client.model.entity.ComponentModel;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.zhilizhan.bhtpvz.common.entity.plant.defence.SteelPumpkinEntity;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.geom.ModelPart;

public class SteelPumpkinModel extends PVZPlantModel<SteelPumpkinEntity> {
	private final ModelPart total;
	private final ModelPart inside;

	public SteelPumpkinModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelPart(this);
		total.setPos(0.0f, 24.0f, 0.0f);
		setRotationAngle(total, 0.0f, 1.5708f, 0.0f);
		total.texOffs(0, 0).addBox(-8.5f, -10.0f, -8.5f, 17.0f, 10.0f, 17.0f, 0.0f, false);
		total.texOffs(2, 27).addBox(8.0f, -6.0f, -8.5f, 1.0f, 6.0f, 17.0f, 0.0f, false);
		total.texOffs(0, 86).addBox(-8.75f, -26.0f, -8.0f, 1.0f, 26.0f, 16.0f, 0.0f, false);
		total.texOffs(34, 97).addBox(-8.75f, -15.0f, -8.0f, 1.0f, 15.0f, 16.0f, 0.1f, false);

		inside = new ModelPart(this);
		inside.setPos(0.0f, 0.0f, 0.0f);
		total.addChild(inside);
		inside.texOffs(0, 50).addBox(-8.0f, -9.5f, -8.0f, 16.0f, 9.0f, 16.0f, 0.0f, false);
	}

	@Override
	public void setupAnim(SteelPumpkinEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.inside.visible = entity.isSolid();
	}

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		total.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	@Override
	public ModelPart getPlantWholeBody() {
		return this.total;
	}
	public static class SteelPumpkinArmorModel<T extends PVZPlantEntity> extends ComponentModel<T> {
		private final ModelPart total;

		public SteelPumpkinArmorModel() {
			texWidth = 128;
			texHeight = 128;

			total = new ModelPart(this);
			total.setPos(0.0f, 24.0f, 0.0f);
			setRotationAngle(total, 0.0f, 1.5708f, 0.0f);
			total.texOffs(0, 0).addBox(-8.5f, -10.0f, -8.5f, 17.0f, 10.0f, 17.0f, 0.0f, false);
			total.texOffs(2, 27).addBox(8.0f, -6.0f, -8.5f, 1.0f, 6.0f, 17.0f, 0.0f, false);
			total.texOffs(0, 86).addBox(-8.75f, -26.0f, -8.0f, 1.0f, 26.0f, 16.0f, 0.0f, false);
			total.texOffs(34, 97).addBox(-8.75f, -15.0f, -8.0f, 1.0f, 15.0f, 16.0f, 0.1f, false);}

		public ModelPart getTotalModel() {
			return this.total;
		}
	}
}
