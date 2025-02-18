package com.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz;

import com.hungteen.pvz.api.interfaces.IBodyEntity;
import com.hungteen.pvz.client.model.entity.zombie.PVZZombieModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.zhilizhan.bhtpvz.common.entity.zombie.AirborneZombieEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class AirborneZombieModel extends PVZZombieModel<AirborneZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer zombie;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	private final ModelRenderer up;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;

	public AirborneZombieModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0f, -12.0f, 0.0f);
		setRotationAngle(total, 0.7854f, 0.0f, 0.0f);

		zombie = new ModelRenderer(this);
		zombie.setPos(0.0f, 0.0f, 0.0f);
		total.addChild(zombie);

		left_leg = new ModelRenderer(this);
		left_leg.setPos(4.0f, 13.0f, 0.0f);
		zombie.addChild(left_leg);
		setRotationAngle(left_leg, -0.7854f, 0.0f, 0.0f);
		left_leg.texOffs(44, 0).addBox(-5.0f, -1.0f, -3.0f, 8.0f, 24.0f, 8.0f, 0.0f, false);

		right_leg = new ModelRenderer(this);
		right_leg.setPos(-4.0f, 13.0f, 0.0f);
		zombie.addChild(right_leg);
		setRotationAngle(right_leg, -0.7854f, 0.0f, 0.0f);
		right_leg.texOffs(0, 0).addBox(-5.0f, -1.0f, -3.0f, 8.0f, 24.0f, 8.0f, 0.0f, false);

		up = new ModelRenderer(this);
		up.setPos(0.0f, 12.0f, 0.0f);
		zombie.addChild(up);
		setRotationAngle(up, 0.5236f, 0.0f, 0.0f);
		up.texOffs(0, 41).addBox(-8.0f, -24.0f, -4.0f, 16.0f, 24.0f, 8.0f, 0.0f, false);

		left_hand = new ModelRenderer(this);
		left_hand.setPos(12.0f, -20.0f, 0.0f);
		up.addChild(left_hand);
		setRotationAngle(left_hand, -1.5708f, -0.2182f, 0.0f);
		left_hand.texOffs(96, 60).addBox(-4.0f, -4.0f, -4.0f, 8.0f, 24.0f, 8.0f, 0.0f, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-12.0f, -20.0f, 0.0f);
		up.addChild(right_hand);
		setRotationAngle(right_hand, -1.6144f, 0.2182f, 0.0f);
		right_hand.texOffs(96, 0).addBox(-4.0f, -4.0f, -4.0f, 8.0f, 24.0f, 8.0f, 0.0f, false);

		head = new ModelRenderer(this);
		head.setPos(0.0f, -24.0f, 0.0f);
		up.addChild(head);
		setRotationAngle(head, -1.0908f, 0.0f, 0.0f);
		head.texOffs(16, 96).addBox(-8.0f, -16.0f, -8.0f, 16.0f, 16.0f, 16.0f, 0.0f, false);
	}

	@Override
	public void setupAnim(AirborneZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		final boolean hasLine = entity.hasLine();

		if(hasLine) {
			this.zombie.xRot = 1.0472f;
			this.left_leg.xRot = - 0.7854f;
			this.right_leg.xRot = - 0.7854f;
			this.up.xRot = 0.2618f;
			this.left_hand.xRot = -1.7453f;
			this.right_hand.xRot = -1.7453f;
			this.head.xRot = -1.0472f;
			this.head.yRot = 0.0f;
			this.updateFreeParts(entity);
		} else {
			this.up.xRot = 0.0f;
			this.zombie.xRot = -0.75f;

			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
		this.updateFreeParts(entity);
	}

	@Override
	public void updateFreeParts(AirborneZombieEntity entity) {
		super.updateFreeParts(entity);
		final boolean hasLine = entity.hasLine();

		this.isLeftHandFree = !hasLine;
	    this.isRightHandFree = !hasLine;
	}

	@Override
	public void tickPartAnim(IBodyEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.left_leg.xRot = 0.0f;
		this.right_leg.xRot = 0.0f;
		this.up.xRot = 0.0f;
		this.head.xRot = 0.0f;
		this.left_hand.xRot = 0.0f;
		this.right_hand.xRot = 0.0f;
		this.zombie.xRot = 0.0f;

		super.tickPartAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	}

	@Override
	public void renderBody(IBodyEntity entity, MatrixStack stack, IVertexBuilder buffer, int packedLight, int packedOverlay) {
		super.renderBody(entity, stack, buffer, packedLight, packedOverlay);
	}

	@Override
	public ModelRenderer getZombieLeftHand() {
		return this.left_hand;
	}

	@Override
	public ModelRenderer getZombieRightHand() {
		return this.right_hand;
	}

	@Override
	public ModelRenderer getZombieLeftLeg() {
		return this.left_leg;
	}

	@Override
	public ModelRenderer getZombieRightLeg() {
		return this.right_leg;
	}

	@Override
	public ModelRenderer getZombieHead() {
		return this.head;
	}

	@Override
	public ModelRenderer getZombieUpBody() {
		return this.up;
	}

	@Override
	public ModelRenderer getZombieWholeBody() {
		return this.total;
	}
}
