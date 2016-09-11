package gigabit101.openlootbags;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gigabit101.openlootbags.api.LootMap;
import gigabit101.openlootbags.api.OpenLootBagsApi;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.FileUtils;
import reborncore.common.util.serialization.SerializationUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gigabit101 on 10/08/2016.
 */
public class LootManager {

	public static File bagFile;
	public static File lootFile;

	public static void initDefaultBags() {
		//Default bags
		OpenLootBagsApi.INSTANCE.getBagManager().addBagType(new ResourceLocation("openlootbags", "common"), 10000);
		OpenLootBagsApi.INSTANCE.getBagManager().addBagType(new ResourceLocation("openlootbags", "uncommon"), 90500);
		OpenLootBagsApi.INSTANCE.getBagManager().addBagType(new ResourceLocation("openlootbags", "rare"), 50900);
		OpenLootBagsApi.INSTANCE.getBagManager().addBagType(new ResourceLocation("openlootbags", "epic"), 20600);
	}

	public static void initDefaultLoot() {
		//Default loot
		//common
		OpenLootBagsApi.INSTANCE.getBagManager().addLoot(new ResourceLocation("openlootbags", "common"), new ItemStack(Items.COAL), 20);
		OpenLootBagsApi.INSTANCE.getBagManager().addLoot(new ResourceLocation("openlootbags", "common"), new ItemStack(Items.REDSTONE), 10);
		OpenLootBagsApi.INSTANCE.getBagManager().addLoot(new ResourceLocation("openlootbags", "common"), new ItemStack(Items.LEATHER), 10);
		OpenLootBagsApi.INSTANCE.getBagManager().addLoot(new ResourceLocation("openlootbags", "common"), new ItemStack(Items.WHEAT), 50);
		OpenLootBagsApi.INSTANCE.getBagManager().addLoot(new ResourceLocation("openlootbags", "common"), new ItemStack(Items.APPLE), 40);
		OpenLootBagsApi.INSTANCE.getBagManager().addLoot(new ResourceLocation("openlootbags", "common"), new ItemStack(Items.STICK), 80);

		//uncommon
		OpenLootBagsApi.INSTANCE.getBagManager().addLoot(new ResourceLocation("openlootbags", "uncommon"), new ItemStack(Items.BUCKET), 16);
		OpenLootBagsApi.INSTANCE.getBagManager().addLoot(new ResourceLocation("openlootbags", "uncommon"), new ItemStack(Items.ITEM_FRAME), 16);
		OpenLootBagsApi.INSTANCE.getBagManager().addLoot(new ResourceLocation("openlootbags", "uncommon"), new ItemStack(Items.CARROT), 16);
		OpenLootBagsApi.INSTANCE.getBagManager().addLoot(new ResourceLocation("openlootbags", "uncommon"), new ItemStack(Items.POTATO), 16);
		OpenLootBagsApi.INSTANCE.getBagManager().addLoot(new ResourceLocation("openlootbags", "uncommon"), new ItemStack(Items.COOKED_PORKCHOP), 16);

		//rare
		OpenLootBagsApi.INSTANCE.getBagManager().addLoot(new ResourceLocation("openlootbags", "rare"), new ItemStack(Items.BLAZE_ROD), 20);
		OpenLootBagsApi.INSTANCE.getBagManager().addLoot(new ResourceLocation("openlootbags", "rare"), new ItemStack(Items.CARROT_ON_A_STICK), 60);
		OpenLootBagsApi.INSTANCE.getBagManager().addLoot(new ResourceLocation("openlootbags", "rare"), new ItemStack(Items.GHAST_TEAR), 60);
		OpenLootBagsApi.INSTANCE.getBagManager().addLoot(new ResourceLocation("openlootbags", "rare"), new ItemStack(Items.SLIME_BALL), 60);

		//epic
		OpenLootBagsApi.INSTANCE.getBagManager().addLoot(new ResourceLocation("openlootbags", "epic"), new ItemStack(Items.EMERALD), 10);
		OpenLootBagsApi.INSTANCE.getBagManager().addLoot(new ResourceLocation("openlootbags", "epic"), new ItemStack(Items.DIAMOND), 10);
		OpenLootBagsApi.INSTANCE.getBagManager().addLoot(new ResourceLocation("openlootbags", "epic"), new ItemStack(Items.DIAMOND_HOE), 20);
	}

	public static void loadConfig() throws IOException {
		Gson gson = SerializationUtil.GSON;
		if (!bagFile.exists()) {
			initDefaultBags();
			saveBags();
		} else {
			Type typeOfHashMap = new TypeToken<List<LootBagInfo>>() {}.getType();
			List<LootBagInfo> bags = gson.fromJson(new BufferedReader(new FileReader(bagFile)), typeOfHashMap);
			for(LootBagInfo bagInfo : bags){
				OpenLootBagsApi.INSTANCE.getBagManager().addBagType(bagInfo.getName(), bagInfo.color);
			}
		}
		if (!lootFile.exists()) {
			initDefaultLoot();
			saveLoot();
		} else {
			Type typeOfHashMap = new TypeToken<List<LootMap>>() {}.getType();
			List<LootMap> bags = gson.fromJson(new BufferedReader(new FileReader(lootFile)), typeOfHashMap);
			for(LootMap bagInfo : bags){
				OpenLootBagsApi.INSTANCE.getBagManager().addLoot(bagInfo.getName(), bagInfo.getStack(), bagInfo.getChance());
			}
		}

		System.out.println("Loaded " + OpenLootBagsApi.INSTANCE.getBagManager().getAllLootMaps().size() + " lootMaps into " + OpenLootBagsApi.INSTANCE.getBagManager().getBagTypes().size() + " bag types");
	}

	public static void saveBags() throws IOException {
		if(bagFile.exists()){
			bagFile.delete();
		}
		Gson gson = SerializationUtil.GSON;
		List<LootBagInfo> bags = new ArrayList<>();
		for (ResourceLocation resourceLocation : OpenLootBagsApi.INSTANCE.getBagManager().getBagTypes()) {
			bags.add(new LootBagInfo(resourceLocation, OpenLootBagsApi.INSTANCE.getBagManager().getBagColorMap().get(resourceLocation)));
		}
		FileUtils.writeStringToFile(bagFile, gson.toJson(bags));
	}

	public static void saveLoot() throws IOException {
		if(lootFile.exists()){
			lootFile.delete();
		}
		Gson gson = SerializationUtil.GSON;
		List<LootMap> loot = OpenLootBagsApi.INSTANCE.getBagManager().getAllLootMaps();
		FileUtils.writeStringToFile(lootFile, gson.toJson(loot));
	}


	private static class LootBagInfo {
		ResourceLocation name;
		int color;

		public LootBagInfo(ResourceLocation name, int color) {
			this.name = name;
			this.color = color;
		}

		public ResourceLocation getName() {
			return name;
		}

		public void setName(ResourceLocation name) {
			this.name = name;
		}

		public int getColor() {
			return color;
		}

		public void setColor(int color) {
			this.color = color;
		}
	}

}
