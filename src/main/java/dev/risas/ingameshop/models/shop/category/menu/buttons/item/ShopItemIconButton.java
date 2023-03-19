package dev.risas.ingameshop.models.shop.category.menu.buttons.item;

import com.cryptomorin.xseries.XMaterial;
import dev.risas.ingameshop.models.menu.button.Button;
import dev.risas.ingameshop.models.shop.item.ShopCategoryItem;
import dev.risas.ingameshop.utilities.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Risas
 * Project: InGameShop
 * Date: 08-08-2022
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */

public class ShopItemIconButton extends Button {

    private final ShopCategoryItem shopCategoryItem;

    public ShopItemIconButton(ShopCategoryItem shopCategoryItem) {
        this.shopCategoryItem = shopCategoryItem;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(XMaterial.ITEM_FRAME.parseMaterial())
                .setName("&aChange Item")
                .setLore(
                        "&7Changes the item as shown in the",
                        "&7shop category item.",
                        "",
                        "&eDrag an item to set as the icon."
                )
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (player.getItemOnCursor() == null
                || player.getItemOnCursor().getType().equals(Material.AIR)
                || player.getItemOnCursor().isSimilar(shopCategoryItem.getItem().clone())) return;

        ItemStack itemStack = player.getItemOnCursor().clone();

        shopCategoryItem.setItem(itemStack);
        shopCategoryItem.save();

        playNeutral(player);
    }
}
