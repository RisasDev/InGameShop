package dev.risas.ingameshop.commands.shop.subcommands;

import dev.risas.ingameshop.InGameShop;
import dev.risas.ingameshop.models.menu.MenuManager;
import dev.risas.ingameshop.models.shop.category.menu.ShopCategoriesEditMenu;
import dev.risas.ingameshop.utilities.command.BaseCommand;
import dev.risas.ingameshop.utilities.command.Command;
import dev.risas.ingameshop.utilities.command.CommandArgs;

public class ShopEditCommand extends BaseCommand {

    private final InGameShop plugin;
    private final MenuManager menuManager;

    public ShopEditCommand(InGameShop plugin) {
        this.plugin = plugin;
        this.menuManager = plugin.getMenuManager();
    }

    @Command(name = "shop.edit", permission = "ingameshop.command.shop.edit")
    @Override
    public void onCommand(CommandArgs command) {
        menuManager.openMenu(command.getPlayer(), new ShopCategoriesEditMenu(plugin));
    }
}
