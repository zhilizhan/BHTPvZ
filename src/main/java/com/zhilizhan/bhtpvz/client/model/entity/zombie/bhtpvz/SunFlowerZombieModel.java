package com.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz;

import com.hungteen.pvz.client.model.entity.zombie.PVZZombieModel;
import com.zhilizhan.bhtpvz.common.entity.zombie.SunFlowerZombieEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class SunFlowerZombieModel extends PVZZombieModel<SunFlowerZombieEntity> {
    private final ModelRenderer total;
    private final ModelRenderer left_leg;
    private final ModelRenderer right_leg;
    private final ModelRenderer up;
    private final ModelRenderer head;
    private final ModelRenderer right_hand;
    private final ModelRenderer left_hand;
    private final ModelRenderer body;
    private final ModelRenderer getZombieLeftHand;
    private final ModelRenderer getZombieRightHand;
    private final ModelRenderer getZombieLeftLeg;
    private final ModelRenderer getZombieRightLeg;
    private final ModelRenderer getZombieHead;
    private final ModelRenderer getZombieUpBody;
    private final ModelRenderer getZombieWholeBody;

    public SunFlowerZombieModel() {
        texWidth = 256;
        texHeight = 256;

        total = new ModelRenderer(this);
        total.setPos(0.0F, 24.0F, 0.0F);


        left_leg = new ModelRenderer(this);
        left_leg.setPos(4.0F, -24.0F, 0.0F);
        total.addChild(left_leg);
        left_leg.texOffs(0, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

        right_leg = new ModelRenderer(this);
        right_leg.setPos(-4.0F, -24.0F, 0.0F);
        total.addChild(right_leg);
        right_leg.texOffs(44, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

        up = new ModelRenderer(this);
        up.setPos(0.0F, -24.0F, 0.0F);
        total.addChild(up);


        head = new ModelRenderer(this);
        head.setPos(0.0F, -24.0F, 0.0F);
        up.addChild(head);
        head.texOffs(158, 184).addBox(-6.0F, -14.0F, -2.0F, 12.0F, 12.0F, 8.0F, 0.0F, false);
        head.texOffs(130, 155).addBox(-8.0F, -16.0F, -5.0F, 16.0F, 14.0F, 7.0F, 0.0F, false);
        head.texOffs(133, 130).addBox(-10.0F, -18.0F, -4.0F, 20.0F, 18.0F, 5.0F, 0.0F, false);

        right_hand = new ModelRenderer(this);
        right_hand.setPos(-12.0F, -20.0F, 0.0F);
        up.addChild(right_hand);
        right_hand.texOffs(96, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

        left_hand = new ModelRenderer(this);
        left_hand.setPos(12.0F, -20.0F, 0.0F);
        up.addChild(left_hand);
        left_hand.texOffs(96, 60).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setPos(0.0F, 0.0F, 0.0F);
        up.addChild(body);
        body.texOffs(0, 41).addBox(-8.0F, -24.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

        getZombieLeftHand = new ModelRenderer(this);
        getZombieLeftHand.setPos(0.0F, 0.0F, 0.0F);


        getZombieRightHand = new ModelRenderer(this);
        getZombieRightHand.setPos(0.0F, 0.0F, 0.0F);


        getZombieLeftLeg = new ModelRenderer(this);
        getZombieLeftLeg.setPos(0.0F, 0.0F, 0.0F);


        getZombieRightLeg = new ModelRenderer(this);
        getZombieRightLeg.setPos(0.0F, 0.0F, 0.0F);


        getZombieHead = new ModelRenderer(this);
        getZombieHead.setPos(0.0F, 0.0F, 0.0F);


        getZombieUpBody = new ModelRenderer(this);
        getZombieUpBody.setPos(0.0F, 0.0F, 0.0F);


        getZombieWholeBody = new ModelRenderer(this);
        getZombieWholeBody.setPos(0.0F, 0.0F, 0.0F);

    }

    public ModelRenderer getZombieLeftHand() {
        return this.left_hand;
    }

    public ModelRenderer getZombieRightHand() {
        return this.right_hand;
    }

    public ModelRenderer getZombieLeftLeg() {
        return this.left_leg;
    }

    public ModelRenderer getZombieRightLeg() {
        return this.right_leg;
    }

    public ModelRenderer getZombieHead() {
        return this.head;
    }

    public ModelRenderer getZombieUpBody() {
        return this.up;
    }

    public ModelRenderer getZombieWholeBody() {
        return this.total;
    }
}
