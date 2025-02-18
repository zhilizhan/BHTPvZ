package com.zhilizhan.bhtpvz.client.model.entity.plant.appease;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.zhilizhan.bhtpvz.common.entity.plant.appease.PeaPodEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

;

public class PeaPodModel extends PVZPlantModel<PeaPodEntity> {
    private final ModelRenderer total;
    private final ModelRenderer pod;
    private final ModelRenderer pod_r1;
    private final ModelRenderer podright;
    private final ModelRenderer podright_r1;
    private final ModelRenderer head2;
    private final ModelRenderer podleft;
    private final ModelRenderer podleft_r1;
    private final ModelRenderer head3;
    private final ModelRenderer head;
    private final ModelRenderer head4;
    private final ModelRenderer head5;
    private final ModelRenderer down;
    private final ModelRenderer n_r1;
    private final ModelRenderer w_r1;
    private final ModelRenderer e_r1;
    private final ModelRenderer s_r1;
    private final ModelRenderer bone;
    private final ModelRenderer getPlantWholeBody;

    public PeaPodModel() {
        texWidth = 128;
        texHeight = 128;

        total = new ModelRenderer(this);
        total.setPos(0.0F, 24.0F, 0.0F);


        pod = new ModelRenderer(this);
        pod.setPos(0.0F, 1.0F, 0.0F);
        total.addChild(pod);
        pod.texOffs(26, 43).addBox(-5.0F, -16.5F, -2.0F, 1.0F, 14.0F, 5.0F, 0.0F, false);
        pod.texOffs(2, 64).addBox(-4.0F, -16.5F, 2.0F, 8.0F, 14.0F, 1.0F, 0.0F, false);
        pod.texOffs(2, 79).addBox(-4.0F, -2.95F, -1.0F, 8.0F, 1.0F, 3.0F, 0.0F, false);
        pod.texOffs(40, 45).addBox(4.0F, -16.5F, -2.0F, 1.0F, 14.0F, 5.0F, 0.0F, false);
        pod.texOffs(52, 28).addBox(-3.5F, -26.0F, -2.5F, 7.0F, 1.0F, 2.0F, 0.0F, false);
        pod.texOffs(74, 2).addBox(-0.05F, -31.0F, -3.5F, 0.05F, 5.0F, 4.0F, 0.0F, false);
        pod.texOffs(35, 13).addBox(-5.0F, -3.0F, -2.5F, 10.0F, 1.0F, 2.0F, 0.0F, false);
        pod.texOffs(58, 19).addBox(3.5F, -25.0F, -2.55F, 1.0F, 9.0F, 2.0F, 0.0F, false);
        pod.texOffs(58, 19).addBox(-4.5F, -25.0F, -2.55F, 1.0F, 9.0F, 2.0F, 0.0F, false);
        pod.texOffs(58, 19).addBox(-5.5F, -17.0F, -2.55F, 1.0F, 15.0F, 2.0F, 0.0F, false);
        pod.texOffs(58, 19).addBox(4.5F, -17.0F, -2.55F, 1.0F, 15.0F, 2.0F, 0.0F, false);
        pod.texOffs(10, 43).addBox(-3.0F, -25.5F, -1.0F, 6.0F, 1.0F, 4.0F, 0.0F, false);
        pod.texOffs(2, 20).addBox(-4.0F, -15.5F, 3.05F, 8.0F, 12.0F, 1.0F, 0.0F, false);
        pod.texOffs(7, 50).addBox(3.0F, -24.9F, -2.0F, 1.0F, 8.4F, 5.0F, 0.0F, false);
        pod.texOffs(46, 14).addBox(-4.0F, -24.9F, -2.0F, 1.0F, 8.4F, 5.0F, 0.0F, false);
        pod.texOffs(2, 21).addBox(-3.0F, -23.5F, 3.0F, 6.0F, 6.0F, 1.0F, 0.0F, false);
        pod.texOffs(2, 23).addBox(-3.0F, -14.0F, 4.0F, 6.0F, 9.0F, 1.0F, 0.0F, false);

        pod_r1 = new ModelRenderer(this);
        pod_r1.setPos(0.0F, -18.0F, 2.5F);
        pod.addChild(pod_r1);
        setRotationAngle(pod_r1, 0.0F, 3.1416F, 0.0F);
        pod_r1.texOffs(48, 64).addBox(-3.0F, -6.5F, -0.5F, 6.0F, 9.0F, 1.0F, 0.0F, false);

        podright = new ModelRenderer(this);
        podright.setPos(-5.0F, -3.5F, 0.0F);
        pod.addChild(podright);
        setRotationAngle(podright, 0.0F, 0.1309F, -0.0873F);
        podright.texOffs(37, 73).addBox(-8.05F, -8.0F, -2.0F, 1.0F, 9.0F, 5.0F, 0.0F, false);
        podright.texOffs(4, 84).addBox(-7.05F, -8.0F, 2.0F, 6.0F, 9.0F, 1.0F, 0.0F, false);
        podright.texOffs(49, 73).addBox(-1.05F, -8.0F, -2.0F, 1.0F, 9.0F, 5.0F, 0.0F, false);
        podright.texOffs(11, 44).addBox(-7.0F, -8.5F, -1.0F, 6.0F, 1.0F, 3.0F, 0.0F, false);
        podright.texOffs(2, 22).addBox(-7.0F, -7.0F, 3.0F, 6.0F, 7.0F, 1.0F, 0.0F, false);
        podright.texOffs(52, 28).addBox(-7.5F, -9.0F, -2.5F, 7.0F, 1.0F, 2.0F, 0.0F, false);
        podright.texOffs(21, 15).addBox(-7.5F, 0.5F, -2.5F, 7.0F, 1.0F, 2.0F, 0.0F, false);
        podright.texOffs(58, 20).addBox(-8.5F, -8.5F, -2.55F, 1.0F, 10.0F, 2.0F, 0.0F, false);
        podright.texOffs(58, 21).addBox(-0.5F, -8.5F, -2.55F, 1.0F, 10.0F, 2.0F, 0.0F, false);
        podright.texOffs(2, 79).addBox(-8.0F, 0.55F, -1.0F, 8.0F, 1.0F, 3.0F, 0.0F, false);

        podright_r1 = new ModelRenderer(this);
        podright_r1.setPos(-4.0F, -13.5F, -2.5F);
        podright.addChild(podright_r1);
        setRotationAngle(podright_r1, 0.0398F, -0.1685F, 0.0456F);
        podright_r1.texOffs(74, 2).addBox(-0.05F, 0.0F, -1.0F, 0.05F, 5.0F, 4.0F, 0.0F, false);

        head2 = new ModelRenderer(this);
        head2.setPos(4.0F, 1.5F, 0.0F);
        podright.addChild(head2);
        head2.texOffs(84, 33).addBox(-11.5F, -8.0F, -4.0F, 7.0F, 7.0F, 7.0F, 0.0F, false);
        head2.texOffs(98, 38).addBox(-8.75F, -3.75F, -5.0F, 1.5F, 1.5F, 1.0F, 0.0F, false);
        head2.texOffs(120, 31).addBox(-9.5F, -4.5F, -6.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);

        podleft = new ModelRenderer(this);
        podleft.setPos(5.0F, -3.5F, 0.0F);
        pod.addChild(podleft);
        setRotationAngle(podleft, 0.0F, -0.1745F, 0.0873F);
        podleft.texOffs(2, 79).addBox(0.0F, 0.55F, -1.0F, 8.0F, 1.0F, 3.0F, 0.0F, false);
        podleft.texOffs(2, 46).addBox(0.5F, -8.5F, -0.5F, 7.0F, 1.0F, 3.0F, 0.0F, false);
        podleft.texOffs(2, 22).addBox(1.0F, -7.0F, 3.0F, 6.0F, 7.0F, 1.0F, 0.0F, false);
        podleft.texOffs(22, 15).addBox(0.5F, 0.5F, -2.5F, 7.0F, 1.0F, 2.0F, 0.0F, false);
        podleft.texOffs(58, 21).addBox(7.5F, -8.5F, -2.55F, 1.0F, 10.0F, 2.0F, 0.0F, false);
        podleft.texOffs(52, 28).addBox(0.5F, -9.5F, -2.5F, 7.0F, 1.0F, 2.0F, 0.0F, false);
        podleft.texOffs(58, 20).addBox(-0.5F, -8.5F, -2.55F, 1.0F, 10.0F, 2.0F, 0.0F, false);
        podleft.texOffs(37, 73).addBox(-0.05F, -7.5F, -2.0F, 1.0F, 8.5F, 5.0F, 0.0F, false);
        podleft.texOffs(4, 84).addBox(0.95F, -7.5F, 2.0F, 6.0F, 8.5F, 1.0F, 0.0F, false);
        podleft.texOffs(49, 73).addBox(6.95F, -7.5F, -2.0F, 1.0F, 8.5F, 5.0F, 0.0F, false);

        podleft_r1 = new ModelRenderer(this);
        podleft_r1.setPos(4.0F, -13.5F, -2.5F);
        podleft.addChild(podleft_r1);
        setRotationAngle(podleft_r1, -0.0015F, 0.002F, -0.0208F);
        podleft_r1.texOffs(74, 2).addBox(-0.05F, -1.0F, -1.0F, 0.05F, 5.0F, 4.0F, 0.0F, false);

        head3 = new ModelRenderer(this);
        head3.setPos(-6.0F, 1.5F, 0.0F);
        podleft.addChild(head3);
        head3.texOffs(84, 52).addBox(6.5F, -8.0F, -4.0F, 7.0F, 7.0F, 7.0F, 0.0F, false);
        head3.texOffs(98, 57).addBox(9.25F, -3.75F, -5.0F, 1.5F, 1.5F, 1.0F, 0.0F, false);
        head3.texOffs(120, 50).addBox(8.5F, -4.5F, -6.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setPos(-1.0F, -2.0F, 0.0F);
        pod.addChild(head);
        head.texOffs(84, 68).addBox(-2.5F, -8.0F, -5.0F, 7.0F, 7.0F, 7.0F, 0.0F, false);
        head.texOffs(98, 73).addBox(0.25F, -3.75F, -6.0F, 1.5F, 1.5F, 1.0F, 0.0F, false);
        head.texOffs(120, 66).addBox(-0.5F, -4.5F, -7.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);

        head5 = new ModelRenderer(this);
        head5.setPos(-1.0F, -2.0F, 0.0F);
        pod.addChild(head5);
        head5.texOffs(84, 114).addBox(-2.5F, -23.0F, -4.0F, 7.0F, 7.0F, 7.0F, 0.0F, false);
        head5.texOffs(98, 119).addBox(0.25F, -18.75F, -5.0F, 1.5F, 1.5F, 1.0F, 0.0F, false);
        head5.texOffs(120, 112).addBox(-0.5F, -19.5F, -6.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);

        head4 = new ModelRenderer(this);
        head4.setPos(-1.0F, -2.0F, 0.0F);
        pod.addChild(head4);
        head4.texOffs(84, 89).addBox(-2.5F, -15.5F, -4.0F, 7.0F, 7.0F, 7.0F, 0.0F, false);
        head4.texOffs(98, 94).addBox(0.25F, -11.25F, -5.0F, 1.5F, 1.5F, 1.0F, 0.0F, false);
        head4.texOffs(120, 87).addBox(-0.5F, -12.0F, -6.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);

        down = new ModelRenderer(this);
        down.setPos(0.0F, 0.0F, 0.0F);
        total.addChild(down);


        n_r1 = new ModelRenderer(this);
        n_r1.setPos(0.0F, 0.8F, 0.0F);
        down.addChild(n_r1);
        setRotationAngle(n_r1, 0.0873F, -0.7854F, 0.0F);
        n_r1.texOffs(2, 18).addBox(-2.0F, -2.3F, -8.0F, 4.0F, 1.0F, 8.0F, 0.0F, false);

        w_r1 = new ModelRenderer(this);
        w_r1.setPos(0.0F, 0.8F, 0.0F);
        down.addChild(w_r1);
        setRotationAngle(w_r1, -0.0437F, -0.7854F, 0.1309F);
        w_r1.texOffs(6, 27).addBox(0.0F, -2.3F, -2.0F, 8.0F, 1.0F, 4.0F, 0.0F, false);

        e_r1 = new ModelRenderer(this);
        e_r1.setPos(0.0F, 0.8F, 0.0F);
        down.addChild(e_r1);
        setRotationAngle(e_r1, 0.1309F, -0.7854F, -0.0873F);
        e_r1.texOffs(26, 27).addBox(-8.0F, -2.3F, -2.0F, 8.0F, 1.0F, 4.0F, 0.0F, false);

        s_r1 = new ModelRenderer(this);
        s_r1.setPos(0.0F, 0.8F, 0.0F);
        down.addChild(s_r1);
        setRotationAngle(s_r1, -0.0437F, -0.7854F, -0.0873F);
        s_r1.texOffs(22, 18).addBox(-2.0F, -2.3F, 0.0F, 4.0F, 1.0F, 8.0F, 0.0F, false);

        bone = new ModelRenderer(this);
        bone.setPos(0.0F, 0.0F, 0.0F);
        down.addChild(bone);


        getPlantWholeBody = new ModelRenderer(this);
        getPlantWholeBody.setPos(0.0F, 0.0F, 0.0F);

    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }


    public void setupAnim(PeaPodEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      //  this.right.zRot = -0.2F + 0.4F * Mth.sin(ageInTicks * 0.2F);
       // this.left.zRot = 0.2F - 0.4F * Mth.sin(ageInTicks * 0.2F);
        // this.leaf.zRot = 0.4F - 0.3F * Mth.sin(ageInTicks * 0.1F);

        this.head.visible=entity.getCount()>=1;
        this.head2.visible=entity.getCount()>=2;
        this.head3.visible=entity.getCount()>=3;
        this.head4.visible=entity.getCount()>=4;
        this.head5.visible=entity.getCount()>=5;

    }

    @Override
    public void renderToBuffer(MatrixStack poseStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        total.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelRenderer getPlantWholeBody() {
        return total;
    }
}
