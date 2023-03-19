package dev.risas.ingameshop.models.shop.category.menu;

import com.google.common.collect.Maps;
import dev.risas.ingameshop.models.menu.Menu;
import dev.risas.ingameshop.models.menu.button.Button;
import dev.risas.ingameshop.models.shop.category.ShopCategory;
import dev.risas.ingameshop.models.shop.item.ShopCategoryItem;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;

import java.util.Map;

@AllArgsConstructor
public class ShopCategoryAddItemsMenu extends Menu {

    private ShopCategory shopCategory;

    @Override
    public String getTitle(Player player) {
        return shopCategory.getName() + " Category Add Items";
    }

    @Override
    public int getSize() {
        return 9 * shopCategory.getMenuRows();
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = Maps.newHashMap();

        for (ShopCategoryItem shopCategoryItem : shopCategory.getItems().values()) {
            buttons.put(shopCategoryItem.getSlot(), Button.placeholder(shopCategoryItem.getItem().clone()));
        }

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
