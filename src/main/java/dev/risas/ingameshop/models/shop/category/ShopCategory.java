package dev.risas.ingameshop.models.shop.category;

import dev.risas.ingameshop.models.shop.item.ShopCategoryItem;
import dev.risas.ingameshop.utilities.ChatUtil;
import dev.risas.ingameshop.utilities.file.FileConfig;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Getter @Setter
public class ShopCategory {

    private final String id;
    private String menuTitle;
    private int menuRows;
    private ItemStack menuItem;
    private int menuItemSlot;
    private Map<Integer, ShopCategoryItem> items;
    private FileConfig file;

    public ShopCategory(String id) {
        this.id = id;
        this.items = new HashMap<>();
    }

    public String getName() {
        return ChatUtil.capitalize(id.replace("_", " "));
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;

        file.getConfiguration().set("menu.title", menuTitle);
        file.save();
        file.reload();
    }

    public void setMenuRows(int menuRows) {
        this.menuRows = menuRows;

        file.getConfiguration().set("menu.rows", menuRows);
        file.save();
        file.reload();
    }

    public void chargeFile() {
        FileConfiguration configuration = file.getConfiguration();

        if (configuration.getKeys(true).size() == 0) {
            configuration.set("menu.title", "<category> Category");
            configuration.set("menu.rows", 5);
            configuration.set("menu-items", Collections.EMPTY_MAP);

            file.save();
            file.reload();
        }
    }
}
