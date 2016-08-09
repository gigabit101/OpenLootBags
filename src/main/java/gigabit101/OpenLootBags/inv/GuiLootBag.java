package gigabit101.OpenLootBags.inv;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import reborncore.client.guibuilder.GuiBuilder;

/**
 * Created by Gigabit101 on 02/08/2016.
 */
public class GuiLootBag extends GuiContainer
{
    GuiBuilder builder = new GuiBuilder(GuiBuilder.defaultTextureSheet);
    public EntityPlayer player;

    public GuiLootBag(EntityPlayer player)
    {
        super(new ContainerLootBag(player));
        this.player = player;
    }

    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {}

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        builder.drawDefaultBackground(this, guiLeft, guiTop, xSize, ySize);
        builder.drawPlayerSlots(this, guiLeft + xSize / 2, guiTop + 83, true);

        int j;
        for (j = 0; j < 5; ++j)
        {
            builder.drawSlot(this, guiLeft + 43 + j * 18, guiTop + 14);
        }
    }
}
