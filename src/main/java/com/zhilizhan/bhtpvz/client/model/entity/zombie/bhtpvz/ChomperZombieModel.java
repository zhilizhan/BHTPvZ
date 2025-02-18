package com.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz;

import com.hungteen.pvz.client.model.entity.zombie.PVZZombieModel;
import com.zhilizhan.bhtpvz.common.entity.zombie.ChomperZombieEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ChomperZombieModel extends PVZZombieModel<ChomperZombieEntity> {
    private final ModelRenderer total;
    private final ModelRenderer right_leg;
    private final ModelRenderer left_leg;
    private final ModelRenderer up;
    private final ModelRenderer body;
    private final ModelRenderer left_hand;
    private final ModelRenderer right_hand;
    private final ModelRenderer head;
    private final ModelRenderer bone14;
    private final ModelRenderer bone15;
    private final ModelRenderer up_mouse;
    private final ModelRenderer bone9;
    private final ModelRenderer bone8;
    private final ModelRenderer bone10;
    private final ModelRenderer bone11;
    private final ModelRenderer bone12;
    private final ModelRenderer bone13;
    private final ModelRenderer down_mouse;
    private final ModelRenderer leaf;
    private final ModelRenderer bone16;
    private final ModelRenderer bone17;
    private final ModelRenderer bone18;
    private final ModelRenderer bone19;
    private final ModelRenderer bone20;
    private final ModelRenderer bone21;
    private final ModelRenderer tongue;
    private final ModelRenderer tongue2;
    private final ModelRenderer getZombieLeftHand;
    private final ModelRenderer getZombieRightHand;
    private final ModelRenderer getZombieLeftLeg;
    private final ModelRenderer getZombieRightLeg;
    private final ModelRenderer getZombieHead;
    private final ModelRenderer getZombieUpBody;
    private final ModelRenderer getZombieWholeBody;

    public ChomperZombieModel() {
        texWidth = 256;
        texHeight = 256;

        total = new ModelRenderer(this);
        total.setPos(0.0F, 24.0F, 0.0F);


        right_leg = new ModelRenderer(this);
        right_leg.setPos(-4.0F, -24.0F, 0.0F);
        total.addChild(right_leg);
        right_leg.texOffs(44, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

        left_leg = new ModelRenderer(this);
        left_leg.setPos(4.0F, -24.0F, 0.0F);
        total.addChild(left_leg);
        left_leg.texOffs(0, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

        up = new ModelRenderer(this);
        up.setPos(0.0F, -24.0F, 0.0F);
        total.addChild(up);


        body = new ModelRenderer(this);
        body.setPos(0.0F, 0.0F, 0.0F);
        up.addChild(body);
        body.texOffs(0, 41).addBox(-8.0F, -24.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

        left_hand = new ModelRenderer(this);
        left_hand.setPos(12.0F, -20.0F, 0.0F);
        up.addChild(left_hand);
        left_hand.texOffs(96, 60).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

        right_hand = new ModelRenderer(this);
        right_hand.setPos(-12.0F, -20.0F, 0.0F);
        up.addChild(right_hand);
        right_hand.texOffs(96, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setPos(0.0F, -29.1569F, 1.2471F);
        up.addChild(head);
        setRotationAngle(head, -0.1863F, 0.0F, 0.0F);
        head.texOffs(200, 113).addBox(-6.0F, -5.0F, -2.0F, 12.0F, 10.0F, 4.0F, 0.0F, false);

        bone14 = new ModelRenderer(this);
        bone14.setPos(0.0F, -5.0F, 2.0F);
        head.addChild(bone14);
        setRotationAngle(bone14, -0.9599F, 0.0F, 0.0F);
        bone14.texOffs(233, 107).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        bone15 = new ModelRenderer(this);
        bone15.setPos(0.0F, -1.0F, 0.0F);
        bone14.addChild(bone15);
        setRotationAngle(bone15, 0.0F, 0.0F, -0.7854F);
        bone15.texOffs(163, 45).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        up_mouse = new ModelRenderer(this);
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

        bone9 = new ModelRenderer(this);
        bone9.setPos(0.0F, -3.0F, -10.0F);
        up_mouse.addChild(bone9);
        setRotationAngle(bone9, -0.0873F, 0.0F, 0.0F);
        bone9.texOffs(137, 99).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

        bone8 = new ModelRenderer(this);
        bone8.setPos(0.0F, -4.0F, 0.0F);
        bone9.addChild(bone8);
        setRotationAngle(bone8, 0.0F, 0.0F, -0.7854F);
        bone8.texOffs(179, 44).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        bone10 = new ModelRenderer(this);
        bone10.setPos(0.0F, -3.0F, -6.0F);
        up_mouse.addChild(bone10);
        setRotationAngle(bone10, -0.1745F, 0.0F, 0.0F);
        bone10.texOffs(132, 90).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        bone11 = new ModelRenderer(this);
        bone11.setPos(0.0F, -3.0F, 0.0F);
        bone10.addChild(bone11);
        setRotationAngle(bone11, 0.0F, 0.0F, -0.7854F);
        bone11.texOffs(190, 44).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        bone12 = new ModelRenderer(this);
        bone12.setPos(0.0F, -3.0F, -2.0F);
        up_mouse.addChild(bone12);
        setRotationAngle(bone12, -0.2618F, 0.0F, 0.0F);
        bone12.texOffs(160, 89).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);

        bone13 = new ModelRenderer(this);
        bone13.setPos(0.0F, -2.0F, 0.0F);
        bone12.addChild(bone13);
        setRotationAngle(bone13, 0.0F, 0.0F, -0.7854F);
        bone13.texOffs(147, 91).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        down_mouse = new ModelRenderer(this);
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

        leaf = new ModelRenderer(this);
        leaf.setPos(0.0F, 0.0F, 2.0F);
        head.addChild(leaf);
        leaf.texOffs(192, 73).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);

        bone16 = new ModelRenderer(this);
        bone16.setPos(0.0F, 0.0F, 1.0F);
        leaf.addChild(bone16);
        setRotationAngle(bone16, -0.5236F, 0.0F, 0.0F);
        bone16.texOffs(186, 65).addBox(-1.0F, -3.866F, -1.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);

        bone17 = new ModelRenderer(this);
        bone17.setPos(0.0F, 0.0F, 1.0F);
        leaf.addChild(bone17);
        setRotationAngle(bone17, -0.5236F, 0.0F, 1.0472F);
        bone17.texOffs(198, 64).addBox(-1.0F, -3.866F, -1.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);

        bone18 = new ModelRenderer(this);
        bone18.setPos(0.0F, 0.0F, 1.0F);
        leaf.addChild(bone18);
        setRotationAngle(bone18, -0.5236F, 0.0F, 2.0944F);
        bone18.texOffs(220, 64).addBox(-1.0F, -3.866F, -1.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);

        bone19 = new ModelRenderer(this);
        bone19.setPos(0.0F, 0.0F, 1.0F);
        leaf.addChild(bone19);
        setRotationAngle(bone19, -0.2618F, 0.0F, -3.1416F);
        bone19.texOffs(236, 61).addBox(-1.0F, -3.866F, -1.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);

        bone20 = new ModelRenderer(this);
        bone20.setPos(0.0F, 0.0F, 1.0F);
        leaf.addChild(bone20);
        setRotationAngle(bone20, -0.5236F, 0.0F, -1.0472F);
        bone20.texOffs(247, 61).addBox(-1.0F, -3.866F, -1.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);

        bone21 = new ModelRenderer(this);
        bone21.setPos(0.0F, 0.0F, 1.0F);
        leaf.addChild(bone21);
        setRotationAngle(bone21, -0.5236F, 0.0F, -2.0944F);
        bone21.texOffs(209, 57).addBox(-1.0F, -3.866F, -1.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);

        tongue = new ModelRenderer(this);
        tongue.setPos(0.0F, 0.0F, -3.0F);
        head.addChild(tongue);
        tongue.texOffs(135, 53).addBox(-1.0F, 0.0F, -3.0F, 2.0F, 1.0F, 4.0F, 0.0F, false);

        tongue2 = new ModelRenderer(this);
        tongue2.setPos(0.0F, 0.0F, -4.0F);
        tongue.addChild(tongue2);
        tongue2.texOffs(151, 52).addBox(-1.0F, 0.0F, -3.0F, 2.0F, 1.0F, 4.0F, 0.0F, false);

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
