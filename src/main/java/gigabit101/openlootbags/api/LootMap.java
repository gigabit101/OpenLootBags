package gigabit101.openlootbags.api;

import net.minecraft.item.ItemStack;

/**
 * Created by Gigabit101 on 10/08/2016.
 */
public class LootMap
{
    String name;
    ItemStack stack;
    int chance;

    public LootMap(String name, ItemStack stack, int chance)
    {
        this.name = name;
        this.stack = stack;
        this.chance = chance;
    }

    public String getName()
    {
        return name;
    }

    public ItemStack getStack()
    {
        return stack;
    }

    public int getChance()
    {
        return chance;
    }
}
