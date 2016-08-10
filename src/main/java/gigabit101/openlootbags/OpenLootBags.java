package gigabit101.openlootbags;

import gigabit101.openlootbags.api.OpenLootBagsApi;
import gigabit101.openlootbags.proxy.CommonProxy;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
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

    @SidedProxy(clientSide = "gigabit101.openlootbags.proxy.ClientProxy", serverSide = "gigabit101.openlootbags.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        OpenLootBagsApi.INSTANCE.setBagManager(new BagManger());

        lootbag = new ItemLootBag();
        GameRegistry.register(lootbag);
    }


    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        //Default bags
        OpenLootBagsApi.INSTANCE.getBagManager().addBagType(new ResourceLocation("openlootbags", "common"), 10000);
        OpenLootBagsApi.INSTANCE.getBagManager().addBagType(new ResourceLocation("openlootbags", "uncommon"), 90500);
        OpenLootBagsApi.INSTANCE.getBagManager().addBagType(new ResourceLocation("openlootbags", "rare"), 50900);
        OpenLootBagsApi.INSTANCE.getBagManager().addBagType(new ResourceLocation("openlootbags", "epic"), 20600);

        LootManager.init();


        proxy.registerRenders();
        NetworkRegistry.INSTANCE.registerGuiHandler(OpenLootBags.MOD_ID, new GuiHandler());
    }
}
