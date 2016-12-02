package Kerlab.DeathArena.Utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ArenaManager {

    public static ArrayList<Arena> arenas = new ArrayList<>();

    public static void loadArenas() {
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("DeathArena-4").getDataFolder() + "/Arenas.yml");
        YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
        for (String name : myFile.getConfigurationSection("Arenas").getKeys(false)) {
            Arena a = new Arena(name, false);
            a.load();
        }
    }

    public static void unloadArenas() {
        for (Arena a : arenas) {
            a.save();
        }
        arenas.clear();
    }

    public static void unloadArena(String name) {
        for (Arena a : arenas) {
            if (a.getName().equals(name)) {
                a.save();
                arenas.remove(a);
                break;
            }
        }
    }

    public static void createArena(String name) {
        Arena a = new Arena(name, true);
        arenas.add(a);
    }

    public static void deleteArena(String name) {
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("DeathArena-4").getDataFolder() + "/Arenas.yml");
        YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
        for (Arena a : arenas) {
            if (a.getName().equalsIgnoreCase(name)) {
                arenas.remove(a);
                break;
            }
        }
        for (String string : myFile.getConfigurationSection("Arenas").getKeys(false)) {
           if (string.equalsIgnoreCase(name)) {
               myFile.set("Arenas." + string, null);
               try {
                   myFile.save(file);
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
        }
    }

    public static boolean loadArena(String name) {
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("DeathArena-4").getDataFolder() + "/Arenas.yml");
        YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
        for (String string : myFile.getConfigurationSection("Arenas").getKeys(false)) {
            if (string.equals(name)) {
                Arena a = new Arena(name, false);
                a.load();
                return true;
            }
        }
        return false;
    }

    public static boolean doesExist(String name) {
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("DeathArena-4").getDataFolder() + "/Arenas.yml");
        YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
        for (Arena a : arenas) {
            if (a.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        for (String string : myFile.getConfigurationSection("Arenas").getKeys(false)) {
            if (string.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public static Arena getRandomArena() {
        Random rand = new Random();
        return arenas.get(rand.nextInt(arenas.size()));
    }

}
