package com.zhilizhan.bhtpvz.client.model.entity.plant.assist;// Made with Blockbench 4.8.1
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.zhilizhan.bhtpvz.common.entity.plant.assist.FodderBushEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class FodderBushModel extends PVZPlantModel<FodderBushEntity> {
	private final ModelRenderer total;
	private final ModelRenderer c1;
	private final ModelRenderer c2;
	private final ModelRenderer f1;
	private final ModelRenderer n_r1;
	private final ModelRenderer s_r1;
	private final ModelRenderer f2;
	private final ModelRenderer n_r2;
	private final ModelRenderer s_r2;
	private final ModelRenderer f3;
	private final ModelRenderer n_r3;
	private final ModelRenderer s_r3;
	private final ModelRenderer f4;
	private final ModelRenderer n_r4;
	private final ModelRenderer s_r4;
	private final ModelRenderer getPlantWholeBody;

	public FodderBushModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		total.texOffs(94, 0).addBox(-3.5F, -6.0F, -4.0F, 8.0F, 6.0F, 8.0F, 0.0F, false);

		c1 = new ModelRenderer(this);
		c1.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(c1);
		c1.texOffs(0, 0).addBox(-4.5F, -16.0F, -5.0F, 10.0F, 16.0F, 10.0F, 0.0F, false);

		c2 = new ModelRenderer(this);
		c2.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(c2);
		c2.texOffs(0, 29).addBox(-4.5F, -15.0F, -5.0F, 10.0F, 16.0F, 10.0F, 0.0F, false);

		f1 = new ModelRenderer(this);
		f1.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(f1);
		

		n_r1 = new ModelRenderer(this);
		n_r1.setPos(0.0F, -11.0F, 0.0F);
		f1.addChild(n_r1);
		setRotationAngle(n_r1, -0.4363F, 0.0F, 0.0F);
		n_r1.texOffs(48, 0).addBox(-7.0F, -2.0F, 0.0F, 15.0F, 13.0F, 0.0F, 0.0F, false);

		s_r1 = new ModelRenderer(this);
		s_r1.setPos(0.0F, -11.0F, 0.0F);
		f1.addChild(s_r1);
		setRotationAngle(s_r1, 0.4363F, 0.0F, 0.0F);
		s_r1.texOffs(48, 44).addBox(-7.0F, -6.0F, 0.0F, 15.0F, 17.0F, 0.0F, 0.0F, false);

		f2 = new ModelRenderer(this);
		f2.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(f2);
		

		n_r2 = new ModelRenderer(this);
		n_r2.setPos(0.0F, -11.0F, 0.0F);
		f2.addChild(n_r2);
		setRotationAngle(n_r2, -0.4363F, 0.0F, 0.0F);
		n_r2.texOffs(48, 22).addBox(-7.0F, -5.0F, 0.0F, 15.0F, 16.0F, 0.0F, 0.0F, false);

		s_r2 = new ModelRenderer(this);
		s_r2.setPos(0.0F, -11.0F, 0.0F);
		f2.addChild(s_r2);
		setRotationAngle(s_r2, 0.4363F, 0.0F, 0.0F);
		s_r2.texOffs(48, 66).addBox(-7.0F, -5.0F, 0.0F, 15.0F, 16.0F, 0.0F, 0.0F, false);

		f3 = new ModelRenderer(this);
		f3.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(f3);
		

		n_r3 = new ModelRenderer(this);
		n_r3.setPos(0.0F, -11.0F, 0.0F);
		f3.addChild(n_r3);
		setRotationAngle(n_r3, -0.4363F, 0.0F, 0.0F);
		n_r3.texOffs(48, 44).addBox(-7.0F, -6.0F, 0.0F, 15.0F, 17.0F, 0.0F, 0.0F, false);

		s_r3 = new ModelRenderer(this);
		s_r3.setPos(0.0F, -11.0F, 0.0F);
		f3.addChild(s_r3);
		setRotationAngle(s_r3, 0.4363F, 0.0F, 0.0F);
		s_r3.texOffs(48, 0).addBox(-7.0F, -6.0F, 0.0F, 15.0F, 17.0F, 0.0F, 0.0F, false);

		f4 = new ModelRenderer(this);
		f4.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(f4);
		

		n_r4 = new ModelRenderer(this);
		n_r4.setPos(0.0F, -11.0F, 0.0F);
		f4.addChild(n_r4);
		setRotationAngle(n_r4, -0.4363F, 0.0F, 0.0F);
		n_r4.texOffs(48, 66).addBox(-7.0F, -6.0F, 0.0F, 15.0F, 17.0F, 0.0F, 0.0F, false);

		s_r4 = new ModelRenderer(this);
		s_r4.setPos(0.0F, -11.0F, 0.0F);
		f4.addChild(s_r4);
		setRotationAngle(s_r4, 0.4363F, 0.0F, 0.0F);
		s_r4.texOffs(48, 22).addBox(-7.0F, -9.0F, 0.0F, 15.0F, 20.0F, 0.0F, 0.0F, false);

		getPlantWholeBody = new ModelRenderer(this);
		getPlantWholeBody.setPos(0.0F, 0.0F, 0.0F);
		
	}

	public void setupAnim(FodderBushEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	public EntityModel<FodderBushEntity> getPlantModel() {
		return this;
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}