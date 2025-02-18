package com.zhilizhan.bhtpvz.common.block;

import com.hungteen.pvz.common.block.AbstractFacingBlock;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import static com.zhilizhan.bhtpvz.common.list.PlantItemList.PLANT_ITEM;

public class PlantPotBlock extends AbstractFacingBlock {
    private static final VoxelShape SHAPE = VoxelShapes.or(
            Block.box(4, 0, 4, 12, 1, 12),
            Block.box(3.0, 1.0, 3.0, 13.0, 10.0, 13.0),
            Block.box(4.0, 10.0, 4.0, 12.0, 12.0, 12.0),
            Block.box(3.0, 12.0, 3.0, 13.0, 14.0, 13.0));

    public PlantPotBlock(Properties properties) {
        super(properties);
    }
    public ActionResultType use(BlockState state, World level, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack plant = PLANT_ITEM.getRandomItem(RANDOM).get().getDefaultInstance();
        if (!level.isClientSide && player.getMainHandItem().getItem()==BHTPvZItems.HAMMER.get()) {
            level.removeBlock(pos, false);
            //植物
            level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(plant.getItem())));
            //扣除耐久
            player.getMainHandItem().hurtAndBreak(2, player, (arg) -> arg.broadcastBreakEvent(player.getUsedItemHand()));
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader level, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, BlockPos pos, MobEntity entity) {
        return PathNodeType.FENCE;
    }
}
