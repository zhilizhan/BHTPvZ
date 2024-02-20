package com.zhilizhan.bhtpvz.common.mixin;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.PlayerModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = LivingEntityRenderer.class)
public abstract class LivingEntityRenderMixin<T extends LivingEntity, M extends EntityModel<T>> extends EntityRenderer<T> implements RenderLayerParent<T, M> {
    @Shadow
    private static float sleepDirectionToRotation(Direction facing) {
        return 0;
    }

    @Shadow protected abstract float getFlipDegrees(T livingEntity);

    @Shadow protected abstract boolean isShaking(T arg);

    protected LivingEntityRenderMixin(EntityRenderDispatcher arg) {
        super(arg);
    }
    /**
     * @author
     * @reason
     */
    @Overwrite
    protected void setupRotations(T entityLiving, PoseStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
        if (this.isShaking(entityLiving)) {
            rotationYaw += (float)(Math.cos((double)entityLiving.tickCount * 3.25) * Math.PI * 0.4000000059604645);
        }

        Pose pose = entityLiving.getPose();
        if (pose != Pose.SLEEPING) {
            matrixStack.mulPose(Vector3f.YP.rotationDegrees(180.0F - rotationYaw));
        }

        if (entityLiving.deathTime > 0) {
            float f = ((float)entityLiving.deathTime + partialTicks - 1.0F) / 20.0F * 1.6F;
            f = Mth.sqrt(f);
            if (f > 1.0F) {
                f = 1.0F;
            }
            if(entityLiving instanceof PVZPlantEntity && ((PVZPlantEntity) entityLiving).isNoAi()){
                f = 0.0F;
            }

            matrixStack.mulPose(Vector3f.ZP.rotationDegrees(f * this.getFlipDegrees(entityLiving)));
        } else if (entityLiving.isAutoSpinAttack()) {
            matrixStack.mulPose(Vector3f.XP.rotationDegrees(-90.0F - entityLiving.xRot));
            matrixStack.mulPose(Vector3f.YP.rotationDegrees(((float)entityLiving.tickCount + partialTicks) * -75.0F));
        } else if (pose == Pose.SLEEPING) {
            Direction direction = entityLiving.getBedOrientation();
            float f1 = direction != null ? sleepDirectionToRotation(direction) : rotationYaw;
            matrixStack.mulPose(Vector3f.YP.rotationDegrees(f1));
            matrixStack.mulPose(Vector3f.ZP.rotationDegrees(this.getFlipDegrees(entityLiving)));
            matrixStack.mulPose(Vector3f.YP.rotationDegrees(270.0F));
        } else if (entityLiving.hasCustomName() || entityLiving instanceof Player) {
            String s = ChatFormatting.stripFormatting(entityLiving.getName().getString());
            if (("Dinnerbone".equals(s) || "Grumm".equals(s)) && (!(entityLiving instanceof Player) || ((Player)entityLiving).isModelPartShown(PlayerModelPart.CAPE))) {
                matrixStack.translate(0.0, entityLiving.getBbHeight() + 0.1F, 0.0);
                matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180.0F));
            }
        }

    }

}
