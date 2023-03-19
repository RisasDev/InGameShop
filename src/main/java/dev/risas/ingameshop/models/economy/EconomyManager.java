package dev.risas.ingameshop.models.economy;

import dev.risas.ingameshop.InGameShop;
import dev.risas.ingameshop.models.economy.impl.VaultEconomy;
import dev.risas.ingameshop.utilities.ChatUtil;
import lombok.Getter;

@Getter
public class EconomyManager {

    private IEconomy economy;

    public EconomyManager(InGameShop plugin) {
        EconomyType economyType = EconomyType.valueOf(plugin.getConfigFile().getString("economy").toLowerCase());

        if (economyType.equals(EconomyType.VAULT)) {
            this.economy = new VaultEconomy();
        }
        else {
            ChatUtil.logger("&cEconomy system type not found.");
        }
    }
}
