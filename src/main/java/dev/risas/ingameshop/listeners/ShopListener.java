package dev.risas.ingameshop.listeners;

import dev.risas.ingameshop.InGameShop;
import dev.risas.ingameshop.models.menu.MenuManager;
import dev.risas.ingameshop.models.shop.category.ShopCategory;
import dev.risas.ingameshop.models.shop.category.ShopCategoryManager;
import dev.risas.ingameshop.models.shop.category.menu.ShopCategoryEditMenu;
import dev.risas.ingameshop.models.shop.item.ShopCategoryItem;
import dev.risas.ingameshop.models.shop.item.ShopCategoryItemManager;
import dev.risas.ingameshop.utilities.ChatUtil;
import dev.risas.ingameshop.utilities.TaskUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopListener implements Listener {

    private final InGameShop plugin;
    private final ShopCategoryManager shopCategoryManager;
    private final ShopCategoryItemManager shopCategoryItemManager;
    private final MenuManager menuManager;

    public ShopListener(InGameShop plugin) {
        this.plugin = plugin;
        this.shopCategoryManager = plugin.getShopCategoryManager();
        this.shopCategoryItemManager = plugin.getShopCategoryItemManager();
        this.menuManager = plugin.getMenuManager();
    }

    @EventHandler
    private void onShopCategoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        if (event.getInventory() != null) {
            if (shopCategoryManager.hasEditor(player, "addItemsEditor")) {
                ShopCategory shopCategory = shopCategoryManager.getEditor(player);

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

                shopCategoryManager.removeEditor(player, "addItemsEditor");

                TaskUtil.runLater(plugin, () -> menuManager.openMenu(player, new ShopCategoryEditMenu(plugin, shopCategory)), 1L);
            }
        }
    }
}
