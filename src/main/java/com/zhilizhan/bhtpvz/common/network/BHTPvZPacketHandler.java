package com.zhilizhan.bhtpvz.common.network;


import com.hungteen.pvz.common.network.toserver.ClickButtonPacket;
import com.zhilizhan.bhtpvz.BHTPvZ;
import com.zhilizhan.bhtpvz.common.network.toserver.BHTPvZClickButtonPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class BHTPvZPacketHandler {

	private static final ResourceLocation CHANNEL_NAME = new ResourceLocation(BHTPvZ.MOD_ID + ":networking");
	private static final String PROTOCOL_VERSION = "1.0";
	
	public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
			CHANNEL_NAME, 
			() -> PROTOCOL_VERSION,
			PROTOCOL_VERSION::equals, 
			PROTOCOL_VERSION::equals
	);

	public static void init() {
		int id = 0;
		CHANNEL.registerMessage(id++, BHTPvZClickButtonPacket.class, BHTPvZClickButtonPacket::encode, BHTPvZClickButtonPacket::new, BHTPvZClickButtonPacket.Handler::onMessage);
	}
	
}
