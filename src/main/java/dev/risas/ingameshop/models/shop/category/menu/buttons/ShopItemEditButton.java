package dev.risas.ingameshop.models.shop.category.menu.buttons;

import dev.risas.ingameshop.InGameShop;
import dev.risas.ingameshop.models.menu.MenuManager;
import dev.risas.ingameshop.models.menu.button.Button;
import dev.risas.ingameshop.models.shop.category.menu.ShopCategoryItemEditMenu;
import dev.risas.ingameshop.models.shop.item.ShopCategoryItem;
import dev.risas.ingameshop.models.shop.item.ShopCategoryItemManager;
import dev.risas.ingameshop.utilities.ChatUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ShopItemEditButton extends Button {

    private final InGameShop plugin;
    private final MenuManager menuManager;
    private final ShopCategoryItem shopCategoryItem;
    private final ShopCategoryItemManager shopCategoryItemManager;

    public ShopItemEditButton(InGameShop plugin, ShopCategoryItem shopCategoryItem) {
        this.plugin = plugin;
        this.menuManager = plugin.getMenuManager();
        this.shopCategoryItem = shopCategoryItem;
        this.shopCategoryItemManager = plugin.getShopCategoryItemManager();
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        ItemStack itemStack = shopCategoryItem.getItem().clone();
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setLore(ChatUtil.translate(Arrays.asList(
                "",
                "&eLeft-Click to edit this item.",
                "&eRight-Click to remove this item."
        )));

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (clickType.isRightClick()) {
            playSuccess(player);
            shopCategoryItemManager.removeCategoryItem(shopCategoryItem.getCategory(), shopCategoryItem);
        }
        else if (clickType.isLeftClick()) {
            playNeutral(player);

            menuManager.openMenu(player, new ShopCategoryItemEditMenu(plugin, shopCategoryItem));
        }
    }
}
