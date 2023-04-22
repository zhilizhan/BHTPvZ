package com.zhilizhan.bhtpvz.client.model.entity.plant.assist;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.zhilizhan.bhtpvz.common.entity.plant.assist.WaterPotEntity;
import net.minecraft.client.model.geom.ModelPart;

public class WaterPotModel extends PVZPlantModel<WaterPotEntity> {
       private final ModelPart total;
       private final ModelPart body;
       private final ModelPart bone;
       private final ModelPart bone2;

       public WaterPotModel() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.total = new ModelPart(this);
        this.total.setPos(0.0F, 24.0F, 0.0F);
        this.total.texOffs(0, 53).addBox(-5.0F, -1.0F, -5.0F, 10.0F, 1.0F, 10.0F, 0.0F, false);
        this.total.texOffs(0, 50).addBox(-6.0F, -3.0F, -6.0F, 12.0F, 2.0F, 12.0F, 0.0F, false);
        this.total.texOffs(0, 47).addBox(-7.0F, -6.0F, -7.0F, 14.0F, 3.0F, 14.0F, 0.0F, false);
        this.total.texOffs(0, 0).addBox(-8.0F, -12.0F, -8.0F, 16.0F, 6.0F, 16.0F, 0.0F, false);
        this.total.texOffs(0, 48).addBox(-8.0F, -13.0F, -8.0F, 1.0F, 1.0F, 15.0F, 0.0F, false);
        this.total.texOffs(2, 31).addBox(7.0F, -13.0F, -8.0F, 1.0F, 1.0F, 15.0F, 0.0F, false);
        this.total.texOffs(1, 32).addBox(-7.0F, -13.0F, -8.0F, 14.0F, 1.0F, 1.0F, 0.0F, false);
        this.total.texOffs(2, 36).addBox(-8.0F, -13.0F, 7.0F, 16.0F, 1.0F, 1.0F, 0.0F, false);
        this.body = new ModelPart(this);
        this.body.setPos(1.0F, -11.5F, 5.5F);
        this.total.addChild(this.body);
        this.setRotationAngle(this.body, -0.1309F, 0.0F, -0.3054F);
        this.body.texOffs(55, 48).addBox(-1.0F, -5.0F, 0.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);
        this.bone = new ModelPart(this);
        this.bone.setPos(0.0F, -4.0F, 0.0F);
        this.body.addChild(this.bone);
        this.setRotationAngle(this.bone, 0.0F, 0.0F, -0.6109F);
        this.bone.texOffs(55, 55).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, -0.1F, false);
        this.bone2 = new ModelPart(this);
        this.bone2.setPos(-0.25F, -3.75F, 0.0F);
        this.body.addChild(this.bone2);
        this.setRotationAngle(this.bone2, 0.0F, 0.0F, 0.6545F);
        this.bone2.texOffs(55, 44).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, -0.1F, false);
        }


    public void setupAnim(WaterPotEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }






public ModelPart getPlantWholeBody() {
        return this.total;
        }
}
