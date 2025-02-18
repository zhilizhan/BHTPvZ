package com.zhilizhan.bhtpvz.client.model.entity.plant.appease;

import com.hungteen.pvz.client.model.entity.plant.PlantShooterModel;
import com.zhilizhan.bhtpvz.common.entity.plant.appease.BeeShooterEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

import java.util.Optional;

public class BeeShooterModel extends PlantShooterModel<BeeShooterEntity> {
    private final ModelRenderer total;
    private final ModelRenderer body;
    private final ModelRenderer stick_r1;
    private final ModelRenderer head;
    private final ModelRenderer hair;
    private final ModelRenderer leafl_r1;
    private final ModelRenderer down;
    private final ModelRenderer n_r1;
    private final ModelRenderer w_r1;
    private final ModelRenderer e_r1;
    private final ModelRenderer s_r1;

    public BeeShooterModel() {
        texWidth = 64;
        texHeight = 64;

        total = new ModelRenderer(this);
        total.setPos(0.0F, 24.0F, 0.0F);


        body = new ModelRenderer(this);
        body.setPos(0.0F, 0.0F, 0.0F);
        total.addChild(body);


        stick_r1 = new ModelRenderer(this);
        stick_r1.setPos(0.0F, 0.0F, 0.0F);
        body.addChild(stick_r1);
        setRotationAngle(stick_r1, -0.0873F, 0.0F, 0.0F);
        stick_r1.texOffs(2, 33).addBox(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setPos(-1.0F, -1.0F, 0.0F);
        body.addChild(head);
        head.texOffs(32, 2).addBox(-1.0F, -16.0F, -7.0F, 4.0F, 4.0F, 3.0F, 0.0F, false);
        head.texOffs(46, 2).addBox(-2.0F, -17.0F, -8.0F, 6.0F, 6.0F, 2.0F, 0.0F, false);
        head.texOffs(0, 0).addBox(-4.0F, -21.0F, -4.0F, 10.0F, 10.0F, 12.0F, 0.0F, false);
        head.texOffs(42, 10).addBox(-1.0F, -11.0F, -1.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);

        hair = new ModelRenderer(this);
        hair.setPos(0.0F, -16.5F, 12.0F);
        head.addChild(hair);


        leafl_r1 = new ModelRenderer(this);
        leafl_r1.setPos(0.0F, 0.0F, 0.0F);
        hair.addChild(leafl_r1);
        setRotationAngle(leafl_r1, 0.0872F, 0.0F, 0.0F);
        leafl_r1.texOffs(10, 36).addBox(-1.0F, -1.5F, -5.0F, 3.0F, 3.0F, 6.0F, 0.0F, false);

        down = new ModelRenderer(this);
        down.setPos(0.0F, 0.0F, 0.0F);
        total.addChild(down);


        n_r1 = new ModelRenderer(this);
        n_r1.setPos(0.0F, 0.8F, 0.0F);
        down.addChild(n_r1);
        setRotationAngle(n_r1, -0.1745F, -0.7854F, 0.0F);
        n_r1.texOffs(6, 22).addBox(-2.0F, -1.0F, -7.0F, 4.0F, 1.0F, 6.0F, 0.0F, false);

        w_r1 = new ModelRenderer(this);
        w_r1.setPos(0.0F, 0.8F, 0.0F);
        down.addChild(w_r1);
        setRotationAngle(w_r1, 0.1745F, -0.7854F, -0.1745F);
        w_r1.texOffs(8, 29).addBox(1.0F, -1.0F, -2.0F, 6.0F, 1.0F, 4.0F, 0.0F, false);

        e_r1 = new ModelRenderer(this);
        e_r1.setPos(0.0F, 0.8F, 0.0F);
        down.addChild(e_r1);
        setRotationAngle(e_r1, -0.1745F, -0.7854F, 0.1745F);
        e_r1.texOffs(28, 29).addBox(-7.0F, -1.0F, -2.0F, 6.0F, 1.0F, 4.0F, 0.0F, false);

        s_r1 = new ModelRenderer(this);
        s_r1.setPos(0.0F, 0.8F, 0.0F);
        down.addChild(s_r1);
        setRotationAngle(s_r1, 0.1745F, -0.7854F, 0.0F);
        s_r1.texOffs(26, 22).addBox(-2.0F, -1.0F, 1.0F, 4.0F, 1.0F, 6.0F, 0.0F, false);
    }

    public Optional<ModelRenderer> getHeadModel() {
        return Optional.ofNullable(this.head);
    }

    public Optional<ModelRenderer> getBodyModel() {
        return Optional.ofNullable(this.body);
    }

    public ModelRenderer getPlantWholeBody() {
        return this.total;
    }
}
