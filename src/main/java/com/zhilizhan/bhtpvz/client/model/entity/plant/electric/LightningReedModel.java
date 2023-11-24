package com.zhilizhan.bhtpvz.client.model.entity.plant.electric;


import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.utils.AnimationUtil;
import com.zhilizhan.bhtpvz.common.entity.plant.electric.LightningReedEntity;
import net.minecraft.client.model.geom.ModelPart;

import java.util.Optional;

public class LightningReedModel extends PVZPlantModel<LightningReedEntity> {
    private final ModelPart total;
    private final ModelPart body;
    private final ModelPart neck;
    private final ModelPart bone_r1;
    private final ModelPart head;
    private final ModelPart bone2_r1;
    private final ModelPart bone2_r2;
    private final ModelPart bone2_r3;
    private final ModelPart down;
    private final ModelPart group_r1;
    private final ModelPart group_r2;
    private final ModelPart group_r3;
    private final ModelPart group_r4;

    public LightningReedModel() {
        texWidth = 32;
        texHeight = 32;

        total = new ModelPart(this);
        total.setPos(-0.1F, 16.0F, 0.5F);
        setRotationAngle(total, 0.0F, 3.1416F, 0.0F);


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
        bone_r1.texOffs(18, 12).addBox(-0.5F, -9.0F, -0.5F, 1.0F, 9.1F, 1.0F, 0.0F, false);

        head = new ModelPart(this);
        head.setPos(0.1F, 8.0F, -0.5F);
        body.addChild(head);


        bone2_r1 = new ModelPart(this);
        bone2_r1.setPos(0.0F, -17.0F, 0.0F);
        head.addChild(bone2_r1);
        setRotationAngle(bone2_r1, 0.4363F, 0.0F, 0.0F);
        bone2_r1.texOffs(0, 0).addBox(-3.6F, -1.4F, -2.4F, 3.0F, 5.0F, 0.0F, 0.0F, false);
        bone2_r1.texOffs(0, 0).addBox(0.6F, -1.4F, -2.4F, 3.0F, 5.0F, 0.0F, 0.0F, false);

        bone2_r2 = new ModelPart(this);
        bone2_r2.setPos(0.0F, -18.0F, -5.5F);
        head.addChild(bone2_r2);
        setRotationAngle(bone2_r2, 0.4014F, 0.0F, 0.0F);
        bone2_r2.texOffs(18, 12).addBox(-0.5F, -3.0F, -0.4F, 1.0F, 4.0F, 1.0F, 0.0F, false);

        bone2_r3 = new ModelPart(this);
        bone2_r3.setPos(0.0F, -9.0F, 0.0F);
        head.addChild(bone2_r3);
        setRotationAngle(bone2_r3, 1.5708F, -1.1781F, -1.5708F);
        bone2_r3.texOffs(2, 1).addBox(-3.5F, -11.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        down = new ModelPart(this);
        down.setPos(0.0F, 0.0F, 0.0F);
        total.addChild(down);


        group_r1 = new ModelPart(this);
        group_r1.setPos(0.1F, 7.1F, -0.5F);
        down.addChild(group_r1);
        setRotationAngle(group_r1, 0.0F, 0.0F, -0.3927F);
        group_r1.texOffs(13, 0).addBox(0.0716F, 1.1481F, -1.5F, 5.0F, 0.0F, 3.0F, 0.0F, false);

        group_r2 = new ModelPart(this);
        group_r2.setPos(0.1F, 7.1F, -0.5F);
        down.addChild(group_r2);
        setRotationAngle(group_r2, 0.3927F, 0.0F, 0.0F);
        group_r2.texOffs(6, 22).addBox(-1.5F, 1.1481F, 0.0716F, 3.0F, 0.0F, 5.0F, 0.0F, false);

        group_r3 = new ModelPart(this);
        group_r3.setPos(0.1F, 7.1F, -0.5F);
        down.addChild(group_r3);
        setRotationAngle(group_r3, 0.0F, 0.0F, 0.3927F);
        group_r3.texOffs(-3, 27).addBox(-5.0716F, 1.1481F, -1.5F, 5.0F, 0.0F, 3.0F, 0.0F, false);

        group_r4 = new ModelPart(this);
        group_r4.setPos(0.1F, 7.1F, -0.5F);
        down.addChild(group_r4);
        setRotationAngle(group_r4, -0.3927F, 0.0F, 0.0F);
        group_r4.texOffs(-1, 22).addBox(-1.5F, 1.1481F, -5.0716F, 3.0F, 0.0F, 5.0F, 0.0F, false);
    }

    public void setupAnim(LightningReedEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getHeadModel().ifPresent((m) -> {
            m.xRot = 0.1F;
        });
        this.getBodyModel().ifPresent((m) -> {
            m.xRot = -0.1F;
        });
        boolean T;
        int tick;
        if (entity.isPlantInSuperMode()) {
            T = true;
            tick = entity.getSuperTime() % 10;
            if (tick >= 0) {
                this.getHeadModel().ifPresent((m) -> {
                    m.xRot = AnimationUtil.getUpDownUpDown((float)tick, 10.0F, this.getMaxRotAngle() / 5.0F) + 0.1F;
                });
                this.getBodyModel().ifPresent((m) -> {
                    m.xRot = AnimationUtil.getUpDownUpDown((float)tick, 10.0F, -this.getMaxRotAngle() / 5.0F) - 0.1F;
                });
            }
        } else {
            tick =  10 - entity.getAttackTime();
            if (tick >= 0) {
                this.getHeadModel().ifPresent((m) -> {
                    m.xRot = AnimationUtil.getUpDownUpDown((float)tick, 10.0F, -this.getMaxRotAngle() / 2.0F) + 0.1F;
                });
                this.getBodyModel().ifPresent((m) -> {
                    m.xRot = AnimationUtil.getUpDownUpDown((float)tick, 10.0F, this.getMaxRotAngle()) - 0.1F;
                });
            }
        }

    }

    public float getMaxRotAngle() {
        return 15.0F;
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
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
