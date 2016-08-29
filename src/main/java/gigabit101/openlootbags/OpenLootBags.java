package gigabit101.openlootbags;

import gigabit101.openlootbags.api.OpenLootBagsApi;
import gigabit101.openlootbags.proxy.CommonProxy;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import reborncore.RebornRegistry;

import java.util.ArrayList;
import java.util.List;

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

    @Mod.EventHandler
    public void serverstarting(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandOpenLootBags());
    }

    public void addToAll(Item item, int chance)
    {
        List<ResourceLocation> all = new ArrayList<ResourceLocation>();
        all.add(LootTableList.ENTITIES_BLAZE);
        all.add(LootTableList.ENTITIES_CAVE_SPIDER);
        all.add(LootTableList.ENTITIES_CREEPER);
        all.add(LootTableList.ENTITIES_ENDERMAN);
        all.add(LootTableList.ENTITIES_MAGMA_CUBE);
        all.add(LootTableList.ENTITIES_ZOMBIE);
        all.add(LootTableList.ENTITIES_ZOMBIE_HORSE);
        all.add(LootTableList.ENTITIES_ZOMBIE_PIGMAN);
        all.add(LootTableList.ENTITIES_SKELETON);

        int j;
        for (j = 0; j < all.size(); ++j)
        {
            RebornRegistry.addLoot(item, chance, all.get(j));
        }
    }
}
