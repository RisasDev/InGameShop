package dev.risas.ingameshop.models.shop.item;

import dev.risas.ingameshop.models.shop.category.ShopCategory;
import dev.risas.ingameshop.utilities.BukkitUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

@Getter @Setter
public class ShopCategoryItem {

    private ItemStack item;
    private ShopCategoryItemType type;
    private int slot;
    private int buyPrice;
    private int sellPrice;
    private ShopCategory category;

    public List<String> getPriceDescription() {
        String buyPricePlaceholder = type == ShopCategoryItemType.BUY ? "&a$" + buyPrice : "&cUnavailable";
        String sellPricePlaceholder = type == ShopCategoryItemType.SELL ? "&c$" + sellPrice : "&cUnavailable";

        if (type == ShopCategoryItemType.BUY_AND_SELL) {
            buyPricePlaceholder = "&a$" + buyPrice;
            sellPricePlaceholder = "&c$" + sellPrice;
        }

        return Arrays.asList(
                "",
                " &7▶ &bBuy&7: " + buyPricePlaceholder,
                " &7▶ &bSell&7: " + sellPricePlaceholder,
                "",
                "&eLeft Click to &abuy &ethis item.",
                "&eRight Click to &csell &ethis item.",
                "&eMiddle Click to &csell &eall items."
        );
    }

    public void save() {
        ConfigurationSection section = category.getFile().getConfiguration().getConfigurationSection("menu-items");

        section.set(slot + ".item", BukkitUtil.serializeItemStack(item));
        section.set(slot + ".type", type.name());
        section.set(slot + ".price.sell", sellPrice);
        section.set(slot + ".price.buy", buyPrice);

        category.getFile().save();
        category.getFile().reload();
    }
}
