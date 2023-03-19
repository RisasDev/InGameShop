package dev.risas.ingameshop.models.shop.category.menu.buttons;

import dev.risas.ingameshop.InGameShop;
import dev.risas.ingameshop.models.menu.MenuManager;
import dev.risas.ingameshop.models.menu.button.Button;
import dev.risas.ingameshop.models.shop.category.ShopCategory;
import dev.risas.ingameshop.models.shop.category.menu.ShopCategoryEditMenu;
import dev.risas.ingameshop.utilities.ChatUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class ShopEditButton extends Button {

    private final InGameShop plugin;
    private final ShopCategory shopCategory;
    private final MenuManager menuManager;

    public ShopEditButton(InGameShop plugin, ShopCategory shopCategory) {
        this.plugin = plugin;
        this.shopCategory = shopCategory;
        this.menuManager = plugin.getMenuManager();
    }


    @Override
    public ItemStack getButtonItem(Player player) {
        ItemStack itemStack = shopCategory.getMenuItem().clone();
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setLore(ChatUtil.translate(Collections.singletonList(
                "&eClick to open category edit menu."
        )));

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        menuManager.openMenu(player, new ShopCategoryEditMenu(plugin, shopCategory));

        playNeutral(player);
    }
}
