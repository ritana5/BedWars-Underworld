package net.ritana5.underworld.items.bedwars1058;

import com.andrei1058.bedwars.api.language.Language;
import com.cryptomorin.xseries.XSound;
import com.hakan.core.HCore;
import net.ritana5.underworld.Underworld;
import net.ritana5.underworld.api.bedwars1058.UnderworldItem;
import net.ritana5.underworld.listeners.bedwars1058.BedWarsListener;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IceBridge extends UnderworldItem {

    @Override
    public ItemStack getItem() {
        return HCore.itemBuilder(Material.ICE).build();
    }

    @Override
    public String defaultName() {
        return Language.getMsg(BedWarsListener.getPlayers(), "addons.underworld.ice.item-name");
    }

    @Override
    public List<String> defaultLore() {
        return Language.getList(BedWarsListener.getPlayers(), "addons.underworld.ice.item-lore");
    }

    @Override
    public int getPrice() {
        return 4;
    }

    @Override
    public HashMap<Player, Integer> getCurrency() {
        return JavaPlugin.getPlugin(Underworld.class).getSouls();
    }

    @Override
    public boolean execute(Player player, Block block2) {
        List<Block> blocks = new ArrayList<>();
        // Get the player's current position and direction
        Location pos = player.getLocation();
        Vector direction = pos.getDirection();

        // Calculate the position of the first block in the ice path
        // based on the player's position and direction
        double x = pos.getX() + direction.getX();
        double y = pos.getY();
        double z = pos.getZ() + direction.getZ();
        World world = pos.getWorld();

        // Set the blocks in a straight line in front of the player
        for (int i = 0; i < 30; i++) {
            double finalX = x;
            double finalZ = z;
            Bukkit.getScheduler().runTaskLater(JavaPlugin.getPlugin(Underworld.class), () -> {
                for (int j = -1; j <= 1; j++) {
                    Vector perp = new Vector(-direction.getZ(), 0, direction.getX()).normalize();
                    double offsetX = j * perp.getX();
                    double offsetZ = j * perp.getZ();
                    Location location = new Location(world, Math.round(finalX + offsetX), y - 1, Math.round(finalZ + offsetZ));
                    Block block = world.getBlockAt(location);
                    if (!block.getType().isSolid()) {
                        XSound.BLOCK_GLASS_BREAK.play(location, 1.0f, 0.5f);
                        world.playEffect(location.clone().add(0, 1.3, 0), Effect.HAPPY_VILLAGER, 0);
                        block.setType(Material.ICE);
                        blocks.add(block);
                    }
                    Bukkit.getScheduler().runTaskLater(JavaPlugin.getPlugin(Underworld.class), () -> {
                        if (blocks.contains(block)) {
                            World w = block.getWorld();
                            XSound.ENTITY_CHICKEN_EGG.play(block.getLocation(), 1.0f, 0.5f);
                            w.playEffect(block.getLocation().clone().add(0, 1.3, 0), Effect.CLOUD, 0);
                            block.setType(Material.AIR);
                            blocks.remove(block);
                        }
                    }, 30 * 20L);
                }
            }, i * 2L);
            x += direction.getX();
            z += direction.getZ();
        }

        /**
         HCore.syncScheduler().every(1L).run((runnable) -> {
         // Check if the player is standing on an ice block
         Block block = world.getBlockAt(player.getLocation().getBlockX(), player.getLocation().getBlockY() - 1, player.getLocation().getBlockZ());
         if (block.getType() == Material.ICE && blocks.contains(block)) {
         // Remove the ice block
         block.setType(Material.AIR);
         blocks.remove(block);
         for(Block block1 : API.getNearbyBlocks(block.getLocation(), new ConfigUtils().getInt("Items." + defaultName() + ".block-break.radius"))){
         if(blocks.contains(block1)){
         block.setType(Material.AIR);
         blocks.remove(block);
         }
         }
         }

         // If there are no more ice blocks, stop the task
         if (blocks.isEmpty()) {
         runnable.cancel();
         }
         });
         **/


        return true;
    }

    @Override
    public boolean isBlockRequired() {
        return false;
    }

    @Override
    public boolean execute(Player player) {
        return false;
    }


}
