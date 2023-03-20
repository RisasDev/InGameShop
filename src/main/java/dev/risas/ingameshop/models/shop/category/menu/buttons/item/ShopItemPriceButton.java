package dev.risas.ingameshop.models.shop.category.menu.buttons.item;

import com.cryptomorin.xseries.XMaterial;
import dev.risas.ingameshop.InGameShop;
import dev.risas.ingameshop.models.menu.button.Button;
import dev.risas.ingameshop.models.shop.item.ShopCategoryItem;
import dev.risas.ingameshop.models.shop.item.prompt.ShopCategoryItemBuyPricePrompt;
import dev.risas.ingameshop.models.shop.item.prompt.ShopCategoryItemSellPricePrompt;
import dev.risas.ingameshop.utilities.PromptUtil;
import dev.risas.ingameshop.utilities.item.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Risas
 * Project: InGameShop
 * Date: 08-08-2022
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */

public class ShopItemPriceButton extends Button {

    private final InGameShop plugin;
    private final ShopCategoryItem shopCategoryItem;

    public ShopItemPriceButton(InGameShop plugin, ShopCategoryItem shopCategoryItem) {
        this.plugin = plugin;
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
        if (!clickType.isLeftClick() && !clickType.isRightClick()) return;

        Map<Object, Object> sessionData = new HashMap<>();
        sessionData.put("shopCategoryItem", shopCategoryItem);

        PromptUtil.createPrompt(
                plugin,
                player,
                clickType.isLeftClick() ? new ShopCategoryItemBuyPricePrompt(plugin) : new ShopCategoryItemSellPricePrompt(plugin),
                sessionData
        );

        player.closeInventory();
    }
}
