package Kerlab.DeathArena.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.HashMap;

public class ArenaUtils implements Listener {

    public static HashMap<Player, Arena> players = new HashMap<>();

    public static Inventory configInventory() {
        Inventory inv = Bukkit.createInventory(null, 45, ChatColor.BLUE + "Arena Config Menu");
        inv.addItem(lobbyLocation());
        inv.addItem(playerLocation());
        inv.addItem(bossLocation());
        return inv;
    }

    private static ItemStack bossLocation() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Set Boss Spawn");
        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack playerLocation() {
        ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Set Player Spawn");
        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack lobbyLocation() {
        ItemStack item = new ItemStack(Material.ARMOR_STAND);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Set Lobby Spawn");
        item.setItemMeta(meta);
        return item;
    }

    @EventHandler
    public void inInventory(InventoryClickEvent e) {
        if (e.getClickedInventory() != null) {
            Inventory inv = e.getClickedInventory();
            if (inv.getName().equals(ChatColor.BLUE + "Arena Config Menu")) {
                if (e.getCurrentItem() != null) {
                    ItemStack item = e.getCurrentItem();
                    if (item.equals(bossLocation())) {
                        Location location = e.getWhoClicked().getLocation();
                        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("DeathArena-4").getDataFolder() + "/Arenas.yml");
                        LocationUtils.saveLocation(file, location, "Arenas." + players.get(e.getWhoClicked()).getName() + ".BossSpawn");
                        e.getWhoClicked().sendMessage(ChatColor.GOLD + "Boss Spawn set!");
                    } else if (item.equals(playerLocation())) {
                        Location location = e.getWhoClicked().getLocation();
                        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("DeathArena-4").getDataFolder() + "/Arenas.yml");
                        LocationUtils.saveLocation(file, location, "Arenas." + players.get(e.getWhoClicked()).getName() + ".PlayerSpawn");
                        e.getWhoClicked().sendMessage(ChatColor.GOLD + "Player Spawn set!");
                    } else if (item.equals(lobbyLocation())) {
                        Location location = e.getWhoClicked().getLocation();
                        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("DeathArena-4").getDataFolder() + "/Arenas.yml");
                        LocationUtils.saveLocation(file, location, "Arenas." + players.get(e.getWhoClicked()).getName() + ".Lobby");
                        e.getWhoClicked().sendMessage(ChatColor.GOLD + "Lobby Spawn set!");
                    }
                }
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (e.getInventory().getName().equals(ChatColor.BLUE + "Arena Config Menu")) {
            if (players.containsKey(e.getPlayer())) {
                players.remove(e.getPlayer());
            }
        }
    }

}
