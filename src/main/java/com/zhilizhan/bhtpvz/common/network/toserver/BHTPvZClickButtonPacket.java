package com.zhilizhan.bhtpvz.common.network.toserver;


import com.zhilizhan.bhtpvz.common.container.CardDecompositionContainer;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class BHTPvZClickButtonPacket {

	private final int type;
	private final int op;
	private final int num;
	public static final int CARD_DECOMPOSITION = 1;

	public BHTPvZClickButtonPacket(int type, int op, int num) {
		this.type = type;
		this.op = op;
		this.num = num;
	}

	public BHTPvZClickButtonPacket(PacketBuffer buffer) {
		this.type = buffer.readInt();
		this.op = buffer.readInt();
		this.num = buffer.readInt();
	}

	public void encode(PacketBuffer buffer) {
		buffer.writeInt(this.type);
		buffer.writeInt(this.op);
		buffer.writeInt(this.num);
	}

	public static class Handler {
		public static void onMessage(BHTPvZClickButtonPacket message, Supplier<NetworkEvent.Context> ctx) {
			final ServerPlayerEntity player = ctx.get().getSender();
			ctx.get().enqueueWork(() -> {
				 if(message.type == CARD_DECOMPOSITION) {
                     if (player != null && player.containerMenu instanceof CardDecompositionContainer) {
                         CardDecompositionContainer container = (CardDecompositionContainer) player.containerMenu;
                         if (message.op == 0) {
                             container.onCraft();
                         }
                     }
                 }
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
