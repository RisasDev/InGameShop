package dev.risas.ingameshop.models.shop.category.menu.buttons.item;

import com.cryptomorin.xseries.XMaterial;
import dev.risas.ingameshop.models.menu.button.Button;
import dev.risas.ingameshop.models.shop.item.ShopCategoryItem;
import dev.risas.ingameshop.models.shop.item.ShopCategoryItemType;
import dev.risas.ingameshop.utilities.item.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Risas
 * Project: InGameShop
 * Date: 08-08-2022
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */

public class ShopItemTypeButton extends Button {

    private final ShopCategoryItem shopCategoryItem;

    public ShopItemTypeButton(ShopCategoryItem shopCategoryItem) {
        this.shopCategoryItem = shopCategoryItem;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lore = new ArrayList<>();

        if (shopCategoryItem.getType() == ShopCategoryItemType.BUY) {
            lore = Arrays.asList(
                    "&7Changes the type as shown in the",
                    "&7shop category item.",
                    "",
                    " &a▶ &fBUY",
                    " &7▶ &fSELL",
                    " &7▶ &fBUY_AND_SELL",
                    "",
                    "&eClick to change type."
            );
        } else if (shopCategoryItem.getType() == ShopCategoryItemType.SELL) {
            lore = Arrays.asList(
                    "&7Changes the type as shown in the",
                    "&7shop category item.",
                    "",
                    " &7▶ &fBUY",
                    " &a▶ &fSELL",
                    " &7▶ &fBUY_AND_SELL",
                    "",
                    "&eClick to change type."
            );
        } else if (shopCategoryItem.getType() == ShopCategoryItemType.BUY_AND_SELL) {
            lore = Arrays.asList(
                    "&7Changes the type as shown in the",
                    "&7shop category item.",
                    "",
                    " &7▶ &fBUY",
                    " &7▶ &fSELL",
                    " &a▶ &fBUY_AND_SELL",
                    "",
                    "&eClick to change type."
            );
        }

        return new ItemBuilder(XMaterial.FIREWORK_STAR.parseMaterial())
                .setName("&aChange Item Type")
                .setLore(lore)
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (shopCategoryItem.getType() == ShopCategoryItemType.BUY) {
            shopCategoryItem.setType(ShopCategoryItemType.SELL);
        }
        else if (shopCategoryItem.getType() == ShopCategoryItemType.SELL) {
            shopCategoryItem.setType(ShopCategoryItemType.BUY_AND_SELL);
        }
        else if (shopCategoryItem.getType() == ShopCategoryItemType.BUY_AND_SELL) {
            shopCategoryItem.setType(ShopCategoryItemType.BUY);
        }

        shopCategoryItem.save();
        playNeutral(player);
    }
}
