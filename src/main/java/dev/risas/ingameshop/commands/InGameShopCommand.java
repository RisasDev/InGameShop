package dev.risas.ingameshop.commands;

import dev.risas.ingameshop.InGameShop;
import dev.risas.ingameshop.utilities.ChatUtil;
import dev.risas.ingameshop.utilities.command.BaseCommand;
import dev.risas.ingameshop.utilities.command.Command;
import dev.risas.ingameshop.utilities.command.CommandArgs;
import org.bukkit.command.CommandSender;

/**
 * Created by Risas
 * Project: InGameShop
 * Date: 19-03-2023
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */

public class InGameShopCommand extends BaseCommand {

    private final InGameShop plugin;

    public InGameShopCommand(InGameShop plugin) {
        this.plugin = plugin;
    }

    @Command(name = "ingameshop", aliases = {"igs"}, permission = "ingameshop.command.ingameshop", inGameOnly = false)
    @Override
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();
        String[] args = command.getArgs();
        String label = command.getLabel();

        if (args.length == 0) {
            ChatUtil.sendMessage(sender, new String[]{
                    ChatUtil.NORMAL_LINE,
                    "&b&lInGameShop Commands",
                    "",
                    " &7▶ &b/" + label + " reload &7- &fReload the plugin.",
                    " &7▶ &b/neron reset &7- &fReset the storage data.",
                    ChatUtil.NORMAL_LINE
            });
            return;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            plugin.onReload();
            ChatUtil.sendMessage(sender, "&aNeron has been reloaded!");
        }
    }
}
