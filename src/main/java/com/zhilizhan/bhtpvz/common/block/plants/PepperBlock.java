package com.zhilizhan.bhtpvz.common.block.plants;

import com.zhilizhan.bhtpvz.common.item.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.IntegerProperty;
import net.minecraft.block.CropsBlock;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class PepperBlock extends CropsBlock {
    public static final IntegerProperty AGE;
    private static final VoxelShape[] SHAPE;

    public PepperBlock(Properties properties) {
        super(properties);
    }

    public IntegerProperty getAgeProperty() {return AGE;}

    public int getMaxAge() {return 3;}

    protected IItemProvider getBaseSeedId() {return ItemRegistry.PEPPER_SEEDS.get();}

    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if (rand.nextInt(4) != 0) {
            super.tick(state, worldIn, pos, rand);
        }
    }

    protected int getBonemealAgeIncrease(World worldIn) {
        return super.getBonemealAgeIncrease(worldIn) / 3;
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE[state.getValue(this.getAgeProperty())];
    }

    static {
        AGE = BlockStateProperties.AGE_3;
        SHAPE = new VoxelShape[]{
                Block.box(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D),
                Block.box(0.0D, 0.0D, 0.0D, 16.0D, 13.0D, 16.0D),
                Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D),
                Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)
        };
    }
}
