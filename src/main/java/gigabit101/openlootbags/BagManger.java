package gigabit101.openlootbags;

import gigabit101.openlootbags.api.IBagManager;
import gigabit101.openlootbags.api.LootMap;
import gigabit101.openlootbags.api.OpenLootBagsApi;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.*;

/**
 * Created by Mark on 10/08/2016.
 */
public class BagManger implements IBagManager {

    public final List<String> BAG_TYPES = new ArrayList<String>();
    public final Map<String, Integer> BAG_TYPES_MAP = new HashMap<String, Integer>();

    public List<LootMap> lootMaps = new ArrayList<LootMap>();

    @Override
    public void addBagType(String bagname, int bagcolour) {
        BAG_TYPES_MAP.put(bagname, bagcolour);
        BAG_TYPES.add(bagname);
    }

    @Override
    public LootMap addLoot(String bagname, ItemStack stack, int chance) {
        LootMap loot = new LootMap(bagname, stack, chance);
        lootMaps.add(loot);
        return loot;
    }

    @Override
    public List<ItemStack> getBagLoot(ItemStack stack) {
        if(stack.getItem() instanceof ItemLootBag)
        {
            ItemLootBag bag = (ItemLootBag) stack.getItem();
            String name = bag.getName(stack);
            System.out.println(lootMaps);
            List<ItemStack> stackList = new ArrayList<ItemStack>();
            for(LootMap map : lootMaps)
            {
                if (map.getName().equals(name))
                {
                    stackList.add(map.getStack().copy());
                }
            }
            return stackList;
        }
        return Collections.emptyList();
    }

    @Override
    public List<LootMap> getAllLootMaps() {
        return Collections.unmodifiableList(lootMaps) ;
    }

    @Override
    public List<String> getBagTypes() {
        return Collections.unmodifiableList(BAG_TYPES);
    }

    @Override
    public Map<String, Integer> getBagColorMap() {
        return Collections.unmodifiableMap(BAG_TYPES_MAP);
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
                    for(LootMap map : OpenLootBagsApi.INSTANCE.getBagManager().getAllLootMaps())
                    {
                        if(map.getName().equals(name))
                        {
                            List<ItemStack> stackList = OpenLootBagsApi.INSTANCE.getBagManager().getBagLoot(stack);
                            if(!stackList.isEmpty()){
                                System.out.println(stackList);
                                int random = world.rand.nextInt(stackList.size());
                                //TODO remove debug
                                System.out.print("  Random = " + random);
                                System.out.print("  StackList size = " + stackList.size());

                                bagInv[i] = stackList.get(random).copy();
                                didChange = true;
                                break;
                            }
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
}