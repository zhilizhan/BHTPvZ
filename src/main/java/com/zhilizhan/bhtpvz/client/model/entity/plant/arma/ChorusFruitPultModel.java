package com.zhilizhan.bhtpvz.client.model.entity.plant.arma;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.zhilizhan.bhtpvz.common.entity.plant.arma.ChorusFruitPultEntity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class ChorusFruitPultModel extends PVZPlantModel<ChorusFruitPultEntity> {
    private final ModelRenderer total;
    private final ModelRenderer chorusfruit;
    private final ModelRenderer dicoration;
    private final ModelRenderer pult;
    private final ModelRenderer out;
    private final ModelRenderer bullet;

    public ChorusFruitPultModel() {
        texWidth = 64;
        texHeight = 64;

        total = new ModelRenderer(this);
        total.setPos(0.0F, 24.0F, 0.0F);


        chorusfruit = new ModelRenderer(this);
        chorusfruit.setPos(0.0F, 0.0F, 0.0F);
        total.addChild(chorusfruit);
        chorusfruit.texOffs(0, 0).addBox(-3.5F, -8.0F, -4.5F, 8.0F, 8.0F, 8.0F, 0.0F, false);
        chorusfruit.texOffs(32, 24).addBox(-5.5F, -4.0F, -4.5F, 2.0F, 4.0F, 5.0F, 0.0F, false);
        chorusfruit.texOffs(32, 24).addBox(4.5F, -4.0F, -4.5F, 2.0F, 4.0F, 5.0F, 0.0F, false);
        chorusfruit.texOffs(0, 38).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        chorusfruit.texOffs(0, 28).addBox(-3.5F, -9.0F, -4.5F, 8.0F, 1.0F, 8.0F, 0.0F, false);

        dicoration = new ModelRenderer(this);
        dicoration.setPos(0.0F, 0.0F, 0.0F);
        chorusfruit.addChild(dicoration);
        dicoration.texOffs(0, 17).addBox(-4.0F, -8.0F, -5.0F, 9.0F, 1.0F, 9.0F, 0.0F, false);

        pult = new ModelRenderer(this);
        pult.setPos(0.0F, -9.0F, 0.0F);
        total.addChild(pult);
        setRotationAngle(pult, -0.4363F, 0.0F, 0.0F);
        pult.texOffs(33, 34).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 7.0F, 1.0F, 0.0F, false);

        out = new ModelRenderer(this);
        out.setPos(0.0F, -6.0F, -1.0F);
        pult.addChild(out);
        setRotationAngle(out, -1.309F, 0.0F, 0.0F);
        out.texOffs(0, 17).addBox(-1.0F, -5.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
        out.texOffs(29, 13).addBox(-3.0F, -11.0F, 0.0F, 6.0F, 6.0F, 4.0F, 0.0F, false);

        bullet = new ModelRenderer(this);
        bullet.setPos(0.0F, -9.0F, 1.0F);
        out.addChild(bullet);
        setRotationAngle(bullet, 1.5708F, 0.0F, 0.0F);
        bullet.texOffs(33, 0).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(ChorusFruitPultEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entity.getAttackTime() > 0) {
            float percent = 1 - entity.getAttackTime() * 1.0f / entity.getPultAnimTime();
            pult.xRot = (1.0f - MathHelper.abs(MathHelper.cos(percent * 3.14159f))) * 1.5f;
            this.bullet.visible = (percent < 0.5);
        } else {
            pult.xRot = MathHelper.sin(ageInTicks / 10) / 8;
            this.bullet.visible = true;
        }
    }

    @Override
    public ModelRenderer getPlantWholeBody() {
        return this.total;
    }
}
