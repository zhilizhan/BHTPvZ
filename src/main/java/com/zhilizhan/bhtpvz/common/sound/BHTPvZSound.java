package com.zhilizhan.bhtpvz.common.sound;

import com.zhilizhan.bhtpvz.BHTPvZ;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = BHTPvZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BHTPvZSound {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, BHTPvZ.MOD_ID);
    public static final RegistryObject<SoundEvent> BUZZER = registerSound("buzzer");
    public static final RegistryObject<SoundEvent> POOL = registerSound("pool");
    public static final RegistryObject<SoundEvent> BOWLING_MUSIC = registerSound("bowling_music");

    private static RegistryObject<SoundEvent> registerSound(String name) {
        return SOUNDS.register(name, () -> {
            return new SoundEvent(BHTPvZ.prefix(name));
        });
    }
}
