package com.zhilizhan.bhtpvz.client.model.entity.plant.toxic;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.zhilizhan.bhtpvz.common.entity.plant.toxic.OriginShroomEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;

public class OriginShroomModel extends PVZPlantModel<OriginShroomEntity> {
    private final ModelPart total;
    private final ModelPart getPlantWholeBody;

    public OriginShroomModel() {
        texWidth = 128;
        texHeight = 128;

        total = new ModelPart(this);
        total.setPos(0.0F, 24.0F, 0.0F);
        total.texOffs(1, 105).addBox(-6.0F, -10.0F, -6.0F, 12.0F, 10.0F, 12.0F, 0.0F, false);
        total.texOffs(51, 101).addBox(-8.0F, -21.0F, -8.0F, 16.0F, 10.0F, 16.0F, 0.0F, false);
        total.texOffs(1, 88).addBox(-7.0F, -11.0F, -7.0F, 14.0F, 1.0F, 14.0F, 0.0F, false);
        total.texOffs(1, 70).addBox(-7.0F, -22.0F, -7.0F, 14.0F, 1.0F, 14.0F, 0.0F, false);
        total.texOffs(4, 63).addBox(-1.0F, -17.0F, -10.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        total.texOffs(16, 60).addBox(-2.0F, -18.0F, -13.0F, 4.0F, 4.0F, 3.0F, 0.0F, false);

        getPlantWholeBody = new ModelPart(this);
        getPlantWholeBody.setPos(0.0F, 0.0F, 0.0F);
    }

    public void setupAnim(OriginShroomEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    public ModelPart getPlantWholeBody() {
        return this.total;
    }

    public EntityModel<OriginShroomEntity> getPlantModel() {
        return this;
    }
}
