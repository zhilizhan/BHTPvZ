package com.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz;

import com.zhilizhan.bhtpvz.common.entity.zombie.bhtpvz.FlowerPotZombieEntity;

import com.hungteen.pvz.client.model.entity.zombie.PVZZombieModel;

import net.minecraft.client.model.geom.ModelPart;

import java.util.Optional;

public class FlowerPotZombieModel extends PVZZombieModel<FlowerPotZombieEntity> {
	private final ModelPart total;
	private final ModelPart left_leg;
	private final ModelPart right_leg;
	private final ModelPart up;
	private final ModelPart head;
	private final ModelPart bone3;
	private final ModelPart red_eyes;
	private final ModelPart normal_eyes;
	private final ModelPart right_hand;
	private final ModelPart flower_pot;
	private final ModelPart body2;
	private final ModelPart bone4;
	private final ModelPart bone5;
	private final ModelPart left_hand;
	private final ModelPart body;
	private final ModelPart getZombieLeftHand;
	private final ModelPart getZombieRightHand;
	private final ModelPart getZombieLeftLeg;
	private final ModelPart getZombieRightLeg;
	private final ModelPart getZombieHead;
	private final ModelPart getZombieUpBody;
	private final ModelPart getZombieWholeBody;

	public FlowerPotZombieModel() {
		texWidth = 256;
		texHeight = 256;

		total = new ModelPart(this);
		total.setPos(0.0f, 24.0f, 0.0f);

		left_leg = new ModelPart(this);
		left_leg.setPos(4.0f, -24.0f, 0.0f);
		total.addChild(left_leg);
		left_leg.texOffs(230, 225).addBox(-3.0f, 0.0f, -3.0f, 6.0f, 22.0f, 6.0f, 0.0f, false);
		left_leg.texOffs(196, 242).addBox(-3.0f, 22.0f, -5.0f, 6.0f, 2.0f, 9.0f, 0.0f, false);

		right_leg = new ModelPart(this);
		right_leg.setPos(-4.0f, -24.0f, 0.0f);
		total.addChild(right_leg);
		right_leg.texOffs(230, 192).addBox(-3.0f, 0.0f, -3.0f, 6.0f, 22.0f, 6.0f, 0.0f, false);
		right_leg.texOffs(191, 220).addBox(-3.0f, 22.0f, -5.0f, 6.0f, 2.0f, 9.0f, 0.0f, false);

		up = new ModelPart(this);
		up.setPos(0.0f, -24.0f, 0.0f);
		total.addChild(up);

		head = new ModelPart(this);
		head.setPos(0.0f, -24.0f, 0.0f);
		up.addChild(head);
		head.texOffs(66, 182).addBox(-7.0f, -14.0f, -7.0f, 14.0f, 14.0f, 14.0f, 0.0f, false);
		head.texOffs(130, 207).addBox(-7.0f, -15.0f, 2.0f, 13.0f, 1.0f, 1.0f, 0.0f, false);
		head.texOffs(169, 208).addBox(-6.0f, -15.0f, -3.0f, 13.0f, 1.0f, 1.0f, 0.0f, false);

		bone3 = new ModelPart(this);
		bone3.setPos(0.0f, -10.0f, 0.0f);
		head.addChild(bone3);
		setRotationAngle(bone3, 0.3491f, 0.0f, 0.0f);
		bone3.texOffs(200, 188).addBox(-8.0f, -1.0f, -10.0f, 1.0f, 1.0f, 11.0f, 0.0f, false);
		bone3.texOffs(140, 180).addBox(7.0f, -1.0f, -10.0f, 1.0f, 1.0f, 11.0f, 0.0f, false);
		bone3.texOffs(183, 187).addBox(-7.0f, -1.0f, -10.0f, 2.0f, 1.0f, 1.0f, 0.0f, false);
		bone3.texOffs(206, 172).addBox(5.0f, -1.0f, -10.0f, 2.0f, 1.0f, 1.0f, 0.0f, false);
		bone3.texOffs(227, 175).addBox(-1.0f, -1.0f, -10.0f, 2.0f, 1.0f, 1.0f, 0.0f, false);
		bone3.texOffs(241, 169).addBox(-5.0f, -2.0f, -10.0f, 4.0f, 3.0f, 1.0f, 0.0f, false);
		bone3.texOffs(174, 175).addBox(1.0f, -2.0f, -10.0f, 4.0f, 3.0f, 1.0f, 0.0f, false);

		red_eyes = new ModelPart(this);
		red_eyes.setPos(0.0f, 0.0f, 0.0f);
		head.addChild(red_eyes);
		red_eyes.texOffs(45, 187).addBox(-6.0f, -13.0f, -7.05f, 5.0f, 5.0f, 1.0f, 0.0f, false);
		red_eyes.texOffs(16, 186).addBox(1.0f, -13.0f, -7.05f, 5.0f, 5.0f, 1.0f, 0.0f, false);

		normal_eyes = new ModelPart(this);
		normal_eyes.setPos(0.0f, 0.0f, 0.0f);
		head.addChild(normal_eyes);
		normal_eyes.texOffs(47, 178).addBox(-6.0f, -13.0f, -7.05f, 5.0f, 5.0f, 1.0f, 0.0f, false);
		normal_eyes.texOffs(20, 175).addBox(1.0f, -13.0f, -7.05f, 5.0f, 5.0f, 1.0f, 0.0f, false);

		right_hand = new ModelPart(this);
		right_hand.setPos(-11.0f, -21.0f, 0.0f);
		up.addChild(right_hand);
		setRotationAngle(right_hand, -1.0472f, 0.0f, 0.0f);
		right_hand.texOffs(148, 222).addBox(-3.0f, -3.0f, -3.0f, 6.0f, 24.0f, 6.0f, 0.0f, false);

		flower_pot = new ModelPart(this);
		flower_pot.setPos(11.0f, 45.0f, 0.0f);
		right_hand.addChild(flower_pot);
		setRotationAngle(flower_pot, 1.0472f, 0.0f, 0.0f);
		flower_pot.texOffs(0, 53).addBox(-5.0f, -8.0f, 10.0f, 10.0f, 1.0f, 10.0f, 1.0f, false);
		flower_pot.texOffs(0, 50).addBox(-6.0f, -10.0f, 9.0f, 12.0f, 2.0f, 12.0f, 1.0f, false);
		flower_pot.texOffs(0, 47).addBox(-7.0f, -13.0f, 8.0f, 14.0f, 3.0f, 14.0f, 1.0f, false);
		flower_pot.texOffs(0, 0).addBox(-8.0f, -19.0f, 7.0f, 16.0f, 6.0f, 16.0f, 1.0f, false);
		flower_pot.texOffs(0, 48).addBox(-8.0f, -20.0f, 7.0f, 1.0f, 1.0f, 15.0f, 1.0f, false);
		flower_pot.texOffs(2, 31).addBox(7.0f, -20.0f, 7.0f, 1.0f, 1.0f, 15.0f, 1.0f, false);
		flower_pot.texOffs(1, 32).addBox(-7.0f, -20.0f, 7.0f, 14.0f, 1.0f, 1.0f, 1.0f, false);
		flower_pot.texOffs(2, 36).addBox(-8.0f, -20.0f, 22.0f, 16.0f, 1.0f, 1.0f, 1.0f, false);

		body2 = new ModelPart(this);
		body2.setPos(1.0f, -11.5f, 5.5f);
		flower_pot.addChild(body2);
		setRotationAngle(body2, -0.1309f, 0.0f, -0.3054f);
		body2.texOffs(55, 48).addBox(-1.0f, -12.0f, 15.0f, 1.0f, 5.0f, 1.0f, 1.0f, false);

		bone4 = new ModelPart(this);
		bone4.setPos(0.0f, -4.0f, 0.0f);
		body2.addChild(bone4);
		setRotationAngle(bone4, 0.0f, 0.0f, -0.6109f);
		bone4.texOffs(55, 55).addBox(-1.0f, -9.0f, 15.0f, 1.0f, 2.0f, 1.0f, 1.0f, false);

		bone5 = new ModelPart(this);
		bone5.setPos(-0.25f, -3.75f, 0.0f);
		body2.addChild(bone5);
		setRotationAngle(bone5, 0.0f, 0.0f, 0.6545f);
		bone5.texOffs(55, 44).addBox(-1.0f, -9.0f, 15.0f, 1.0f, 2.0f, 1.0f, 1.0f, false);

		left_hand = new ModelPart(this);
		left_hand.setPos(11.0f, -21.0f, 0.0f);
		up.addChild(left_hand);
		setRotationAngle(left_hand, -1.0472f, 0.0f, 0.0f);
		left_hand.texOffs(59, 221).addBox(-3.0f, -3.0f, -3.0f, 6.0f, 24.0f, 6.0f, 0.0f, false);

		body = new ModelPart(this);
		body.setPos(0.0f, -7.0f, 0.0f);
		up.addChild(body);
		body.texOffs(3, 221).addBox(-8.0f, -17.0f, -4.0f, 16.0f, 24.0f, 8.0f, 0.0f, false);

		getZombieLeftHand = new ModelPart(this);
		getZombieLeftHand.setPos(0.0f, 0.0f, 0.0f);

		getZombieRightHand = new ModelPart(this);
		getZombieRightHand.setPos(0.0f, 0.0f, 0.0f);

		getZombieLeftLeg = new ModelPart(this);
		getZombieLeftLeg.setPos(0.0f, 0.0f, 0.0f);

		getZombieRightLeg = new ModelPart(this);
		getZombieRightLeg.setPos(0.0f, 0.0f, 0.0f);

		getZombieHead = new ModelPart(this);
		getZombieHead.setPos(0.0f, 0.0f, 0.0f);

		getZombieUpBody = new ModelPart(this);
		getZombieUpBody.setPos(0.0f, 0.0f, 0.0f);

		getZombieWholeBody = new ModelPart(this);
		getZombieWholeBody.setPos(0.0f, 0.0f, 0.0f);
	}

	@Override
	public void updateFreeParts(FlowerPotZombieEntity entity) {
		super.updateFreeParts(entity);
		final boolean isPaperDestroyed = true;
		//this.isLeftHandFree = isPaperDestroyed;
		//this.isRightHandFree = isPaperDestroyed;
		//this.red_eyes.visible = isPaperDestroyed;
		this.normal_eyes.visible = isPaperDestroyed;
		this.flower_pot.visible = isPaperDestroyed;
	}

	@Override
	public void refreshAnim() {
		this.getZombieLeftHand().xRot = -1.0472f;
		this.getZombieRightHand().xRot = -1.0472f;
	}



	@Override
	public Optional<ModelPart> getHandDefence() {
		return Optional.ofNullable(this.flower_pot);
	}

	@Override
	public ModelPart getZombieLeftHand() {
		return this.left_hand;
	}

	@Override
	public ModelPart getZombieRightHand() {
		return this.right_hand;
	}

	@Override
	public ModelPart getZombieLeftLeg() {
		return this.left_leg;
	}

	@Override
	public ModelPart getZombieRightLeg() {
		return this.right_leg;
	}

	@Override
	public ModelPart getZombieHead() {
		return this.head;
	}

	@Override
	public ModelPart getZombieUpBody() {
		return this.up;
	}

	@Override
	public ModelPart getZombieWholeBody() {
		return this.total;
	}

}