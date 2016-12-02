package Kerlab.DeathArena.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.File;

public class Arena {

    private String name;
    private Location playerSpawn;
    private Location bossSpawn;
    private Location lobby;

    public Arena(String name, boolean bool) {
        this.name = name;
        if (bool) {
            Location generic = new Location(Bukkit.getWorld("world"), 0, 0, 0);
            playerSpawn = generic;
            bossSpawn = generic;
            lobby = generic;
        }
    }

    public void load() {
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("DeathArena-4").getDataFolder() + "/Arenas.yml");
        playerSpawn = LocationUtils.getLocation(file, "Arenas." + name + ".PlayerSpawn");
        bossSpawn = LocationUtils.getLocation(file, "Arenas." + name + ".BossSpawn");
        lobby = LocationUtils.getLocation(file, "Arenas." + name + ".Lobby");
    }

    public void save() {
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("DeathArena-4").getDataFolder() + "/Arenas.yml");
        LocationUtils.saveLocation(file, playerSpawn, "Arenas." + name + ".PlayerSpawn");
        LocationUtils.saveLocation(file, bossSpawn, "Arenas." + name + ".BossSpawn");
        LocationUtils.saveLocation(file, lobby, "Arenas." + name + ".Lobby");
    }

    public String getName() {
        return this.name;
    }

    public Location getPlayerSpawn() {
        return this.playerSpawn;
    }

    public Location getBossSpawn() {
        return this.bossSpawn;
    }

    public Location getLobby() {
        return this.lobby;
    }

}
