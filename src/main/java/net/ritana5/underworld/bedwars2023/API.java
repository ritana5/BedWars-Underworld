package net.ritana5.underworld.bedwars2023;

import com.tomkeuper.bedwars.api.BedWars;
import com.tomkeuper.bedwars.api.language.Language;
import net.ritana5.underworld.Underworld;
import net.ritana5.underworld.api.bedwars2023.UnderworldItem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class API {


    /**
     * A list of rotation items. This should never be used outside main plugin, only edit if you know what you are doing!
     */
    public static List<UnderworldItem> items = new ArrayList<>();

    /**
     * Gets the BedWars API.
     *
     * @return the BedWars API
     */
    public static BedWars getBedwarsAPI() {
        return Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider();
    }

    public static Underworld getMain() {
        return Underworld.getPlugin(Underworld.class);
    }


    /**
     * Determines if a given inventory title belongs to a shop inventory.
     *
     * @param player the player to check the inventory for
     * @param title  the title of the inventory
     * @return true if the inventory belongs to a shop, false otherwise
     */
    public static boolean isShop(Player player, String title) {
        return (title.equals(Language.getMsg(player, "shop-items-messages.inventory-name")) || title
                .equals(Language.getMsg(player, "shop-items-messages.blocks-category.inventory-name")) || title
                .equals(Language.getMsg(player, "shop-items-messages.melee-category.inventory-name")) || title
                .equals(Language.getMsg(player, "shop-items-messages.armor-category.inventory-name")) || title
                .equals(Language.getMsg(player, "shop-items-messages.tools-category.inventory-name")) || title
                .equals(Language.getMsg(player, "shop-items-messages.ranged-category.inventory-name")) || title
                .equals(Language.getMsg(player, "shop-items-messages.potions-category.inventory-name")) || title
                .equals(Language.getMsg(player, "shop-items-messages.utility-category.inventory-name")));
    }

    /**
     * Saves a value to the main config file if it does not already exist.
     *
     * @param path the path to save the value to
     * @param ob   the value to save
     */
    public static void saveToMainConfigIfNotExists(String path, Object ob) {
        getMain().getConfig().set(path, ob);
        getMain().getConfig().options().copyDefaults(true);
        getMain().saveConfig();

    }

    /**
     * Converts a material representing a currency to a human-readable string.
     *
     * @param souls the material representing a currency
     * @return a human-readable string representation of the currency
     */
    public static String convertCurrencyToString(Player player, HashMap<Player, Integer> souls) {
        return Language.getMsg(player, "addons.underworld.meaning-souls-plural");

    }

    /**
     * Returns a string indicating whether the player has enough resources to buy an item or not.
     *
     * @param player the player to check the resources for
     * @param price  the price of the item
     * @param souls  the currency used to purchase the item
     * @return a string indicating whether the player can afford the item or not
     */
    public static String getStatus(Player player, int price, HashMap<Player, Integer> souls) {
        if (JavaPlugin.getPlugin(Underworld.class).getSouls().get(player) >= price) {
            return Language.getMsg(player, "shop-lore-status-can-buy");
        }
        return Language.getMsg(player, "shop-lore-status-cant-afford").replace("{currency}", convertCurrencyToString(player, souls));
    }


    /**
     * Returns a string indicating whether the player has enough resources to buy an item or not.
     *
     * @param player the player to check the resources for
     * @param price  the price of the item
     * @return a string indicating whether the player can afford the item or not
     */
    public static String getNameStatus(Player player, int price) {
        if (JavaPlugin.getPlugin(Underworld.class).getSouls().get(player) >= price) {
            return "&a";
        }
        return "&c";
    }

    /**
     * Updates or creates the selected item and index in the list based on the values in the config object.
     *
     * @param items  a list of UnderworldItem objects
     * @param config a FileConfiguration object
     */
    public static void updateSelectedItem(List<UnderworldItem> items, FileConfiguration config) {
        // Updates or creates, selected random item and index's of them in List
        if (config.getString("item") == null) {
            API.saveToMainConfigIfNotExists("item", items.get(ThreadLocalRandom.current().nextInt(items.size())).defaultName());
        }
        int index = 0;
        boolean isIndexSet = false;
        for (UnderworldItem item : items) {
            if (!isIndexSet) {
                index++;
                if (item.defaultName().equals(config.getString("item"))) {
                    isIndexSet = true;
                    index = index - 1;
                }
            }
        }

        if (config.getInt("item-index") != index) {
            API.saveToMainConfigIfNotExists("item-index", index);
        }
    }


    /**
     * Gets a list of blocks within a certain radius of a given location.
     *
     * @param location The starting location.
     * @param radius   The radius of the search area.
     * @return A list of blocks within the given radius of the starting location.
     */
    public static List<Block> getNearbyBlocks(Location location, int radius) {
        List<Block> blocks = new ArrayList<Block>();
        for (int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
            for (int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
                for (int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
                    blocks.add(location.getWorld().getBlockAt(x, y, z));
                }
            }
        }
        return blocks;
    }


    public static void debug(Object ob) {
        getMain().getLogger().info("Debug: " + ob.toString());
    }
}