package dev.risas.ingameshop.models.shop.item.prompt;

import dev.risas.ingameshop.InGameShop;
import dev.risas.ingameshop.models.menu.MenuManager;
import dev.risas.ingameshop.models.shop.category.menu.ShopCategoryItemEditMenu;
import dev.risas.ingameshop.models.shop.item.ShopCategoryItem;
import dev.risas.ingameshop.utilities.ChatUtil;
import dev.risas.ingameshop.utilities.JavaUtil;
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

public class ShopCategoryItemBuyPricePrompt extends StringPrompt {

    private final InGameShop plugin;
    private final MenuManager menuManager;

    public ShopCategoryItemBuyPricePrompt(InGameShop plugin) {
        this.plugin = plugin;
        this.menuManager = plugin.getMenuManager();
    }

    @Override
    public Prompt acceptInput(ConversationContext context, String input) {
        Conversable conversable = context.getForWhom();
        Player player = (Player) conversable;

        Map<Object, Object> sessionData = context.getAllSessionData();
        ShopCategoryItem shopCategoryItem = (ShopCategoryItem) sessionData.get("shopCategoryItem");

        if (input.equalsIgnoreCase("cancel")) {
            ChatUtil.sendRawMessage(conversable, "&cProcess has been cancelled.");
            TaskUtil.runLater(plugin, () -> menuManager.openMenu(player, new ShopCategoryItemEditMenu(plugin, shopCategoryItem)), 1L);
            return END_OF_CONVERSATION;
        }

        Integer newPrice = JavaUtil.tryParseInt(input);

        if (newPrice == null) {
            ChatUtil.sendRawMessage(conversable, "&cPrice must be a number.");
            return this;
        }

        if (newPrice < 0) {
            ChatUtil.sendRawMessage(conversable, "&cPrice must be a positive number.");
            return this;
        }

        shopCategoryItem.setBuyPrice(newPrice);
        shopCategoryItem.save();

        ChatUtil.sendRawMessage(conversable, "&aYou have successfully changed the buy price of the item to &6$" + newPrice + "&a.");
        TaskUtil.runLater(plugin, () -> menuManager.openMenu(player, new ShopCategoryItemEditMenu(plugin, shopCategoryItem)), 1L);
        return END_OF_CONVERSATION;
    }

    @Override
    public String getPromptText(ConversationContext context) {
        return ChatUtil.translate("&aPlease enter the new buy price, or type '&ccancel&a' to cancel the process.");
    }
}
