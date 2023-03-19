package dev.risas.ingameshop.models.shop.category.menu.buttons.category;

import dev.risas.ingameshop.models.menu.button.Button;
import dev.risas.ingameshop.models.shop.item.ShopCategoryItem;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Risas
 * Project: InGameShop
 * Date: 26-01-2023
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */

@AllArgsConstructor
public class ShopCategoryNoAvailableSlotButton extends Button {

    private final ShopCategoryItem shopCategoryItem;

    @Override
    public ItemStack getButtonItem(Player player) {
        return shopCategoryItem.getItem().clone();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        playFail(player);
    }
}
