package com.zhilizhan.bhtpvz.common.tileentity;

import com.hungteen.pvz.api.types.IPlantType;
import com.zhilizhan.bhtpvz.common.list.PlantList;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;

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
    }

    public boolean isCharmed() {
        return charmed;
    }

    public void setCharmed(boolean charmed) {
        this.charmed = charmed;
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
