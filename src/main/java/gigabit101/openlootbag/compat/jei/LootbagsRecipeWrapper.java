package gigabit101.openlootbag.compat.jei;

import com.google.common.collect.ImmutableList;
import gigabit101.openlootbag.OpenLootBags;
import gigabit101.openlootbag.api.LootMap;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Gigabit101 on 10/08/2016.
 */
public class LootbagsRecipeWrapper implements IRecipeWrapper
{
    private final List inputs;
    private final ItemStack outputs;

    public LootbagsRecipeWrapper(LootMap map)
    {
        ImmutableList.Builder builder = ImmutableList.builder();

        builder.add(new ItemStack(OpenLootBags.lootbag));
        inputs = builder.build();
        outputs = map.getStack();
    }

    @Override
    public List getInputs() {
        return inputs;
    }

    @Override
    public List getOutputs() {
        return ImmutableList.of(outputs);
    }

    @Override
    public List<FluidStack> getFluidInputs() {
        return ImmutableList.of();
    }

    @Override
    public List<FluidStack> getFluidOutputs() {
        return ImmutableList.of();
    }

    @Override
    public void drawInfo(@Nonnull Minecraft minecraft, int i, int i1, int i2, int i3) {}

    @Override
    public void drawAnimations(@Nonnull Minecraft minecraft, int i, int i1) {}

    @Nullable
    @Override
    public List<String> getTooltipStrings(int i, int i1) {
        return null;
    }

    @Override
    public boolean handleClick(@Nonnull Minecraft minecraft, int i, int i1, int i2) {
        return false;
    }
}
