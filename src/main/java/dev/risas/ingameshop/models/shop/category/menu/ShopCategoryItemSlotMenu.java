package dev.risas.ingameshop.models.shop.category.menu;

import dev.risas.ingameshop.InGameShop;
import dev.risas.ingameshop.models.menu.Menu;
import dev.risas.ingameshop.models.menu.button.Button;
import dev.risas.ingameshop.models.shop.category.menu.buttons.category.ShopCategoryAvailableSlotButton;
import dev.risas.ingameshop.models.shop.category.menu.buttons.category.ShopCategoryNoAvailableSlotButton;
import dev.risas.ingameshop.models.shop.item.ShopCategoryItem;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ShopCategoryItemSlotMenu extends Menu {

    private final InGameShop plugin;
    private final ShopCategoryItem shopCategoryItem;

    public ShopCategoryItemSlotMenu(InGameShop plugin, ShopCategoryItem shopCategoryItem) {
        this.plugin = plugin;
        this.shopCategoryItem = shopCategoryItem;
    }

    @Override
    public String getTitle(Player player) {
        return "Shop Item Slot";
    }

    @Override
    public int getSize() {
        return 9 * shopCategoryItem.getCategory().getMenuRows();
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        for (int i = 0; i < getSize(); i++) {
            buttons.put(i, new ShopCategoryAvailableSlotButton(plugin, shopCategoryItem));
        }

        for (ShopCategoryItem shopCategoryItem : shopCategoryItem.getCategory().getItems().values()) {
            buttons.put(shopCategoryItem.getSlot(), new ShopCategoryNoAvailableSlotButton(shopCategoryItem));
        }

        return buttons;
    }

    @Override
    public boolean isUpdateAfterClick() {
        return true;
    }
}
