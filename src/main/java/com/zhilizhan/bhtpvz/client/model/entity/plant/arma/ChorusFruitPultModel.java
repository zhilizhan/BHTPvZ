package com.zhilizhan.bhtpvz.client.model.entity.plant.arma;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.zhilizhan.bhtpvz.common.entity.plant.arma.ChorusFruitPultEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class ChorusFruitPultModel extends PVZPlantModel<ChorusFruitPultEntity> {
    private final ModelPart total;
    private final ModelPart chorusfruit;
    private final ModelPart dicoration;
    private final ModelPart pult;
    private final ModelPart out;
    private final ModelPart bullet;

    public ChorusFruitPultModel() {
        texWidth = 64;
        texHeight = 64;

        total = new ModelPart(this);
        total.setPos(0.0F, 24.0F, 0.0F);


        chorusfruit = new ModelPart(this);
        chorusfruit.setPos(0.0F, 0.0F, 0.0F);
        total.addChild(chorusfruit);
        chorusfruit.texOffs(0, 29).addBox(-3.5F, -8.0F, -4.5F, 8.0F, 8.0F, 8.0F, 0.0F, false);
        chorusfruit.texOffs(9, 16).addBox(-5.5F, -4.0F, -4.5F, 2.0F, 4.0F, 5.0F, 0.0F, false);
        chorusfruit.texOffs(9, 16).addBox(4.5F, -4.0F, -4.5F, 2.0F, 4.0F, 5.0F, 0.0F, false);
        chorusfruit.texOffs(0, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        chorusfruit.texOffs(32, 20).addBox(-3.5F, -9.0F, -4.5F, 8.0F, 1.0F, 8.0F, 0.0F, false);

        dicoration = new ModelPart(this);
        dicoration.setPos(0.0F, 0.0F, 0.0F);
        chorusfruit.addChild(dicoration);
        dicoration.texOffs(0, 16).addBox(-4.0F, -8.0F, -5.0F, 9.0F, 1.0F, 9.0F, 0.0F, false);

        pult = new ModelPart(this);
        pult.setPos(0.0F, -9.0F, 0.0F);
        total.addChild(pult);
        setRotationAngle(pult, -0.4363F, 0.0F, 0.0F);
        pult.texOffs(8, 0).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 7.0F, 1.0F, 0.0F, false);

        out = new ModelPart(this);
        out.setPos(0.0F, -6.0F, -1.0F);
        pult.addChild(out);
        setRotationAngle(out, -1.309F, 0.0F, 0.0F);
        out.texOffs(0, 0).addBox(-1.0F, -5.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
        out.texOffs(42, 0).addBox(-3.0F, -11.0F, 0.0F, 6.0F, 6.0F, 4.0F, 0.0F, false);

        bullet = new ModelPart(this);
        bullet.setPos(0.0F, -9.0F, 1.0F);
        out.addChild(bullet);
        setRotationAngle(bullet, 1.5708F, 0.0F, 0.0F);
        bullet.texOffs(0, 45).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(ChorusFruitPultEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entity.getAttackTime() > 0) {
            float percent = 1 - entity.getAttackTime() * 1.0f / entity.getPultAnimTime();
            pult.xRot = (1.0f - Mth.abs(Mth.cos(percent * 3.14159f))) * 1.5f;
            this.bullet.visible = (percent < 0.5);
        } else {
            pult.xRot = Mth.sin(ageInTicks / 10) / 8;
            this.bullet.visible = true;
        }
    }

    @Override
    public ModelPart getPlantWholeBody() {
        return this.total;
    }
}
