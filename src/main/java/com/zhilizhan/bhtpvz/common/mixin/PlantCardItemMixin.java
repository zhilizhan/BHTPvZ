package com.zhilizhan.bhtpvz.common.mixin;


import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.types.IPAZType;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.enchantment.card.plantcard.SoillessPlantEnchantment;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.item.spawn.card.ImitaterCardItem;
import com.hungteen.pvz.common.item.spawn.card.PlantCardItem;
import com.hungteen.pvz.common.item.spawn.card.SummonCardItem;
import com.zhilizhan.bhtpvz.common.block.BHTPvZBlocks;
import com.zhilizhan.bhtpvz.common.block.PotGrassBlock;
import com.zhilizhan.bhtpvz.common.block.WaterPotBlock;
import com.zhilizhan.bhtpvz.common.impl.plant.BHTPvZPlants;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.hungteen.pvz.common.item.spawn.card.PlantCardItem.checkSunAndPlaceBlock;
import static com.hungteen.pvz.common.item.spawn.card.PlantCardItem.checkSunAndSummonPlant;

@Mixin(value = PlantCardItem.class,remap = false)
public abstract class PlantCardItemMixin extends SummonCardItem {

    public PlantCardItemMixin(IPAZType type, boolean isEnjoyCard) {
        super(type, isEnjoyCard);
    }

    @Shadow
    private static ItemStack getPlantStack(ItemStack stack) {
        return ImitaterCardItem.getDoubleStack(stack).getSecond();
    }

    @Shadow
    private static ItemStack getHeldStack(ItemStack stack) {
        return null;
    }


    @Inject(method = "getBlockState(Lnet/minecraft/entity/player/PlayerEntity;Lcom/hungteen/pvz/api/types/IPlantType;)Lnet/minecraft/block/BlockState;", at = @At("HEAD"), cancellable = true)
    private static void getBlockState(PlayerEntity player, IPlantType plant, CallbackInfoReturnable<BlockState> cir) {
        BlockState blockState = plant == PVZPlants.LILY_PAD ? BlockRegister.LILY_PAD.get().getStateForPlacement(player) :
                (plant == PVZPlants.FLOWER_POT ? BlockRegister.FLOWER_POT.get().getStateForPlacement(player) :
                        (plant == BHTPvZPlants.WATER_POT ? ((WaterPotBlock) BHTPvZBlocks.WATER_POT.get()).getStateForPlacement(player) :
                                (plant == BHTPvZPlants.POT_GRASS ? ((PotGrassBlock) BHTPvZBlocks.POT_GRASS.get()).getStateForPlacement(player) : null)));
        cir.setReturnValue(blockState);
        cir.cancel();
    }


    @Inject(method = "getBlockState(Lnet/minecraft/util/Direction;Lcom/hungteen/pvz/api/types/IPlantType;)Lnet/minecraft/block/BlockState;", at = @At("HEAD"), cancellable = true)
    private static void getBlockState(Direction direction, IPlantType plant, CallbackInfoReturnable<BlockState> cir) {
        BlockState blockState = plant == PVZPlants.LILY_PAD ? BlockRegister.LILY_PAD.get().getStateForPlacement(direction) :
                (plant == PVZPlants.FLOWER_POT ? BlockRegister.FLOWER_POT.get().getStateForPlacement(direction) :
                        (plant == BHTPvZPlants.WATER_POT ? ((WaterPotBlock) BHTPvZBlocks.WATER_POT.get()).getStateForPlacement(direction) :
                                (plant == BHTPvZPlants.POT_GRASS ? ((PotGrassBlock) BHTPvZBlocks.POT_GRASS.get()).getStateForPlacement(direction) : null)));

        cir.setReturnValue(blockState);
        cir.cancel();
    }


    /**
     * This method is the overridden 'use' method, which is called when a player interacts with an item.
     * It handles the logic for using a plant card item in the game.
     *
     * @param world  The current level or world in which the player is interacting.
     * @param player The player who is interacting with the item.
     * @param handIn The hand used by the player for the interaction.
     * @return An instance of InteractionResultHolder containing the resulting ItemStack and additional information about the interaction result.
     * @author SuSen36
     * @reason none
     */
    @Overwrite
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand handIn) {
        ItemStack heldStack = getHeldStack(player.getItemInHand(handIn));
        ItemStack plantStack = getPlantStack(heldStack);
        PlantCardItem cardItem = (PlantCardItem) plantStack.getItem();
        IPlantType plantType = cardItem.plantType;
        if (world.isClientSide) {
            return ActionResult.success(heldStack);
        } else if (player.getCooldowns().isOnCooldown(heldStack.getItem())) {
            this.notifyPlayerAndCD(player, heldStack, PlacementErrors.CD_ERROR);
            return ActionResult.fail(heldStack);
        } else {
            BlockRayTraceResult result = getPlayerPOVHitResult(world, player, RayTraceContext.FluidMode.SOURCE_ONLY);
            if (result.getType() != RayTraceResult.Type.BLOCK) {
                return ActionResult.pass(heldStack);
            } else {
                BlockRayTraceResult raytraceResult = result.withPosition(result.getBlockPos().above());
                BlockPos pos = raytraceResult.getBlockPos();
                if (world.getFluidState(pos.below()).getType() == Fluids.WATER && raytraceResult.getDirection() == Direction.UP && world.isEmptyBlock(pos) || ((world.getBlockState(pos.below()).is(BHTPvZBlocks.WATER_POT.get()) && raytraceResult.getDirection() == Direction.UP && world.isEmptyBlock(pos)))) {
                    if (!plantType.isWaterPlant() || plantType == PVZPlants.CAT_TAIL && !SoillessPlantEnchantment.isSoilless(plantStack)) {
                        this.notifyPlayerAndCD(player, heldStack, PlacementErrors.GROUND_ERROR);
                        return ActionResult.fail(heldStack);
                    } else {
                        if (plantType.getPlantBlock().isPresent()) {
                            if (checkSunAndPlaceBlock(player, heldStack, plantStack, cardItem, pos)) {
                                return ActionResult.success(heldStack);
                            }
                        } else if (checkSunAndSummonPlant(player, heldStack, plantStack, cardItem, pos, (l) -> {
                        })) {
                            return ActionResult.success(heldStack);
                        }

                        return ActionResult.fail(heldStack);
                    }
                } else {
                    return ActionResult.pass(heldStack);
                }
            }
        }

    }
    /**
     * This method is used to handle the interaction of using a plant item on a specific context.
     * It performs various checks and actions based on the context information.
     *
     * @param context The context in which the plant item is being used
     * @return The result of the interaction (SUCCESS if the interaction is successful, FAIL if there is an error)
     * @author SuSen36
     * @reason none
     */
    @Overwrite
    public ActionResultType useOn(ItemUseContext context) {
        World world = context.getLevel();
        PlayerEntity player = context.getPlayer();
        Hand hand = context.getHand();
        ItemStack heldStack = context.getItemInHand();
        ItemStack plantStack = getPlantStack(context.getItemInHand());
        PlantCardItem cardItem = (PlantCardItem)plantStack.getItem();
        IPlantType plantType = cardItem.plantType;
        BlockPos pos = context.getClickedPos();
        boolean isSoilless = SoillessPlantEnchantment.isSoilless(plantStack);
        if (world.isClientSide) {
            return ActionResultType.SUCCESS;
        } else if (plantType == null) {
            PVZMod.LOGGER.error("Plant Card Use : Error Card !");
            return ActionResultType.FAIL;
        } else if (player!=null&&player.getCooldowns().isOnCooldown(heldStack.getItem())) {
            this.notifyPlayerAndCD(player, heldStack, PlacementErrors.CD_ERROR);
            return ActionResultType.FAIL;
        } else if (plantType.isOuterPlant()) {
            this.notifyPlayerAndCD(player, heldStack, PlacementErrors.OUTER_ERROR);
            return ActionResultType.FAIL;
        }
        else {
            if (plantType.isWaterPlant()&&player!=null) {
                if (plantType == PVZPlants.CAT_TAIL) {
                    if (isSoilless && world.getFluidState(pos.above()).getType() == Fluids.WATER) {
                        return this.use(world, player, hand).getResult();
                    } if (context.getClickedFace() == Direction.UP&& world.isEmptyBlock(pos.above()) && world.getBlockState(pos).getBlock() == BHTPvZBlocks.WATER_POT.get()) {
                        return this.use(world, player, hand).getResult();
                    }
                } else if (!isSoilless || world.getFluidState(pos.above()).getType() == Fluids.WATER) {
                    return this.use(world, player, hand).getResult();
                }
            }
            if (context.getClickedFace() == Direction.UP && world.isEmptyBlock(pos.above())) {
                if (!isSoilless && plantType.getUpgradeFrom().isPresent()) {
                    this.notifyPlayerAndCD(player, heldStack, PlacementErrors.UPGRADE_ERROR);
                    return ActionResultType.FAIL;
                } else if (!isSoilless && !plantType.getPlacement().canPlaceOnBlock(world.getBlockState(pos).getBlock())) {
                    this.notifyPlayerAndCD(player, heldStack, PlacementErrors.GROUND_ERROR);
                    return ActionResultType.FAIL;
                } else {
                    if (plantType.getPlantBlock().isPresent()) {
                        if (world.getBlockState(pos).canBeReplaced(new BlockItemUseContext(context))) {
                            if (player != null) {
                                checkSunAndPlaceBlock(player, heldStack, plantStack, cardItem, pos);
                            }
                            return ActionResultType.SUCCESS;
                        }

                        if (world.isEmptyBlock(pos.above()) && world.getBlockState(pos).canOcclude()) {
                            if (player != null) {
                                checkSunAndPlaceBlock(player, heldStack, plantStack, cardItem, pos.above());
                            }
                            return ActionResultType.SUCCESS;
                        }
                    } else {
                        BlockPos spawnPos = pos;
                        if (!world.getBlockState(pos).getCollisionShape(world, pos).isEmpty()) {
                            spawnPos = pos.relative(context.getClickedFace());
                        }

                        if (player != null && checkSunAndSummonPlant(player, heldStack, plantStack, cardItem, spawnPos, (l) -> {
                        })) {
                            return ActionResultType.SUCCESS;
                        }
                    }

                    return ActionResultType.FAIL;
                }
            } else {
                this.notifyPlayerAndCD(player, heldStack, PlacementErrors.GROUND_ERROR);
                return ActionResultType.FAIL;
            }
        }
    }
}
