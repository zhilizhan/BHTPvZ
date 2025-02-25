package com.zhilizhan.bhtpvz.common.block;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.block.AbstractFacingBlock;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.common.tileentity.PotGrassTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.UUID;

import static com.zhilizhan.bhtpvz.common.list.PlantItemList.PLANT_ITEM;
import static com.zhilizhan.bhtpvz.common.list.PlantList.PLANT;

public class PotGrassBlock extends AbstractFacingBlock {
    private static final VoxelShape SHAPE = VoxelShapes.or(
            Block.box(4, 0, 4, 12, 1, 12),
            Block.box(3.0, 1.0, 3.0, 13.0, 10.0, 13.0),
            Block.box(4.0, 10.0, 4.0, 12.0, 12.0, 12.0),
            Block.box(3.0, 12.0, 3.0, 13.0, 14.0, 13.0));

    public PotGrassBlock(Block.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public void onPlace(BlockState state, World level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!oldState.is(state.getBlock())) {
            IPlantType plant = PLANT.getRandomItem(RANDOM).get();

            if (level.getBlockState(pos.below()).getBlock() == Blocks.GRASS_BLOCK) {
                PotGrassTileEntity tileEntity = (PotGrassTileEntity) level.getBlockEntity(pos);
                if (tileEntity != null) {
                    tileEntity.setPlantType(plant);
                }
                this.trySpawnPlant(level, pos);
            }
        }
    }

    private void trySpawnPlant(World level, BlockPos pos) {
        TileEntity tileEntity = level.getBlockEntity(pos);
        if (tileEntity instanceof PotGrassTileEntity) {
            PotGrassTileEntity potGrassTileEntity = (PotGrassTileEntity) tileEntity;
            IPlantType plantType = potGrassTileEntity.getPlantType();
            UUID placerId = potGrassTileEntity.getOwnerId();

            level.removeBlock(pos, false);
            if (plantType != null) {
                CreatureEntity plant = plantType.getEntityType().get().create(level);
                if (plant instanceof PVZPlantEntity) {
                    EntityUtil.onEntitySpawn(level, plant, pos);

                    if (placerId != null) {
                        PlayerEntity placer = level.getPlayerByUUID(placerId);
                        if (placer != null) {
                            ((PVZPlantEntity) plant).setOwnerUUID(placerId);
                        }
                    }
                    if (potGrassTileEntity.isCharmed()) {
                        ((PVZPlantEntity) plant).setCharmed(true);
                    }
                    EntityUtil.onEntitySpawn(level, plant, pos);
                }
            }
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader level, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, BlockPos pos, MobEntity entity) {
        return PathNodeType.FENCE;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new PotGrassTileEntity();
    }

}


