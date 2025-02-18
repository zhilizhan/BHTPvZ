package com.zhilizhan.bhtpvz.client.model.entity.bullet;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.zhilizhan.bhtpvz.common.entity.bullet.BeeEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class BeeEntityModel<T extends BeeEntity> extends EntityModel<T> {
    private final ModelRenderer bone;
    private final ModelRenderer body;
    private final ModelRenderer rightWing;
    private final ModelRenderer leftWing;

    private final ModelRenderer stinger;
    private final ModelRenderer leftAntenna;
    private final ModelRenderer rightAntenna;


    public BeeEntityModel() {
        super();
        this.texWidth = 64;
        this.texHeight = 64;
        this.bone = new ModelRenderer(this);
        this.bone.setPos(0.0F, 19.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, 0.0F, 0.0F);
        this.bone.addChild(this.body);
        this.body.addBox(-3.5F, -4.0F, -5.0F, 7.0F, 7.0F, 10.0F, 0.0F);
        this.stinger = new ModelRenderer(this, 26, 7);
        this.stinger.addBox(0.0F, -1.0F, 5.0F, 0.0F, 1.0F, 2.0F, 0.0F);
        this.body.addChild(this.stinger);
        this.leftAntenna = new ModelRenderer(this, 2, 0);
        this.leftAntenna.setPos(0.0F, -2.0F, -5.0F);
        this.leftAntenna.addBox(1.5F, -2.0F, -3.0F, 1.0F, 2.0F, 3.0F, 0.0F);
        this.rightAntenna = new ModelRenderer(this, 2, 3);
        this.rightAntenna.setPos(0.0F, -2.0F, -5.0F);
        this.rightAntenna.addBox(-2.5F, -2.0F, -3.0F, 1.0F, 2.0F, 3.0F, 0.0F);
        this.body.addChild(this.leftAntenna);
        this.body.addChild(this.rightAntenna);
        this.rightWing = new ModelRenderer(this, 0, 18);
        this.rightWing.setPos(-1.5F, -4.0F, -3.0F);
        this.rightWing.xRot = 0.0F;
        this.rightWing.yRot = -0.2618F;
        this.rightWing.zRot = 0.0F;
        this.bone.addChild(this.rightWing);
        this.rightWing.addBox(-9.0F, 0.0F, 0.0F, 9.0F, 0.0F, 6.0F, 0.001F);
        this.leftWing = new ModelRenderer(this, 0, 18);
        this.leftWing.setPos(1.5F, -4.0F, -3.0F);
        this.leftWing.xRot = 0.0F;
        this.leftWing.yRot = 0.2618F;
        this.leftWing.zRot = 0.0F;
        this.leftWing.mirror = true;
        this.bone.addChild(this.leftWing);
        this.leftWing.addBox(0.0F, 0.0F, 0.0F, 9.0F, 0.0F, 6.0F, 0.001F);
         }



    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.rightWing.xRot = 0.0F;
        this.leftAntenna.xRot = 0.0F;
        this.rightAntenna.xRot = 0.0F;
        this.bone.xRot = 0.0F;
        this.bone.y = 19.0F;
        boolean bl = entity.isOnGround() && entity.getDeltaMovement().lengthSqr() < 1.0E-7;
        float l;
        if (bl) {
            this.rightWing.yRot = -0.2618F;
            this.rightWing.zRot = 0.0F;
            this.leftWing.xRot = 0.0F;
            this.leftWing.yRot = 0.2618F;
            this.leftWing.zRot = 0.0F;
        } else {
            l = ageInTicks * 2.1F;
            this.rightWing.yRot = 0.0F;
            this.rightWing.zRot = MathHelper.cos(l) * 3.1415927F * 0.15F;
            this.leftWing.xRot = this.rightWing.xRot;
            this.leftWing.yRot = this.rightWing.yRot;
            this.leftWing.zRot = -this.rightWing.zRot;
            this.bone.xRot = 0.0F;
            this.bone.yRot = 0.0F;
            this.bone.zRot = 0.0F;
        }

    }

    @Override
    public void renderToBuffer(MatrixStack poseStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.bone.render(poseStack, buffer, packedLight, packedOverlay);
    }
}
