package com.zhilizhan.bhtpvz.client.model.entity.plant.enforce;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.zhilizhan.bhtpvz.common.entity.plant.enforce.NutBowlingEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class NutBowlingModel extends PVZPlantModel<NutBowlingEntity> {

    private final ModelRenderer body;


    public NutBowlingModel() {
        texWidth = 128;
        texHeight = 128;

        body = new ModelRenderer(this);
        body.setPos(0.0F, 14.53F, 0.0F);
        body.texOffs(0, 42).addBox(-5.0F, 8.47F, -5.0F, 10.0F, 1.0F, 10.0F, 0.0F, false);
        body.texOffs(0, 0).addBox(-7.0F, -4.53F, -7.0F, 14.0F, 13.0F, 14.0F, 0.0F, false);
        body.texOffs(0, 27).addBox(-6.0F, -7.53F, -6.0F, 12.0F, 3.0F, 12.0F, 0.0F, false);
        body.texOffs(0, 57).addBox(-7.0F, -4.53F, -6.6F, 14.0F, 13.0F, 1.0F, 0.0F, false);
        body.texOffs(56, 0).addBox(-3.0F, -2.53F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
    }


    public void setupAnim(NutBowlingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entity.isAlive()) {
            this.body.xRot = ageInTicks/3;
        }
    }

    public ModelRenderer getPlantWholeBody() {
        return this.body;
    }

    public EntityModel<NutBowlingEntity> getPlantModel() {
        return this;
    }

}
