package com.zhilizhan.bhtpvz.client.model.entity.zombie.bhtpvz;

import com.zhilizhan.bhtpvz.common.entity.zombie.bhtpvz.SteelPumpkinZombieEntity;
import com.hungteen.pvz.client.model.entity.zombie.PVZZombieModel;
import net.minecraft.client.model.geom.ModelPart;

import java.util.Optional;

public class SteelPumpkinZombieModel extends PVZZombieModel<SteelPumpkinZombieEntity> {
    private final ModelPart total;
    private final ModelPart right_leg;
    private final ModelPart left_leg;
    private final ModelPart up;
    private final ModelPart body;
    private final ModelPart hammer;
    private final ModelPart left_hand;
    private final ModelPart right_hand;
    private final ModelPart head;
    private final ModelPart helmet;
    private final ModelPart ladder;
    private final ModelPart floor;
    private final ModelPart getZombieLeftHand;
    private final ModelPart getZombieRightHand;
    private final ModelPart getZombieLeftLeg;
    private final ModelPart getZombieRightLeg;
    private final ModelPart getZombieHead;
    private final ModelPart getZombieUpBody;
    private final ModelPart getZombieWholeBody;

    public SteelPumpkinZombieModel() {
        texWidth = 256;
        texHeight = 256;

        total = new ModelPart(this);
        total.setPos(0.0f, 24.0f, 0.0f);

        right_leg = new ModelPart(this);
        right_leg.setPos(-4.0f, -23.0f, 0.0f);
        total.addChild(right_leg);
        right_leg.texOffs(44, 0).addBox(-4.0f, -1.0f, -4.0f, 8.0f, 24.0f, 8.0f, 0.0f, false);

        left_leg = new ModelPart(this);
        left_leg.setPos(4.0f, -23.0f, 0.0f);
        total.addChild(left_leg);
        left_leg.texOffs(0, 0).addBox(-4.0f, -1.0f, -4.0f, 8.0f, 24.0f, 8.0f, 0.0f, false);

        up = new ModelPart(this);
        up.setPos(0.0f, 0.0f, 0.0f);
        total.addChild(up);

        body = new ModelPart(this);
        body.setPos(0.0f, -23.0f, 0.0f);
        up.addChild(body);
        body.texOffs(0, 41).addBox(-8.0f, -25.0f, -4.0f, 16.0f, 24.0f, 8.0f, 0.0f, false);

        hammer = new ModelPart(this);
        hammer.setPos(9.0f, -1.0f, 0.5f);
        body.addChild(hammer);
        setRotationAngle(hammer, 0.4363f, 0.0f, 0.0f);

        left_hand = new ModelPart(this);
        left_hand.setPos(8.0f, -48.0f, 0.0f);
        up.addChild(left_hand);
        setRotationAngle(left_hand, -0.8727f, 0.0f, 0.0f);
        left_hand.texOffs(96, 60).addBox(0.0f, 0.0f, -4.0f, 8.0f, 24.0f, 8.0f, 0.0f, false);

        right_hand = new ModelPart(this);
        right_hand.setPos(-8.0f, -48.0f, 0.0f);
        up.addChild(right_hand);
        setRotationAngle(right_hand, -0.8727f, 0.0f, 0.0f);
        right_hand.texOffs(96, 0).addBox(-8.0f, 0.0f, -4.0f, 8.0f, 24.0f, 8.0f, 0.0f, false);

        head = new ModelPart(this);
        head.setPos(0.0f, -48.0f, 0.0f);
        up.addChild(head);
        head.texOffs(16, 96).addBox(-8.0f, -16.0f, -8.0f, 16.0f, 16.0f, 16.0f, 0.0f, false);

        helmet = new ModelPart(this);
        helmet.setPos(0.0f, 19.0f, 0.0f);
        head.addChild(helmet);
        helmet.texOffs(196, 240).addBox(-6.0f, -38.0f, -9.0f, 15.0f, 1.0f, 15.0f, 0.0f, false);
        helmet.texOffs(158, 206).addBox(-16.0f, -36.0f, -16.0f, 32.0f, 16.0f, 1.0f, 0.0f, false);
        helmet.texOffs(190, 160).addBox(-16.0f, -36.0f, 15.0f, 32.0f, 16.0f, 1.0f, 0.0f, false);
        helmet.texOffs(161, 161).addBox(-16.0f, -36.0f, -15.0f, 1.0f, 16.0f, 30.0f, 0.0f, false);
        helmet.texOffs(194, 132).addBox(15.0f, -36.0f, -15.0f, 1.0f, 16.0f, 30.0f, 0.0f, false);
        helmet.texOffs(128, 130).addBox(-16.0f, -20.0f, -16.0f, 32.0f, 1.0f, 32.0f, 0.0f, false);
        helmet.texOffs(131, 130).addBox(-15.0f, -37.0f, -15.0f, 30.0f, 1.0f, 30.0f, 0.0f, false);
        helmet.texOffs(167, 244).addBox(-2.0f, -42.5f, -5.0f, 7.0f, 5.0f, 7.0f, 0.0f, false);

        ladder = new ModelPart(this);
        ladder.setPos(0.0f, -38.0f, -20.0f);
        up.addChild(ladder);
        setRotationAngle(ladder, 0.2182f, 0.0f, 0.0f);
        ladder.texOffs(15, 202).addBox(-12.0f, -22.0f, -2.0f, 3.0f, 50.0f, 4.0f, 0.1f, false);
        ladder.texOffs(0, 202).addBox(9.0f, -22.0f, -2.0f, 3.0f, 50.0f, 4.0f, 0.1f, false);
        ladder.texOffs(0, 194).addBox(8.0f, 28.0f, -3.0f, 5.0f, 1.0f, 6.0f, 0.0f, false);
        ladder.texOffs(0, 185).addBox(-13.0f, 28.0f, -3.0f, 5.0f, 1.0f, 6.0f, 0.0f, false);

        floor = new ModelPart(this);
        floor.setPos(0.0f, 0.0f, 0.0f);
        ladder.addChild(floor);
        floor.texOffs(31, 250).addBox(-9.0f, 20.0f, -2.0f, 18.0f, 2.0f, 4.0f, 0.0f, false);
        floor.texOffs(31, 242).addBox(-9.0f, 12.0f, -2.0f, 18.0f, 2.0f, 4.0f, 0.0f, false);
        floor.texOffs(30, 234).addBox(-9.0f, 4.0f, -2.0f, 18.0f, 2.0f, 4.0f, 0.0f, false);
        floor.texOffs(32, 225).addBox(-9.0f, -4.0f, -2.0f, 18.0f, 2.0f, 4.0f, 0.0f, false);
        floor.texOffs(30, 217).addBox(-9.0f, -12.0f, -2.0f, 18.0f, 2.0f, 4.0f, 0.0f, false);

        getZombieLeftHand = new ModelPart(this);
        getZombieLeftHand.setPos(0.0f, 0.0f, 0.0f);

        getZombieRightHand = new ModelPart(this);
        getZombieRightHand.setPos(0.0f, 0.0f, 0.0f);

        getZombieLeftLeg = new ModelPart(this);
        getZombieLeftLeg.setPos(0.0f, 0.0f, 0.0f);

        getZombieRightLeg = new ModelPart(this);
        getZombieRightLeg.setPos(0.0f, 0.0f, 0.0f);

        getZombieHead = new ModelPart(this);
        getZombieHead.setPos(0.0f, 0.0f, 0.0f);

        getZombieUpBody = new ModelPart(this);
        getZombieUpBody.setPos(0.0f, 0.0f, 0.0f);

        getZombieWholeBody = new ModelPart(this);
        getZombieWholeBody.setPos(0.0f, 0.0f, 0.0f);
    }

    @Override
    public void updateFreeParts(SteelPumpkinZombieEntity entity) {
        super.updateFreeParts(entity);
        final boolean hasLadder = entity.hasMetal();
        this.ladder.visible = hasLadder;
        this.isLeftHandFree = ! hasLadder;
        this.isRightHandFree = ! hasLadder;
    }

    @Override
    public void refreshAnim() {
        super.refreshAnim();
        this.right_hand.xRot = -0.8727f;
        this.left_hand.xRot = -0.8727f;
    }

    @Override
    public Optional<ModelPart> getHandDefence() {
        return Optional.ofNullable(this.ladder);
    }

    @Override
    public ModelPart getZombieLeftHand() {
        return this.left_hand;
    }

    @Override
    public ModelPart getZombieRightHand() {
        return this.right_hand;
    }

    @Override
    public ModelPart getZombieLeftLeg() {
        return this.left_leg;
    }

    @Override
    public ModelPart getZombieRightLeg() {
        return this.right_leg;
    }

    @Override
    public ModelPart getZombieHead() {
        return this.head;
    }

    @Override
    public ModelPart getZombieUpBody() {
        return this.up;
    }

    @Override
    public ModelPart getZombieWholeBody() {
        return this.total;
    }
}
