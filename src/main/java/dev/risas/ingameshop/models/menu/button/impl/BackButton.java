package dev.risas.ingameshop.models.menu.button.impl;

import com.cryptomorin.xseries.XMaterial;
import dev.risas.ingameshop.InGameShop;
import dev.risas.ingameshop.models.menu.Menu;
import dev.risas.ingameshop.models.menu.MenuManager;
import dev.risas.ingameshop.models.menu.button.Button;
import dev.risas.ingameshop.utilities.item.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class BackButton extends Button {

    private final Menu menu;
    private final MenuManager menuManager;

    public BackButton(Menu menu, InGameShop plugin) {
        this.menu = menu;
        this.menuManager = plugin.getMenuManager();
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(XMaterial.REDSTONE.parseMaterial())
                .setName("&cBack")
                .build();
    }

    @Override
    public void clicked(Player player, int i, ClickType clickType, int hb) {
        playNeutral(player);
        menuManager.openMenu(player, menu);
    }
}
