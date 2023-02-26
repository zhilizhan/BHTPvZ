package com.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz;

import com.zhilizhan.bhtpvz.common.entity.zombie.bhtpvz.Edgar090547Entity;

import com.hungteen.pvz.api.interfaces.IBodyEntity;
import com.hungteen.pvz.api.paz.IZombieModel;
import com.hungteen.pvz.utils.AnimationUtil;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class Edgar090547Model<T extends Edgar090547Entity> extends EntityModel<T> implements IZombieModel<T>{
	private final ModelPart total;
	private final ModelPart left_leg;
	private final ModelPart right_leg;
	private final ModelPart up;
	private final ModelPart body;
	private final ModelPart pipe1;
	private final ModelPart pipe2;
	private final ModelPart head;
	private final ModelPart mouse;
	private final ModelPart bone3;
	private final ModelPart angry;
	private final ModelPart left_arm;
	private final ModelPart pole;
	private final ModelPart right_arm;
	private final ModelPart back;
	private final ModelPart imp;
	private final ModelPart left_leg3;
	private final ModelPart right_leg3;
	private final ModelPart up3;
	private final ModelPart body3;
	private final ModelPart right_arm2;
	private final ModelPart cube_r1;
	private final ModelPart left_arm2;
	private final ModelPart cube_r2;
	private final ModelPart head3;
	private final ModelPart bone;

	public Edgar090547Model() {
		texWidth = 256;
		texHeight = 256;

		total = new ModelPart(this);
		total.setPos(0.0f, 24.0f, 0.0f);

		left_leg = new ModelPart(this);
		left_leg.setPos(6.0f, -41.0f, 0.0f);
		total.addChild(left_leg);
		left_leg.texOffs(155, 211).addBox(-5.0f, 0.0f, -3.0f, 10.0f, 33.0f, 10.0f, 0.0f, false);
		left_leg.texOffs(198, 230).addBox(-6.0f, 33.0f, -8.0f, 12.0f, 8.0f, 16.0f, 0.0f, false);

		right_leg = new ModelPart(this);
		right_leg.setPos(-7.0f, -41.0f, 0.0f);
		total.addChild(right_leg);
		right_leg.texOffs(154, 164).addBox(-4.0f, 0.0f, -3.0f, 10.0f, 33.0f, 10.0f, 0.0f, false);
		right_leg.texOffs(198, 187).addBox(-5.0f, 33.0f, -8.0f, 12.0f, 8.0f, 16.0f, 0.0f, false);

		up = new ModelPart(this);
		up.setPos(0.0f, -41.0f, 0.0f);
		total.addChild(up);

		body = new ModelPart(this);
		body.setPos(0.0f, 0.0f, 0.0f);
		up.addChild(body);
		body.texOffs(186, 2).addBox(-11.0f, -40.0f, -4.0f, 22.0f, 40.0f, 12.0f, 0.0f, false);

		pipe1 = new ModelPart(this);
		pipe1.setPos(9.0f, -39.0f, 6.0f);
		body.addChild(pipe1);
		setRotationAngle(pipe1, -0.6109f, 0.0f, 0.0f);
		pipe1.texOffs(200, 170).addBox(1.0f, -2.0f, 0.0f, 2.0f, 2.0f, 10.0f, 0.0f, false);
		pipe1.texOffs(245, 166).addBox(1.0f, -2.0f, 10.0f, 2.0f, 16.0f, 2.0f, 0.0f, false);

		pipe2 = new ModelPart(this);
		pipe2.setPos(9.0f, -39.0f, 6.0f);
		body.addChild(pipe2);
		setRotationAngle(pipe2, -0.6109f, 0.0f, 0.0f);
		pipe2.texOffs(226, 150).addBox(-21.0f, -2.0f, 0.0f, 2.0f, 2.0f, 10.0f, 0.0f, false);
		pipe2.texOffs(210, 146).addBox(-21.0f, -2.0f, 10.0f, 2.0f, 16.0f, 2.0f, 0.0f, false);

		head = new ModelPart(this);
		head.setPos(0.0f, -40.0f, -3.0f);
		up.addChild(head);
		head.texOffs(188, 58).addBox(-8.0f, -9.0f, -16.0f, 16.0f, 13.0f, 16.0f, 0.0f, false);
		head.texOffs(195, 118).addBox(2.0f, -13.0f, -13.0f, 2.0f, 4.0f, 2.0f, 0.0f, false);
		head.texOffs(195, 134).addBox(-4.0f, -13.0f, -13.0f, 2.0f, 4.0f, 2.0f, 0.0f, false);
		head.texOffs(172, 97).addBox(8.0f, -4.0f, -9.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
		head.texOffs(171, 77).addBox(9.0f, -5.0f, -10.0f, 2.0f, 3.0f, 3.0f, 0.0f, false);
		head.texOffs(176, 103).addBox(-9.0f, -4.0f, -9.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
		head.texOffs(154, 98).addBox(-11.0f, -5.0f, -10.0f, 2.0f, 3.0f, 3.0f, 0.0f, false);

		mouse = new ModelPart(this);
		mouse.setPos(0.0f, 6.0f, -1.0f);
		head.addChild(mouse);
		mouse.texOffs(186, 91).addBox(-8.0f, 0.0f, -15.0f, 16.0f, 3.0f, 16.0f, 0.0f, false);
		mouse.texOffs(134, 6).addBox(-8.0f, -2.0f, -3.0f, 16.0f, 2.0f, 4.0f, 0.0f, false);
		mouse.texOffs(0, 0).addBox(-1.0f, -1.0f, -15.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
		mouse.texOffs(0, 0).addBox(1.0f, -2.0f, -15.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
		mouse.texOffs(0, 0).addBox(4.0f, -2.0f, -15.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
		mouse.texOffs(0, 0).addBox(-2.0f, -2.0f, -15.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
		mouse.texOffs(0, 0).addBox(-5.0f, -2.0f, -15.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
		mouse.texOffs(0, 0).addBox(-7.0f, -2.0f, -15.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
		mouse.texOffs(0, 0).addBox(-8.0f, -2.0f, -13.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
		mouse.texOffs(0, 0).addBox(-8.0f, -2.0f, -9.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
		mouse.texOffs(0, 0).addBox(-8.0f, -2.0f, -6.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
		mouse.texOffs(0, 0).addBox(7.0f, -2.0f, -7.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
		mouse.texOffs(0, 0).addBox(7.0f, -2.0f, -12.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
		mouse.texOffs(0, 0).addBox(-1.0f, -1.0f, -15.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
		mouse.texOffs(0, 0).addBox(2.0f, -1.0f, -15.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
		mouse.texOffs(0, 0).addBox(6.0f, -1.0f, -15.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
		mouse.texOffs(0, 0).addBox(-5.0f, -1.0f, -15.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
		mouse.texOffs(0, 0).addBox(-8.0f, -1.0f, -15.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
		mouse.texOffs(0, 0).addBox(-8.0f, -1.0f, -11.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
		mouse.texOffs(0, 0).addBox(-8.0f, -1.0f, -8.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
		mouse.texOffs(0, 0).addBox(7.0f, -1.0f, -12.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
		mouse.texOffs(0, 0).addBox(7.0f, -1.0f, -8.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
		mouse.texOffs(0, 0).addBox(7.0f, -1.0f, -5.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);

		bone3 = new ModelPart(this);
		bone3.setPos(0.0f, 0.0f, 2.0f);
		head.addChild(bone3);
		setRotationAngle(bone3, 1.1345f, 0.0f, 0.0f);
		bone3.texOffs(151, 151).addBox(-11.0f, -3.0f, 0.0f, 22.0f, 3.0f, 3.0f, 0.0f, false);
		bone3.texOffs(155, 126).addBox(-11.0f, -3.0f, -11.0f, 3.0f, 3.0f, 11.0f, 0.0f, false);
		bone3.texOffs(152, 108).addBox(8.0f, -3.0f, -11.0f, 3.0f, 3.0f, 11.0f, 0.0f, false);
		bone3.texOffs(102, 247).addBox(-11.0f, -3.0f, -14.0f, 22.0f, 3.0f, 3.0f, 0.0f, false);

		angry = new ModelPart(this);
		angry.setPos(-2.0f, -1.0f, -17.0f);
		head.addChild(angry);

		left_arm = new ModelPart(this);
		left_arm.setPos(15.0f, -35.0f, 2.0f);
		up.addChild(left_arm);
		left_arm.texOffs(108, 180).addBox(-4.0f, 0.0f, -5.0f, 10.0f, 44.0f, 10.0f, 0.6f, false);

		pole = new ModelPart(this);
		pole.setPos(4.0f, 46.0f, -12.0f);
		left_arm.addChild(pole);
		pole.texOffs(185, 98).addBox(-1.0f, 7.0f, -39.0f, 2.0f, 2.0f, 4.0f, 0.0f, false);
		pole.texOffs(185, 98).addBox(-1.0f, -10.0f, -39.0f, 2.0f, 2.0f, 4.0f, 0.0f, false);
		pole.texOffs(0, 102).addBox(-2.0f, -3.0f, -50.0f, 4.0f, 4.0f, 72.0f, 0.0f, false);
		pole.texOffs(47, 117).addBox(-2.0f, -13.0f, -36.0f, 4.0f, 24.0f, 3.0f, 0.2f, false);
		pole.texOffs(47, 117).addBox(-2.0f, -9.0f, -46.0f, 4.0f, 16.0f, 3.0f, 0.2f, false);
		pole.texOffs(185, 98).addBox(-1.0f, -7.0f, -49.0f, 2.0f, 2.0f, 4.0f, 0.0f, false);
		pole.texOffs(185, 98).addBox(-1.0f, 3.0f, -49.0f, 2.0f, 2.0f, 4.0f, 0.0f, false);

		right_arm = new ModelPart(this);
		right_arm.setPos(-16.0f, -35.0f, 2.0f);
		up.addChild(right_arm);
		right_arm.texOffs(108, 180).addBox(-5.0f, 0.0f, -5.0f, 10.0f, 44.0f, 10.0f, 0.6f, false);

		back = new ModelPart(this);
		back.setPos(0.0f, -23.0f, 23.0f);
		up.addChild(back);
		back.texOffs(135, 16).addBox(-8.0f, -15.0f, -15.0f, 16.0f, 17.0f, 1.0f, 0.0f, false);
		back.texOffs(40, 84).addBox(-8.0f, 2.0f, -15.0f, 16.0f, 1.0f, 17.0f, 0.0f, false);
		back.texOffs(29, 211).addBox(8.0f, -15.0f, -15.0f, 1.0f, 17.0f, 18.0f, 0.0f, false);
		back.texOffs(33, 96).addBox(-8.0f, -15.0f, 2.0f, 16.0f, 17.0f, 1.0f, 0.0f, false);
		back.texOffs(29, 211).addBox(-9.0f, -15.0f, -15.0f, 1.0f, 17.0f, 18.0f, 0.0f, false);

		imp = new ModelPart(this);
		imp.setPos(0.0f, 3.0f, -1.0f);
		back.addChild(imp);

		left_leg3 = new ModelPart(this);
		left_leg3.setPos(2.0f, -12.0f, 0.0f);
		imp.addChild(left_leg3);
		left_leg3.texOffs(125, 82).addBox(-1.0f, -1.0f, -2.0f, 4.0f, 13.0f, 4.0f, 0.0f, false);

		right_leg3 = new ModelPart(this);
		right_leg3.setPos(-3.0f, -12.0f, 0.0f);
		imp.addChild(right_leg3);
		right_leg3.texOffs(6, 76).addBox(-2.0f, -1.0f, -2.0f, 4.0f, 13.0f, 4.0f, 0.0f, false);

		up3 = new ModelPart(this);
		up3.setPos(0.0f, -12.0f, 0.0f);
		imp.addChild(up3);

		body3 = new ModelPart(this);
		body3.setPos(0.0f, 0.0f, 0.0f);
		up3.addChild(body3);
		body3.texOffs(2, 24).addBox(-5.0f, -16.0f, -2.0f, 10.0f, 15.0f, 4.0f, 0.0f, false);

		right_arm2 = new ModelPart(this);
		right_arm2.setPos(-7.0f, -14.0f, 0.0f);
		up3.addChild(right_arm2);

		cube_r1 = new ModelPart(this);
		cube_r1.setPos(0.25f, 0.0f, 0.0f);
		right_arm2.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.7854f, 0.0f, 0.0f);
		cube_r1.texOffs(0, 1).addBox(-2.0f, -2.0f, -2.0f, 4.0f, 15.0f, 4.0f, -0.1f, false);

		left_arm2 = new ModelPart(this);
		left_arm2.setPos(7.0f, -14.0f, 0.0f);
		up3.addChild(left_arm2);

		cube_r2 = new ModelPart(this);
		cube_r2.setPos(-0.25f, 0.0f, 0.0f);
		left_arm2.addChild(cube_r2);
		setRotationAngle(cube_r2, -0.7854f, 0.0f, 0.0f);
		cube_r2.texOffs(146, 68).addBox(-2.0f, -2.0f, -2.0f, 4.0f, 15.0f, 4.0f, -0.1f, false);

		head3 = new ModelPart(this);
		head3.setPos(0.0f, -16.0f, 0.0f);
		up3.addChild(head3);

		bone = new ModelPart(this);
		bone.setPos(0.0f, 89.0f, -22.0f);
		head3.addChild(bone);
		bone.texOffs(49, 23).addBox(-5.0f, -97.0f, 17.0f, 10.0f, 10.0f, 10.0f, 0.3f, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.yRot = netHeadYaw / (180f / (float)Math.PI);
		this.head.xRot = headPitch / (180f / (float)Math.PI);
		if(entity.getAttackTime() == 0) {
			this.left_leg.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
			this.right_leg.xRot = Mth.cos(limbSwing * 0.6662f + (float)Math.PI) * 1.4f * limbSwingAmount;
			this.right_arm.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
			this.left_arm.xRot = Mth.cos(limbSwing * 0.6662f + (float)Math.PI) * 1.4f * limbSwingAmount;
			this.up.xRot = 0;
		} else if(entity.getAttackTime() > 0) {// normal attack
			int total = entity.getCrushCD();
			int now = total - entity.getAttackTime();
			int frontT = total * 2 / 3;
			if(now < frontT) {
				this.left_arm.xRot = AnimationUtil.getUpDown(now, frontT, - 120);
				this.left_arm.yRot = 0;
			} else {
				this.left_arm.xRot = AnimationUtil.getUpDown(now - frontT , total - frontT, 30);
				this.left_arm.yRot = AnimationUtil.getUpDown(now - frontT , total - frontT, 30);
			}
			this.up.xRot = AnimationUtil.getUpDownUpDown(now, total, - 15);
			this.right_leg.xRot = 0;
			this.left_leg.xRot = 0;
			this.right_arm.xRot = 0;
		} else {
			int total = entity.getThrowCD();
			int now = total + entity.getAttackTime();
			int frontT = total * 3 / 4;
			if(now < frontT) {
				this.right_arm.xRot = AnimationUtil.getUp(now, frontT, - 225);
			} else {
				this.right_arm.xRot = AnimationUtil.getDown(now - frontT, total - frontT, - 225);
			}
			this.left_arm.xRot = 0;
			this.right_leg.xRot = 0;
			this.left_leg.xRot = 0;
			this.up.xRot = 0;
		}
		this.imp.visible = entity.hasImp();
	}

	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		total.render(stack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelPart ModelPart, float x, float y, float z) {
		ModelPart.xRot = x;
		ModelPart.yRot = y;
		ModelPart.zRot = z;
	}

	@Override
	public void tickPartAnim(IBodyEntity iBodyEntity, float v, float v1, float v2, float v3, float v4) {
	}

	@Override
	public void renderBody(IBodyEntity entity, PoseStack stack, VertexConsumer buffer, int packedLight, int packedOverlay) {
		this.angry.visible = entity.hasHandDefence();
		this.total.render(stack, buffer, packedLight, packedOverlay);
	}

	@Override
	public EntityModel<T> getZombieModel() {
		return this;
	}
}
