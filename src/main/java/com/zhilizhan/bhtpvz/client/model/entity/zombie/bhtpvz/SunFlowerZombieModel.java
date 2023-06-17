package com.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz;

import com.hungteen.pvz.client.model.entity.zombie.PVZZombieModel;
import com.zhilizhan.bhtpvz.common.entity.zombie.bhtpvz.SunFlowerZombieEntity;
import net.minecraft.client.model.geom.ModelPart;

public class SunFlowerZombieModel extends PVZZombieModel<SunFlowerZombieEntity> {
    private final ModelPart total;
    private final ModelPart left_leg;
    private final ModelPart right_leg;
    private final ModelPart up;
    private final ModelPart head;
    private final ModelPart right_hand;
    private final ModelPart left_hand;
    private final ModelPart body;
    private final ModelPart getZombieLeftHand;
    private final ModelPart getZombieRightHand;
    private final ModelPart getZombieLeftLeg;
    private final ModelPart getZombieRightLeg;
    private final ModelPart getZombieHead;
    private final ModelPart getZombieUpBody;
    private final ModelPart getZombieWholeBody;

    public SunFlowerZombieModel() {
        texWidth = 256;
        texHeight = 256;

        total = new ModelPart(this);
        total.setPos(0.0F, 24.0F, 0.0F);


        left_leg = new ModelPart(this);
        left_leg.setPos(4.0F, -24.0F, 0.0F);
        total.addChild(left_leg);
        left_leg.texOffs(0, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

        right_leg = new ModelPart(this);
        right_leg.setPos(-4.0F, -24.0F, 0.0F);
        total.addChild(right_leg);
        right_leg.texOffs(44, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

        up = new ModelPart(this);
        up.setPos(0.0F, -24.0F, 0.0F);
        total.addChild(up);


        head = new ModelPart(this);
        head.setPos(0.0F, -24.0F, 0.0F);
        up.addChild(head);
        head.texOffs(158, 184).addBox(-6.0F, -14.0F, -2.0F, 12.0F, 12.0F, 8.0F, 0.0F, false);
        head.texOffs(130, 155).addBox(-8.0F, -16.0F, -5.0F, 16.0F, 14.0F, 7.0F, 0.0F, false);
        head.texOffs(133, 130).addBox(-10.0F, -18.0F, -4.0F, 20.0F, 18.0F, 5.0F, 0.0F, false);

        right_hand = new ModelPart(this);
        right_hand.setPos(-12.0F, -20.0F, 0.0F);
        up.addChild(right_hand);
        right_hand.texOffs(96, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

        left_hand = new ModelPart(this);
        left_hand.setPos(12.0F, -20.0F, 0.0F);
        up.addChild(left_hand);
        left_hand.texOffs(96, 60).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

        body = new ModelPart(this);
        body.setPos(0.0F, 0.0F, 0.0F);
        up.addChild(body);
        body.texOffs(0, 41).addBox(-8.0F, -24.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

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

    public ModelPart getZombieLeftHand() {
        return this.left_hand;
    }

    public ModelPart getZombieRightHand() {
        return this.right_hand;
    }

    public ModelPart getZombieLeftLeg() {
        return this.left_leg;
    }

    public ModelPart getZombieRightLeg() {
        return this.right_leg;
    }

    public ModelPart getZombieHead() {
        return this.head;
    }

    public ModelPart getZombieUpBody() {
        return this.up;
    }

    public ModelPart getZombieWholeBody() {
        return this.total;
    }
}
