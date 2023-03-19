package dev.risas.ingameshop.models.shop.category.menu.buttons.item;

import com.cryptomorin.xseries.XMaterial;
import dev.risas.ingameshop.models.menu.button.Button;
import dev.risas.ingameshop.models.shop.item.ShopCategoryItem;
import dev.risas.ingameshop.models.shop.item.setting.ShopCategoryItemSetting;
import dev.risas.ingameshop.models.shop.item.setting.ShopCategoryItemSettingType;
import dev.risas.ingameshop.utilities.ChatUtil;
import dev.risas.ingameshop.utilities.item.ItemBuilder;
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

public class ShopItemPriceButton extends Button {

    private final ShopCategoryItem shopCategoryItem;

    public ShopItemPriceButton(ShopCategoryItem shopCategoryItem) {
        this.shopCategoryItem = shopCategoryItem;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(XMaterial.GOLD_NUGGET.parseMaterial())
                .setName("&aChange Item Price")
                .setLore(
                        "&7Changes the price as shown in the",
                        "&7shop category item.",
                        "",
                        "&eLeft-Click to change buy price.",
                        "&eRight-Click to change sell price."
                )
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (clickType.isLeftClick()) {
            playSuccess(player);

            ShopCategoryItemSetting.setShopCategoryItemSetting(ShopCategoryItemSettingType.BUY_PRICE, player, shopCategoryItem);

            ChatUtil.sendMessage(player, new String[]{
                    "&bYou're now editing buy price of '&f" + ChatUtil.toReadable(shopCategoryItem.getItem()) + "&b'.",
                    "&bType '&ccancel&b' in the chat to cancel the process."
            });

            player.closeInventory();
        }
        else if (clickType.isRightClick()) {
            playSuccess(player);

            ShopCategoryItemSetting.setShopCategoryItemSetting(ShopCategoryItemSettingType.SELL_PRICE, player, shopCategoryItem);

            ChatUtil.sendMessage(player, new String[]{
                    "&bYou're now editing sell price of '&f" + ChatUtil.toReadable(shopCategoryItem.getItem()) + "&b'.",
                    "&bType '&ccancel&b' in the chat to cancel the process."
            });

            player.closeInventory();
        }
    }
}
