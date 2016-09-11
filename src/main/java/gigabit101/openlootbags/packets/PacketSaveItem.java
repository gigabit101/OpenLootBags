package gigabit101.openlootbags.packets;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import reborncore.common.network.ExtendedPacketBuffer;
import reborncore.common.network.INetworkPacket;

import java.io.IOException;

public class PacketSaveItem implements INetworkPacket<PacketSaveItem> {

	ResourceLocation bagName;
	ItemStack stack;
	float chance;

	public PacketSaveItem(ResourceLocation bagName, ItemStack stack, float chance) {
		this.bagName = bagName;
		this.stack = stack;
		this.chance = chance;
	}

	public PacketSaveItem() {
	}

	@Override
	public void writeData(ExtendedPacketBuffer buffer) throws IOException {
		buffer.writeInt(bagName.getResourceDomain().length());
		buffer.writeString(bagName.getResourceDomain());
		buffer.writeInt(bagName.getResourcePath().length());
		buffer.writeString(bagName.getResourcePath());
		buffer.writeItemStackToBuffer(stack);
		buffer.writeFloat(chance);
	}

	@Override
	public void readData(ExtendedPacketBuffer buffer) throws IOException {
		String resourceDomain = buffer.readStringFromBuffer(buffer.readInt());
		String resourcePath = buffer.readStringFromBuffer(buffer.readInt());
		bagName = new ResourceLocation(resourceDomain, resourcePath);
		stack = buffer.readItemStackFromBuffer();
		chance = buffer.readFloat();
	}

	@Override
	public void processData(PacketSaveItem message, MessageContext context) {
		System.out.println(message.bagName + ":" + message.stack + ":" + message.chance);
	}
}
