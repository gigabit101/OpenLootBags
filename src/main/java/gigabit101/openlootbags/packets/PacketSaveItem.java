package gigabit101.openlootbags.packets;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import reborncore.common.network.ExtendedPacketBuffer;
import reborncore.common.network.INetworkPacket;

import java.io.IOException;

public class PacketSaveItem implements INetworkPacket<PacketSaveItem> {

	String bagName;
	ItemStack stack;
	float chance;

	public PacketSaveItem(String bagName, ItemStack stack, float chance) {
		this.bagName = bagName;
		this.stack = stack;
		this.chance = chance;
	}

	public PacketSaveItem() {
	}

	@Override
	public void writeData(ExtendedPacketBuffer buffer) throws IOException {
		buffer.writeInt(bagName.length());
		buffer.writeString(bagName);
		buffer.writeItemStackToBuffer(stack);
		buffer.writeFloat(chance);
	}

	@Override
	public void readData(ExtendedPacketBuffer buffer) throws IOException {
		bagName = buffer.readStringFromBuffer(buffer.readInt());
		stack = buffer.readItemStackFromBuffer();
		chance = buffer.readFloat();
	}

	@Override
	public void processData(PacketSaveItem message, MessageContext context) {
		System.out.println(message.bagName + ":" + message.stack + ":" + message.chance);
	}
}
