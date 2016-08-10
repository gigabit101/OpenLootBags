package gigabit101.openlootbags;

import gigabit101.openlootbags.api.IBagManager;
import gigabit101.openlootbags.api.LootMap;
import gigabit101.openlootbags.api.OpenLootBagsApi;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.*;

/**
 * Created by Mark on 10/08/2016.
 */
public class BagManger implements IBagManager {

    public final List<ResourceLocation> BAG_TYPES = new ArrayList<ResourceLocation>();
    public final Map<ResourceLocation, Integer> BAG_TYPES_MAP = new HashMap<ResourceLocation, Integer>();

    public List<LootMap> lootMaps = new ArrayList<LootMap>();

    @Override
    public void addBagType(ResourceLocation bagname, int bagcolour) {
        for(ResourceLocation location : BAG_TYPES){
            if(location.equals(bagname)){
                throw new RuntimeException(bagname + " all ready exits");
            }
        }
        BAG_TYPES_MAP.put(bagname, bagcolour);
        BAG_TYPES.add(bagname);
    }

    @Override
    public LootMap addLoot(ResourceLocation bagname, ItemStack stack, int chance) {
        LootMap loot = new LootMap(bagname, stack, chance);
        lootMaps.add(loot);
        return loot;
    }

    @Override
    public List<ItemStack> getBagLoot(ItemStack stack) {
        if(stack.getItem() instanceof ItemLootBag)
        {
            ItemLootBag bag = (ItemLootBag) stack.getItem();
            ResourceLocation name = bag.getName(stack);
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
    public List<ResourceLocation> getBagTypes() {
        return Collections.unmodifiableList(BAG_TYPES);
    }

    @Override
    public Map<ResourceLocation, Integer> getBagColorMap() {
        return Collections.unmodifiableMap(BAG_TYPES_MAP);
    }

    //TODO make this cleaner and use chance
    public static void populateBag(ItemStack stack, World world)
    {
        if(stack.getItem() instanceof ItemLootBag)
        {
            ItemLootBag bag = (ItemLootBag) stack.getItem();
            ResourceLocation name = bag.getName(stack);
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
