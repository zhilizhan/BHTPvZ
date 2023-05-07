package com.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz;

import com.hungteen.pvz.client.model.entity.zombie.PVZZombieModel;
import com.zhilizhan.bhtpvz.common.entity.zombie.bhtpvz.MJZombieEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class MJZombieModel extends PVZZombieModel<MJZombieEntity> {
    private final ModelPart total;
    private final ModelPart right_leg;
    private final ModelPart left_leg;
    private final ModelPart up;
    private final ModelPart left_hand;
    private final ModelPart right_hand;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart getZombieLeftHand;
    private final ModelPart getZombieRightHand;
    private final ModelPart getZombieLeftLeg;
    private final ModelPart getZombieRightLeg;
    private final ModelPart getZombieHead;
    private final ModelPart getZombieUpBody;
    private final ModelPart getZombieWholeBody;

    public MJZombieModel() {
        texWidth = 256;
        texHeight = 256;

        total = new ModelPart(this);
        total.setPos(0.0F, 24.0F, 0.0F);


        right_leg = new ModelPart(this);
        right_leg.setPos(5.0F, -30.0F, 0.0F);
        total.addChild(right_leg);
        right_leg.texOffs(218, 239).addBox(-4.0F, 24.0F, -6.0F, 8.0F, 6.0F, 11.0F, -0.5F, false);
        right_leg.texOffs(0, 226).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

        left_leg = new ModelPart(this);
        left_leg.setPos(-5.0F, -30.0F, 0.0F);
        total.addChild(left_leg);
        left_leg.texOffs(36, 239).addBox(-4.0F, 24.0F, -6.0F, 8.0F, 6.0F, 11.0F, -0.5F, false);
        left_leg.texOffs(96, 226).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

        up = new ModelPart(this);
        up.setPos(0.0F, -30.0F, 0.0F);
        total.addChild(up);


        left_hand = new ModelPart(this);
        left_hand.setPos(11.0F, -21.0F, 1.0F);
        up.addChild(left_hand);
        left_hand.texOffs(60, 188).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

        right_hand = new ModelPart(this);
        right_hand.setPos(-11.0F, -22.0F, 0.0F);
        up.addChild(right_hand);
        right_hand.texOffs(6, 179).addBox(-3.0F, -2.0F, -2.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

        head = new ModelPart(this);
        head.setPos(0.0F, -24.0F, 0.0F);
        up.addChild(head);
        head.texOffs(0, 0).addBox(-6.0F, -12.0F, -5.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);
        head.texOffs(196, 84).addBox(-8.0F, -18.0F, -5.0F, 16.0F, 6.0F, 14.0F, 0.0F, false);
        head.texOffs(226, 0).addBox(-10.0F, -15.0F, -4.0F, 4.0F, 12.0F, 11.0F, 0.0F, false);
        head.texOffs(226, 0).addBox(6.0F, -15.0F, -4.0F, 4.0F, 12.0F, 11.0F, 0.0F, false);
        head.texOffs(162, 212).addBox(-6.0F, -12.0F, 7.0F, 12.0F, 8.0F, 2.0F, 0.0F, false);

        body = new ModelPart(this);
        body.setPos(0.0F, 0.0F, 0.0F);
        up.addChild(body);
        body.texOffs(141, 224).addBox(-8.0F, -24.0F, -3.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

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

    public void setupAnim(MJZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entity.getSummonTime() > 0) {
            this.total.yRot = 0.0F;
            this.left_hand.xRot = 0.0F;
            this.right_hand.xRot = -2.5F;
            this.left_leg.xRot = 0.0F;
            this.right_leg.xRot = 0.0F;
        } else if (entity.getAttackTime() > 0) {
            int tick = entity.getAttackTime();
            int max = 100;
            this.total.yRot = -Mth.sin(6.28318F * (float)tick / (float)max);
            this.right_hand.xRot = -3.0F * Mth.abs(Mth.sin(12.56636F * (float)tick / (float)max));
            this.left_hand.xRot = -3.0F * Mth.abs(Mth.sin(12.56636F * (float)tick / (float)max));
            this.left_leg.xRot = 0.0F;
            this.right_leg.xRot = 0.0F;
        } else {
            super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        }
    }

    public void refreshAnim() {
        this.total.yRot = 0.0F;
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
