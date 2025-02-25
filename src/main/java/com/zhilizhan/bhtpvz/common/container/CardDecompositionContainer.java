package com.zhilizhan.bhtpvz.common.container;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.container.PVZContainer;
import com.hungteen.pvz.common.item.spawn.card.PlantCardItem;
import com.hungteen.pvz.common.item.tool.plant.SunStorageSaplingItem;
import com.zhilizhan.bhtpvz.common.block.BHTPvZBlocks;
import com.zhilizhan.bhtpvz.common.tileentity.CardDecompositionTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.SlotItemHandler;

public class CardDecompositionContainer extends PVZContainer {
    public final CardDecompositionTileEntity te;
    private final IWorldPosCallable access;
    private final PlayerEntity player;

    public CardDecompositionContainer(int id, PlayerEntity player, BlockPos pos) {
        super(BHTPvZContainer.CARD_DECOMPOSITION.get(), id);
        this.te = (CardDecompositionTileEntity)player.level.getBlockEntity(pos);
        this.player = player;
        this.access = IWorldPosCallable.create(player.level, pos);
        if (this.te == null) {
            System.out.println("Error: Open Card Decomposition GUI !");
        } else {
            this.addDataSlots(this.te.array);
            //阳光充能槽
            this.addSlot(new SlotItemHandler(this.te.handler, 0, 7, 119) {
                             public boolean mayPlace(ItemStack stack) {
                                 return stack.getItem() instanceof SunStorageSaplingItem;
                             }
                         });
            // 输入槽位（左侧）
            this.addSlot(new SlotItemHandler(this.te.handler, 1, 59, 64) { // 坐标(8, 35)
                public boolean mayPlace(ItemStack stack) {
                    return stack.getItem() instanceof PlantCardItem && ((PlantCardItem) stack.getItem()).plantType != null && !((PlantCardItem) stack.getItem()).isEnjoyCard;
                }
            });

            // 输出槽位1 (上方)
            this.addSlot(new SlotItemHandler(this.te.handler, 2, 116, 64) { // 坐标(29, 17)
                public boolean mayPlace(ItemStack stack) {
                    return false; // 不能手动放置物品
                }
            });

            // 输出槽位2 (上方)
            this.addSlot(new SlotItemHandler(this.te.handler, 3, 141, 64) { // 坐标(59, 17)
                public boolean mayPlace(ItemStack stack) {
                    return false; // 不能手动放置物品
                }
            });

            // 输出槽位3 (上方)
            this.addSlot(new SlotItemHandler(this.te.handler, 4, 166, 64) { // 坐标(89, 17)
                public boolean mayPlace(ItemStack stack) {
                    return false; // 不能手动放置物品
                }
            });

            int l;
            int i1;

            for(l = 0; l < 3; ++l) {
                for(i1 = 0; i1 < 9; ++i1) {
                    this.addSlot(new Slot(player.inventory, i1 + l * 9 + 9, 25 + i1 * 18, 143 + l * 18));
                }
            }

            for(l = 0; l < 9; ++l) {
                this.addSlot(new Slot(player.inventory, l, 25 + l * 18, 201));
            }
        }
    }

    public void onCraft() {
        ItemStack[] results = this.getResults(); // 获取分解后的3种材料
        this.te.handler.setStackInSlot(2, results[0].copy()); // 放入输出槽位1
        this.te.handler.setStackInSlot(3, results[1].copy()); // 放入输出槽位2
        this.te.handler.setStackInSlot(4, results[2].copy()); // 放入输出槽位3
        this.te.clearCraftingSlots(); // 清空输入槽位
        this.te.consumeSunAmount();
    }

    public ItemStack[] getResults() {
        ItemStack input = this.te.handler.getStackInSlot(1).copy(); // 获取输入槽位的物品
        ItemStack[] results = new ItemStack[3]; // 用于存放3种分解材料

        if (!input.isEmpty() && input.getItem() instanceof PlantCardItem && ((PlantCardItem) input.getItem()).plantType != null && !((PlantCardItem) input.getItem()).isEnjoyCard) {
            IPlantType type = ((PlantCardItem) input.getItem()).plantType;
            results[0] = new ItemStack(type.getEnjoyCard().get(), 4); // 材料1
            results[1] = new ItemStack(type.getRank().getTemplateCard(), Math.random() <= 0.33f ? 1 : 0); // 材料2
            results[2] = new ItemStack(type.getEssence().getEssenceItem(), 3); // 材料3
        } else {
            results[0] = ItemStack.EMPTY;
            results[1] = ItemStack.EMPTY;
            results[2] = ItemStack.EMPTY;
        }

        return results;
    }

    public boolean canCraft() {
        ItemStack input = this.te.handler.getStackInSlot(1);
        ItemStack output1 = this.te.handler.getStackInSlot(2);
        ItemStack output2 = this.te.handler.getStackInSlot(3);
        ItemStack output3 = this.te.handler.getStackInSlot(4);

        return this.isValidCard(input) && output1.isEmpty() && output2.isEmpty() && output3.isEmpty() && this.te.array.get(0)>= CardDecompositionTileEntity.CRAFT_SUN_COST;
    }

    private boolean isValidCard(ItemStack stack) {
        return stack.getItem() instanceof PlantCardItem && ((PlantCardItem) stack.getItem()).plantType != null && !((PlantCardItem) stack.getItem()).isEnjoyCard;
    }

    public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index != 0 && index >= 12) {
                if (index < 40) {
                    if (!this.moveItemStackTo(itemstack1, 0, 13, false) && !this.moveItemStackTo(itemstack1, 40, this.slots.size(), false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.moveItemStackTo(itemstack1, 0, 40, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 13, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    public boolean stillValid(PlayerEntity playerIn) {
        return stillValid(this.access, this.player, BHTPvZBlocks.CARD_DECOMPOSITION_TABLE.get());
    }
}
