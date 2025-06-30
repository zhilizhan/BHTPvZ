package com.zhilizhan.bhtpvz.common.network.toclient;

import com.zhilizhan.bhtpvz.common.api.ISteelPumpkin;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SteelPumpkinSyncPacket {
    private final int entityId;
    private final boolean isSteelPumpkin;

    public static final int SYNC_STEEL_PUMPKIN = 1;

    public SteelPumpkinSyncPacket(int entityId, boolean isSteelPumpkin) {
        this.entityId = entityId;
        this.isSteelPumpkin = isSteelPumpkin;
    }

    public SteelPumpkinSyncPacket(PacketBuffer buffer) {
        this.entityId = buffer.readInt();
        this.isSteelPumpkin = buffer.readBoolean();
    }

    public void encode(PacketBuffer buffer) {
        buffer.writeInt(this.entityId);
        buffer.writeBoolean(this.isSteelPumpkin);
    }

    public static class Handler {
        public static void onMessage(SteelPumpkinSyncPacket msg, Supplier<NetworkEvent.Context> ctx) {
            NetworkEvent.Context context = ctx.get();
            context.enqueueWork(() -> {
                if (ctx.get().getDirection().getReceptionSide().isClient()) {
                    // 客户端处理
                    World world = Minecraft.getInstance().level;
                    if (world != null) {
                        Entity entity = world.getEntity(msg.entityId);
                        if (entity instanceof ISteelPumpkin) {
                            ((ISteelPumpkin) entity).setCachedSteelPumpkin(msg.isSteelPumpkin);
                        }
                    }
                } else {
                    // 服务器端处理 (可选)
                    ServerPlayerEntity player = ctx.get().getSender();
                    // 可以添加服务端验证逻辑...
                }
            });
            context.setPacketHandled(true);
        }
    }
}