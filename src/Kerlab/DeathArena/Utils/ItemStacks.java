package Kerlab.DeathArena.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemStacks {

    //Lore Strings

    private static String[] rangerLore = {"Ranger Item"};
    private static String[] tankLore = {"Tank Item"};
    private static String[] alchemistLore = {"Alchemist Lore"};

    //Ranger

    public static ItemStack[] rangerArmor = {rangerBoots(), rangerLegs(), rangerChest(), rangerHelmet()};

    public static ItemStack bow() {
        Player player = Bukkit.getPlayer("");
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta bowMeta = bow.getItemMeta();
        bowMeta.setDisplayName(ChatColor.GREEN + "Ranger Bow");
        bowMeta.setLore(Arrays.asList(rangerLore));
        bow.setItemMeta(bowMeta);
        return bow;
    }

    public static ItemStack rangerHelmet() {
        ItemStack item = new ItemStack(Material.LEATHER_HELMET);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(Arrays.asList(rangerLore));
        meta.setDisplayName(ChatColor.GREEN + "Ranger Helmet");
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack rangerChest() {
        ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(Arrays.asList(rangerLore));
        meta.setDisplayName(ChatColor.GREEN + "Ranger Chestplate");
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack rangerLegs() {
        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(Arrays.asList(rangerLore));
        meta.setDisplayName(ChatColor.GREEN + "Ranger Leggings");
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack rangerBoots() {
        ItemStack item = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(Arrays.asList(rangerLore));
        meta.setDisplayName(ChatColor.GREEN + "Ranger Boots");
        item.setItemMeta(meta);
        return item;
    }

    //Tank

    public static ItemStack[] tankArmor = {tankBoots(), tankLegs(), tankChest(), tankHelmet()};

    public static ItemStack tankHelmet() {
        ItemStack item = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Tank Helmet");
        meta.setLore(Arrays.asList(tankLore));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack tankChest() {
        ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Tank Chestplate");
        meta.setLore(Arrays.asList(tankLore));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack tankLegs() {
        ItemStack item = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Tank Leggings");
        meta.setLore(Arrays.asList(tankLore));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack tankBoots() {
        ItemStack item = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Tank Boots");
        meta.setLore(Arrays.asList(tankLore));
        item.setItemMeta(meta);
        return item;
    }

    //Alchemist

    public static ItemStack[] alchemistArmor = {alcBoots(), alcLeggings(), alcChest(), alcHelmet()};

    public static ItemStack alcHelmet() {
        ItemStack item = new ItemStack(Material.CHAINMAIL_HELMET);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + "Alchemist Helmet");
        meta.setLore(Arrays.asList(alchemistLore));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack alcChest() {
        ItemStack item = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + "Alchemist Chestplate");
        meta.setLore(Arrays.asList(alchemistLore));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack alcLeggings() {
        ItemStack item = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + "Alchemist Leggings");
        meta.setLore(Arrays.asList(alchemistLore));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack alcBoots() {
        ItemStack item = new ItemStack(Material.CHAINMAIL_BOOTS);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + "Alchemist Boots");
        meta.setLore(Arrays.asList(alchemistLore));
        item.setItemMeta(meta);
        return item;
    }

}
