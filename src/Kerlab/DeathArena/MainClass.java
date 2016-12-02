package Kerlab.DeathArena;

import Kerlab.DeathArena.Commands.ArenaCommand;
import Kerlab.DeathArena.Utils.ArenaManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class MainClass extends JavaPlugin {

    public void onEnable() {
        registerCommands();
        registerEvents();
        verifyFiles();
        ArenaManager.loadArenas();
    }

    public void onDisable() {
        ArenaManager.unloadArenas();
    }

    private void registerCommands() {
        getCommand("arena").setExecutor(new ArenaCommand());
    }

    private void registerEvents() {

    }

    private void verifyFiles() {
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("DeathArena-4").getDataFolder() + "/Arenas.yml");
        if (file.exists()) {
            Bukkit.getLogger().log(Level.INFO, ChatColor.YELLOW + "Arena file not found!");
            YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
            try {
                myFile.save(file);
                Bukkit.getLogger().log(Level.INFO, ChatColor.GREEN + "Creating Arena File!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
