package dev.risas.ingameshop.models.economy;

import java.util.UUID;

public interface IEconomy {

    double getBalance(UUID uuid);
    void giveBalance(UUID uuid, int amount);
    void removeBalance(UUID uuid, int amount);
}
