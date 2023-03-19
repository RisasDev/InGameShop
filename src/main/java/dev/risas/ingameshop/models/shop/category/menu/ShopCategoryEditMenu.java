package dev.risas.ingameshop.models.shop.category.menu;

import dev.risas.ingameshop.InGameShop;
import dev.risas.ingameshop.models.menu.Menu;
import dev.risas.ingameshop.models.menu.button.Button;
import dev.risas.ingameshop.models.menu.button.impl.BackButton;
import dev.risas.ingameshop.models.shop.category.ShopCategory;
import dev.risas.ingameshop.models.shop.category.menu.buttons.category.ShopCategoryAddItemsButton;
import dev.risas.ingameshop.models.shop.category.menu.buttons.category.ShopCategoryEditItemsButton;
import dev.risas.ingameshop.models.shop.category.menu.buttons.category.ShopCategoryRowsButton;
import dev.risas.ingameshop.models.shop.category.menu.buttons.category.ShopCategoryTitleButton;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Risas
 * Project: InGameShop
 * Date: 10-08-2022
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */

public class ShopCategoryEditMenu extends Menu {

    private final InGameShop plugin;
    private final ShopCategory shopCategory;

    public ShopCategoryEditMenu(InGameShop plugin, ShopCategory shopCategory) {
        this.plugin = plugin;
        this.shopCategory = shopCategory;
    }

    @Override
    public String getTitle(Player player) {
        return shopCategory.getName() + " Category Edit";
    }

    @Override
    public int getSize() {
        return 9 * 3;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(11, new ShopCategoryTitleButton(plugin, shopCategory));
        buttons.put(12, new ShopCategoryRowsButton(shopCategory));
        buttons.put(14, new ShopCategoryAddItemsButton(plugin, shopCategory));
        buttons.put(15, new ShopCategoryEditItemsButton(plugin, shopCategory));
        buttons.put(22, new BackButton(new ShopCategoriesEditMenu(plugin), plugin));

        return buttons;
    }

    @Override
    public boolean isUpdateAfterClick() {
        return true;
    }
}
