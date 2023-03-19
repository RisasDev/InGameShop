package dev.risas.ingameshop.commands.shop;

import dev.risas.ingameshop.InGameShop;
import dev.risas.ingameshop.models.menu.MenuManager;
import dev.risas.ingameshop.models.shop.category.menu.ShopMenu;
import dev.risas.ingameshop.utilities.command.BaseCommand;
import dev.risas.ingameshop.utilities.command.Command;
import dev.risas.ingameshop.utilities.command.CommandArgs;

public class ShopCommand extends BaseCommand {

    private final InGameShop plugin;
    private final MenuManager menuManager;

    public ShopCommand(InGameShop plugin) {
        this.plugin = plugin;
        this.menuManager = plugin.getMenuManager();
    }

    @Command(name = "shop")
    @Override
    public void onCommand(CommandArgs command) {
        menuManager.openMenu(command.getPlayer(), new ShopMenu(plugin));
    }
}
