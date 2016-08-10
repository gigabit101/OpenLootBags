package gigabit101.openlootbags.compat.jei;

import gigabit101.openlootbags.api.OpenLootBagsApi;
import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModRegistry;

import javax.annotation.Nonnull;

/**
 * Created by Gigabit101 on 10/08/2016.
 */
@mezz.jei.api.JEIPlugin
public class OpenLootbagsJEIPlugin extends BlankModPlugin
{
    public static final String LOOTBAGS_ID = "lootbags";
    @Override
    public void register(@Nonnull IModRegistry registry)
    {
        IJeiHelpers jeiHelpers = registry.getJeiHelpers();

        registry.addRecipeCategories(new LootbagsRecipeCategory(jeiHelpers.getGuiHelper()));
        registry.addRecipeHandlers(new LootbagsRecipeHandler());

        registry.addRecipes(OpenLootBagsApi.lootMaps);
    }
}
