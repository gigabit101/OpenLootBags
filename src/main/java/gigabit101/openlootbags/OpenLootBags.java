package gigabit101.openlootbags;

import gigabit101.openlootbags.api.OpenLootBagsApi;
import gigabit101.openlootbags.packets.PacketSaveItem;
import gigabit101.openlootbags.proxy.CommonProxy;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import reborncore.common.network.RegisterPacketEvent;

import java.io.File;
import java.io.IOException;

/**
 * Created by Gigabit101 on 02/08/2016.
 */
@Mod(name = OpenLootBags.MOD_NAME, modid = OpenLootBags.MOD_ID, version = OpenLootBags.MOD_VERSION)
public class OpenLootBags
{
    public static final String MOD_NAME = "openlootbags";
    public static final String MOD_ID = "openlootbags";
    public static final String MOD_VERSION = "alpha1";

    public static Item lootbag;

    @Mod.Instance
    public static OpenLootBags instance;

    @SidedProxy(clientSide = "gigabit101.openlootbags.proxy.ClientProxy", serverSide = "gigabit101.openlootbags.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        OpenLootBagsApi.INSTANCE.setBagManager(new BagManger());
	    LootManager.bagFile = new File(new File(event.getModConfigurationDirectory(), "openlootbags"), "bags.json");
	    LootManager.lootFile = new File(new File(event.getModConfigurationDirectory(), "openlootbags"), "loot.json");

        lootbag = new ItemLootBag();
        GameRegistry.register(lootbag);
	    MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(MobDropHandler.instance);
    }


    @Mod.EventHandler
    public void init(FMLInitializationEvent event) throws IOException {
        LootManager.loadConfig();

        proxy.registerRenders();
        NetworkRegistry.INSTANCE.registerGuiHandler(OpenLootBags.MOD_ID, new GuiHandler());
    }

    @Mod.EventHandler
    public void serverstarting(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandOpenLootBags());
    }

	@SubscribeEvent
	public void loadPackets(RegisterPacketEvent event){
		event.registerPacket(PacketSaveItem.class, Side.SERVER);
	}
}
