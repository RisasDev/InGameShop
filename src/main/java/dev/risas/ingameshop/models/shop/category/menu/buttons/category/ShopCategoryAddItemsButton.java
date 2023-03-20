package dev.risas.ingameshop.models.shop.category.menu.buttons.category;

import com.cryptomorin.xseries.XMaterial;
import dev.risas.ingameshop.InGameShop;
import dev.risas.ingameshop.models.menu.MenuManager;
import dev.risas.ingameshop.models.menu.button.Button;
import dev.risas.ingameshop.models.shop.category.ShopCategory;
import dev.risas.ingameshop.models.shop.category.ShopCategoryManager;
import dev.risas.ingameshop.models.shop.category.menu.ShopCategoryAddItemsMenu;
import dev.risas.ingameshop.utilities.item.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Risas
 * Project: InGameShop
 * Date: 10-08-2022
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */

public class ShopCategoryAddItemsButton extends Button {

    private final ShopCategory shopCategory;
    private final MenuManager menuManager;
    private final ShopCategoryManager shopCategoryManager;

    public ShopCategoryAddItemsButton(InGameShop plugin, ShopCategory shopCategory) {
        this.shopCategory = shopCategory;
        this.menuManager = plugin.getMenuManager();
        this.shopCategoryManager = plugin.getShopCategoryManager();
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(XMaterial.CHEST.parseMaterial())
                .setName("&aAdd Category Items")
                .setLore("&eClick to open add items menu.")
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        menuManager.openMenu(player, new ShopCategoryAddItemsMenu(shopCategory));
        playNeutral(player);

        shopCategoryManager.setEditor(player, shopCategory, "addItemsEditor");
    }
}
