package dev.risas.ingameshop.models.shop.category.menu;

import com.google.common.collect.Maps;
import dev.risas.ingameshop.InGameShop;
import dev.risas.ingameshop.models.menu.Menu;
import dev.risas.ingameshop.models.menu.button.Button;
import dev.risas.ingameshop.models.menu.button.impl.BackButton;
import dev.risas.ingameshop.models.shop.category.ShopCategory;
import dev.risas.ingameshop.models.shop.category.menu.buttons.ShopItemButton;
import dev.risas.ingameshop.models.shop.item.ShopCategoryItem;
import dev.risas.ingameshop.utilities.ChatUtil;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;

import java.util.Map;

@AllArgsConstructor
public class ShopCategoryMenu extends Menu {

    private final InGameShop plugin;
    private ShopCategory shopCategory;

    @Override
    public String getTitle(Player player) {
        return ChatUtil.translate(shopCategory.getMenuTitle()
                .replace("<category>", shopCategory.getName()));
    }

    @Override
    public int getSize() {
        return shopCategory.getMenuRows() * 9;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = Maps.newHashMap();

        for (ShopCategoryItem shopCategoryItem : shopCategory.getItems().values()) {
            buttons.put(shopCategoryItem.getSlot(), new ShopItemButton(plugin, shopCategoryItem));
        }

        buttons.put(getSize() - 5, new BackButton(new ShopMenu(plugin), plugin));
        return buttons;
    }
}
