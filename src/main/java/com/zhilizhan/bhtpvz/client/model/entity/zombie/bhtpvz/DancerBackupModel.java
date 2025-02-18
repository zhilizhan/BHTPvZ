package com.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz;

import com.hungteen.pvz.client.model.entity.zombie.PVZZombieModel;
import com.zhilizhan.bhtpvz.common.entity.zombie.DancerBackupEntity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class DancerBackupModel extends PVZZombieModel<DancerBackupEntity> {
    private final ModelRenderer total;
    private final ModelRenderer right_leg;
    private final ModelRenderer left_leg;
    private final ModelRenderer up;
    private final ModelRenderer left_hand;
    private final ModelRenderer right_hand;
    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer getZombieLeftHand;
    private final ModelRenderer getZombieRightHand;
    private final ModelRenderer getZombieLeftLeg;
    private final ModelRenderer getZombieRightLeg;
    private final ModelRenderer getZombieHead;
    private final ModelRenderer getZombieUpBody;
    private final ModelRenderer getZombieWholeBody;

    public DancerBackupModel() {
        texWidth = 256;
        texHeight = 256;

        total = new ModelRenderer(this);
        total.setPos(0.0F, 24.0F, 0.0F);


        right_leg = new ModelRenderer(this);
        right_leg.setPos(5.0F, -30.0F, 0.0F);
        total.addChild(right_leg);
        right_leg.texOffs(218, 239).addBox(-4.0F, 24.0F, -6.0F, 8.0F, 6.0F, 11.0F, -0.5F, false);
        right_leg.texOffs(0, 226).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

        left_leg = new ModelRenderer(this);
        left_leg.setPos(-5.0F, -30.0F, 0.0F);
        total.addChild(left_leg);
        left_leg.texOffs(36, 239).addBox(-4.0F, 24.0F, -6.0F, 8.0F, 6.0F, 11.0F, -0.5F, false);
        left_leg.texOffs(96, 226).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

        up = new ModelRenderer(this);
        up.setPos(0.0F, -29.0F, 0.0F);
        total.addChild(up);


        left_hand = new ModelRenderer(this);
        left_hand.setPos(11.0F, -22.0F, 1.0F);
        up.addChild(left_hand);
        left_hand.texOffs(60, 188).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

        right_hand = new ModelRenderer(this);
        right_hand.setPos(-11.0F, -23.0F, 0.0F);
        up.addChild(right_hand);
        right_hand.texOffs(6, 179).addBox(-3.0F, -2.0F, -2.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setPos(0.0F, -25.0F, 0.0F);
        up.addChild(head);
        head.texOffs(0, 0).addBox(-7.0F, -14.0F, -6.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);
        head.texOffs(0, 30).addBox(-7.0F, -20.0F, -6.0F, 14.0F, 6.0F, 14.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setPos(0.0F, -1.0F, 0.0F);
        up.addChild(body);
        body.texOffs(141, 224).addBox(-8.0F, -24.0F, -3.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

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

    public void setupAnim(DancerBackupEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entity.getAttackTime() > 0) {
            int tick = entity.getAttackTime();
            int max = 100;
            this.total.yRot = -MathHelper.sin(6.28318F * (float)tick / (float)max);
            this.right_hand.xRot = -3.0F * MathHelper.abs(MathHelper.sin(12.56636F * (float)tick / (float)max));
            this.left_hand.xRot = -3.0F * MathHelper.abs(MathHelper.sin(12.56636F * (float)tick / (float)max));
            this.left_leg.xRot = 0.0F;
            this.right_leg.xRot = 0.0F;
        } else {
            super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        }
    }

    public void refreshAnim() {
        this.total.yRot = 0.0F;
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