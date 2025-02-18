package com.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz;

import com.hungteen.pvz.client.model.entity.zombie.PVZZombieModel;
import com.zhilizhan.bhtpvz.common.entity.zombie.MCZombieEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class MCZombieModel extends PVZZombieModel<MCZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;
	private final ModelRenderer getZombieLeftHand;
	private final ModelRenderer getZombieRightHand;
	private final ModelRenderer getZombieLeftLeg;
	private final ModelRenderer getZombieRightLeg;
	private final ModelRenderer getZombieHead;
	private final ModelRenderer getZombieUpBody;
	private final ModelRenderer getZombieWholeBody;

	public MCZombieModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0f, 24.0f, 0.0f);

		right_leg = new ModelRenderer(this);
		right_leg.setPos(-4.0f, -24.0f, 0.0f);
		total.addChild(right_leg);
		right_leg.texOffs(0, 32).addBox(-4.0f, 0.0f, -4.0f, 8.0f, 24.0f, 8.0f, 0.0f, false);

		left_leg = new ModelRenderer(this);
		left_leg.setPos(4.0f, -24.0f, 0.0f);
		total.addChild(left_leg);
		left_leg.texOffs(0, 32).addBox(-4.0f, 0.0f, -4.0f, 8.0f, 24.0f, 8.0f, 0.0f, false);

		up = new ModelRenderer(this);
		up.setPos(0.0f, -24.0f, 0.0f);
		total.addChild(up);

		body = new ModelRenderer(this);
		body.setPos(0.0f, -7.0f, 0.0f);
		up.addChild(body);
		body.texOffs(32, 32).addBox(-8.0f, -17.0f, -4.0f, 16.0f, 24.0f, 8.0f, 0.0f, false);

		left_hand = new ModelRenderer(this);
		left_hand.setPos(12.0f, -20.0f, 0.0f);
		up.addChild(left_hand);
		left_hand.texOffs(80, 32).addBox(-4.0f, -4.0f, -4.0f, 8.0f, 24.0f, 8.0f, 0.0f, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-12.0f, -20.0f, 0.0f);
		up.addChild(right_hand);
		right_hand.texOffs(80, 32).addBox(-4.0f, -4.0f, -4.0f, 8.0f, 24.0f, 8.0f, 0.0f, false);

		head = new ModelRenderer(this);
		head.setPos(0.0f, -24.0f, 0.0f);
		up.addChild(head);
		head.texOffs(0, 0).addBox(-8.0f, -16.0f, -8.0f, 16.0f, 16.0f, 16.0f, 0.0f, false);

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
