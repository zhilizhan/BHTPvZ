package com.zhilizhan.bhtpvz.common.container;

import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.client.gui.screen.CardDecompositionScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(
        modid = BHTPvZ.MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.MOD
)
public class BHTPvZContainer {
    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, BHTPvZ.MOD_ID);

    public static final RegistryObject<ContainerType<CardDecompositionContainer>> CARD_DECOMPOSITION = CONTAINER_TYPES.register("card_decomposition", () -> {
        return IForgeContainerType.create((windowId, inv, data) -> new CardDecompositionContainer(windowId, inv.player, data.readBlockPos()));
    });


    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ScreenManager.register(CARD_DECOMPOSITION.get(), CardDecompositionScreen::new);
        });
    }

}
