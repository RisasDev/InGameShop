package dev.risas.ingameshop.models.shop.category.menu.buttons;

import dev.risas.ingameshop.InGameShop;
import dev.risas.ingameshop.models.menu.MenuManager;
import dev.risas.ingameshop.models.menu.button.Button;
import dev.risas.ingameshop.models.shop.category.ShopCategory;
import dev.risas.ingameshop.models.shop.category.menu.ShopCategoryMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class ShopButton extends Button {

    private final InGameShop plugin;
    private final ShopCategory shopCategory;
    private final MenuManager menuManager;

    public ShopButton(InGameShop plugin, ShopCategory shopCategory) {
        this.plugin = plugin;
        this.shopCategory = shopCategory;
        this.menuManager = plugin.getMenuManager();
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return shopCategory.getMenuItem().clone();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        menuManager.openMenu(player, new ShopCategoryMenu(plugin, shopCategory));
    }
}
