package dev.risas.ingameshop.models.shop.category.menu;

import com.google.common.collect.Maps;
import dev.risas.ingameshop.InGameShop;
import dev.risas.ingameshop.models.menu.Menu;
import dev.risas.ingameshop.models.menu.button.Button;
import dev.risas.ingameshop.models.menu.button.impl.BackButton;
import dev.risas.ingameshop.models.shop.category.ShopCategory;
import dev.risas.ingameshop.models.shop.category.menu.buttons.ShopItemEditButton;
import dev.risas.ingameshop.models.shop.item.ShopCategoryItem;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;

import java.util.Map;

@AllArgsConstructor
public class ShopCategoryEditItemsMenu extends Menu {

    private InGameShop plugin;
    private ShopCategory shopCategory;

    @Override
    public String getTitle(Player player) {
        return shopCategory.getName() + " Category Edit Items";
    }

    @Override
    public int getSize() {
        return 9 * shopCategory.getMenuRows();
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = Maps.newHashMap();

        for (ShopCategoryItem shopCategoryItem : shopCategory.getItems().values()) {
            buttons.put(shopCategoryItem.getSlot(), new ShopItemEditButton(plugin, shopCategoryItem));
        }

        buttons.put(getSize() - 5, new BackButton(new ShopCategoryEditMenu(plugin, shopCategory), plugin));

        return buttons;
    }

    @Override
    public boolean isUpdateAfterClick() {
        return true;
    }
}
