package dev.risas.ingameshop.models.shop.category.menu.buttons.category;

import com.cryptomorin.xseries.XMaterial;
import dev.risas.ingameshop.InGameShop;
import dev.risas.ingameshop.models.menu.button.Button;
import dev.risas.ingameshop.models.shop.category.ShopCategory;
import dev.risas.ingameshop.models.shop.category.prompt.ShopCategoryTitlePrompt;
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
 * Date: 10-08-2022
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */

public class ShopCategoryTitleButton extends Button {

    private final InGameShop plugin;
    private final ShopCategory shopCategory;

    public ShopCategoryTitleButton(InGameShop plugin, ShopCategory shopCategory) {
        this.plugin = plugin;
        this.shopCategory = shopCategory;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(XMaterial.NAME_TAG.parseMaterial())
                .setName("&aChange Category Menu Title")
                .setLore(
                        "&7Changes the category menu title.",
                        "",
                        " &7▶ &bMenu Title&7: &f" + shopCategory.getMenuTitle(),
                        "",
                        "&eClick to change category menu title."
                )
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        Map<Object, Object> sessionData = new HashMap<>();
        sessionData.put("shopCategory", shopCategory);

        PromptUtil.createPrompt(
                plugin,
                player,
                new ShopCategoryTitlePrompt(plugin),
                sessionData
        );

        playSuccess(player);
        player.closeInventory();
    }
}
