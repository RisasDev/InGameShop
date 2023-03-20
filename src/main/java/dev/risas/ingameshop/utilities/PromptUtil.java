package dev.risas.ingameshop.utilities;

import dev.risas.ingameshop.InGameShop;
import lombok.experimental.UtilityClass;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Player;

import java.util.Map;

/**
 * Created by Risas
 * Project: InGameShop
 * Date: 3/20/2023
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */

@UtilityClass
public class PromptUtil {

    public void createPrompt(InGameShop plugin, Player player, StringPrompt prompt, Map<Object, Object> sessionData) {
        Conversation conversation = plugin.getFactory()
                .withFirstPrompt(prompt)
                .withInitialSessionData(sessionData)
                .buildConversation(player);
        conversation.begin();
    }
}
