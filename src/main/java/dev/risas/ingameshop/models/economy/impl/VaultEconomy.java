package dev.risas.ingameshop.models.economy.impl;

import dev.risas.ingameshop.models.economy.IEconomy;
import dev.risas.ingameshop.utilities.ChatUtil;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.UUID;

/**
 * Created by Risas
 * Project: InGameShop
 * Date: 06-02-2023
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */

public class VaultEconomy implements IEconomy {

    private Economy economy;

    public VaultEconomy() {
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServicesManager().getRegistration(Economy.class);

        if (rsp == null) {
            ChatUtil.logger("&cEconomy system hook with Vault not found.");
            return;
        }


        this.economy = rsp.getProvider();
    }

    @Override
    public double getBalance(UUID uuid) {
        return economy.getBalance(getOfflinePlayer(uuid));
    }

    @Override
    public void giveBalance(UUID uuid, int amount) {
        EconomyResponse response = economy.depositPlayer(getOfflinePlayer(uuid), amount);
        economy.format(response.amount);
    }

    @Override
    public void removeBalance(UUID uuid, int amount) {
        EconomyResponse response = economy.withdrawPlayer(getOfflinePlayer(uuid), amount);
        economy.format(response.amount);
    }

    public OfflinePlayer getOfflinePlayer(UUID uuid) {
        return Bukkit.getOfflinePlayer(uuid);
    }
}

