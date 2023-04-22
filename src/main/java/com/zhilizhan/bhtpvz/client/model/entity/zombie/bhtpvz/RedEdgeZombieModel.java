package com.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz;// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.hungteen.pvz.client.model.entity.zombie.PVZZombieModel;
import com.zhilizhan.bhtpvz.common.entity.zombie.bhtpvz.RedEdgeZombieEntity;
import net.minecraft.client.model.geom.ModelPart;

public class RedEdgeZombieModel extends PVZZombieModel<RedEdgeZombieEntity> {
	private final ModelPart total;
	private final ModelPart right_leg;
	private final ModelPart left_leg;
	private final ModelPart up;
	private final ModelPart body;
	private final ModelPart left_hand;
	private final ModelPart sword;
	private final ModelPart cross_r1;
	private final ModelPart right_hand;
	private final ModelPart head;
	private final ModelPart getZombieLeftHand;
	private final ModelPart getZombieRightHand;
	private final ModelPart getZombieLeftLeg;
	private final ModelPart getZombieRightLeg;
	private final ModelPart getZombieHead;
	private final ModelPart getZombieUpBody;
	private final ModelPart getZombieWholeBody;

	public RedEdgeZombieModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelPart(this);
		total.setPos(0.0F, 24.0F, 0.0F);


		right_leg = new ModelPart(this);
		right_leg.setPos(-4.0F, -24.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.texOffs(0, 32).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		left_leg = new ModelPart(this);
		left_leg.setPos(4.0F, -24.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.texOffs(0, 32).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		up = new ModelPart(this);
		up.setPos(0.0F, -24.0F, 0.0F);
		total.addChild(up);


		body = new ModelPart(this);
		body.setPos(0.0F, -7.0F, 0.0F);
		up.addChild(body);
		body.texOffs(32, 32).addBox(-8.0F, -17.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

		left_hand = new ModelPart(this);
		left_hand.setPos(12.0F, -20.0F, 0.0F);
		up.addChild(left_hand);
		left_hand.texOffs(80, 32).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		sword = new ModelPart(this);
		sword.setPos(0.0F, 0.0F, 0.0F);
		left_hand.addChild(sword);


		cross_r1 = new ModelPart(this);
		cross_r1.setPos(0.0F, 0.0F, 0.0F);
		sword.addChild(cross_r1);
		setRotationAngle(cross_r1, 1.5708F, 0.0F, 0.0F);
		cross_r1.texOffs(102, 113).addBox(-2.0F, -13.0F, -21.0F, 2.0F, 3.0F, 13.0F, 0.0F, false);
		cross_r1.texOffs(22, 82).addBox(-1.0F, -47.0F, -17.0F, 1.0F, 34.0F, 4.0F, 0.0F, false);
		cross_r1.texOffs(36, 112).addBox(0.0F, -16.0F, -17.0F, 1.0F, 13.0F, 3.0F, 0.0F, false);

		right_hand = new ModelPart(this);
		right_hand.setPos(-12.0F, -20.0F, 0.0F);
		up.addChild(right_hand);
		right_hand.texOffs(80, 32).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		head = new ModelPart(this);
		head.setPos(0.0F, -24.0F, 0.0F);
		up.addChild(head);
		head.texOffs(0, 0).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		getZombieLeftHand = new ModelPart(this);
		getZombieLeftHand.setPos(0.0F, 0.0F, 0.0F);


		getZombieRightHand = new ModelPart(this);
		getZombieRightHand.setPos(0.0F, 0.0F, 0.0F);


		getZombieLeftLeg = new ModelPart(this);
		getZombieLeftLeg.setPos(0.0F, 0.0F, 0.0F);


		getZombieRightLeg = new ModelPart(this);
		getZombieRightLeg.setPos(0.0F, 0.0F, 0.0F);


		getZombieHead = new ModelPart(this);
		getZombieHead.setPos(0.0F, 0.0F, 0.0F);


		getZombieUpBody = new ModelPart(this);
		getZombieUpBody.setPos(0.0F, 0.0F, 0.0F);


		getZombieWholeBody = new ModelPart(this);
		getZombieWholeBody.setPos(0.0F, 0.0F, 0.0F);

	}

	@Override
	public ModelPart getZombieLeftHand() {
		return this.right_hand;
	}

	@Override
	public ModelPart getZombieRightHand() {
		return this.left_hand;
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