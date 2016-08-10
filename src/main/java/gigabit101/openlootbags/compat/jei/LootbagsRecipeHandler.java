package gigabit101.openlootbags.compat.jei;

import gigabit101.openlootbags.api.LootMap;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

import javax.annotation.Nonnull;

/**
 * Created by Gigabit101 on 10/08/2016.
 */
public class LootbagsRecipeHandler implements IRecipeHandler<LootMap>
{
    @Nonnull
    @Override
    public Class<LootMap> getRecipeClass() {
        return LootMap.class;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid() {
        return OpenLootbagsJEIPlugin.LOOTBAGS_ID;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid(@Nonnull LootMap lootMap) {
        return OpenLootbagsJEIPlugin.LOOTBAGS_ID;
    }

    @Nonnull
    @Override
    public IRecipeWrapper getRecipeWrapper(@Nonnull LootMap lootMap) {
        return new LootbagsRecipeWrapper(lootMap);
    }

    @Override
    public boolean isRecipeValid(@Nonnull LootMap lootMap) {
        return true;
    }
}
