package com.zhilizhan.bhtpvz.client.model.entity.plant.fire;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.utils.AnimationUtil;
import com.zhilizhan.bhtpvz.common.entity.plant.fire.BlazeWartEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class BlazeWartModel<T extends LivingEntity> extends PVZPlantModel<BlazeWartEntity> {
    private final ModelRenderer total;
    private final ModelRenderer right_arm;
    private final ModelRenderer right1;
    private final ModelRenderer right2;
    private final ModelRenderer right3;
    private final ModelRenderer right4;
    private final ModelRenderer right5;
    private final ModelRenderer left_arm;
    private final ModelRenderer left1;
    private final ModelRenderer left2;
    private final ModelRenderer left3;
    private final ModelRenderer left4;
    private final ModelRenderer left5;
    private final ModelRenderer face;
    private final ModelRenderer body;
    private final ModelRenderer getPlantWholeBody;

    public BlazeWartModel() {
        texWidth = 128;
        texHeight = 128;

        total = new ModelRenderer(this);
        total.setPos(0.0F, 24.0F, 0.0F);


        right_arm = new ModelRenderer(this);
        right_arm.setPos(-5.0F, -0.75F, 0.0F);
        total.addChild(right_arm);
        right_arm.texOffs(89, 80).addBox(-4.0F, -3.5F, -1.0F, 4.0F, 1.0F, 2.0F, 0.0F, false);

        right1 = new ModelRenderer(this);
        right1.setPos(-4.0F, 0.0F, 0.0F);
        right_arm.addChild(right1);
        setRotationAngle(right1, 0.0F, 0.0F, 0.2618F);
        right1.texOffs(63, 78).addBox(-2.8706F, -3.517F, -1.0F, 3.0F, 1.0F, 2.0F, 0.0F, false);

        right2 = new ModelRenderer(this);
        right2.setPos(-3.0F, 0.0F, -0.5F);
        right1.addChild(right2);
        setRotationAngle(right2, 0.0F, 0.0F, 0.2618F);
        right2.texOffs(114, 113).addBox(-3.75F, -3.567F, -0.5F, 4.0F, 1.0F, 3.0F, 0.0F, false);

        right3 = new ModelRenderer(this);
        right3.setPos(-4.0F, 0.0F, 0.0F);
        right2.addChild(right3);
        setRotationAngle(right3, 0.0F, 0.0F, 0.2618F);
        right3.texOffs(64, 85).addBox(-4.6464F, -3.6464F, -0.5F, 5.0F, 1.0F, 3.0F, 0.0F, false);

        right4 = new ModelRenderer(this);
        right4.setPos(-4.0F, 0.0F, 0.5F);
        right3.addChild(right4);
        setRotationAngle(right4, 0.0F, 0.0F, 0.3927F);
        right4.texOffs(97, 72).addBox(-5.0F, -4.0F, -1.0F, 5.0F, 2.0F, 3.0F, 0.05F, false);

        right5 = new ModelRenderer(this);
        right5.setPos(-4.0F, 0.0F, 0.0F);
        right4.addChild(right5);
        setRotationAngle(right5, 0.0F, 0.0F, 0.5236F);
        right5.texOffs(14, 67).addBox(-4.0F, -5.0F, -2.0F, 4.0F, 4.0F, 5.0F, 0.0F, false);

        left_arm = new ModelRenderer(this);
        left_arm.setPos(5.0F, -0.75F, 0.0F);
        total.addChild(left_arm);
        setRotationAngle(left_arm, 0.0F, 3.1416F, 0.0F);
        left_arm.texOffs(73, 78).addBox(-4.0F, -3.5F, -1.0F, 4.0F, 1.0F, 2.0F, 0.0F, false);

        left1 = new ModelRenderer(this);
        left1.setPos(-4.0F, 0.0F, 0.0F);
        left_arm.addChild(left1);
        setRotationAngle(left1, 0.0F, 0.0F, 0.2618F);
        left1.texOffs(57, 123).addBox(-2.8706F, -3.517F, -1.0F, 3.0F, 1.0F, 2.0F, 0.0F, false);

        left2 = new ModelRenderer(this);
        left2.setPos(-3.0F, 0.0F, 0.0F);
        left1.addChild(left2);
        setRotationAngle(left2, 0.0F, 0.0F, 0.2618F);
        left2.texOffs(38, 74).addBox(-3.75F, -3.567F, -2.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);

        left3 = new ModelRenderer(this);
        left3.setPos(-4.0F, 0.0F, 0.0F);
        left2.addChild(left3);
        setRotationAngle(left3, 0.0F, 0.0F, 0.2618F);
        left3.texOffs(52, 74).addBox(-4.6464F, -3.6464F, -2.0F, 5.0F, 1.0F, 3.0F, 0.0F, false);

        left4 = new ModelRenderer(this);
        left4.setPos(-3.8232F, -0.1768F, -0.5F);
        left3.addChild(left4);
        setRotationAngle(left4, 0.0F, 0.0F, 0.3927F);
        left4.texOffs(83, 75).addBox(-5.0F, -4.0F, -1.5F, 5.0F, 2.0F, 3.0F, 0.05F, false);

        left5 = new ModelRenderer(this);
        left5.setPos(-4.0F, 0.0F, 0.0F);
        left4.addChild(left5);
        setRotationAngle(left5, 0.0F, 0.0F, 0.5236F);
        left5.texOffs(68, 69).addBox(-4.0F, -5.0F, -2.5F, 4.0F, 4.0F, 5.0F, 0.0F, false);

        face = new ModelRenderer(this);
        face.setPos(0.0F, -2.25F, -1.0F);
        total.addChild(face);
        face.texOffs(56, 116).addBox(0.5F, -8.0F, -6.5F, 4.0F, 5.0F, 1.0F, -0.45F, false);
        face.texOffs(0, 88).addBox(-5.5F, -8.0F, -6.5F, 4.0F, 5.0F, 1.0F, -0.45F, false);

        body = new ModelRenderer(this);
        body.setPos(-5.0F, -0.75F, 0.0F);
        total.addChild(body);
        body.texOffs(0, 83).addBox(-1.0F, -4.5F, -6.0F, 12.0F, 5.0F, 12.0F, 0.4F, false);
        body.texOffs(0, 100).addBox(-2.0F, -10.5F, -7.0F, 14.0F, 10.0F, 14.0F, 0.0F, false);
        body.texOffs(56, 114).addBox(-1.0F, -11.25F, -6.0F, 12.0F, 2.0F, 12.0F, 0.0F, false);

        getPlantWholeBody = new ModelRenderer(this);
        getPlantWholeBody.setPos(0.0F, 0.0F, 0.0F);

    }

    public void setupAnim(BlazeWartEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entity.getAttackTime() == 0) {
            this.left_arm.xRot = 0.0F;
            this.left_arm.yRot = 3.1416F;
            this.left_arm.zRot = 0.0F;
            this.right_arm.xRot = 0.0F;
            this.right_arm.yRot = 0.0F;
            this.right_arm.zRot = 0.0F;
        } else {
            int now;
            int tot;
            if (entity.getAttackTime() < 0) {
                now = entity.getAttackTime();
                tot = entity.getAttackCD();
                this.right_arm.xRot = AnimationUtil.getUpDown((float)now, (float)tot, 60.0F);
                this.right_arm.yRot = AnimationUtil.getUpDown((float)now, (float)tot, -60.0F);
            } else {
                now = -entity.getAttackTime();
                tot = entity.getAttackCD();
                this.left_arm.xRot = AnimationUtil.getUpDown((float)now, (float)tot, -60.0F);
                this.left_arm.yRot = -(3.1416F - AnimationUtil.getUpDown((float)now, (float)tot, 60.0F));
            }
        }

    }
    @Override
    public ModelRenderer getPlantWholeBody() {
        return this.total;
    }

    public EntityModel<BlazeWartEntity> getPlantModel() {
        return this;
    }
}
