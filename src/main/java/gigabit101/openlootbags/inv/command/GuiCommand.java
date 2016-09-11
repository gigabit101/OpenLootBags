package gigabit101.openlootbags.inv.command;

import gigabit101.openlootbags.packets.PacketSaveItem;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import reborncore.client.guibuilder.GuiBuilder;
import reborncore.common.network.NetworkManager;

/**
 * Created by Gigabit101 on 29/08/2016.
 */
public class GuiCommand extends GuiContainer
{
    GuiBuilder builder = new GuiBuilder(GuiBuilder.defaultTextureSheet);
    public EntityPlayer player;

    public GuiCommand(EntityPlayer player)
    {
        super(new ContainerCommand(player));
        this.player = player;
        buttonList.clear();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        builder.drawDefaultBackground(this, guiLeft, guiTop, xSize, ySize);
        builder.drawPlayerSlots(this, guiLeft + xSize / 2, guiTop + 83, true);
        builder.drawSlot(this, guiLeft + 119, guiTop + 39);
        builder.drawSlot(this, guiLeft + 40, guiTop + 39);


        buttonList.add(new GuiButton(0, guiLeft + 118, guiTop + 18, 20, 20, "^"));
        buttonList.add(new GuiButton(1, guiLeft + 118, guiTop + 58, 20, 20, "v"));

        buttonList.add(new GuiButton(2, guiLeft + 30, guiTop + 18, 40, 20, "ADD"));
        buttonList.add(new GuiButton(3, guiLeft + 30, guiTop + 58, 40, 20, "REMOVE"));
    }

	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		NetworkManager.sendToServer(new PacketSaveItem(new ResourceLocation("test", "test"), new ItemStack(Blocks.DIAMOND_BLOCK), 0.25F));
	}
}
