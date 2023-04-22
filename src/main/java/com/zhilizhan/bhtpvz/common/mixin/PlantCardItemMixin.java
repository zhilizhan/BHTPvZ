package com.zhilizhan.bhtpvz.common.mixin;


import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.types.IPAZType;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.block.plants.LilyPadBlock;
import com.hungteen.pvz.common.block.special.FlowerPotBlock;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.item.spawn.card.ImitaterCardItem;
import com.hungteen.pvz.common.item.spawn.card.PlantCardItem;
import com.hungteen.pvz.common.item.spawn.card.SummonCardItem;
import com.hungteen.pvz.utils.ConfigUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import com.zhilizhan.bhtpvz.common.block.BHTPvZBlocks;
import com.zhilizhan.bhtpvz.common.block.WaterPotBlock;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;
import java.util.function.Predicate;

import static com.hungteen.pvz.common.item.spawn.card.PlantCardItem.*;

@Mixin(value = PlantCardItem.class,remap = false)
public abstract class PlantCardItemMixin extends SummonCardItem{


    public PlantCardItemMixin(IPAZType type, boolean isEnjoyCard) {
        super(type, isEnjoyCard);
    }

    private static ItemStack getHeldStack(ItemStack stack) {
        return (ItemStack)ImitaterCardItem.getDoubleStack(stack).getFirst();
    }
    @Shadow
    private static ItemStack getPlantStack(ItemStack stack) {
        return (ItemStack)ImitaterCardItem.getDoubleStack(stack).getSecond();
    }
    @Nullable
    @Overwrite
    public static BlockState getBlockState(Player player, IPlantType plant) {
        return plant == PVZPlants.LILY_PAD ? ((LilyPadBlock)BlockRegister.LILY_PAD.get()).getStateForPlacement(player) : (plant == PVZPlants.FLOWER_POT ? ((FlowerPotBlock)BlockRegister.FLOWER_POT.get()).getStateForPlacement(player) : (plant == BHTPvZPlants.WATER_POT ? ((WaterPotBlock)BHTPvZBlocks.WATER_POT.get()).getStateForPlacement(player): null));
    }

    @Nullable
    @Overwrite
    public static BlockState getBlockState(Direction direction, IPlantType plant) {
        return plant == PVZPlants.LILY_PAD ? ((LilyPadBlock)BlockRegister.LILY_PAD.get()).getStateForPlacement(direction) : (plant == PVZPlants.FLOWER_POT ? ((FlowerPotBlock)BlockRegister.FLOWER_POT.get()).getStateForPlacement(direction) : (plant == BHTPvZPlants.WATER_POT ? ((WaterPotBlock)BHTPvZBlocks.WATER_POT.get()).getStateForPlacement(direction):null));
    }
    @Shadow
    public static boolean checkSunAndPlaceBlock(Player player, ItemStack plantStack, ItemStack heldStack, PlantCardItem cardItem, BlockPos pos) {
        IPlantType plantType = cardItem.plantType;
        BlockState state = getBlockState(player, plantType);
        if (checkSunAndCD(player, cardItem, plantStack, true, (p) -> {
            if (state == null) {
                PVZMod.LOGGER.error("Plant Card : No such plant block !");
                return false;
            } else {
                return true;
            }
        })) {
            onUsePlantCard(player, heldStack, plantStack, (PlantCardItem)heldStack.getItem());
            if (heldStack.getItem() instanceof ImitaterCardItem) {
                if (!ImitaterCardItem.summonImitater(player, heldStack, plantStack, cardItem, pos, (imitater) -> {
                })) {
                    return false;
                }
            } else {
                handlePlantBlock(player.level, plantType, state, pos);
            }

            if (player instanceof ServerPlayer) {
                CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)player, pos, heldStack);
            }

            return true;
        } else {
            return false;
        }
    }
    @Shadow
    private static boolean checkSunAndCD(Player player, PlantCardItem cardItem, ItemStack stack, boolean ignore, Predicate<Player> pre) {
        if (player.getCooldowns().isOnCooldown(cardItem)) {
            cardItem.notifyPlayerAndCD(player, stack, PlacementErrors.CD_ERROR);
            return false;
        } else if (!cardItem.isEnjoyCard && PlayerUtil.isPAZLocked(player, cardItem.plantType) && ConfigUtil.needUnlockToPlant() && !player.isCreative()) {
            cardItem.notifyPlayerAndCD(player, stack, PlacementErrors.LOCK_ERROR, cardItem.plantType.getRequiredLevel());
            return false;
        } else {
            int sunCost = ignore ? cardItem.getBasisSunCost(stack) : cardItem.getCardSunCost(player, stack);
            if (sunCost > PlayerUtil.getResource(player, Resources.SUN_NUM) && !player.isCreative()) {
                if (sunCost == cardItem.getBasisSunCost(stack)) {
                    cardItem.notifyPlayerAndCD(player, stack, PlacementErrors.SUN_ERROR, sunCost);
                } else {
                    cardItem.notifyPlayerAndCD(player, stack, PlacementErrors.MULTIPLE_SUN_ERROR, sunCost);
                }

                return false;
            } else if (pre.test(player)) {
                if (!player.isCreative()) {
                    PlayerUtil.addResource(player, Resources.SUN_NUM, -sunCost);
                }

                return true;
            } else {
                return false;
            }
        }

    }
    @Overwrite
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand handIn) {
        ItemStack heldStack = getHeldStack(player.getItemInHand(handIn));
        ItemStack plantStack = getPlantStack(heldStack);
        PlantCardItem cardItem = (PlantCardItem)plantStack.getItem();
        IPlantType plantType = cardItem.plantType;
        if (world.isClientSide) {
            return InteractionResultHolder.success(heldStack);
        } else if (player.getCooldowns().isOnCooldown(heldStack.getItem())) {
            this.notifyPlayerAndCD(player, heldStack, PlacementErrors.CD_ERROR);
            return InteractionResultHolder.fail(heldStack);
        } else {
            BlockHitResult result = getPlayerPOVHitResult(world, player, ClipContext.Fluid.SOURCE_ONLY);
            if (result.getType() != HitResult.Type.BLOCK) {
                return InteractionResultHolder.pass(heldStack);
            } else {
                BlockHitResult raytraceResult = result.withPosition(result.getBlockPos().above());
                BlockPos pos = raytraceResult.getBlockPos();
                if (world.getFluidState(pos.below()).getType() == Fluids.WATER && raytraceResult.getDirection() == Direction.UP && world.isEmptyBlock(pos)||world.getBlockState(pos.below()).is(BHTPvZBlocks.WATER_POT.get())&& raytraceResult.getDirection() == Direction.UP && world.isEmptyBlock(pos)) {
                    if (!plantType.isWaterPlant()|| plantType == PVZPlants.CAT_TAIL) {
                        this.notifyPlayerAndCD(player, heldStack, PlacementErrors.GROUND_ERROR);
                        return InteractionResultHolder.fail(heldStack);
                    } else {
                        if (plantType.getPlantBlock().isPresent()) {
                            if (checkSunAndPlaceBlock(player, heldStack, plantStack, cardItem, pos)) {
                                return InteractionResultHolder.success(heldStack);
                            }
                        } else if (checkSunAndSummonPlant(player, heldStack, plantStack, cardItem, pos, (l) -> {
                        })) {
                            return InteractionResultHolder.success(heldStack);
                        }

                        return InteractionResultHolder.fail(heldStack);
                    }
                } else {
                    return InteractionResultHolder.pass(heldStack);
                }
            }
        }
    }
}
