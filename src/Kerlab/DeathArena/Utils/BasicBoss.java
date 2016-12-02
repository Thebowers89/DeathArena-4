package Kerlab.DeathArena.Utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Zombie;

public class BasicBoss {

    public static void spawn(Location location) {
        Zombie boss = location.getWorld().spawn(location, Zombie.class);
        boss.setCustomName(ChatColor.GOLD + "Basic Boss");
        boss.setCustomNameVisible(true);
    }

}
