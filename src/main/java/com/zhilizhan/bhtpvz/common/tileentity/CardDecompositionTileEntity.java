package com.zhilizhan.bhtpvz.common.tileentity;


import com.hungteen.pvz.common.item.tool.plant.SunStorageSaplingItem;
import com.zhilizhan.bhtpvz.common.container.CardDecompositionContainer;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.items.ItemStackHandler;

public class CardDecompositionTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {
    public final ItemStackHandler handler = new ItemStackHandler(12);

    public static final int CRAFT_SUN_AMOUNT = 10000;
    public static final int CRAFT_SUN_COST = 5000;
    public IIntArray array = new IntArray(2);
    public int sunAmount = 0;

    public CardDecompositionTileEntity() {
        super(BHTPvZTileEntity.CARD_DECOMPOSITION_TABLE.get());
    }

    public void tick() {
        if (!this.level.isClientSide) {
            this.absorbSunAmount();
            this.array.set(0, this.sunAmount);
        }

    }

    private void absorbSunAmount() {
        ItemStack stack = this.handler.getStackInSlot(0);
        if (!stack.isEmpty() && stack.getItem() instanceof SunStorageSaplingItem) {
            int amount = SunStorageSaplingItem.getStorageSunAmount(stack);
            int decAmount = Math.min(CRAFT_SUN_AMOUNT - this.sunAmount, Math.min(100, amount));
            amount -= decAmount;
            this.sunAmount += decAmount;
            SunStorageSaplingItem.setStorageSunAmount(stack, amount);
        }

    }

    public void clearCraftingSlots() {
        this.handler.getStackInSlot(1).shrink(1);
    }

    public void consumeSunAmount() {
        int amount = Math.max(0, CRAFT_SUN_COST);
        this.sunAmount -= amount;
    }

    public void load(BlockState state, CompoundNBT compound) {
        super.load(state, compound);
        this.handler.deserializeNBT(compound.getCompound("itemstack_list"));
        this.sunAmount = compound.getInt("sun_amount");
    }

    public CompoundNBT save(CompoundNBT compound) {
        compound.put("itemstack_list", this.handler.serializeNBT());
        compound.putInt("sun_amount", this.sunAmount);
        return super.save(compound);
    }

    public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
        return new CardDecompositionContainer(id, player, this.worldPosition);
    }

    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("gui.pvz.card_decomposition");
    }
}