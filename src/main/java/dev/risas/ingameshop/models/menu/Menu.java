package dev.risas.ingameshop.models.menu;

import dev.risas.ingameshop.models.menu.button.Button;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;


@Getter @Setter
public abstract class Menu {

    private Map<Integer, Button> buttons;
    private boolean updateAfterClick;
    private boolean closedByMenu;
    private boolean placeholder;
    private boolean cancelPlayerInventory;
    private Button placeholderButton;

    public Menu() {
        this.buttons = new HashMap<>();
        this.cancelPlayerInventory = true;
    }

    public int size(Map<Integer, Button> buttons) {
        int highest = 0;

        for (int buttonValue : buttons.keySet()) {
            if (buttonValue > highest) {
                highest = buttonValue;
            }
        }

        return (int) (Math.ceil((highest + 1) / 9D) * 9D);
    }

    public int getSlot(int x, int y) {
        return ((9 * y) + x);
    }

    public int getSize() {
        return -1;
    }

    public Button getPlaceholderButton() {
        return null;
    }

    public abstract String getTitle(Player player);

    public abstract Map<Integer, Button> getButtons(Player player);
}