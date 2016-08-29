package gigabit101.openlootbags;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

/**
 * Created by Gigabit101 on 29/08/2016.
 */
public class CommandOpenLootBags extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "openlootbags";
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "commands.forge.usage";
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0)
        {
            sender.addChatMessage(new TextComponentString("You need to use arguments, see /openlootbags help"));
        }
        else if ("help".equals(args[0]))
        {
            sender.addChatMessage(new TextComponentString("gui 	- Opens the openlootbags GUI"));
        }
        else if ("gui".equals(args[0]))
        {
            if(sender.getCommandSenderEntity() instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer) sender.getCommandSenderEntity();
                player.openGui(OpenLootBags.instance, GuiHandler.commandgui, player.worldObj, 0, 0, 0);
            }
        }
    }
}
