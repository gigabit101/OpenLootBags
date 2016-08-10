package gigabit101.OpenLootBags.compat.jei;

import gigabit101.OpenLootBags.OpenLootBags;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import reborncore.client.guibuilder.GuiBuilder;

import javax.annotation.Nonnull;

/**
 * Created by Gigabit101 on 10/08/2016.
 */
public class LootbagsRecipeCategory implements IRecipeCategory
{
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    String title;
    public static final ResourceLocation texture = GuiBuilder.defaultTextureSheet;
    private final IDrawable background;

    public LootbagsRecipeCategory(IGuiHelper guiHelper)
    {
        background = guiHelper.createDrawable(texture, 45, 28, 86, 39);
        title = I18n.translateToLocal("openlootbags.jei.category.lootbags");
    }

    @Nonnull
    @Override
    public String getUid() {
        return OpenLootbagsJEIPlugin.LOOTBAGS_ID;
    }

    @Nonnull
    @Override
    public String getTitle() {
        return title;
    }

    @Nonnull
    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void drawExtras(@Nonnull Minecraft minecraft) {

    }

    @Override
    public void drawAnimations(@Nonnull Minecraft minecraft) {

    }
    @SuppressWarnings("unchecked")
    @Override
    public void setRecipe(@Nonnull IRecipeLayout iRecipeLayout, @Nonnull IRecipeWrapper iRecipeWrapper) {
        iRecipeLayout.getItemStacks().init(INPUT_SLOT, true, 2, 6);
        iRecipeLayout.getItemStacks().init(OUTPUT_SLOT, false, 62, 6);
        if (iRecipeWrapper instanceof LootbagsRecipeWrapper)
        {
            LootbagsRecipeWrapper wrapper = (LootbagsRecipeWrapper) iRecipeWrapper;
            iRecipeLayout.getItemStacks().set(INPUT_SLOT, wrapper.getInputs());
            iRecipeLayout.getItemStacks().set(OUTPUT_SLOT, wrapper.getOutputs());
        }
    }
}
