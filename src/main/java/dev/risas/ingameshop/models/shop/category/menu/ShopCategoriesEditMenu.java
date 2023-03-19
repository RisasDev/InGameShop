package dev.risas.ingameshop.models.shop.category.menu;

import com.google.common.collect.Maps;
import dev.risas.ingameshop.InGameShop;
import dev.risas.ingameshop.models.menu.Menu;
import dev.risas.ingameshop.models.menu.button.Button;
import dev.risas.ingameshop.models.menu.button.impl.BackButton;
import dev.risas.ingameshop.models.shop.category.ShopCategory;
import dev.risas.ingameshop.models.shop.category.ShopCategoryManager;
import dev.risas.ingameshop.models.shop.category.menu.buttons.ShopEditButton;
import org.bukkit.entity.Player;

import java.util.Map;

public class ShopCategoriesEditMenu extends Menu {

    private final InGameShop plugin;
    private final ShopCategoryManager shopCategoryManager;

    public ShopCategoriesEditMenu(InGameShop plugin) {
        this.plugin = plugin;
        this.shopCategoryManager = plugin.getShopCategoryManager();
    }

    @Override
    public String getTitle(Player player) {
        return "Shop Categories Edit";
    }

    @Override
    public int getSize() {
        return 9 * 6;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = Maps.newHashMap();

        int index = 0;

        for (ShopCategory shopCategory : shopCategoryManager.getShopCategories().values()) {
            buttons.put(index, new ShopEditButton(plugin, shopCategory));
            index++;
        }

        buttons.put(getSize() - 5, new BackButton(new ShopMenu(plugin), plugin));

        return buttons;
    }
}
