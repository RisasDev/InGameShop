package dev.risas.ingameshop.models.shop.category.setting;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import dev.risas.ingameshop.InGameShop;
import dev.risas.ingameshop.models.shop.category.ShopCategory;
import dev.risas.ingameshop.utilities.ChatUtil;
import dev.risas.ingameshop.utilities.TaskUtil;
import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by Risas
 * Project: InGameShop
 * Date: 14-03-2022
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */

@UtilityClass
public class ShopCategorySetting {

    private final Table<UUID, ShopCategorySettingType, ShopCategory> SHOP_CATEGORY_SETTING = HashBasedTable.create();

    public void setShopCategorySetting(InGameShop plugin, Player player, ShopCategorySettingType type, ShopCategory shopCategory, boolean remove) {
        SHOP_CATEGORY_SETTING.put(player.getUniqueId(), type, shopCategory);

        if (remove) {
            ChatUtil.sendMessage(player, new String[]{
                    "&bYou're now editing " + ChatUtil.toReadable(type) + " of '&f" + shopCategory.getName() + "&b'.",
                    "&bType '&ccancel&b' in the chat to cancel the process.",
                    "&7&o(This process automatically cancel in 15 seconds)"
            });

            TaskUtil.runLater(plugin, () -> removeShopCategorySetting(player, type), 20L * 15L);
        }
    }

    public ShopCategory getShopCategorySetting(Player player, ShopCategorySettingType type) {
        return SHOP_CATEGORY_SETTING.get(player.getUniqueId(), type);
    }

    public boolean hasShopCategorySetting(Player player, ShopCategorySettingType type) {
        return SHOP_CATEGORY_SETTING.contains(player.getUniqueId(), type);
    }

    public void removeShopCategorySetting(Player player, ShopCategorySettingType type) {
        SHOP_CATEGORY_SETTING.remove(player.getUniqueId(), type);
    }
}
