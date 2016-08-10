package gigabit101.openlootbags.api;

import net.minecraft.item.ItemStack;

/**
 * Created by Gigabit101 on 02/08/2016.
 */
//TODO move to RebornCore
public interface IColorable
{
    int getColorFromItemStack(ItemStack stack, int tintIndex);
}
