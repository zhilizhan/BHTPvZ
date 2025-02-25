package com.zhilizhan.bhtpvz.common.tileentity;

import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.tileentity.CardFusionTileEntity;
import com.mojang.datafixers.types.Type;
import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.common.block.BHTPvZBlocks;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BHTPvZTileEntity {
    public static final DeferredRegister<TileEntityType<?>>  TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, BHTPvZ.MOD_ID);

    public static final RegistryObject<TileEntityType<PotGrassTileEntity>> POT_GRASS = TILE_ENTITY_TYPES.register("pot_grass", () -> {
        return TileEntityType.Builder.of(PotGrassTileEntity::new, new Block[]{BHTPvZBlocks.POT_GRASS.get()}).build(null);
    });
    public static final RegistryObject<TileEntityType<CardDecompositionTileEntity>> CARD_DECOMPOSITION_TABLE = TILE_ENTITY_TYPES.register("card_decomposition_table", () -> {
        return TileEntityType.Builder.of(CardDecompositionTileEntity::new, new Block[]{BHTPvZBlocks.CARD_DECOMPOSITION_TABLE.get()}).build(null);
    });
}
