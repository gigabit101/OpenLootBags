package gigabit101.OpenLootBags.api;

import gigabit101.OpenLootBags.ItemLootBag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.*;

/**
 * Created by Gigabit101 on 02/08/2016.
 */
public class OpenLootBagsApi
{
    public static final List<String> BAG_TYPES = new ArrayList<String>();
    public static final Map<String, Integer> BAG_TYPES_MAP = new HashMap<String, Integer>();

    public static List<LootMap> lootMaps = new ArrayList<LootMap>();

    public static void addBagType(String bagname, Integer bagcolour)
    {
        BAG_TYPES_MAP.put(bagname, bagcolour);
        BAG_TYPES.add(bagname);
    }

    public static LootMap addLoot(String bagname, ItemStack stack, int chance)
    {
        LootMap loot = new LootMap(bagname, stack, chance);
        lootMaps.add(loot);
        return loot;
    }

    //TODO make this cleaner and use chance
    public static void populateBag(ItemStack stack, World world)
    {
        if(stack.getItem() instanceof ItemLootBag)
        {
            ItemLootBag bag = (ItemLootBag) stack.getItem();
            String name = bag.getName(stack);
            ItemStack[] bagInv = bag.loadStacks(stack);
            for(int i = 0; i < 5; i++)
            {
                ItemStack stackAt = bagInv[i];
                boolean didChange = false;
                if (stackAt == null)
                {
                    for(LootMap map : OpenLootBagsApi.lootMaps)
                    {
                        if(map.name.matches(name))
                        {
                            int random = world.rand.nextInt(OpenLootBagsApi.lootMaps.size());
                            bagInv[i] = OpenLootBagsApi.lootMaps.get(random).getStack();
                            didChange = true;
                            break;
                        }
                    }
                }
                if (didChange)
                {
                    ItemLootBag.setStacks(stack, bagInv);
                }
            }
        }
    }

    //This might work
    public static List<ItemStack> getBagLoot(ItemStack stack)
    {
        if(stack.getItem() instanceof ItemLootBag)
        {
            ItemLootBag bag = (ItemLootBag) stack.getItem();
            String name = bag.getName(stack);
            for(LootMap map : OpenLootBagsApi.lootMaps)
            {
                if (name.matches(map.getName()))
                {
                    List<ItemStack> stackList = new ArrayList<ItemStack>();
                    stackList.add(map.getStack());
                    return stackList;
                }
            }
        }
        return null;
    }
}
