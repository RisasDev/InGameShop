package dev.risas.ingameshop.models.menu.button.impl;

import com.cryptomorin.xseries.XMaterial;
import dev.risas.ingameshop.models.menu.button.Button;
import dev.risas.ingameshop.utilities.item.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class CloseButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(XMaterial.REDSTONE.parseMaterial())
                .setName("&cClose")
                .build();
    }

    @Override
    public void clicked(Player player, int i, ClickType clickType, int hb) {
        playNeutral(player);
        player.closeInventory();
    }
}
