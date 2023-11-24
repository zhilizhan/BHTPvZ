package com.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz;

import com.hungteen.pvz.client.model.entity.zombie.PVZZombieModel;
import com.zhilizhan.bhtpvz.common.entity.zombie.bhtpvz.ChomperZombieEntity;
import net.minecraft.client.model.geom.ModelPart;

public class ChomperZombieModel extends PVZZombieModel<ChomperZombieEntity> {
    private final ModelPart total;
    private final ModelPart right_leg;
    private final ModelPart left_leg;
    private final ModelPart up;
    private final ModelPart body;
    private final ModelPart left_hand;
    private final ModelPart right_hand;
    private final ModelPart head;
    private final ModelPart bone14;
    private final ModelPart bone15;
    private final ModelPart up_mouse;
    private final ModelPart bone9;
    private final ModelPart bone8;
    private final ModelPart bone10;
    private final ModelPart bone11;
    private final ModelPart bone12;
    private final ModelPart bone13;
    private final ModelPart down_mouse;
    private final ModelPart leaf;
    private final ModelPart bone16;
    private final ModelPart bone17;
    private final ModelPart bone18;
    private final ModelPart bone19;
    private final ModelPart bone20;
    private final ModelPart bone21;
    private final ModelPart tongue;
    private final ModelPart tongue2;
    private final ModelPart getZombieLeftHand;
    private final ModelPart getZombieRightHand;
    private final ModelPart getZombieLeftLeg;
    private final ModelPart getZombieRightLeg;
    private final ModelPart getZombieHead;
    private final ModelPart getZombieUpBody;
    private final ModelPart getZombieWholeBody;

    public ChomperZombieModel() {
        texWidth = 256;
        texHeight = 256;

        total = new ModelPart(this);
        total.setPos(0.0F, 24.0F, 0.0F);


        right_leg = new ModelPart(this);
        right_leg.setPos(-4.0F, -24.0F, 0.0F);
        total.addChild(right_leg);
        right_leg.texOffs(44, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

        left_leg = new ModelPart(this);
        left_leg.setPos(4.0F, -24.0F, 0.0F);
        total.addChild(left_leg);
        left_leg.texOffs(0, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

        up = new ModelPart(this);
        up.setPos(0.0F, -24.0F, 0.0F);
        total.addChild(up);


        body = new ModelPart(this);
        body.setPos(0.0F, 0.0F, 0.0F);
        up.addChild(body);
        body.texOffs(0, 41).addBox(-8.0F, -24.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

        left_hand = new ModelPart(this);
        left_hand.setPos(12.0F, -20.0F, 0.0F);
        up.addChild(left_hand);
        left_hand.texOffs(96, 60).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

        right_hand = new ModelPart(this);
        right_hand.setPos(-12.0F, -20.0F, 0.0F);
        up.addChild(right_hand);
        right_hand.texOffs(96, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

        head = new ModelPart(this);
        head.setPos(0.0F, -29.1569F, 1.2471F);
        up.addChild(head);
        setRotationAngle(head, -0.1863F, 0.0F, 0.0F);
        head.texOffs(200, 113).addBox(-6.0F, -5.0F, -2.0F, 12.0F, 10.0F, 4.0F, 0.0F, false);

        bone14 = new ModelPart(this);
        bone14.setPos(0.0F, -5.0F, 2.0F);
        head.addChild(bone14);
        setRotationAngle(bone14, -0.9599F, 0.0F, 0.0F);
        bone14.texOffs(233, 107).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        bone15 = new ModelPart(this);
        bone15.setPos(0.0F, -1.0F, 0.0F);
        bone14.addChild(bone15);
        setRotationAngle(bone15, 0.0F, 0.0F, -0.7854F);
        bone15.texOffs(163, 45).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        up_mouse = new ModelPart(this);
        up_mouse.setPos(0.0F, -3.0F, 0.0F);
        head.addChild(up_mouse);
        setRotationAngle(up_mouse, -0.5236F, 0.0F, 0.0F);
        up_mouse.texOffs(203, 94).addBox(6.0F, -1.0F, -11.0F, 1.0F, 4.0F, 11.0F, 0.0F, false);
        up_mouse.texOffs(150, 96).addBox(-6.0F, -2.0F, -12.0F, 12.0F, 5.0F, 12.0F, 0.0F, false);
        up_mouse.texOffs(231, 89).addBox(-5.0F, -1.0F, -13.0F, 10.0F, 4.0F, 1.0F, 0.0F, false);
        up_mouse.texOffs(228, 70).addBox(-7.0F, -1.0F, -11.0F, 1.0F, 4.0F, 11.0F, 0.0F, false);
        up_mouse.texOffs(141, 110).addBox(-6.0F, 3.0F, -7.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        up_mouse.texOffs(141, 110).addBox(-6.0F, 3.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        up_mouse.texOffs(141, 110).addBox(-6.0F, 3.0F, -3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        up_mouse.texOffs(141, 110).addBox(-6.0F, 3.0F, -9.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        up_mouse.texOffs(141, 110).addBox(-6.0F, 3.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        up_mouse.texOffs(141, 110).addBox(-5.0F, 3.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        up_mouse.texOffs(141, 110).addBox(-3.0F, 3.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        up_mouse.texOffs(141, 110).addBox(-1.0F, 3.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        up_mouse.texOffs(141, 110).addBox(1.0F, 3.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        up_mouse.texOffs(141, 110).addBox(3.0F, 3.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        up_mouse.texOffs(141, 110).addBox(5.0F, 3.0F, -12.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        up_mouse.texOffs(141, 110).addBox(5.0F, 3.0F, -10.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        up_mouse.texOffs(141, 110).addBox(5.0F, 3.0F, -8.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        up_mouse.texOffs(141, 110).addBox(5.0F, 3.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        up_mouse.texOffs(141, 110).addBox(5.0F, 3.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        bone9 = new ModelPart(this);
        bone9.setPos(0.0F, -3.0F, -10.0F);
        up_mouse.addChild(bone9);
        setRotationAngle(bone9, -0.0873F, 0.0F, 0.0F);
        bone9.texOffs(137, 99).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

        bone8 = new ModelPart(this);
        bone8.setPos(0.0F, -4.0F, 0.0F);
        bone9.addChild(bone8);
        setRotationAngle(bone8, 0.0F, 0.0F, -0.7854F);
        bone8.texOffs(179, 44).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        bone10 = new ModelPart(this);
        bone10.setPos(0.0F, -3.0F, -6.0F);
        up_mouse.addChild(bone10);
        setRotationAngle(bone10, -0.1745F, 0.0F, 0.0F);
        bone10.texOffs(132, 90).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        bone11 = new ModelPart(this);
        bone11.setPos(0.0F, -3.0F, 0.0F);
        bone10.addChild(bone11);
        setRotationAngle(bone11, 0.0F, 0.0F, -0.7854F);
        bone11.texOffs(190, 44).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        bone12 = new ModelPart(this);
        bone12.setPos(0.0F, -3.0F, -2.0F);
        up_mouse.addChild(bone12);
        setRotationAngle(bone12, -0.2618F, 0.0F, 0.0F);
        bone12.texOffs(160, 89).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);

        bone13 = new ModelPart(this);
        bone13.setPos(0.0F, -2.0F, 0.0F);
        bone12.addChild(bone13);
        setRotationAngle(bone13, 0.0F, 0.0F, -0.7854F);
        bone13.texOffs(147, 91).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        down_mouse = new ModelPart(this);
        down_mouse.setPos(0.0F, 2.0F, 0.0F);
        head.addChild(down_mouse);
        setRotationAngle(down_mouse, 0.5236F, 0.0F, 0.0F);
        down_mouse.texOffs(174, 79).addBox(6.0F, -1.0F, -11.0F, 1.0F, 3.0F, 11.0F, 0.0F, false);
        down_mouse.texOffs(133, 63).addBox(-6.0F, -1.0F, -12.0F, 12.0F, 4.0F, 12.0F, 0.0F, false);
        down_mouse.texOffs(202, 88).addBox(-5.0F, -1.0F, -13.0F, 10.0F, 3.0F, 1.0F, 0.0F, false);
        down_mouse.texOffs(201, 71).addBox(-7.0F, -1.0F, -11.0F, 1.0F, 3.0F, 11.0F, 0.0F, false);
        down_mouse.texOffs(133, 110).addBox(-6.0F, -2.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        down_mouse.texOffs(133, 110).addBox(5.0F, -2.0F, -3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        down_mouse.texOffs(133, 110).addBox(-6.0F, -2.0F, -8.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        down_mouse.texOffs(133, 110).addBox(-6.0F, -2.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        down_mouse.texOffs(133, 110).addBox(-6.0F, -2.0F, -10.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        down_mouse.texOffs(133, 110).addBox(-6.0F, -2.0F, -12.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        down_mouse.texOffs(133, 110).addBox(-4.0F, -2.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        down_mouse.texOffs(133, 110).addBox(-2.0F, -2.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        down_mouse.texOffs(133, 110).addBox(0.0F, -2.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        down_mouse.texOffs(133, 110).addBox(2.0F, -2.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        down_mouse.texOffs(133, 110).addBox(4.0F, -2.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        down_mouse.texOffs(133, 110).addBox(5.0F, -2.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        down_mouse.texOffs(133, 110).addBox(5.0F, -2.0F, -9.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        down_mouse.texOffs(133, 110).addBox(5.0F, -2.0F, -7.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        down_mouse.texOffs(133, 110).addBox(5.0F, -2.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        leaf = new ModelPart(this);
        leaf.setPos(0.0F, 0.0F, 2.0F);
        head.addChild(leaf);
        leaf.texOffs(192, 73).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);

        bone16 = new ModelPart(this);
        bone16.setPos(0.0F, 0.0F, 1.0F);
        leaf.addChild(bone16);
        setRotationAngle(bone16, -0.5236F, 0.0F, 0.0F);
        bone16.texOffs(186, 65).addBox(-1.0F, -3.866F, -1.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);

        bone17 = new ModelPart(this);
        bone17.setPos(0.0F, 0.0F, 1.0F);
        leaf.addChild(bone17);
        setRotationAngle(bone17, -0.5236F, 0.0F, 1.0472F);
        bone17.texOffs(198, 64).addBox(-1.0F, -3.866F, -1.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);

        bone18 = new ModelPart(this);
        bone18.setPos(0.0F, 0.0F, 1.0F);
        leaf.addChild(bone18);
        setRotationAngle(bone18, -0.5236F, 0.0F, 2.0944F);
        bone18.texOffs(220, 64).addBox(-1.0F, -3.866F, -1.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);

        bone19 = new ModelPart(this);
        bone19.setPos(0.0F, 0.0F, 1.0F);
        leaf.addChild(bone19);
        setRotationAngle(bone19, -0.2618F, 0.0F, -3.1416F);
        bone19.texOffs(236, 61).addBox(-1.0F, -3.866F, -1.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);

        bone20 = new ModelPart(this);
        bone20.setPos(0.0F, 0.0F, 1.0F);
        leaf.addChild(bone20);
        setRotationAngle(bone20, -0.5236F, 0.0F, -1.0472F);
        bone20.texOffs(247, 61).addBox(-1.0F, -3.866F, -1.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);

        bone21 = new ModelPart(this);
        bone21.setPos(0.0F, 0.0F, 1.0F);
        leaf.addChild(bone21);
        setRotationAngle(bone21, -0.5236F, 0.0F, -2.0944F);
        bone21.texOffs(209, 57).addBox(-1.0F, -3.866F, -1.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);

        tongue = new ModelPart(this);
        tongue.setPos(0.0F, 0.0F, -3.0F);
        head.addChild(tongue);
        tongue.texOffs(135, 53).addBox(-1.0F, 0.0F, -3.0F, 2.0F, 1.0F, 4.0F, 0.0F, false);

        tongue2 = new ModelPart(this);
        tongue2.setPos(0.0F, 0.0F, -4.0F);
        tongue.addChild(tongue2);
        tongue2.texOffs(151, 52).addBox(-1.0F, 0.0F, -3.0F, 2.0F, 1.0F, 4.0F, 0.0F, false);

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

    public void setupAnim(ChomperZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity,limbSwing,limbSwingAmount,ageInTicks,netHeadYaw,headPitch);

        int tick = entity.getAttackTime();
        if (tick > 0 && tick <= 15) {
            this.head.xRot = 0.25F - 0.075F * (float)tick;
            if (tick <= 10) {
                this.up_mouse.xRot = -0.52F - 0.05F * (float)tick;
                this.down_mouse.xRot = 0.52F + 0.05F * (float)tick;
            } else {
                float tmp = (float)tick - 20.0F;
                this.up_mouse.xRot = 0.82F + 0.2F * tmp;
                this.down_mouse.xRot = -0.82F - 0.2F * tmp;
                this.head.xRot = 0.47F;
            }
        } else if (tick > 15) {
            int tmp = tick - 15;
            this.head.xRot = -0.5F + 0.075F * (float)tmp;
            this.up_mouse.xRot = -0.05F * (float)tmp;
            this.down_mouse.xRot = 0.05F * (float)tmp;
        } else {
            if (entity.isResting()) {
                this.tongue.xRot = entity.getRandom().nextFloat() - 0.5F;
                this.tongue2.xRot = entity.getRandom().nextFloat() - 0.5F;
                this.head.xRot = 1.04F;
                this.up_mouse.xRot = -0.15F;
                this.down_mouse.xRot = 0.15F;
                this.leaf.zRot = 0.52F;
            } else {
                this.tongue.xRot = 0.0F;
                this.tongue2.xRot = 0.0F;
                this.head.xRot = 0.25F;
                this.up_mouse.xRot = -0.52F;
                this.down_mouse.xRot = 0.52F;
                this.leaf.zRot = 0.0F;
            }
        }

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
