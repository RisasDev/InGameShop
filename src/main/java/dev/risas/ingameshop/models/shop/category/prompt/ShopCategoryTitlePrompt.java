package dev.risas.ingameshop.models.shop.category.prompt;

import dev.risas.ingameshop.InGameShop;
import dev.risas.ingameshop.models.menu.MenuManager;
import dev.risas.ingameshop.models.shop.category.ShopCategory;
import dev.risas.ingameshop.models.shop.category.menu.ShopCategoryEditMenu;
import dev.risas.ingameshop.utilities.ChatUtil;
import dev.risas.ingameshop.utilities.TaskUtil;
import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Player;

import java.util.Map;

/**
 * Created by Risas
 * Project: InGameShop
 * Date: 14-03-2022
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */

public class ShopCategoryTitlePrompt extends StringPrompt {

    private final InGameShop plugin;
    private final MenuManager menuManager;

    public ShopCategoryTitlePrompt(InGameShop plugin) {
        this.plugin = plugin;
        this.menuManager = plugin.getMenuManager();
    }

    @Override
    public Prompt acceptInput(ConversationContext context, String input) {
        Conversable conversable = context.getForWhom();
        Player player = (Player) conversable;

        Map<Object, Object> sessionData = context.getAllSessionData();
        ShopCategory shopCategory = (ShopCategory) sessionData.get("shopCategory");

        if (input.equalsIgnoreCase("cancel")) {
            ChatUtil.sendRawMessage(conversable, "&cProcess has been cancelled.");
            TaskUtil.runLater(plugin, () -> menuManager.openMenu(player, new ShopCategoryEditMenu(plugin, shopCategory)), 1L);
            return END_OF_CONVERSATION;
        }

        shopCategory.setMenuTitle(input);

        ChatUtil.sendRawMessage(conversable, "&aYou have successfully changed the category menu title to &7" + input + "&a.");
        TaskUtil.runLater(plugin, () -> menuManager.openMenu(player, new ShopCategoryEditMenu(plugin, shopCategory)), 1L);
        return END_OF_CONVERSATION;
    }

    @Override
    public String getPromptText(ConversationContext context) {
        return ChatUtil.translate("&aPlease enter the new title, or type '&ccancel&a' to cancel the process.");
    }
}
