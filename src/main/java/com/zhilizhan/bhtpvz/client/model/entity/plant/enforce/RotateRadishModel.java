package com.zhilizhan.bhtpvz.client.model.entity.plant.enforce;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.utils.EntityUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.zhilizhan.bhtpvz.common.entity.plant.enforce.RotateRadishEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class RotateRadishModel extends PVZPlantModel<RotateRadishEntity> {
    private final ModelRenderer total;
    private final ModelRenderer bone2;
    private final ModelRenderer bone;

    public RotateRadishModel() {
        texWidth = 64;
        texHeight = 64;

        total = new ModelRenderer(this);
        total.setPos(0.0F, 24.0F, 0.0F);
        total.texOffs(0, 0).addBox(-4.0F, -7.0F, -4.0F, 8.0F, 7.0F, 8.0F, 0.0F, false);
        total.texOffs(0, 21).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);
        total.texOffs(4, 4).addBox(-1.0F, 1.0F, 0.0F, 2.0F, 3.0F, 0.0F, 0.0F, false);
        total.texOffs(0, 0).addBox(0.0F, 1.0F, -1.0F, 0.0F, 3.0F, 2.0F, 0.0F, false);

        bone2 = new ModelRenderer(this);
        bone2.setPos(0.0F, -5.0F, 0.0F);
        total.addChild(bone2);
        setRotationAngle(bone2, 0.0F, 0.0F, -0.0873F);
        bone2.texOffs(0, 3).addBox(0.0F, -4.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.2F, false);
        bone2.texOffs(0, 15).addBox(-12.0F, -4.0F, -3.0F, 12.0F, 0.0F, 6.0F, 0.2F, false);

        bone = new ModelRenderer(this);
        bone.setPos(0.0F, -5.0F, 0.0F);
        total.addChild(bone);
        setRotationAngle(bone, 0.0F, 0.0F, 0.0873F);
        bone.texOffs(18, 0).addBox(0.0F, -4.0F, -3.0F, 12.0F, 0.0F, 6.0F, 0.2F, false);
        bone.texOffs(4, 0).addBox(0.0F, -4.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.2F, false);
    }

    @Override
    public void setupAnim(RotateRadishEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (EntityUtil.isEntityValid(entity)) {
            this.total.yRot = ageInTicks / 5.0F;
        }
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        total.render(matrixStack, buffer, packedLight, packedOverlay);
    }
    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
    @Override
    public ModelRenderer getPlantWholeBody() {
        return this.total;
    }
}
    