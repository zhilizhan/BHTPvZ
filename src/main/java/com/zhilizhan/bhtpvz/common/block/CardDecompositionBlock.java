package com.zhilizhan.bhtpvz.common.block;

import com.hungteen.pvz.common.block.AbstractFacingBlock;

import com.zhilizhan.bhtpvz.common.tileentity.CardDecompositionTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;

public class CardDecompositionBlock extends AbstractFacingBlock {

    public CardDecompositionBlock() {
        super(Properties.copy(Blocks.IRON_BLOCK));
    }

    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isClientSide && handIn == Hand.MAIN_HAND) {
            CardDecompositionTileEntity te = (CardDecompositionTileEntity)worldIn.getBlockEntity(pos);
            NetworkHooks.openGui((ServerPlayerEntity)player, te, pos);
        }

        return ActionResultType.SUCCESS;
    }

    public void appendHoverText(ItemStack itemStack, @Nullable IBlockReader iBlockReader, List<ITextComponent> textComponents, ITooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, iBlockReader, textComponents, tooltipFlag);
        textComponents.add((new TranslationTextComponent("tooltip.bhtpvz.card_decomposition_table")).withStyle(TextFormatting.GREEN));
    }

    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new CardDecompositionTileEntity();
    }

    public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tileentity = worldIn.getBlockEntity(pos);
            if (tileentity instanceof CardDecompositionTileEntity) {
                CardDecompositionTileEntity te = (CardDecompositionTileEntity)worldIn.getBlockEntity(pos);

                for(int i = 0; i < te.handler.getSlots(); ++i) {
                    InventoryHelper.dropItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), te.handler.getStackInSlot(i));
                }
            }

            super.onRemove(state, worldIn, pos, newState, isMoving);
        }

    }
}
