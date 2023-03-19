package dev.risas.ingameshop.listeners;

import dev.risas.ingameshop.InGameShop;
import dev.risas.ingameshop.models.menu.Menu;
import dev.risas.ingameshop.models.menu.MenuManager;
import dev.risas.ingameshop.models.menu.button.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class ButtonListener implements Listener {

    private final MenuManager menuManager;

    public ButtonListener(InGameShop plugin) {
        this.menuManager = plugin.getMenuManager();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    private void onButtonPress(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Menu menu = menuManager.getMenu(player);

        System.out.println("click");

        if (menu != null) {
            System.out.println("click2");
            event.setCancelled(menu.isCancelPlayerInventory());

            if (event.getSlot() != event.getRawSlot()) {
                if ((event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT)) {
                    event.setCancelled(menu.isCancelPlayerInventory());
                }
                return;
            }

            if (menu.getButtons().containsKey(event.getSlot())) {
                Button button = menu.getButtons().get(event.getSlot());
                boolean shouldCancel = button.shouldCancel(player, event.getSlot(), event.getClick());
                boolean shouldShift = button.shouldShift(player, event.getSlot(), event.getClick());

                if (shouldCancel && shouldShift) {
                    event.setCancelled(true);
                } else {
                    event.setCancelled(shouldCancel);
                }

                button.clicked(player, event.getSlot(), event.getClick(), event.getHotbarButton());

                if (menuManager.getMenus().containsKey(player.getUniqueId())) {
                    Menu newMenu = menuManager.getMenu(player);

                    if (newMenu == menu) {
                        if (menu.isUpdateAfterClick()) {
                            menu.setClosedByMenu(true);
                            menuManager.openMenu(player, newMenu);
                        }
                    }
                }
                else if (button.shouldUpdate(player, event.getSlot(), event.getClick())) {
                    menu.setClosedByMenu(true);
                    menuManager.openMenu(player, menu);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Menu openMenu = menuManager.getMenu(player);

        if (openMenu != null) {
            menuManager.removeMenu(player);
        }
    }
}