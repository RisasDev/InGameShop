package dev.risas.ingameshop.models.shop.category.menu;

import com.google.common.collect.Maps;
import dev.risas.ingameshop.InGameShop;
import dev.risas.ingameshop.models.menu.Menu;
import dev.risas.ingameshop.models.menu.button.Button;
import dev.risas.ingameshop.models.shop.category.ShopCategory;
import dev.risas.ingameshop.models.shop.category.ShopCategoryManager;
import dev.risas.ingameshop.models.shop.category.menu.buttons.ShopButton;
import dev.risas.ingameshop.utilities.ChatUtil;
import org.bukkit.entity.Player;

import java.util.Map;

public class ShopMenu extends Menu {

    private final InGameShop plugin;
    private final ShopCategoryManager shopCategoryManager;

    public ShopMenu(InGameShop plugin) {
        this.plugin = plugin;
        this.shopCategoryManager = plugin.getShopCategoryManager();
    }

    @Override
    public String getTitle(Player player) {
        return ChatUtil.translate(plugin.getShopFile().getString("shop-menu.title"));
    }

    @Override
    public int getSize() {
        return 9 * plugin.getShopFile().getInt("shop-menu.rows");
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = Maps.newHashMap();

        for (ShopCategory shopCategory : shopCategoryManager.getShopCategories().values()) {
            buttons.put(shopCategory.getMenuItemSlot(), new ShopButton(plugin, shopCategory));
        }

        return buttons;
    }
}
