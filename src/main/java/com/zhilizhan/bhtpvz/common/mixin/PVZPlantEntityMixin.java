package com.zhilizhan.bhtpvz.common.mixin;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.magic.CoffeeBeanEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.item.spawn.card.PlantCardItem;
import com.zhilizhan.bhtpvz.common.entity.plant.defence.SteelPumpkinEntity;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = PVZPlantEntity.class,remap = false)
public abstract  class PVZPlantEntityMixin extends Entity {


    public PVZPlantEntityMixin(EntityType<?> arg, Level arg2) {
        super(arg, arg2);
    }


    @Overwrite
    public InteractionResult interactAt(Player player, Vec3 vec3d, InteractionHand hand) {
         if (!this.level.isClientSide) {
            ItemStack stack = player.getItemInHand(hand);
            if (stack.getItem() instanceof PlantCardItem) {
                PlantCardItem item = (PlantCardItem) stack.getItem();
                if (PlantCardItem.checkSunAndInteractEntity(player, this, item, stack, (type) -> {
                    return type == BHTPvZPlants.STEEL_PUMPKIN;
                }, (plantEntity) -> {
                    if (plantEntity instanceof SteelPumpkinEntity) {
                        ((SteelPumpkinEntity) plantEntity).setSolid(false);

                    }
                })){}else if (PlantCardItem.checkSunAndInteractEntity(player, this, item, stack, (type) -> {
                    return type == PVZPlants.COFFEE_BEAN;
                }, (plantEntity) -> {
                    if (plantEntity instanceof CoffeeBeanEntity) {
                        plantEntity.startRiding(this);
                    }
                })){}
            }
            return InteractionResult.SUCCESS;
        }


            return super.interactAt(player, vec3d, hand);
    }
}
//!PlantCardItem.checkSunAndHealPlant(player, plantEntity, item, stack) && !PlantCardItem.checkSunAndUpgradePlant(player, plantEntity, item, stack) && !PlantCardItem.checkSunAndOuterPlant(player, plantEntity, item, stack)&&