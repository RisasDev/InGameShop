package dev.risas.ingameshop.utilities;

import dev.risas.ingameshop.InGameShop;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

@UtilityClass
public class TaskUtil {
    
    public void run(InGameShop plugin, Runnable runnable) {
        Bukkit.getServer().getScheduler().runTask(plugin, runnable);
    }

    public void runTimer(InGameShop plugin, Runnable runnable, long delay, long timer) {
        Bukkit.getServer().getScheduler().runTaskTimer(plugin, runnable, delay, timer);
    }

    public void runTimer(InGameShop plugin, BukkitRunnable runnable, long delay, long timer) {
        runnable.runTaskTimer(plugin, delay, timer);
    }

    public void runTimerAsync(InGameShop plugin, Runnable runnable, long delay, long timer) {
        Bukkit.getServer().getScheduler().runTaskTimerAsynchronously(plugin, runnable, delay, timer);
    }

    public void runTimerAsync(InGameShop plugin, BukkitRunnable runnable, long delay, long timer) {
        runnable.runTaskTimerAsynchronously(plugin, delay, timer);
    }

    public void runLater(InGameShop plugin, Runnable runnable, long delay) {
        Bukkit.getServer().getScheduler().runTaskLater(plugin, runnable, delay);
    }

    public void runLaterAsync(InGameShop plugin, Runnable runnable, long delay) {
        Bukkit.getServer().getScheduler().runTaskLaterAsynchronously(plugin, runnable, delay);
    }

    public void runTaskTimer(InGameShop plugin, Runnable runnable, long delay, long timer) {
        Bukkit.getServer().getScheduler().runTaskTimer(plugin, runnable, 20L * delay, 20L * timer);
    }

    public void runTaskTimerAsynchronously(InGameShop plugin, Runnable runnable, int delay) {
        Bukkit.getServer().getScheduler().runTaskTimerAsynchronously(plugin, runnable, 20L * delay, 20L * delay);
    }

    public void runAsync(InGameShop plugin, Runnable runnable) {
        Bukkit.getServer().getScheduler().runTaskAsynchronously(plugin, runnable);
    }

    public void scheduleSyncDelayedTask(InGameShop plugin, Runnable runnable) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, runnable);
    }

    public void scheduleSyncDelayedTask(InGameShop plugin, Runnable runnable, long delay) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, runnable, delay);
    }

    public void cancelTask(int id) {
        Bukkit.getServer().getScheduler().cancelTask(id);
    }
}