package gigabit101.openlootbags.api;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Gigabit101 on 10/08/2016.
 */
public class LootMap
{
    public ResourceLocation name;
    ItemStack stack;
    int chance;

    public LootMap(ResourceLocation name, ItemStack stack, int chance)
    {
        this.name = name;
        this.stack = stack;
        this.chance = chance;
    }

    public ResourceLocation getName()
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
