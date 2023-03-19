package dev.risas.ingameshop.models.shop.category.menu.buttons;

import dev.risas.ingameshop.InGameShop;
import dev.risas.ingameshop.models.economy.EconomyManager;
import dev.risas.ingameshop.models.menu.button.Button;
import dev.risas.ingameshop.models.shop.item.ShopCategoryItem;
import dev.risas.ingameshop.models.shop.item.ShopCategoryItemType;
import dev.risas.ingameshop.utilities.BukkitUtil;
import dev.risas.ingameshop.utilities.ChatUtil;
import dev.risas.ingameshop.utilities.PlayerUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ShopItemButton extends Button {

    private final ShopCategoryItem shopCategoryItem;
    private final EconomyManager economyManager;

    public ShopItemButton(InGameShop plugin, ShopCategoryItem shopCategoryItem) {
        this.shopCategoryItem = shopCategoryItem;
        this.economyManager = plugin.getEconomyManager();
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        ItemStack itemStack = shopCategoryItem.getItem().clone();
        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemMeta.hasLore()) {
            List<String> lore = itemMeta.getLore();
            lore.addAll(ChatUtil.translate(shopCategoryItem.getPriceDescription()));
            itemMeta.setLore(lore);
        }
        else {
            itemMeta.setLore(ChatUtil.translate(shopCategoryItem.getPriceDescription()));
        }

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (clickType.isLeftClick()) {
            if (shopCategoryItem.getType() == ShopCategoryItemType.BUY || shopCategoryItem.getType() == ShopCategoryItemType.BUY_AND_SELL) {
                int price = shopCategoryItem.getBuyPrice();

                if (economyManager.getEconomy().getBalance(player.getUniqueId()) < price) {
                    ChatUtil.sendMessage(player, "&cYou don't have enough money to buy this item!");
                    playFail(player);
                    return;
                }

                if (PlayerUtil.isInventoryFull(player)) {
                    ChatUtil.sendMessage(player, "&cYour inventory is full!");
                    playFail(player);
                    return;
                }

                economyManager.getEconomy().removeBalance(player.getUniqueId(), price);

                ItemStack itemStack = shopCategoryItem.getItem().clone();
                player.getInventory().addItem(itemStack);
                ChatUtil.sendMessage(player, "&eYou have bought &fx" + itemStack.getAmount() + " "
                        + ChatUtil.toReadable(itemStack) + " &efor &a$" + price + "&e.");
                playSuccess(player);
            }
        }
        else if (clickType.isCreativeAction()) {
            if (shopCategoryItem.getType() == ShopCategoryItemType.SELL || shopCategoryItem.getType() == ShopCategoryItemType.BUY_AND_SELL) {
                ItemStack itemStack = shopCategoryItem.getItem().clone();
                int sellQuantity = BukkitUtil.countAmounts(player.getInventory(), itemStack);

                if (sellQuantity <= 0) {
                    ChatUtil.sendMessage(player, "&cYou don't have any of this item in your inventory!");
                    playFail(player);
                    return;
                }

                float cal = (float) shopCategoryItem.getSellPrice() / itemStack.getAmount();
                int price = Math.round(cal * sellQuantity);

                economyManager.getEconomy().giveBalance(player.getUniqueId(), price);
                BukkitUtil.removeItems(player.getInventory(), itemStack, sellQuantity);
                player.updateInventory();
                ChatUtil.sendMessage(player, "&eYou have sold &fx" + sellQuantity + " "
                        + ChatUtil.toReadable(itemStack) + " &efor &a$" + price + "&e.");
                playSuccess(player);
            }
        }
        else if (clickType.isRightClick()) {
            if (shopCategoryItem.getType() == ShopCategoryItemType.SELL || shopCategoryItem.getType() == ShopCategoryItemType.BUY_AND_SELL) {
                ItemStack itemStack = shopCategoryItem.getItem().clone();
                int sellQuantity = BukkitUtil.countAmount(player.getInventory(), itemStack);

                if (sellQuantity <= 0) {
                    ChatUtil.sendMessage(player, "&cYou don't have any of this item in your inventory!");
                    playFail(player);
                    return;
                }

                float cal = (float) shopCategoryItem.getSellPrice() / itemStack.getAmount();
                int price = Math.round(cal * sellQuantity);

                economyManager.getEconomy().giveBalance(player.getUniqueId(), price);
                BukkitUtil.removeItem(player.getInventory(), itemStack, sellQuantity);
                player.updateInventory();
                ChatUtil.sendMessage(player, "&eYou have sold &fx" + sellQuantity + " "
                        + ChatUtil.toReadable(itemStack) + " &efor &a$" + price + "&e.");
                playSuccess(player);
            }
        }
    }
}
