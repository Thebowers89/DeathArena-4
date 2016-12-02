package Kerlab.DeathArena.Utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ArenaManager {

    public static ArrayList<Arena> arenas = new ArrayList<>();

    public static void debug(Player player) {
        for (Arena a : arenas) {
            player.sendMessage(a.getName());
        }
    }

    public static void loadArenas() {
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("DeathArena-4").getDataFolder() + "/Arenas.yml");
        YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
        for (String name : myFile.getConfigurationSection("Arenas").getKeys(false)) {
            if (!name.equals("IgnoreThis")) {
                Arena a = new Arena(name, false);
                arenas.add(a);
                a.load();
            }
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
                arenas.add(a);
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

    public static Arena getArena(String name) {
        for (Arena a : arenas) {
            if (a.getName().equalsIgnoreCase(name)) {
                return a;
            }
        }
        return null;
    }

    public static Arena getRandomArena() {
        Random rand = new Random();
        System.out.println(arenas.size());
        int random = rand.nextInt(arenas.size());
        System.out.println(random);
        return arenas.get(random);
    }

}
