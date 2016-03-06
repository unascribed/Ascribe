package com.unascribed.ascribe;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;

public class AttackedAtYawMessage implements IMessage, IMessageHandler<AttackedAtYawMessage, IMessage> {

	public float yaw;
	
	@Override
	public void fromBytes(ByteBuf buf) {
		yaw = buf.readFloat();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeFloat(yaw);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IMessage onMessage(AttackedAtYawMessage message, MessageContext ctx) {
		if (Ascribe.getOption(AscribeOption.SYNC_ATTACKED_AT_YAW)) {
			Minecraft.getMinecraft().thePlayer.attackedAtYaw = message.yaw;
		}
		return null;
	}

}