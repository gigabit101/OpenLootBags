package gigabit101.openlootbags;

import gigabit101.openlootbags.inv.ContainerLootBag;
import gigabit101.openlootbags.inv.GuiLootBag;
import gigabit101.openlootbags.inv.command.ContainerCommand;
import gigabit101.openlootbags.inv.command.GuiCommand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by Gigabit101 on 02/08/2016.
 */
public class GuiHandler implements IGuiHandler
{
    public static int bagID = 0;
    public static int commandgui = 1;
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == bagID)
        {
            return new ContainerLootBag(player);
        }
        if(ID == commandgui)
        {
            return new ContainerCommand(player);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == bagID)
        {
            return new GuiLootBag(player);
        }
        if(ID == commandgui)
        {
            return new GuiCommand(player);
        }
        return null;
    }
}
