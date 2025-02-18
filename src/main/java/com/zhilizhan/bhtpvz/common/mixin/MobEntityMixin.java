package com.zhilizhan.bhtpvz.common.mixin;

import com.zhilizhan.bhtpvz.BHTPvZ;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Objects;
  
@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends Entity {
    public MobEntityMixin(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    @Redirect(method = "isSunBurnTick", at = @At(value = "INVOKE",target = "Lnet/minecraft/world/World;isDay()Z"))
    protected boolean isSunBurnTick(World instance) {
        Biome biome = instance.getBiome(this.blockPosition());
        boolean flag = Objects.equals(biome.getRegistryName(), new ResourceLocation(BHTPvZ.MOD_ID, "night"));

        return instance.isDay() && !flag;
    }
}
