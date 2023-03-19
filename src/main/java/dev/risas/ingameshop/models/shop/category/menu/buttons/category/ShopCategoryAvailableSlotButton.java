package dev.risas.ingameshop.models.shop.category.menu.buttons.category;

import com.cryptomorin.xseries.XMaterial;
import dev.risas.ingameshop.InGameShop;
import dev.risas.ingameshop.models.menu.MenuManager;
import dev.risas.ingameshop.models.menu.button.Button;
import dev.risas.ingameshop.models.shop.category.menu.ShopCategoryItemEditMenu;
import dev.risas.ingameshop.models.shop.item.ShopCategoryItem;
import dev.risas.ingameshop.models.shop.item.ShopCategoryItemManager;
import dev.risas.ingameshop.utilities.item.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Risas
 * Project: InGameShop
 * Date: 26-01-2023
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */

public class ShopCategoryAvailableSlotButton extends Button {

    private final InGameShop plugin;
    private final ShopCategoryItem shopCategoryItem;
    private final ShopCategoryItemManager shopCategoryItemManager;
    private final MenuManager menuManager;

    public ShopCategoryAvailableSlotButton(InGameShop plugin, ShopCategoryItem shopCategoryItem) {
        this.plugin = plugin;
        this.shopCategoryItem = shopCategoryItem;
        this.shopCategoryItemManager = plugin.getShopCategoryItemManager();
        this.menuManager = plugin.getMenuManager();
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(XMaterial.LIME_STAINED_GLASS_PANE.parseMaterial())
                .setName("&aAvailable Slot")
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        playSuccess(player);

        shopCategoryItemManager.removeCategoryItem(shopCategoryItem.getCategory(), shopCategoryItem);

        shopCategoryItem.setSlot(slot);
        shopCategoryItem.save();

        shopCategoryItem.getCategory().getItems().put(slot, shopCategoryItem);

        menuManager.openMenu(player, new ShopCategoryItemEditMenu(plugin, shopCategoryItem));
    }
}
