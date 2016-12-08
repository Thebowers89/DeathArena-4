package Kerlab.DeathArena;

import Kerlab.DeathArena.Commands.ArenaCommand;
import Kerlab.DeathArena.Commands.DAUtilsCommand;
import Kerlab.DeathArena.Utils.ArenaManager;
import Kerlab.DeathArena.Utils.ArenaUtils;
import Kerlab.DeathArena.Utils.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
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
        getCommand("deatharena").setExecutor(new ArenaCommand());
        getCommand("dautils").setExecutor(new DAUtilsCommand());
        getCommand("debug").setExecutor(new DebugCommand());
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ArenaUtils(), this);
        pm.registerEvents(new GameManager(), this);
    }

    private void verifyFiles() {
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("DeathArena-4").getDataFolder() + "/Arenas.yml");
        if (!file.exists()) {
            Bukkit.getLogger().log(Level.INFO, "[DeathArena-4] Arena file not found!");
            YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
            try {
                myFile.set("Arenas.IgnoreThis", "IgnoreThis");
                myFile.save(file);
                Bukkit.getLogger().log(Level.INFO, "[DeathArena-4] Creating Arena File!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
