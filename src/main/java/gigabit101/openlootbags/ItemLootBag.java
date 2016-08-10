package gigabit101.openlootbags;

import gigabit101.openlootbags.api.IColorable;
import gigabit101.openlootbags.api.OpenLootBagsApi;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import reborncore.common.util.ItemNBTHelper;

import java.util.List;

/**
 * Created by Gigabit101 on 02/08/2016.
 */
public class ItemLootBag extends Item implements IColorable
{
    private static final String TAG_ITEMS = "InvItems";
    private static final String TAG_SLOT = "Slot";

    public ItemLootBag()
    {
        setMaxStackSize(1);
        setUnlocalizedName(OpenLootBags.MOD_NAME.toLowerCase() + ".lootbag");
        setRegistryName("lootbag");
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> subItems)
    {
        for (int meta = 0; meta < OpenLootBagsApi.BAG_TYPES.size(); ++meta)
        {
            ItemStack stack = new ItemStack(item, 1, meta);
            String name = OpenLootBagsApi.BAG_TYPES.get(meta);
            ItemNBTHelper.setString(stack, "type", name);
            ItemNBTHelper.setInt(stack, "colour", OpenLootBagsApi.BAG_TYPES_MAP.get(name));
            subItems.add(stack);
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        OpenLootBagsApi.populateBag(itemStackIn, worldIn);
        playerIn.openGui(OpenLootBags.instance, GuiHandler.bagID, worldIn, 0, 0, 0);
        return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStackIn);
    }

    public static ItemStack[] loadStacks(ItemStack stack)
    {
        NBTTagList var2 = ItemNBTHelper.getList(stack, TAG_ITEMS, 10, false);
        ItemStack[] inventorySlots = new ItemStack[5];
        for(int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = var2.getCompoundTagAt(var3);
            byte var5 = var4.getByte(TAG_SLOT);
            if(var5 >= 0 && var5 < inventorySlots.length)
            {
                inventorySlots[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }
        return inventorySlots;
    }

    public static void setStacks(ItemStack stack, ItemStack[] inventorySlots)
    {
        NBTTagList var2 = new NBTTagList();
        for(int var3 = 0; var3 < inventorySlots.length; ++var3)
        {
            if (inventorySlots[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte(TAG_SLOT, (byte) var3);
                inventorySlots[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
            ItemNBTHelper.setList(stack, TAG_ITEMS, var2);
        }
    }

    @Override
    public int getColorFromItemStack(ItemStack par1ItemStack, int par2)
    {
        return getColor(par1ItemStack);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        tooltip.add(TextFormatting.DARK_PURPLE + "Whats Inside?");
        if(getName(stack) != null)
        {
            tooltip.add(TextFormatting.GREEN + getName(stack).toUpperCase());
        }
    }

    public int getColor(ItemStack stack)
    {
        int colour = ItemNBTHelper.getInt(stack, "colour", 0);
        return colour;
    }

    public String getName(ItemStack stack)
    {
        String name = ItemNBTHelper.getString(stack, "type", "");
        return name;
    }
}