package com.zhilizhan.bhtpvz.client.model.entity.plant.electric;

import com.hungteen.pvz.client.model.entity.plant.PlantShooterModel;
import com.zhilizhan.bhtpvz.common.entity.plant.electric.MagnifyingGrassEntity;
import net.minecraft.client.model.geom.ModelPart;

import java.util.Optional;

public class MagnifyingGrassModel extends PlantShooterModel<MagnifyingGrassEntity> {
    private final ModelPart total;
    private final ModelPart down;
    private final ModelPart group_r1;
    private final ModelPart group_r2;
    private final ModelPart group_r3;
    private final ModelPart group_r4;
    private final ModelPart body;
    private final ModelPart neck;
    private final ModelPart bone_r1;
    private final ModelPart head;
    private final ModelPart bone2_r1;
    private final ModelPart bone2_r2;
    private final ModelPart bone4_r1;

    public MagnifyingGrassModel() {
        texWidth = 32;
        texHeight = 32;

        total = new ModelPart(this);
        total.setPos(-0.1F, 16.0F, 0.5F);
        setRotationAngle(total, 0.0F, 3.1416F, 0.0F);


        down = new ModelPart(this);
        down.setPos(0.0F, 0.0F, 0.0F);
        total.addChild(down);


        group_r1 = new ModelPart(this);
        group_r1.setPos(3.7829F, 7.0449F, -0.5F);
        down.addChild(group_r1);
        setRotationAngle(group_r1, 0.0F, 0.0F, -0.2182F);
        group_r1.texOffs(13, 0).addBox(-3.5F, 0.0F, -1.5F, 7.0F, 0.0F, 3.0F, 0.0F, false);

        group_r2 = new ModelPart(this);
        group_r2.setPos(0.1F, 7.1F, -0.5F);
        down.addChild(group_r2);
        setRotationAngle(group_r2, 0.2618F, 0.0F, 0.0F);
        group_r2.texOffs(6, 22).addBox(-1.5F, 0.9F, 0.0716F, 3.0F, 0.0F, 5.0F, 0.0F, false);

        group_r3 = new ModelPart(this);
        group_r3.setPos(-3.5829F, 7.0449F, -0.5F);
        down.addChild(group_r3);
        setRotationAngle(group_r3, 0.0F, 0.0F, 0.2618F);
        group_r3.texOffs(-3, 27).addBox(-3.5F, 0.0F, -1.5F, 7.0F, 0.0F, 3.0F, 0.0F, false);

        group_r4 = new ModelPart(this);
        group_r4.setPos(0.1F, 7.1F, -0.5F);
        down.addChild(group_r4);
        setRotationAngle(group_r4, -0.2618F, 0.0F, 0.0F);
        group_r4.texOffs(-1, 22).addBox(-1.5F, 0.9F, -5.0716F, 3.0F, 0.0F, 5.0F, 0.0F, false);

        body = new ModelPart(this);
        body.setPos(0.0F, 0.0F, 0.0F);
        total.addChild(body);


        neck = new ModelPart(this);
        neck.setPos(0.0F, 0.0F, 0.0F);
        body.addChild(neck);


        bone_r1 = new ModelPart(this);
        bone_r1.setPos(0.1F, 8.0F, -0.5F);
        neck.addChild(bone_r1);
        setRotationAngle(bone_r1, 0.1309F, 0.0F, 0.0F);
        bone_r1.texOffs(18, 12).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        head = new ModelPart(this);
        head.setPos(0.15F, 0.0184F, -0.6946F);
        neck.addChild(head);
        setRotationAngle(head, -0.2618F, 0.0F, 0.0F);


        bone2_r1 = new ModelPart(this);
        bone2_r1.setPos(-1.45F, -0.463F, 1.1169F);
        head.addChild(bone2_r1);
        setRotationAngle(bone2_r1, 0.4363F, 0.0F, 0.0F);
        bone2_r1.texOffs(0, 2).addBox(-1.0F, -2.0244F, 0.0F, 2.0F, 3.0F, 0.0F, 0.0F, false);

        bone2_r2 = new ModelPart(this);
        bone2_r2.setPos(1.35F, -0.4849F, 1.164F);
        head.addChild(bone2_r2);
        setRotationAngle(bone2_r2, 0.4363F, 0.0F, 0.0F);
        bone2_r2.texOffs(0, 2).addBox(-1.0F, -2.0025F, 0.0F, 2.0F, 3.0F, 0.0F, 0.0F, false);

        bone4_r1 = new ModelPart(this);
        bone4_r1.setPos(-0.05F, -0.9874F, 0.2313F);
        head.addChild(bone4_r1);
        setRotationAngle(bone4_r1, 1.5708F, -1.1781F, -1.5708F);
        bone4_r1.texOffs(4, 3).addBox(-3.1F, -3.75F, -3.75F, 3.5F, 10.0F, 1.75F, 0.0F, false);
        bone4_r1.texOffs(4, 3).addBox(-3.1F, -3.75F, 1.75F, 3.5F, 10.0F, 1.75F, 0.0F, false);
        bone4_r1.texOffs(2, 1).addBox(-3.5F, -5.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
    }
    public Optional<ModelPart> getHeadModel() {
        return Optional.ofNullable(this.head);
    }

    public Optional<ModelPart> getBodyModel() {
        return Optional.ofNullable(this.body);
    }

    public ModelPart getPlantWholeBody() {
        return this.total;
    }
}
