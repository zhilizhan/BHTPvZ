package com.github.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz;

import com.github.zhilizhan.bhtpvz.common.entity.zombie.bhtpvz.MCZombieEntity;

import com.hungteen.pvz.client.model.entity.zombie.PVZZombieModel;

import net.minecraft.client.model.geom.ModelPart;

public class MCZombieModel extends PVZZombieModel<MCZombieEntity> {
	private final ModelPart total;
	private final ModelPart right_leg;
	private final ModelPart left_leg;
	private final ModelPart up;
	private final ModelPart body;
	private final ModelPart left_hand;
	private final ModelPart right_hand;
	private final ModelPart head;
	private final ModelPart getZombieLeftHand;
	private final ModelPart getZombieRightHand;
	private final ModelPart getZombieLeftLeg;
	private final ModelPart getZombieRightLeg;
	private final ModelPart getZombieHead;
	private final ModelPart getZombieUpBody;
	private final ModelPart getZombieWholeBody;

	public MCZombieModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelPart(this);
		total.setPos(0.0f, 24.0f, 0.0f);

		right_leg = new ModelPart(this);
		right_leg.setPos(-4.0f, -24.0f, 0.0f);
		total.addChild(right_leg);
		right_leg.texOffs(0, 32).addBox(-4.0f, 0.0f, -4.0f, 8.0f, 24.0f, 8.0f, 0.0f, false);

		left_leg = new ModelPart(this);
		left_leg.setPos(4.0f, -24.0f, 0.0f);
		total.addChild(left_leg);
		left_leg.texOffs(0, 32).addBox(-4.0f, 0.0f, -4.0f, 8.0f, 24.0f, 8.0f, 0.0f, false);

		up = new ModelPart(this);
		up.setPos(0.0f, -24.0f, 0.0f);
		total.addChild(up);

		body = new ModelPart(this);
		body.setPos(0.0f, -7.0f, 0.0f);
		up.addChild(body);
		body.texOffs(32, 32).addBox(-8.0f, -17.0f, -4.0f, 16.0f, 24.0f, 8.0f, 0.0f, false);

		left_hand = new ModelPart(this);
		left_hand.setPos(12.0f, -20.0f, 0.0f);
		up.addChild(left_hand);
		left_hand.texOffs(80, 32).addBox(-4.0f, -4.0f, -4.0f, 8.0f, 24.0f, 8.0f, 0.0f, false);

		right_hand = new ModelPart(this);
		right_hand.setPos(-12.0f, -20.0f, 0.0f);
		up.addChild(right_hand);
		right_hand.texOffs(80, 32).addBox(-4.0f, -4.0f, -4.0f, 8.0f, 24.0f, 8.0f, 0.0f, false);

		head = new ModelPart(this);
		head.setPos(0.0f, -24.0f, 0.0f);
		up.addChild(head);
		head.texOffs(0, 0).addBox(-8.0f, -16.0f, -8.0f, 16.0f, 16.0f, 16.0f, 0.0f, false);

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
