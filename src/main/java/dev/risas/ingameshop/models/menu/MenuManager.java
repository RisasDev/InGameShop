package dev.risas.ingameshop.models.menu;

import dev.risas.ingameshop.models.menu.button.Button;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class MenuManager {

    private final Map<UUID, Menu> menus;

    public MenuManager() {
        this.menus = new HashMap<>();
    }

    public Menu getMenu(Player player) {
        return this.menus.get(player.getUniqueId());
    }

    public void addMenu(Player player, Menu menu) {
        this.menus.put(player.getUniqueId(), menu);
    }

    public void removeMenu(Player player) {
        this.menus.remove(player.getUniqueId());
    }

    public void openMenu(Player player, Menu menu) {
        Map<Integer, Button> buttons = menu.getButtons(player);

        Menu previousMenu = this.getMenu(player);
        Inventory inventory = null;
        String title = menu.getTitle(player);
        int size = menu.getSize() == -1 ? menu.size(buttons) : menu.getSize();
        boolean update = false;

        if (title.length() > 32) {
            title = title.substring(0, 32);
        }

        if (player.getOpenInventory() != null) {
            if (previousMenu == null) {
                player.closeInventory();
            }
            else {
                int previousSize = player.getOpenInventory().getTopInventory().getSize();

                if (previousSize == size && player.getOpenInventory().getTitle().equals(title)) {
                    inventory = player.getOpenInventory().getTopInventory();
                    update = true;
                }
                else {
                    previousMenu.setClosedByMenu(true);
                    player.closeInventory();
                }
            }
        }

        if (inventory == null) {
            inventory = Bukkit.createInventory(player, size, title);
        }

        inventory.setContents(new ItemStack[inventory.getSize()]);

        this.addMenu(player, menu);

        for (Map.Entry<Integer, Button> entry : buttons.entrySet()) {
            inventory.setItem(entry.getKey(), entry.getValue().getButtonItem(player));
        }

        if (menu.isPlaceholder()) {
            for (int index = 0; index < size; index++) {
                Button fillButton = menu.getPlaceholderButton();

                if (buttons.get(index) == null) {
                    buttons.put(index, fillButton);
                    inventory.setItem(index, fillButton.getButtonItem(player));
                }
            }
        }

        if (update) {
            player.updateInventory();
        }
        else {
            player.openInventory(inventory);
        }

        menu.setClosedByMenu(false);
    }
}
