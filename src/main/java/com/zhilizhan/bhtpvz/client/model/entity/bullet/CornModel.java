package com.zhilizhan.bhtpvz.client.model.entity.bullet;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.zhilizhan.bhtpvz.common.entity.bullet.CornEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;

public class CornModel extends EntityModel<CornEntity> {
    private final ModelPart total;
    private final ModelPart corn;

    public CornModel() {
        texWidth = 64;
        texHeight = 64;

        total = new ModelPart(this);
        total.setPos(0.0F, 24.0F, 0.0F);


        corn = new ModelPart(this);
        corn.setPos(0.0F, -1.0F, -2.0F);
        total.addChild(corn);
        corn.texOffs(10, 32).addBox(-1.5F, -9.0F, 0.5F, 3.0F, 10.0F, 3.0F, 0.0F, false);
        corn.texOffs(4, 61).addBox(-1.3F, -9.3F, 0.8F, 2.3F, 0.3F, 2.3F, -0.25F, false);
    }
    public void setupAnim(CornEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.total.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

}
