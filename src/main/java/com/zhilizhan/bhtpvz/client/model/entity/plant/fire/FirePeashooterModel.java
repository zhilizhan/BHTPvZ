package com.zhilizhan.bhtpvz.client.model.entity.plant.fire;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.zhilizhan.bhtpvz.common.entity.plant.fire.FirePeashooterEntity;
import net.minecraft.client.model.geom.ModelPart;

public class FirePeashooterModel extends PVZPlantModel<FirePeashooterEntity> {
    private final ModelPart total;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart hair_r1;
    private final ModelPart down;
    private final ModelPart n_r1;
    private final ModelPart w_r1;
    private final ModelPart e_r1;
    private final ModelPart s_r1;

    public FirePeashooterModel() {
        texWidth = 64;
        texHeight = 64;

        total = new ModelPart(this);
        total.setPos(0.0F, 24.0F, 0.0F);


        body = new ModelPart(this);
        body.setPos(0.0F, 0.0F, 0.0F);
        total.addChild(body);
        setRotationAngle(body, -0.0873F, 0.0F, 0.0F);
        body.texOffs(0, 31).addBox(-1.0F, -12.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);

        head = new ModelPart(this);
        head.setPos(0.0F, -12.0F, 0.0F);
        body.addChild(head);
        setRotationAngle(head, 0.0873F, 0.0F, 0.0F);
        head.texOffs(30, 0).addBox(-2.0F, -5.0F, -8.0F, 4.0F, 4.0F, 3.0F, 0.0F, false);
        head.texOffs(44, 0).addBox(-3.0F, -6.0F, -9.0F, 6.0F, 6.0F, 2.0F, 0.0F, false);
        head.texOffs(0, 0).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, 0.0F, false);
        head.texOffs(40, 8).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);

        hair_r1 = new ModelPart(this);
        hair_r1.setPos(0.0F, -10.0F, 5.0F);
        head.addChild(hair_r1);
        setRotationAngle(hair_r1, -0.2618F, 0.0F, 0.0F);
        hair_r1.texOffs(28, 30).addBox(-5.0F, -3.0F, -7.0F, 10.0F, 7.0F, 8.0F, 0.0F, false);

        down = new ModelPart(this);
        down.setPos(0.0F, 0.0F, 0.0F);
        total.addChild(down);


        n_r1 = new ModelPart(this);
        n_r1.setPos(0.0F, 0.8F, 0.0F);
        down.addChild(n_r1);
        setRotationAngle(n_r1, -0.1745F, -0.7854F, 0.0F);
        n_r1.texOffs(4, 20).addBox(-2.0F, -1.0F, -7.0F, 4.0F, 1.0F, 6.0F, 0.0F, false);

        w_r1 = new ModelPart(this);
        w_r1.setPos(0.0F, 0.8F, 0.0F);
        down.addChild(w_r1);
        setRotationAngle(w_r1, 0.1745F, -0.7854F, -0.1745F);
        w_r1.texOffs(6, 27).addBox(1.0F, -1.0F, -2.0F, 6.0F, 1.0F, 4.0F, 0.0F, false);

        e_r1 = new ModelPart(this);
        e_r1.setPos(0.0F, 0.8F, 0.0F);
        down.addChild(e_r1);
        setRotationAngle(e_r1, -0.1745F, -0.7854F, 0.1745F);
        e_r1.texOffs(26, 27).addBox(-7.0F, -1.0F, -2.0F, 6.0F, 1.0F, 4.0F, 0.0F, false);

        s_r1 = new ModelPart(this);
        s_r1.setPos(0.0F, 0.8F, 0.0F);
        down.addChild(s_r1);
        setRotationAngle(s_r1, 0.1745F, -0.7854F, 0.0F);
        s_r1.texOffs(24, 20).addBox(-2.0F, -1.0F, 1.0F, 4.0F, 1.0F, 6.0F, 0.0F, false);
    }

    @Override
    public ModelPart getPlantWholeBody() {
        return this.total;
    }

    @Override
    public void setupAnim(FirePeashooterEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
}
