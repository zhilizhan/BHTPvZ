package com.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz;

import com.hungteen.pvz.client.model.entity.zombie.PVZZombieModel;
import com.zhilizhan.bhtpvz.common.entity.zombie.FlowerPotZombieEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

import java.util.Optional;

public class FlowerPotZombieModel extends PVZZombieModel<FlowerPotZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	private final ModelRenderer up;
	private final ModelRenderer head;
	private final ModelRenderer bone3;
	private final ModelRenderer red_eyes;
	private final ModelRenderer normal_eyes;
	private final ModelRenderer right_hand;
	private final ModelRenderer flower_pot;
	private final ModelRenderer body2;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer left_hand;
	private final ModelRenderer body;
	private final ModelRenderer getZombieLeftHand;
	private final ModelRenderer getZombieRightHand;
	private final ModelRenderer getZombieLeftLeg;
	private final ModelRenderer getZombieRightLeg;
	private final ModelRenderer getZombieHead;
	private final ModelRenderer getZombieUpBody;
	private final ModelRenderer getZombieWholeBody;

	public FlowerPotZombieModel() {
		texWidth = 256;
		texHeight = 256;

		total = new ModelRenderer(this);
		total.setPos(0.0f, 24.0f, 0.0f);

		left_leg = new ModelRenderer(this);
		left_leg.setPos(4.0f, -24.0f, 0.0f);
		total.addChild(left_leg);
		left_leg.texOffs(230, 225).addBox(-3.0f, 0.0f, -3.0f, 6.0f, 22.0f, 6.0f, 0.0f, false);
		left_leg.texOffs(196, 242).addBox(-3.0f, 22.0f, -5.0f, 6.0f, 2.0f, 9.0f, 0.0f, false);

		right_leg = new ModelRenderer(this);
		right_leg.setPos(-4.0f, -24.0f, 0.0f);
		total.addChild(right_leg);
		right_leg.texOffs(230, 192).addBox(-3.0f, 0.0f, -3.0f, 6.0f, 22.0f, 6.0f, 0.0f, false);
		right_leg.texOffs(191, 220).addBox(-3.0f, 22.0f, -5.0f, 6.0f, 2.0f, 9.0f, 0.0f, false);

		up = new ModelRenderer(this);
		up.setPos(0.0f, -24.0f, 0.0f);
		total.addChild(up);

		head = new ModelRenderer(this);
		head.setPos(0.0f, -24.0f, 0.0f);
		up.addChild(head);
		head.texOffs(66, 182).addBox(-7.0f, -14.0f, -7.0f, 14.0f, 14.0f, 14.0f, 0.0f, false);
		head.texOffs(130, 207).addBox(-7.0f, -15.0f, 2.0f, 13.0f, 1.0f, 1.0f, 0.0f, false);
		head.texOffs(169, 208).addBox(-6.0f, -15.0f, -3.0f, 13.0f, 1.0f, 1.0f, 0.0f, false);

		bone3 = new ModelRenderer(this);
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

		red_eyes = new ModelRenderer(this);
		red_eyes.setPos(0.0f, 0.0f, 0.0f);
		head.addChild(red_eyes);
		red_eyes.texOffs(45, 187).addBox(-6.0f, -13.0f, -7.05f, 5.0f, 5.0f, 1.0f, 0.0f, false);
		red_eyes.texOffs(16, 186).addBox(1.0f, -13.0f, -7.05f, 5.0f, 5.0f, 1.0f, 0.0f, false);

		normal_eyes = new ModelRenderer(this);
		normal_eyes.setPos(0.0f, 0.0f, 0.0f);
		head.addChild(normal_eyes);
		normal_eyes.texOffs(47, 178).addBox(-6.0f, -13.0f, -7.05f, 5.0f, 5.0f, 1.0f, 0.0f, false);
		normal_eyes.texOffs(20, 175).addBox(1.0f, -13.0f, -7.05f, 5.0f, 5.0f, 1.0f, 0.0f, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-11.0f, -21.0f, 0.0f);
		up.addChild(right_hand);
		setRotationAngle(right_hand, -1.0472f, 0.0f, 0.0f);
		right_hand.texOffs(148, 222).addBox(-3.0f, -3.0f, -3.0f, 6.0f, 24.0f, 6.0f, 0.0f, false);

		flower_pot = new ModelRenderer(this);
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

		body2 = new ModelRenderer(this);
		body2.setPos(1.0f, -11.5f, 5.5f);
		flower_pot.addChild(body2);
		setRotationAngle(body2, -0.1309f, 0.0f, -0.3054f);
		body2.texOffs(55, 48).addBox(-1.0f, -12.0f, 15.0f, 1.0f, 5.0f, 1.0f, 1.0f, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(0.0f, -4.0f, 0.0f);
		body2.addChild(bone4);
		setRotationAngle(bone4, 0.0f, 0.0f, -0.6109f);
		bone4.texOffs(55, 55).addBox(-1.0f, -9.0f, 15.0f, 1.0f, 2.0f, 1.0f, 1.0f, false);

		bone5 = new ModelRenderer(this);
		bone5.setPos(-0.25f, -3.75f, 0.0f);
		body2.addChild(bone5);
		setRotationAngle(bone5, 0.0f, 0.0f, 0.6545f);
		bone5.texOffs(55, 44).addBox(-1.0f, -9.0f, 15.0f, 1.0f, 2.0f, 1.0f, 1.0f, false);

		left_hand = new ModelRenderer(this);
		left_hand.setPos(11.0f, -21.0f, 0.0f);
		up.addChild(left_hand);
		setRotationAngle(left_hand, -1.0472f, 0.0f, 0.0f);
		left_hand.texOffs(59, 221).addBox(-3.0f, -3.0f, -3.0f, 6.0f, 24.0f, 6.0f, 0.0f, false);

		body = new ModelRenderer(this);
		body.setPos(0.0f, -7.0f, 0.0f);
		up.addChild(body);
		body.texOffs(3, 221).addBox(-8.0f, -17.0f, -4.0f, 16.0f, 24.0f, 8.0f, 0.0f, false);

		getZombieLeftHand = new ModelRenderer(this);
		getZombieLeftHand.setPos(0.0f, 0.0f, 0.0f);

		getZombieRightHand = new ModelRenderer(this);
		getZombieRightHand.setPos(0.0f, 0.0f, 0.0f);

		getZombieLeftLeg = new ModelRenderer(this);
		getZombieLeftLeg.setPos(0.0f, 0.0f, 0.0f);

		getZombieRightLeg = new ModelRenderer(this);
		getZombieRightLeg.setPos(0.0f, 0.0f, 0.0f);

		getZombieHead = new ModelRenderer(this);
		getZombieHead.setPos(0.0f, 0.0f, 0.0f);

		getZombieUpBody = new ModelRenderer(this);
		getZombieUpBody.setPos(0.0f, 0.0f, 0.0f);

		getZombieWholeBody = new ModelRenderer(this);
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
	public Optional<ModelRenderer> getHandDefence() {
		return Optional.ofNullable(this.flower_pot);
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