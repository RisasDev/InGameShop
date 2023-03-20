package dev.risas.ingameshop.models.shop.category.menu;

import com.cryptomorin.xseries.XMaterial;
import dev.risas.ingameshop.InGameShop;
import dev.risas.ingameshop.models.menu.Menu;
import dev.risas.ingameshop.models.menu.button.Button;
import dev.risas.ingameshop.models.menu.button.impl.BackButton;
import dev.risas.ingameshop.models.shop.category.menu.buttons.item.*;
import dev.risas.ingameshop.models.shop.item.ShopCategoryItem;
import dev.risas.ingameshop.utilities.item.ItemBuilder;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Risas
 * Project: InGameShop
 * Date: 08-08-2022
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */

public class ShopCategoryItemEditMenu extends Menu {

    private final InGameShop plugin;
    private final ShopCategoryItem shopCategoryItem;
    private final Integer[] slots = {12, 13, 14, 21, 23, 30, 31, 32};
    private final Button placeholder;

    public ShopCategoryItemEditMenu(InGameShop plugin, ShopCategoryItem shopCategoryItem) {
        this.plugin = plugin;
        this.shopCategoryItem = shopCategoryItem;
        this.placeholder = Button.placeholder(new ItemBuilder(XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial())
                .setName(" ")
                .setData(XMaterial.GRAY_STAINED_GLASS_PANE.getData())
                .build());
    }

    @Override
    public String getTitle(Player player) {
        return "Shop Item Edit";
    }

    @Override
    public int getSize() {
        return 9 * 6;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        for (int slot : slots) {
            buttons.put(slot, placeholder);
        }

        buttons.put(22, new ShopItemDisplayButton(shopCategoryItem));
        buttons.put(38, new ShopItemIconButton(shopCategoryItem));
        buttons.put(39, new ShopItemTypeButton(shopCategoryItem));
        buttons.put(41, new ShopItemSlotButton(plugin, shopCategoryItem));
        buttons.put(42, new ShopItemPriceButton(plugin, shopCategoryItem));
        buttons.put(getSize() - 5, new BackButton(new ShopCategoryEditItemsMenu(plugin, shopCategoryItem.getCategory()), plugin));

        return buttons;
    }

    @Override
    public boolean isUpdateAfterClick() {
        return true;
    }

    @Override
    public boolean isCancelPlayerInventory() {
        return false;
    }
}
