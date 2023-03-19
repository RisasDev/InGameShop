package dev.risas.ingameshop.models.shop.item.prompt;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;

/**
 * Created by Risas
 * Project: InGameShop
 * Date: 14-03-2022
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */

public class ShopCategoryItemSellPricePrompt extends StringPrompt {

    @Override
    public Prompt acceptInput(ConversationContext context, String input) {
        System.out.println(input);
        System.out.println(context.getForWhom());
        return END_OF_CONVERSATION;
    }

    @Override
    public String getPromptText(ConversationContext context) {
        return "Escribe tu respuesta aquí:";
    }
}
