package dev.risas.ingameshop.models.menu.button.impl;

import com.cryptomorin.xseries.XMaterial;
import dev.risas.ingameshop.models.menu.button.Button;
import dev.risas.ingameshop.models.menu.pagination.PaginatedMenu;
import dev.risas.ingameshop.utilities.item.ItemBuilder;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class PageInfoButton extends Button {

    private final PaginatedMenu paginatedMenu;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(XMaterial.NETHER_STAR.parseMaterial())
                .setName("&ePage Info")
                .setLore("&e" + paginatedMenu.getPage() + "&7/&a" + paginatedMenu.getPages(player))
                .build();
    }

    @Override
    public boolean shouldCancel(Player player, int slot, ClickType clickType) {
        return true;
    }
}
