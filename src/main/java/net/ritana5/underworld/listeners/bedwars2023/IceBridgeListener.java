package net.ritana5.underworld.listeners.bedwars1058;

import com.andrei1058.bedwars.api.language.Language;
import com.hakan.core.HCore;
import net.ritana5.underworld.api.bedwars1058.UnderworldItem;
import net.ritana5.underworld.bedwars1058.API;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class IceBridgeListener implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) return;
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        UnderworldItem underworldItem = API.items.get(5);

        if (item == null || item.getType() == Material.AIR) return;
        if (item.getItemMeta() == null || item.getItemMeta().getDisplayName() == null) return;
        // if the items are same
        //  && item.getItemMeta().getDisplayName().contains("&r&f" + Language.getMsg(player, "addons.underworld.ice.item-nameChat"))
        if (item.getType().equals(underworldItem.getItem().getType())) {
            // If "execute" method returns true, it means that it executed without errors and should proceed
            if (underworldItem.isBlockRequired() && event.getClickedBlock() != null) {
                if (underworldItem.execute(player, event.getClickedBlock())) {
                    event.setCancelled(true);
                    if (player.getItemInHand().getAmount() == 1) {
                        player.setItemInHand(HCore.itemBuilder(Material.AIR).build());
                        return;
                    }
                    player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
                }
            } else {
                if (underworldItem.execute(player, null)) {
                    event.setCancelled(true);
                    if (player.getItemInHand().getAmount() == 1) {
                        player.setItemInHand(HCore.itemBuilder(Material.AIR).build());
                        return;
                    }
                    player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
                }
            }

        }
    }
}