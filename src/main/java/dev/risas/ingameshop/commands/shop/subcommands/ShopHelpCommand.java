package dev.risas.ingameshop.commands.shop.subcommands;

import dev.risas.ingameshop.utilities.ChatUtil;
import dev.risas.ingameshop.utilities.command.BaseCommand;
import dev.risas.ingameshop.utilities.command.Command;
import dev.risas.ingameshop.utilities.command.CommandArgs;

public class ShopHelpCommand extends BaseCommand {

    @Command(name = "shop.help", permission = "ingameshop.command.shop.help", inGameOnly = false)
    @Override
    public void onCommand(CommandArgs command) {
        String label = command.getLabel().
                replace(".help", "");

        ChatUtil.sendMessage(command.getSender(), new String[]{
                ChatUtil.NORMAL_LINE,
                "&3&lShop Commands",
                "",
                " &f<> &7= &fRequired &7| &f[] &7= &fOptional",
                "",
                " &7▶ &b/" + label + " category <category> &7- &fOpen a specific category.",
                " &7▶ &b/" + label + " editor &7- &fOpen the shop editor menu.",
                ChatUtil.NORMAL_LINE
        });
    }
}
