package dev.risas.ingameshop.models.menu.button.impl;

import dev.risas.ingameshop.models.menu.button.Button;
import dev.risas.ingameshop.utilities.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class AirButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.AIR).build();
    }

    @Override
    public boolean shouldCancel(Player player, int slot, ClickType clickType) {
        return true;
    }
}
