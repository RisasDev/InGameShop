package dev.risas.ingameshop.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopListener implements Listener {

    @EventHandler
    private void onShopCategoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        if (event.getInventory() != null) {
            if (ShopCategorySetting.hasShopCategorySetting(player, ShopCategorySettingType.ADD_ITEMS)) {
                ShopCategorySettingType type = ShopCategorySettingType.ADD_ITEMS;
                ShopCategory shopCategory = ShopCategorySetting.getShopCategorySetting(player, type);

                if (shopCategory != null) {
                    Inventory inventory = event.getInventory();
                    ItemStack[] contents = inventory.getContents().clone();

                    for (int i = 0; i < inventory.getSize(); i++) {
                        if (contents[i] == null) continue;

                        ShopCategoryItem shopCategoryItem = shopCategoryItemManager.getShopCategoryItem(shopCategory, i);

                        if (shopCategoryItem != null) continue;

                        ItemStack itemStack = contents[i].clone();
                        shopCategoryItemManager.addShopCategoryItem(shopCategory, itemStack, i);
                    }

                    ChatUtil.sendMessage(player, "&aYou have successfully edited the category!");
                }

                ShopCategorySetting.removeShopCategorySetting(player, type);

                TaskUtil.runLater(plugin, () -> {
                    ShopCategoryEditMenu shopCategoryEditMenu = new ShopCategoryEditMenu(plugin, shopCategory);
                    shopCategoryEditMenu.openMenu(player);
                }, 1L);
            }
        }
    }
}
