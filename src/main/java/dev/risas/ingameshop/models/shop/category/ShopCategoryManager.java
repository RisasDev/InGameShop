package dev.risas.ingameshop.models.shop.category;

import dev.risas.ingameshop.InGameShop;
import dev.risas.ingameshop.models.shop.item.ShopCategoryItemManager;
import dev.risas.ingameshop.utilities.file.FileConfig;
import dev.risas.ingameshop.utilities.item.ItemBuilder;
import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ShopCategoryManager {

    private final InGameShop plugin;

    @Getter
    private final Map<String, ShopCategory> shopCategories;
    private final Set<String> shopCategoryNames;

    private final ShopCategoryItemManager shopCategoryItemManager;

    public ShopCategoryManager(InGameShop plugin) {
        this.plugin = plugin;
        this.shopCategories = new LinkedHashMap<>();
        this.shopCategoryNames = new HashSet<>();
        this.shopCategoryItemManager = plugin.getShopCategoryItemManager();
        this.loadOrRefresh();
    }

    public ShopCategory getCategory(String name) {
        return shopCategories.get(name);
    }

    public boolean exists(String name) {
        return shopCategories.containsKey(name);
    }

    public ShopCategory loadOrCreateShopCategory(String shopCategoryName, ConfigurationSection section) {
        ShopCategory shopCategory = exists(shopCategoryName) ? getCategory(shopCategoryName) : new ShopCategory(shopCategoryName);

        FileConfig fileConfig = new FileConfig(plugin, "shop/categories/" + shopCategoryName + ".yml");
        shopCategory.setFile(fileConfig);
        shopCategory.chargeFile();
        shopCategory.setMenuTitle(shopCategory.getFile().getString("menu.title"));
        shopCategory.setMenuRows(shopCategory.getFile().getInt("menu.rows"));
        shopCategory.setMenuItem(new ItemBuilder(section.getString(shopCategoryName + ".item.material"))
                .setName(section.getString(shopCategoryName + ".item.name"))
                .setLore(section.getStringList(shopCategoryName + ".item.description"))
                .setData(section.getInt(shopCategoryName + ".item.data"))
                .build());
        shopCategory.setMenuItemSlot(section.getInt(shopCategoryName + ".item.slot"));
        return shopCategory;
    }

    public void loadOrRefresh() {
        FileConfig shopFile = plugin.getShopFile();
        ConfigurationSection section = shopFile.getConfiguration().getConfigurationSection("categories");

        for (String shopCategoryName : section.getKeys(false)) {
            ShopCategory shopCategory = loadOrCreateShopCategory(shopCategoryName, section);
            shopCategoryItemManager.loadOrRefresh(shopCategory);

            if (!exists(shopCategoryName)) shopCategories.put(shopCategoryName, shopCategory);
        }

        for (ShopCategory shopCategory : shopCategories.values()) {
            if (!section.contains(shopCategory.getId())) {
                shopCategoryNames.add(shopCategory.getId());
            }
        }

        shopCategoryNames.forEach(shopCategories::remove);
        shopCategoryNames.clear();
    }
}
