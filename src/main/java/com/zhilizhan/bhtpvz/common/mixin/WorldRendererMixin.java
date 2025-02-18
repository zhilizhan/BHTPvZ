package com.zhilizhan.bhtpvz.common.mixin;

import com.zhilizhan.bhtpvz.BHTPvZ;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Objects;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {


    @Shadow private ClientWorld level;

    @Redirect(method = "renderSky", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/world/ClientWorld;getTimeOfDay(F)F",ordinal = 0))
    public float renderSkyA(ClientWorld instance, float v) {
        return this.getDatTime(v);
    }
    @Redirect(method = "renderSky", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/world/ClientWorld;getTimeOfDay(F)F",ordinal = 1))
    public float renderSkyB(ClientWorld instance, float v) {
        return this.getDatTime(v);
    }
    @Unique
    public float getDatTime(float fv) {
        PlayerEntity player = Minecraft.getInstance().player;
        if (player != null) {
            Biome biome = this.getLevel().getBiome(player.blockPosition());
            boolean flag = Objects.equals(biome.getRegistryName(), new ResourceLocation(BHTPvZ.MOD_ID, "night"));
            if (flag) {
                return 0.47F;
            }
        }
        return this.getLevel().getTimeOfDay(fv);
    }
    @Unique
    private World getLevel() {
        return this.level;
    }
}
