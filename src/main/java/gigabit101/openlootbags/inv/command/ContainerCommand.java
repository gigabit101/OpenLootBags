package gigabit101.openlootbags.inv.command;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import reborncore.common.container.RebornContainer;

/**
 * Created by Gigabit101 on 29/08/2016.
 */
public class ContainerCommand extends RebornContainer
{
    EntityPlayer player;
    public CommandInv inv;
    public ContainerCommand(EntityPlayer player)
    {
        super();
        this.player = player;
        int slot = player.inventory.currentItem;

        inv = new CommandInv(player, slot);
        addPlayersHotbar();
        addPlayersInventory();
        addSlotToContainer(new Slot(inv, 0, 40, 40));
        addSlotToContainer(new Slot(inv, 1, 120, 40));
    }

    public void addPlayersHotbar()
    {
        int i;
        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
        }
    }

    public void addPlayersInventory()
    {
        int i;
        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return true;
    }
}
