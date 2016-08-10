package gigabit101.OpenLootBags.api;

import java.util.*;

/**
 * Created by Gigabit101 on 02/08/2016.
 */
public class OpenLootBagsApi
{
    public static final List<String> BAG_TYPES = new ArrayList<String>();
    public static final Map<String, Integer> BAG_TYPES_MAP = new HashMap<String, Integer>();

    public static void addBagType(String bagname, Integer bagcolour)
    {
        BAG_TYPES_MAP.put(bagname, bagcolour);
        BAG_TYPES.add(bagname);
    }
}
