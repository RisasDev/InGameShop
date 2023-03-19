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
public class PageButton extends Button {

    private final int mod;
    private final PaginatedMenu menu;

    @Override
    public ItemStack getButtonItem(Player player) {
        ItemBuilder itemBuilder = new ItemBuilder(XMaterial.LEVER.parseMaterial());

        if (hasNext(player)) {
            itemBuilder.setName(mod > 0 ? "&aNext page" : "&cPrevious page");
        }
        else {
            itemBuilder.setName("&7" + (mod > 0 ? "Last page" : "First page"));
        }

        return itemBuilder.build();
    }

    @Override
    public void clicked(Player player, int i, ClickType clickType, int hb) {
        if (hasNext(player)) {
            menu.modPage(player, mod);
            playNeutral(player);
        }
        else {
            playFail(player);
        }
    }

    private boolean hasNext(Player player) {
        int pg = menu.getPage() + mod;
        return pg > 0 && menu.getPages(player) >= pg;
    }
}
