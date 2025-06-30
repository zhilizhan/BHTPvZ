package com.zhilizhan.bhtpvz.common.tileentity;

import com.hungteen.pvz.api.types.IPlantType;
import com.zhilizhan.bhtpvz.common.list.PlantList;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nullable;
import java.util.UUID;

public class PotGrassTileEntity extends TileEntity {
    private IPlantType plantType;
    private UUID placerId;
    private boolean charmed;

    public PotGrassTileEntity() {
        super(BHTPvZTileEntity.POT_GRASS.get());
    }

    public IPlantType getPlantType() {
        return plantType;
    }

    public void setPlantType(IPlantType plantType) {
        this.plantType = plantType;
    }

    public UUID getOwnerId() {
        return placerId;
    }

    public void setOwnerId(UUID placerId) {
        this.placerId = placerId;
        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    public boolean isCharmed() {
        return charmed;
    }

    public void setCharmed(boolean charmed) {
        this.charmed = charmed;;
        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT nbt = super.getUpdateTag();
        // 保存需要同步到客户端的数据
        nbt.putBoolean("Charmed", charmed);
        return nbt;
    }

    // 在客户端处理更新包
    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT nbt) {
        super.handleUpdateTag(state, nbt);
        if (nbt.contains("Charmed")) {
            this.charmed = nbt.getBoolean("Charmed");
        }
    }

    // 添加以下方法以处理TileEntity的实时更新
    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(
                this.worldPosition,
                1, // 自定义包ID，可以是任意数字
                this.getUpdateTag()
        );
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net, pkt);
        // 当客户端收到更新包时处理
        CompoundNBT tag = pkt.getTag();
        this.handleUpdateTag(this.getBlockState(), tag);
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        super.save(nbt);
        // 保存nbt数据
        if (plantType != null) {
            nbt.putString("PlantType", plantType.getIdentity());
        }
        if (placerId != null) {
            nbt.putUUID("PlacerId", placerId);
        }
        nbt.putBoolean("Charmed", charmed);
        return nbt;
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);
        // 加载nbt数据
        if (nbt.contains("PlantType")) {
            String plantTypeName = nbt.getString("PlantType");
            plantType = PlantList.PLANT.getItemList().stream()
                    .filter(type -> type.getIdentity().equals(plantTypeName))
                    .findFirst()
                    .orElse(null);
        }
        if (nbt.contains("PlacerId")) {
            placerId = nbt.getUUID("PlacerId");
        }
        if (nbt.contains("Charmed")) {
            charmed = nbt.getBoolean("Charmed");
        }
    }
}
