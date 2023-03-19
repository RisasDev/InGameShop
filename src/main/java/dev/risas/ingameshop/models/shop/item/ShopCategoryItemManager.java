package dev.risas.ingameshop.models.shop.item;

import dev.risas.ingameshop.models.shop.category.ShopCategory;
import dev.risas.ingameshop.utilities.BukkitUtil;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class ShopCategoryItemManager {

    private final Set<Integer> shopCategoryItemNames;

    public ShopCategoryItemManager() {
        this.shopCategoryItemNames = new HashSet<>();
    }

    public ShopCategoryItem getShopCategoryItem(ShopCategory shopCategory, int slot) {
        return shopCategory.getItems().get(slot);
    }

    public ShopCategoryItem getShopCategoryItem(ShopCategory shopCategory, String slot) {
        return getShopCategoryItem(shopCategory, Integer.parseInt(slot));
    }

    public boolean exists(ShopCategory shopCategory, int slot) {
        return shopCategory.getItems().containsKey(slot);
    }

    public boolean exists(ShopCategory shopCategory, String slot) {
        return exists(shopCategory, Integer.parseInt(slot));
    }

    public void addShopCategoryItem(ShopCategory shopCategory, ItemStack itemStack, int slot) {
        ShopCategoryItem shopCategoryItem = new ShopCategoryItem();
        shopCategoryItem.setItem(itemStack);
        shopCategoryItem.setType(ShopCategoryItemType.BUY_AND_SELL);
        shopCategoryItem.setSlot(slot);
        shopCategoryItem.setCategory(shopCategory);
        shopCategoryItem.setBuyPrice(0);
        shopCategoryItem.setSellPrice(0);
        shopCategoryItem.save();

        shopCategory.getItems().put(slot, shopCategoryItem);
    }

    public void removeCategoryItem(ShopCategory shopCategory, ShopCategoryItem shopCategoryItem) {
        ConfigurationSection section = shopCategory.getFile().getConfiguration().getConfigurationSection("menu-items");

        section.set(String.valueOf(shopCategoryItem.getSlot()), null);

        shopCategory.getFile().save();
        shopCategory.getFile().reload();

        shopCategory.getItems().remove(shopCategoryItem.getSlot(), shopCategoryItem);
    }

    public void loadOrCreateShopCategoryItem(ShopCategory shopCategory, String shopCategoryItemName, ConfigurationSection section) {
        ShopCategoryItem shopCategoryItem = exists(shopCategory, shopCategoryItemName) ? getShopCategoryItem(shopCategory, shopCategoryItemName) : new ShopCategoryItem();
        shopCategoryItem.setCategory(shopCategory);
        shopCategoryItem.setItem(BukkitUtil.deserializeItemStack(section.getString(shopCategoryItemName + ".item")));
        shopCategoryItem.setType(ShopCategoryItemType.valueOf(section.getString(shopCategoryItemName + ".type")));
        shopCategoryItem.setSlot(Integer.parseInt(shopCategoryItemName));
        shopCategoryItem.setBuyPrice(section.getInt(shopCategoryItemName + ".price.buy"));
        shopCategoryItem.setSellPrice(section.getInt(shopCategoryItemName + ".price.sell"));

        if (!exists(shopCategory, shopCategoryItemName)) shopCategory.getItems().put(shopCategoryItem.getSlot(), shopCategoryItem);
    }

    public void loadOrRefresh(ShopCategory shopCategory) {
        ConfigurationSection section = shopCategory.getFile().getConfiguration().getConfigurationSection("menu-items");

        if (section != null) {
            for (String shopCategoryItemName : section.getKeys(false)) {
                loadOrCreateShopCategoryItem(shopCategory, shopCategoryItemName, section);
            }

            for (ShopCategoryItem shopCategoryItem : shopCategory.getItems().values()) {
                String shopCategoryItemName = String.valueOf(shopCategoryItem.getSlot());

                if (!section.contains(shopCategoryItemName)) {
                    shopCategoryItemNames.add(shopCategoryItem.getSlot());
                }
            }

            shopCategoryItemNames.forEach(shopCategory.getItems()::remove);
            shopCategoryItemNames.clear();
        }
    }
}
