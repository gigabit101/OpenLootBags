package gigabit101.openlootbags.api;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

/**
 * An interface to aid with adding bag types
 */
public interface IBagManager {

    /**
     * Adds a new bag type to the mod
     * @param bagname The name of the bag to be added
     * @param bagcolour The hex color of the bag
     */
    void addBagType(ResourceLocation bagname, int bagcolour);

    /**
     * Adds an item stack to a bag
     * @param bagname The name of the bag to add the item stack
     * @param stack The item stack to add to the bag
     * @param chance The chance of the itemstack to show up
     * @return the LootMap
     */
    LootMap addLoot(ResourceLocation bagname, ItemStack stack, int chance);


    /**
     *
     * The gets a list of itemstacks that will be in the bag
     *
     * @param stack The bag to find the itemStacks in
     * @return The list of itemstacks in this bag
     */
    @Nullable List<ItemStack> getBagLoot(ItemStack stack);

    /**
     * @return A list of all the lootmaps in the mod
     */
    List<LootMap> getAllLootMaps();

    /**
     * @return A list of all the bag types in the mod
     */
    List<ResourceLocation> getBagTypes();


    Map<ResourceLocation, Integer> getBagColorMap();
}
