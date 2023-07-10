package com.zhilizhan.bhtpvz.client.model.entity.plant.enforce;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.zhilizhan.bhtpvz.common.entity.plant.enforce.RotateRadishEntity;
import net.minecraft.client.model.geom.ModelPart;

public class RotateRadishModel extends PVZPlantModel<RotateRadishEntity> {
    private final ModelPart total;
    private final ModelPart bone2;
    private final ModelPart bone;

    public RotateRadishModel() {
        texWidth = 64;
        texHeight = 64;

        total = new ModelPart(this);
        total.setPos(0.0F, 24.0F, 0.0F);
        total.texOffs(0, 0).addBox(-4.0F, -7.0F, -4.0F, 8.0F, 7.0F, 8.0F, 0.0F, false);
        total.texOffs(0, 21).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);
        total.texOffs(4, 4).addBox(-1.0F, 1.0F, 0.0F, 2.0F, 3.0F, 0.0F, 0.0F, false);
        total.texOffs(0, 0).addBox(0.0F, 1.0F, -1.0F, 0.0F, 3.0F, 2.0F, 0.0F, false);

        bone2 = new ModelPart(this);
        bone2.setPos(0.0F, -5.0F, 0.0F);
        total.addChild(bone2);
        setRotationAngle(bone2, 0.0F, 0.0F, -0.0873F);
        bone2.texOffs(0, 3).addBox(0.0F, -4.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.2F, false);
        bone2.texOffs(0, 15).addBox(-12.0F, -4.0F, -3.0F, 12.0F, 0.0F, 6.0F, 0.2F, false);

        bone = new ModelPart(this);
        bone.setPos(0.0F, -5.0F, 0.0F);
        total.addChild(bone);
        setRotationAngle(bone, 0.0F, 0.0F, 0.0873F);
        bone.texOffs(18, 0).addBox(0.0F, -4.0F, -3.0F, 12.0F, 0.0F, 6.0F, 0.2F, false);
        bone.texOffs(4, 0).addBox(0.0F, -4.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.2F, false);
    }

    @Override
    public void setupAnim(RotateRadishEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entity.isAlive()&&entity.getAttackTime()>0) {
            this.total.yRot = ageInTicks / 5.0F;
        }
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        total.render(matrixStack, buffer, packedLight, packedOverlay);
    }
    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
    @Override
    public ModelPart getPlantWholeBody() {
        return this.total;
    }
}
    