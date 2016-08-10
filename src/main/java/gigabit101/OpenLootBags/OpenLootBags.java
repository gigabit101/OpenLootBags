package gigabit101.OpenLootBags;

import gigabit101.OpenLootBags.api.OpenLootBagsApi;
import gigabit101.OpenLootBags.proxy.CommonProxy;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Gigabit101 on 02/08/2016.
 */
@Mod(name = OpenLootBags.MOD_NAME, modid = OpenLootBags.MOD_ID, version = OpenLootBags.MOD_VERSION)
public class OpenLootBags
{
    public static final String MOD_NAME = "openlootbags";
    public static final String MOD_ID = "openlootbags";
    public static final String MOD_VERSION = "1.0.0";

    public static Item lootbag;

    @Mod.Instance
    public static OpenLootBags instance;

    @SidedProxy(clientSide = "gigabit101.OpenLootBags.proxy.ClientProxy", serverSide = "gigabit101.OpenLootBags.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        OpenLootBagsApi.addBagType("common", 10000);
        OpenLootBagsApi.addBagType("uncommon", 90500);
        OpenLootBagsApi.addBagType("rare", 50900);
        OpenLootBagsApi.addBagType("epic", 20600);


        lootbag = new ItemLootBag();
        GameRegistry.register(lootbag);
        proxy.registerRenders();
        NetworkRegistry.INSTANCE.registerGuiHandler(OpenLootBags.MOD_ID, new GuiHandler());
    }
}
