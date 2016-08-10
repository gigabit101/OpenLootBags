package gigabit101.openlootbags.proxy;

import gigabit101.openlootbags.OpenLootBags;
import gigabit101.openlootbags.api.IColorable;
import gigabit101.openlootbags.api.OpenLootBagsApi;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by Gigabit101 on 02/08/2016.
 */
public class ClientProxy extends CommonProxy
{
    @Override
    public void registerRenders()
    {
        int i;
        for (i = 0; i < OpenLootBagsApi.INSTANCE.getBagManager().getBagTypes().size(); ++i)
        {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(OpenLootBags.lootbag, i, new ModelResourceLocation("openlootbags" + ":" + "lootbag", "inventory"));
        }

        ItemColors items = Minecraft.getMinecraft().getItemColors();

        items.registerItemColorHandler(new IItemColor()
        {
            @Override
            public int getColorFromItemstack(ItemStack stack, int tintIndex)
            {
                Item item = stack.getItem();
                if (item instanceof IColorable)
                {
                    return ((IColorable) item).getColorFromItemStack(stack, tintIndex);
                }
                else
                {
                    return 0xFFFFFF;
                }
            }
        }, OpenLootBags.lootbag);
    }
}
