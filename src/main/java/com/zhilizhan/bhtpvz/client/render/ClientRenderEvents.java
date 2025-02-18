package com.zhilizhan.bhtpvz.client.render;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.zhilizhan.bhtpvz.BHTPvZ;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BHTPvZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)

public class ClientRenderEvents {
    @SubscribeEvent
    public static void changeMobRender(RenderLivingEvent.Pre<LivingEntity, EntityModel<LivingEntity>> event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof PVZPlantEntity && ((PVZPlantEntity) entity).isNoAi()) {
            EntitySize entityDimensions = entity.getDimensions(entity.getPose());
            event.getMatrixStack().scale(1, (float) (0.1 / entityDimensions.height), 1);
        }
    }
}
