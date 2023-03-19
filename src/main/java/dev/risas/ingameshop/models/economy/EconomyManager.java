package dev.risas.ingameshop.models.economy;

import dev.risas.ingameshop.InGameShop;
import dev.risas.ingameshop.models.economy.impl.VaultEconomy;
import dev.risas.ingameshop.utilities.ChatUtil;
import lombok.Getter;
import org.bukkit.Bukkit;

@Getter
public class EconomyManager {

    private IEconomy economy;

    public EconomyManager(InGameShop plugin) {
        EconomyType economyType = EconomyType.valueOf(plugin.getConfigFile().getString("economy").toUpperCase());

        if (Bukkit.getPluginManager().getPlugin("Vault") != null && economyType.equals(EconomyType.VAULT)) {
            this.economy = new VaultEconomy();
        }
        else {
            ChatUtil.logger("&cEconomy system type not found.");
        }
    }
}
