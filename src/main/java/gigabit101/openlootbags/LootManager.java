package gigabit101.openlootbags;

import gigabit101.openlootbags.api.OpenLootBagsApi;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Created by Gigabit101 on 10/08/2016.
 */
public class LootManager
{
    public static void init()
    {
        //Default loot
        //common
        OpenLootBagsApi.INSTANCE.getBagManager().addLoot("common", new ItemStack(Items.COAL), 20);
        OpenLootBagsApi.INSTANCE.getBagManager().addLoot("common", new ItemStack(Items.REDSTONE), 10);
        OpenLootBagsApi.INSTANCE.getBagManager().addLoot("common", new ItemStack(Items.LEATHER), 10);
        OpenLootBagsApi.INSTANCE.getBagManager().addLoot("common", new ItemStack(Items.WHEAT), 50);
        OpenLootBagsApi.INSTANCE.getBagManager().addLoot("common", new ItemStack(Items.APPLE), 40);
        OpenLootBagsApi.INSTANCE.getBagManager().addLoot("common", new ItemStack(Items.STICK), 80);
        //uncommon
        OpenLootBagsApi.INSTANCE.getBagManager().addLoot("uncommon", new ItemStack(Items.BUCKET), 16);
        OpenLootBagsApi.INSTANCE.getBagManager().addLoot("uncommon", new ItemStack(Items.BUCKET), 6);

        //rare
        OpenLootBagsApi.INSTANCE.getBagManager().addLoot("rare", new ItemStack(Items.BLAZE_ROD), 20);
        OpenLootBagsApi.INSTANCE.getBagManager().addLoot("rare", new ItemStack(Items.CARROT_ON_A_STICK), 60);

        //epic
        OpenLootBagsApi.INSTANCE.getBagManager().addLoot("epic", new ItemStack(Items.EMERALD), 10);
        OpenLootBagsApi.INSTANCE.getBagManager().addLoot("epic", new ItemStack(Items.DIAMOND), 10);
        OpenLootBagsApi.INSTANCE.getBagManager().addLoot("epic", new ItemStack(Items.DIAMOND_HOE), 20);
    }
}
