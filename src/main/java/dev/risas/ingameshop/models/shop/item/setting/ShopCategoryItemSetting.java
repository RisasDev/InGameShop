package dev.risas.ingameshop.models.shop.item.setting;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import dev.risas.ingameshop.models.shop.item.ShopCategoryItem;
import lombok.Getter;
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
public class ShopCategoryItemSetting {

    @Getter
    private final Table<ShopCategoryItemSettingType, UUID, ShopCategoryItem> SHOP_CATEGORY_ITEM_SETTING = HashBasedTable.create();

    public boolean hasShopCategoryItemSetting(ShopCategoryItemSettingType setting, Player player) {
        return SHOP_CATEGORY_ITEM_SETTING.contains(setting, player.getUniqueId());
    }

    public ShopCategoryItem getShopCategoryItemSetting(ShopCategoryItemSettingType setting, Player player) {
        return SHOP_CATEGORY_ITEM_SETTING.get(setting, player.getUniqueId());
    }

    public void setShopCategoryItemSetting(ShopCategoryItemSettingType setting, Player player, ShopCategoryItem shopCategoryItem) {
        SHOP_CATEGORY_ITEM_SETTING.put(setting, player.getUniqueId(), shopCategoryItem);
    }

    public void removeShopCategoryItemSetting(ShopCategoryItemSettingType setting, Player player) {
        SHOP_CATEGORY_ITEM_SETTING.remove(setting, player.getUniqueId());
    }
}
