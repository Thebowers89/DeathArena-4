package Kerlab.DeathArena.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LocationUtils {

    public static Location getLocation(File file, String path) {
        YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
        World world = Bukkit.getWorld(myFile.getString(path + ".World"));
        double x = myFile.getDouble(path + ".X");
        double y = myFile.getDouble(path + ".Y");
        double z = myFile.getDouble(path + ".Z");
        float yaw = (float) myFile.getDouble(path + ".Yaw");
        float pitch = (float) myFile.getDouble(path + ".Pitch");
        return new Location(world, x, y, z, yaw, pitch);
    }

    public static boolean saveLocation(File file, Location location, String path) {
        YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
        myFile.set(path + ".World", location.getWorld().getName());
        myFile.set(path + ".X", location.getX());
        myFile.set(path + ".Y", location.getY());
        myFile.set(path + ".Z", location.getZ());
        myFile.set(path + ".Yaw", location.getYaw());
        myFile.set(path + ".Pitch", location.getPitch());
        try {
            myFile.save(file);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Location common() {
        String path = "Locations.Common";
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("DeathArena-4").getDataFolder() + "/Utils.yml");
        return getLocation(file, path);
    }

}
