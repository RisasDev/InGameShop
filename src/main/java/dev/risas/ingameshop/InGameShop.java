package dev.risas.ingameshop;

import dev.risas.ingameshop.commands.InGameShopCommand;
import dev.risas.ingameshop.commands.shop.ShopCommand;
import dev.risas.ingameshop.commands.shop.subcommands.ShopCategoryCommand;
import dev.risas.ingameshop.commands.shop.subcommands.ShopEditorCommand;
import dev.risas.ingameshop.commands.shop.subcommands.ShopHelpCommand;
import dev.risas.ingameshop.listeners.ButtonListener;
import dev.risas.ingameshop.listeners.ShopListener;
import dev.risas.ingameshop.models.economy.EconomyManager;
import dev.risas.ingameshop.models.menu.MenuManager;
import dev.risas.ingameshop.models.shop.category.ShopCategoryManager;
import dev.risas.ingameshop.models.shop.item.ShopCategoryItemManager;
import dev.risas.ingameshop.utilities.command.CommandManager;
import dev.risas.ingameshop.utilities.file.FileConfig;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class InGameShop extends JavaPlugin {

    private FileConfig configFile, shopFile;

    private CommandManager commandManager;
    private MenuManager menuManager;
    private EconomyManager economyManager;
    private ShopCategoryManager shopCategoryManager;
    private ShopCategoryItemManager shopCategoryItemManager;

    private ConversationFactory factory;

    @Override
    public void onEnable() {
        this.configFile = new FileConfig(this, "config.yml");
        this.shopFile = new FileConfig(this, "shop/shop.yml");

        this.commandManager = new CommandManager(this);
        this.menuManager = new MenuManager();
        this.economyManager = new EconomyManager(this);
        this.shopCategoryItemManager = new ShopCategoryItemManager();
        this.shopCategoryManager = new ShopCategoryManager(this);
        this.factory = new ConversationFactory(this);

        String shopName = configFile.getString("shop-command.name");

        commandManager.registerCommands(new InGameShopCommand(this));
        commandManager.registerCommands(new ShopCommand(this), shopName);
        commandManager.registerCommands(new ShopCategoryCommand(this), shopName + ".category");
        commandManager.registerCommands(new ShopEditorCommand(this), shopName + ".editor");
        commandManager.registerCommands(new ShopHelpCommand(), shopName + ".help");

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new ButtonListener(this), this);
        pluginManager.registerEvents(new ShopListener(this), this);
    }

    public void onReload() {
        this.configFile.reload();
        this.shopFile.reload();
        this.shopCategoryManager.loadOrRefresh();
    }
}