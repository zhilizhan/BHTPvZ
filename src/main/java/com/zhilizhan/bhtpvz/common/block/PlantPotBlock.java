package com.zhilizhan.bhtpvz.common.block;

import com.hungteen.pvz.common.block.AbstractFacingBlock;
import com.zhilizhan.bhtpvz.common.item.BHTPvZItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

import static com.zhilizhan.bhtpvz.common.list.PlantItemList.PLANT_ITEM;

public class PlantPotBlock extends AbstractFacingBlock {
    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(4, 0, 4, 12, 1, 12),
            Block.box(3.0, 1.0, 3.0, 13.0, 10.0, 13.0),
            Block.box(4.0, 10.0, 4.0, 12.0, 12.0, 12.0),
            Block.box(3.0, 12.0, 3.0, 13.0, 14.0, 13.0));

    public PlantPotBlock(Properties properties) {
        super(properties);
    }
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.playerDestroy(level, player, pos, state, blockEntity, tool);
        ItemStack plant = PLANT_ITEM.getRandomItem(RANDOM).get().getDefaultInstance();
        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, tool) == 0 && ItemStack.isSameIgnoreDurability(BHTPvZItems.HAMMER.get().getDefaultInstance(), tool)) {
            level.removeBlock(pos, false);
            //植物
            level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(plant.getItem())));
            }
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
