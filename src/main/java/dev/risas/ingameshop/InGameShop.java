package dev.risas.ingameshop;

import dev.risas.ingameshop.listeners.ButtonListener;
import dev.risas.ingameshop.models.economy.EconomyManager;
import dev.risas.ingameshop.models.menu.MenuManager;
import dev.risas.ingameshop.models.shop.category.ShopCategoryManager;
import dev.risas.ingameshop.models.shop.item.ShopCategoryItemManager;
import dev.risas.ingameshop.utilities.file.FileConfig;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class InGameShop extends JavaPlugin {

    private FileConfig configFile, shopFile;

    private MenuManager menuManager;
    private EconomyManager economyManager;
    private ShopCategoryManager shopCategoryManager;
    private ShopCategoryItemManager shopCategoryItemManager;

    @Override
    public void onEnable() {
        this.configFile = new FileConfig(this, "config.yml");
        this.shopFile = new FileConfig(this, "shop.yml");

        this.menuManager = new MenuManager();
        this.economyManager = new EconomyManager(this);
        this.shopCategoryManager = new ShopCategoryManager(this);
        this.shopCategoryItemManager = new ShopCategoryItemManager();

        Bukkit.getPluginManager().registerEvents(new ButtonListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}