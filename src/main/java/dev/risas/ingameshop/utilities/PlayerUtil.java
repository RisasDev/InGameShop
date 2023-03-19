package dev.risas.ingameshop.utilities;

import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;

/**
 * Created by Risas
 * Project: InGameShop
 * Date: 08-03-2022
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */

@UtilityClass
public class PlayerUtil {

    public boolean isInventoryFull(Player player) {
        return player.getInventory().firstEmpty() < 0;
    }
}
