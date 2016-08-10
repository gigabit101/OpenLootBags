package gigabit101.openlootbag.inv;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;

/**
 * Created by Gigabit101 on 02/08/2016.
 */
public class ContainerLootBag extends ContainerBase
{
    public InvLootBag inv;
    public EntityPlayer player;

    public ContainerLootBag(EntityPlayer player)
    {
        super();
        this.player = player;

        int slot = player.inventory.currentItem;
        inv = new InvLootBag(player, slot);

        int j;
        for (j = 0; j < 5; ++j)
        {
            addSlotToContainer(new SlotLocked(inv, j, 44 + j * 18, 15));
        }
        addPlayersHotbar();
        addPlayersInventory();
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
    public boolean canInteractWith(EntityPlayer player)
    {
        boolean can = inv.isUseableByPlayer(player);
        if(!can)
        {
            onContainerClosed(player);
        }
        return can;
    }

    @Override
    public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);
        inv.pushInventory();
    }

}
