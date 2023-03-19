package dev.risas.ingameshop.models.menu.pagination;

import dev.risas.ingameshop.models.menu.Menu;
import dev.risas.ingameshop.models.menu.MenuManager;
import dev.risas.ingameshop.models.menu.button.Button;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public abstract class PaginatedMenu extends Menu {

    private final MenuManager menuManager;

    public PaginatedMenu(MenuManager menuManager) {
        this.menuManager = menuManager;
    }

    @Getter
    private int page = 1;

    @Override
    public String getTitle(Player player) {
        return getPrePaginatedTitle(player) + " - " + page + "/" + getPages(player);
    }

    public final void modPage(Player player, int mod) {
        page += mod;
        getButtons().clear();
        menuManager.openMenu(player, this);
    }

    public final int getPages(Player player) {
        int buttonAmount = getAllPagesButtons(player).size();

        if (buttonAmount == 0) {
            return 1;
        }

        return (int) Math.ceil(buttonAmount / (double) getMaxItemsPerPage(player));
    }

    @Override
    public final Map<Integer, Button> getButtons(Player player) {
        int minIndex = (int) ((double) (page - 1) * getMaxItemsPerPage(player));
        int maxIndex = (int) ((double) (page) * getMaxItemsPerPage(player));

        HashMap<Integer, Button> buttons = new HashMap<>();

        for (Map.Entry<Integer, Button> entry : getAllPagesButtons(player).entrySet()) {
            int ind = entry.getKey();

            if (ind >= minIndex && ind < maxIndex) {
                ind -= (int) ((double) (getMaxItemsPerPage(player)) * (page - 1));
                buttons.put(ind, entry.getValue());
            }
        }

        Map<Integer, Button> global = getGlobalButtons(player);

        if (global != null) {
            buttons.putAll(global);
        }

        return buttons;
    }

    public int getMaxItemsPerPage(Player player) {
        return 18;
    }

    public Map<Integer, Button> getGlobalButtons(Player player) {
        return null;
    }

    public abstract String getPrePaginatedTitle(Player player);

    public abstract Map<Integer, Button> getAllPagesButtons(Player player);
}
