package com.zhilizhan.bhtpvz.common.block;

import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.common.block.AbstractFacingBlock;
import com.hungteen.pvz.utils.EntityUtil;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import static com.zhilizhan.bhtpvz.common.list.PlantItemList.PLANT_ITEM;
import static com.zhilizhan.bhtpvz.common.list.ZombieList.ZOMBIE;

public class QuestionMarkPotBlock extends AbstractFacingBlock {

    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(4.0, 0.0, 4.0, 12.0, 1.0, 12.0),
            Block.box(3.0, 1.0, 3.0, 13.0, 10.0, 13.0),
            Block.box(4.0, 10.0, 4.0, 12.0, 12.0, 12.0),
            Block.box(3.0, 12.0, 3.0, 13.0, 14.0, 13.0));

    public QuestionMarkPotBlock(Properties properties) {
        super(properties);
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        IZombieType zombieType = (IZombieType) ZOMBIE.getRandomItem(RANDOM).get();
        ItemStack plant = PLANT_ITEM.getRandomItem(RANDOM).get().getDefaultInstance();
        ItemStack pot = BHTPvZItems.POT_GRASS_CARD.get().getDefaultInstance();

        if (!level.isClientSide && player.getMainHandItem().getItem()==BHTPvZItems.HAMMER.get()) {
            level.removeBlock(pos, false);
            //僵尸
            PathfinderMob zombie = zombieType.getEntityType().get().create(level);
            if (RANDOM.nextInt(2) == 0) {
                EntityUtil.onEntitySpawn(level, zombie, pos);
            } else if(RANDOM.nextInt(10)==0){
                level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(pot.getItem())));
            }else {
                level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(plant.getItem())));
            }
        }
        //扣除耐久
        player.getMainHandItem().hurtAndBreak(2, player, (arg) -> arg.broadcastBreakEvent(player.getUsedItemHand()));
        return InteractionResult.SUCCESS;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public BlockPathTypes getAiPathNodeType(BlockState state, BlockGetter world, BlockPos pos, Mob entity) {
        return BlockPathTypes.FENCE;
    }

}
