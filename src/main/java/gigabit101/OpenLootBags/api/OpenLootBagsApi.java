package gigabit101.OpenLootBags.api;

import gigabit101.OpenLootBags.ItemLootBag;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.*;

/**
 * Created by Gigabit101 on 02/08/2016.
 */
public class OpenLootBagsApi
{
//    public static final String[] BAG_TYPES = new String[]{"common", "uncommon", "rare", "epic"};
    public static final Map<String, Float> BAG_TYPES_MAP = new HashMap<String, Float>();

    public static void addBagType(String bagname, Float bagcolour)
    {
        BAG_TYPES_MAP.put(bagname, bagcolour);
    }

    public static List<ItemStack> lootstacks = new ArrayList<ItemStack>();

    public static void registerLootStack(ItemStack stack)
    {
        lootstacks.add(stack);
    }

    public static void populateBag(ItemStack bagStack, Random random)
    {
        ItemLootBag bag = (ItemLootBag) bagStack.getItem();
        bag.loadStacks(bagStack);
        if(bag.loadStacks(bagStack) != null)
        {
            int i;
            for(i = 0; i < 5; ++i)
            {
                ItemStack[] bagInv = bag.loadStacks(bagStack);
                ItemStack stackAt = bagInv[0];
//                if(stackAt == null)
//                {
                    stackAt = new ItemStack(Items.DYE);//lootstacks.get(0);
//                }
            }
        }
    }
}
