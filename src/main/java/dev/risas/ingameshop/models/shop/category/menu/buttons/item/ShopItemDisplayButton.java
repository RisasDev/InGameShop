package dev.risas.ingameshop.models.shop.category.menu.buttons.item;

import dev.risas.ingameshop.models.menu.button.Button;
import dev.risas.ingameshop.models.shop.item.ShopCategoryItem;
import dev.risas.ingameshop.utilities.ChatUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/**
 * Created by Risas
 * Project: InGameShop
 * Date: 08-08-2022
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */

public class ShopItemDisplayButton extends Button {

    private final ShopCategoryItem shopCategoryItem;

    public ShopItemDisplayButton(ShopCategoryItem shopCategoryItem) {
        this.shopCategoryItem = shopCategoryItem;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        ItemStack itemStack = shopCategoryItem.getItem().clone();
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setLore(ChatUtil.translate(Arrays.asList(
                "",
                " &7▶ &bCategory&7: &f" + shopCategoryItem.getCategory().getName(),
                " &7▶ &bType&7: &f" + shopCategoryItem.getType().name(),
                " &7▶ &bSlot&7: &f" + shopCategoryItem.getSlot(),
                " &7▶ &bBuy Price&7: &a$" + shopCategoryItem.getBuyPrice(),
                " &7▶ &bSell Price&7: &c$" + shopCategoryItem.getSellPrice()
        )));

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
