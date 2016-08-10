package gigabit101.openlootbags.api;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;

/**
 * Created by Gigabit101 on 02/08/2016.
 */
public class OpenLootBagsApi
{

    public static final OpenLootBagsApi INSTANCE = new OpenLootBagsApi();

    IBagManager bagManager;


    /**
     * Gets the {@Link gigabit101.openlootbags.api.IBagManager} instance that is being used
     *
     * @return The bag manager
     */
    public IBagManager getBagManager() {
        return bagManager;
    }

    /**
     * Internal call to set the bag manager, will crash if you try anc call this from your mod
     *
     * @param bagManager The instance of the bag manager
     */
    public void setBagManager(IBagManager bagManager) {
        ModContainer mc = Loader.instance().activeModContainer();

        if (mc == null || !"openlootbags".equals(mc.getModId())) {
            throw new IllegalAccessError("Api cannot be set using " + mc);
        }
        this.bagManager = bagManager;
    }

}
