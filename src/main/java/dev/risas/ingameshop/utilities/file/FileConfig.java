package dev.risas.ingameshop.utilities.file;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class FileConfig {

    private final File file;
    private FileConfiguration configuration;

    public FileConfig(JavaPlugin plugin, String fileName) {
        this.file = new File(plugin.getDataFolder(), fileName);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            if (plugin.getResource(fileName) == null) {
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    plugin.getLogger().severe("Failed to create new file " + fileName);
                }
            }
            else {
                plugin.saveResource(fileName, false);
            }
        }

        this.configuration = YamlConfiguration.loadConfiguration(file);
    }

    public double getDouble(String path) {
        if (configuration.contains(path)) {
            return configuration.getDouble(path);
        }
        return 0;
    }

    public int getInt(String path) {
        if (configuration.contains(path)) {
            return configuration.getInt(path);
        }
        return 0;
    }

    public boolean getBoolean(String path) {
        if (configuration.contains(path)) {
            return configuration.getBoolean(path);
        }
        return false;
    }

    public long getLong(String path) {
        if (configuration.contains(path)) {
            return configuration.getLong(path);
        }
        return 0L;
    }

    public String getString(String path) {
        if (configuration.contains(path)) {
            return ChatColor.translateAlternateColorCodes('&', configuration.getString(path));
        }
        return null;
    }

    public List<String> getStringList(String path) {
        if (configuration.contains(path)) {
            ArrayList<String> strings = new ArrayList<>();

            for (String string : configuration.getStringList(path)) {
                strings.add(ChatColor.translateAlternateColorCodes('&', string));
            }
            return strings;
        }
        return Collections.singletonList("ERROR: STRING LIST NOT FOUND!");
    }

    public void save() {
        try {
            configuration.save(file);
        } catch (IOException ex) {
            Bukkit.getLogger().severe("Could not save config file " + file.toString());
            ex.printStackTrace();
        }
    }

    public void reload() {
        configuration = YamlConfiguration.loadConfiguration(file);
    }
}