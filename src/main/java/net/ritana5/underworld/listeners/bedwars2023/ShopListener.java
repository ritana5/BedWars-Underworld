package net.ritana5.underworld.listeners.bedwars2023;

import com.hakan.core.HCore;
import com.hakan.core.ui.inventory.InventoryGui;
import com.tomkeuper.bedwars.api.language.Language;
import net.ritana5.underworld.Underworld;
import net.ritana5.underworld.bedwars2023.API;
import net.ritana5.underworld.menu.bedwars2023.UnderworldMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class ShopListener implements Listener {

    @EventHandler
    public void onPlayerShopOpen(InventoryOpenEvent event) {
        Inventory inv = event.getInventory();
        String title = event.getView().getTitle();
        Player player = (Player) event.getPlayer();
        // Check if the inventory is a shop inventory
        if (inv == null || !API.isShop(player, title)) {
            return;
        }
        API.debug("Player " + player.getName() + " opened the shop");
        API.debug("Adding the item.");

        String itemName = Language.getMsg(player, "addons.underworld.main.name");
        List<String> itemLore = Language.getList(player, "addons.underworld.main.lore");
        itemLore.replaceAll((s) -> s.replace("%souls%", JavaPlugin.getPlugin(Underworld.class).getSouls().get(player).toString()));
        ItemStack item = HCore.itemBuilder(Material.SKULL_ITEM)
                .name(true, itemName)
                .lores(true, itemLore)
                .build();
        inv.setItem(JavaPlugin.getPlugin(Underworld.class).menuSlot, item);
    }

    @EventHandler
    public void onPlayerShopClick(InventoryClickEvent e) {
        Inventory inv = e.getInventory();
        String title = e.getView().getTitle();
        Player player = (Player) e.getWhoClicked();
        String itemName = Language.getMsg(player, "addons.underworld.main.name");
        // Check if the inventory is a shop inventory
        if (inv == null) return;
        if (!API.isShop(player, title)) return;
        API.debug("Player " + player.getName() + " clicked in the shop");
        e.setCancelled(true);

        try {
            if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem().getItemMeta() != null && e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals(itemName)) {
                    InventoryGui menu = new UnderworldMenu(player);
                    menu.open((Player) e.getWhoClicked());
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            Bukkit.getLogger().severe("Error Message: " + exception.getMessage());
        }
    }
}