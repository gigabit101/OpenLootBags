package gigabit101.openlootbags;

import gigabit101.openlootbags.api.OpenLootBagsApi;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import reborncore.common.util.ItemNBTHelper;

import java.util.Random;

/**
 * Created by Gigabit101 on 11/09/2016.
 */
public class MobDropHandler
{
    public static Random random = new Random();

    public static MobDropHandler instance = new MobDropHandler();

    @SubscribeEvent
    public void onEntityDrop(LivingDropsEvent event)
    {
        if(event.getSource().getEntity() == null ||  event.getSource().getEntity() instanceof FakePlayer || !(event.getSource().getEntity() instanceof EntityPlayer))
            return;

        //This is bad remove at some point
        //set NBT to stack
        if(random.nextInt(10) == 1)
        {
            int type = random.nextInt(OpenLootBagsApi.INSTANCE.getBagManager().getBagTypes().size());
            ItemStack stack = new ItemStack(OpenLootBags.lootbag, 1, type);
            ResourceLocation name = OpenLootBagsApi.INSTANCE.getBagManager().getBagTypes().get(type);
            ItemNBTHelper.setString(stack, "typeName", name.getResourcePath());
            ItemNBTHelper.setString(stack, "typeDomain", name.getResourceDomain());
            ItemNBTHelper.setInt(stack, "colour", OpenLootBagsApi.INSTANCE.getBagManager().getBagColorMap().get(name));

            event.getEntityLiving().entityDropItem(stack, random.nextInt(2) + 1);
        }
    }
}
