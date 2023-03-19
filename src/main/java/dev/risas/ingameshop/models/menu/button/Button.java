package dev.risas.ingameshop.models.menu.button;

import com.cryptomorin.xseries.XSound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public abstract class Button {

    public static Button placeholder(ItemStack itemStack) {
        return (new Button() {
            public ItemStack getButtonItem(Player player) {
                return itemStack.clone();
            }
        });
    }

    public void playFail(Player player) {
        player.playSound(player.getLocation(), XSound.BLOCK_GRASS_BREAK.parseSound(), 20F, 0.1F);
    }

    public void playSuccess(Player player) {
        player.playSound(player.getLocation(), XSound.BLOCK_NOTE_BLOCK_HARP.parseSound(), 20F, 15F);
    }

    public void playNeutral(Player player) {
        player.playSound(player.getLocation(), XSound.ENTITY_EXPERIENCE_ORB_PICKUP.parseSound(),20F, 1F);
    }

    public abstract ItemStack getButtonItem(Player player);

    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
    }

    public boolean shouldUpdate(Player player, int slot, ClickType clickType) {
        return false;
    }

    public boolean shouldCancel(Player player, int slot, ClickType clickType) {
        return true;
    }

    public boolean shouldShift(Player player, int slot, ClickType clickType) {
        return true;
    }

    public void close(Player player) {
        player.closeInventory();
    }
}