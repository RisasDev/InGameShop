package dev.risas.ingameshop.models.shop.category.menu.buttons.category;

import com.cryptomorin.xseries.XMaterial;
import dev.risas.ingameshop.models.menu.button.Button;
import dev.risas.ingameshop.models.shop.category.ShopCategory;
import dev.risas.ingameshop.utilities.item.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Risas
 * Project: InGameShop
 * Date: 10-08-2022
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */

public class ShopCategoryRowsButton extends Button {

    private final ShopCategory shopCategory;

    public ShopCategoryRowsButton(ShopCategory shopCategory) {
        this.shopCategory = shopCategory;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(XMaterial.ANVIL.parseMaterial())
                .setName("&aChange Category Menu Rows")
                .setLore(
                        "&7Changes the category menu rows.",
                        "",
                        " &7▶ &bMenu Rows&7: &f" + shopCategory.getMenuRows(),
                        "",
                        "&eLeft-Click to decrement rows.",
                        "&eRight-Click to increment rows."
                )
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (clickType.isLeftClick() && shopCategory.getMenuRows() > 1) {
            playNeutral(player);
            shopCategory.setMenuRows(shopCategory.getMenuRows() - 1);
        } else if (clickType.isRightClick() && shopCategory.getMenuRows() < 6) {
            playNeutral(player);
            shopCategory.setMenuRows(shopCategory.getMenuRows() + 1);
        }
    }
}
