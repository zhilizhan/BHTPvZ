package com.zhilizhan.bhtpvz.client.model.entity.plant.electric;

import com.hungteen.pvz.client.model.entity.plant.PlantShooterModel;
import com.zhilizhan.bhtpvz.common.entity.plant.electric.MagnifyingGrassEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

import java.util.Optional;

public class MagnifyingGrassModel extends PlantShooterModel<MagnifyingGrassEntity> {
    private final ModelRenderer total;
    private final ModelRenderer head;
    private final ModelRenderer eye;
    private final ModelRenderer leaf1;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer leaf2;
    private final ModelRenderer cube_r4;
    private final ModelRenderer cube_r5;
    private final ModelRenderer cube_r6;
    private final ModelRenderer leaf3;
    private final ModelRenderer cube_r7;
    private final ModelRenderer cube_r8;
    private final ModelRenderer cube_r9;
    private final ModelRenderer leaf4;
    private final ModelRenderer cube_r10;
    private final ModelRenderer cube_r11;
    private final ModelRenderer cube_r12;

    public MagnifyingGrassModel() {
        texWidth = 64;
        texHeight = 64;

        total = new ModelRenderer(this);
        total.setPos(0.0F, 19.0F, 0.0F);
        setRotationAngle(total, 0.0F, 1.5708F, 0.0F);


        head = new ModelRenderer(this);
        head.setPos(0.0F, 0.0F, 0.0F);
        total.addChild(head);
        head.texOffs(0, 0).addBox(-2.5F, -8.0F, -5.5F, 5.0F, 8.0F, 11.0F, 0.01F, false);
        head.texOffs(24, 19).addBox(-1.0F, -13.0F, -2.0F, 2.0F, 18.0F, 4.0F, 0.01F, false);
        head.texOffs(0, 19).addBox(-2.0F, -11.0F, -4.0F, 4.0F, 14.0F, 8.0F, 0.01F, false);

        eye = new ModelRenderer(this);
        eye.setPos(0.0F, 0.0F, 0.0F);
        head.addChild(eye);
        eye.texOffs(0, 32).addBox(2.7F, -6.0F, -4.5F, 0.0F, 4.0F, 9.0F, 0.0F, false);

        leaf1 = new ModelRenderer(this);
        leaf1.setPos(0.0F, 0.0F, 0.0F);
        total.addChild(leaf1);


        cube_r1 = new ModelRenderer(this);
        cube_r1.setPos(0.0F, 5.0F, 0.0F);
        leaf1.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.8727F, -0.3054F, 0.0F);
        cube_r1.texOffs(26, 6).addBox(-3.0F, 2.55F, 3.05F, 4.0F, 0.0F, 6.0F, 0.0F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setPos(0.0F, 5.0F, 0.0F);
        leaf1.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, -0.3054F, 0.0F);
        cube_r2.texOffs(0, 19).addBox(-3.0F, -8.3F, 7.8F, 4.0F, 3.0F, 0.0F, 0.0F, false);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setPos(0.0F, 5.0F, 0.0F);
        leaf1.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.1745F, -0.3054F, 0.0F);
        cube_r3.texOffs(32, 20).addBox(-3.0F, 0.0F, 0.0F, 4.0F, 0.0F, 4.0F, 0.0F, false);

        leaf2 = new ModelRenderer(this);
        leaf2.setPos(0.0F, 0.0F, 0.0F);
        total.addChild(leaf2);


        cube_r4 = new ModelRenderer(this);
        cube_r4.setPos(0.0F, 5.0F, 0.0F);
        leaf2.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.8727F, 0.3054F, 0.0F);
        cube_r4.texOffs(23, 0).addBox(-1.0F, 2.55F, 3.05F, 4.0F, 0.0F, 6.0F, 0.0F, false);

        cube_r5 = new ModelRenderer(this);
        cube_r5.setPos(0.0F, 5.0F, 0.0F);
        leaf2.addChild(cube_r5);
        setRotationAngle(cube_r5, 0.0F, 0.3054F, 0.0F);
        cube_r5.texOffs(0, 6).addBox(-1.0F, -8.3F, 7.8F, 4.0F, 3.0F, 0.0F, 0.0F, false);

        cube_r6 = new ModelRenderer(this);
        cube_r6.setPos(0.0F, 5.0F, 0.0F);
        leaf2.addChild(cube_r6);
        setRotationAngle(cube_r6, 0.1745F, 0.3054F, 0.0F);
        cube_r6.texOffs(28, 16).addBox(-1.0F, 0.0F, 0.0F, 4.0F, 0.0F, 4.0F, 0.0F, false);

        leaf3 = new ModelRenderer(this);
        leaf3.setPos(0.0F, 0.0F, 0.0F);
        total.addChild(leaf3);


        cube_r7 = new ModelRenderer(this);
        cube_r7.setPos(0.0F, 5.0F, 0.0F);
        leaf3.addChild(cube_r7);
        setRotationAngle(cube_r7, 0.8727F, 2.8362F, 0.0F);
        cube_r7.texOffs(10, 19).addBox(-3.0F, 2.55F, 3.05F, 4.0F, 0.0F, 6.0F, 0.0F, false);

        cube_r8 = new ModelRenderer(this);
        cube_r8.setPos(0.0F, 5.0F, 0.0F);
        leaf3.addChild(cube_r8);
        setRotationAngle(cube_r8, 0.0F, 2.8362F, 0.0F);
        cube_r8.texOffs(0, 3).addBox(-3.0F, -8.3F, 7.8F, 4.0F, 3.0F, 0.0F, 0.0F, false);

        cube_r9 = new ModelRenderer(this);
        cube_r9.setPos(0.0F, 5.0F, 0.0F);
        leaf3.addChild(cube_r9);
        setRotationAngle(cube_r9, 0.1745F, 2.8362F, 0.0F);
        cube_r9.texOffs(28, 12).addBox(-3.0F, 0.0F, 0.0F, 4.0F, 0.0F, 4.0F, 0.0F, false);

        leaf4 = new ModelRenderer(this);
        leaf4.setPos(0.0F, 0.0F, 0.0F);
        total.addChild(leaf4);


        cube_r10 = new ModelRenderer(this);
        cube_r10.setPos(0.0F, 5.0F, 0.0F);
        leaf4.addChild(cube_r10);
        setRotationAngle(cube_r10, 0.8727F, -2.8362F, 0.0F);
        cube_r10.texOffs(15, 0).addBox(-1.0F, 2.55F, 3.05F, 4.0F, 0.0F, 6.0F, 0.0F, false);

        cube_r11 = new ModelRenderer(this);
        cube_r11.setPos(0.0F, 5.0F, 0.0F);
        leaf4.addChild(cube_r11);
        setRotationAngle(cube_r11, 0.0F, -2.8362F, 0.0F);
        cube_r11.texOffs(0, 0).addBox(-1.0F, -8.3F, 7.8F, 4.0F, 3.0F, 0.0F, 0.0F, false);

        cube_r12 = new ModelRenderer(this);
        cube_r12.setPos(0.0F, 5.0F, 0.0F);
        leaf4.addChild(cube_r12);
        setRotationAngle(cube_r12, 0.1745F, -2.8362F, 0.0F);
        cube_r12.texOffs(17, 6).addBox(-1.0F, 0.0F, 0.0F, 4.0F, 0.0F, 4.0F, 0.0F, false);
    }
    public Optional<ModelRenderer> getHeadModel() {
        return Optional.ofNullable(this.head);
    }

    public Optional<ModelRenderer> getBodyModel() {
        return this.getHeadModel();
    }

    public ModelRenderer getPlantWholeBody() {
        return this.total;
    }
}
