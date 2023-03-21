package dev.risas.ingameshop.commands.shop.subcommands;

import dev.risas.ingameshop.InGameShop;
import dev.risas.ingameshop.models.menu.MenuManager;
import dev.risas.ingameshop.models.shop.category.ShopCategory;
import dev.risas.ingameshop.models.shop.category.ShopCategoryManager;
import dev.risas.ingameshop.models.shop.category.menu.ShopCategoryMenu;
import dev.risas.ingameshop.utilities.ChatUtil;
import dev.risas.ingameshop.utilities.command.BaseCommand;
import dev.risas.ingameshop.utilities.command.Command;
import dev.risas.ingameshop.utilities.command.CommandArgs;
import org.bukkit.entity.Player;

public class ShopCategoryCommand extends BaseCommand {

    private final InGameShop plugin;
    private final ShopCategoryManager shopCategoryManager;
    private final MenuManager menuManager;

    public ShopCategoryCommand(InGameShop plugin) {
        this.plugin = plugin;
        this.shopCategoryManager = plugin.getShopCategoryManager();
        this.menuManager = plugin.getMenuManager();
    }

    @Command(permission = "ingameshop.command.shop.category")
    @Override
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();
        String label = command.getLabel()
                .replace(".category", "");

        if (args.length == 0) {
            ChatUtil.sendMessage(player, "&cUsage: /" + label + " category <category>");
            return;
        }

        String categoryName = args[0];
        ShopCategory shopCategory = shopCategoryManager.getCategory(categoryName);

        if (shopCategory == null) {
            ChatUtil.sendMessage(player, "&cCategory '" + categoryName + " 'not found.");
            return;
        }

        menuManager.openMenu(player, new ShopCategoryMenu(plugin, shopCategory));
    }
}
