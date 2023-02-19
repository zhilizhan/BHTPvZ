package com.zhilizhan.bhtpvz.common.block.superBlock;

import com.hungteen.pvz.common.block.AbstractFacingBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.MobEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class PlantPot extends AbstractFacingBlock {

    private static VoxelShape shape;

    static {
        VoxelShape base = Block.box(4,0,4,12,1,12);
        VoxelShape plant_pot = Block.box(3,1,3,13,10,13);
        VoxelShape top_1 = Block.box(4,10,4,12,12,12);
        VoxelShape top_2 = Block.box(3,12,3,13,14,13);
        shape = VoxelShapes.or(base,plant_pot,top_1,top_2);
    }

    public PlantPot(Properties properties) {
        super(properties);
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return shape;
    }

    public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, BlockPos pos, MobEntity entity) {
        return PathNodeType.FENCE;
    }


}
