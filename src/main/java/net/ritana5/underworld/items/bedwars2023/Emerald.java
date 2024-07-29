package net.ritana5.underworld.items.bedwars2023;

import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XTag;
import com.hakan.core.HCore;
import com.tomkeuper.bedwars.api.language.Language;
import net.ritana5.underworld.Underworld;
import net.ritana5.underworld.api.bedwars2023.UnderworldItem;
import net.ritana5.underworld.bedwars2023.API;
import net.ritana5.underworld.listeners.bedwars2023.BedWarsListener;
import net.ritana5.underworld.utils.bedwars2023.ConfigUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;


public class Emerald extends UnderworldItem {
    @Override
    public ItemStack getItem() {
        return HCore.itemBuilder(Material.EMERALD, 2).build();
    }

    @Override
    public String defaultName() {
        return Language.getMsg(BedWarsListener.getPlayers(), "addons.underworld.emerald.item-name");
    }

    @Override
    public List<String> defaultLore() {
        return Language.getList(BedWarsListener.getPlayers(), "addons.underworld.emerald.item-lore");
    }

    @Override
    public int getPrice() {
        return 5;
    }

    @Override
    public HashMap<Player, Integer> getCurrency() {
        return JavaPlugin.getPlugin(Underworld.class).getSouls();
    }

    @Override
    public boolean execute(Player player) {
        return false;
    }

    @Override
    public boolean execute(Player player, Block block) {
        if (player == null || block == null) {
            // Player or block is null, return false
            return false;
        }
        if (!API.getBedwarsAPI().getArenaUtil().getArenaByPlayer(player).isBlockPlaced(block)) {
            player.sendMessage(Language.getMsg(player, "item-rotation.Bridge-Zapper.deny"));
            return false;
        }
        if (!isWool(block)) {
            player.sendMessage(Language.getMsg(player, "item-rotation.Bridge-Zapper.deny"));
            return false;
        }

        int radius = new ConfigUtils().getInt("Items." + defaultName() + ".radius");
        if (radius <= 0) {
            // Radius is not a positive integer, return false
            return false;
        }

        Location blockLocation = block.getLocation();
        if (blockLocation == null) {
            // Block location is null, return false
            return false;
        }

        Collection<Block> nearbyBlocks = API.getNearbyBlocks(blockLocation, radius);

        for (Block nearbyBlock : nearbyBlocks) {
            if (isWool(nearbyBlock)) {
                nearbyBlock.setType(Material.AIR);
            }
        }

        return true;

    }

    @Override
    public boolean isBlockRequired() {
        return true;
    }

    /**
     * Returns true if the given block is wool, false otherwise.
     *
     * @param block the block to check
     * @return true if the block is wool, false otherwise
     */
    public boolean isWool(Block block) {
        return XTag.WOOL.isTagged(XMaterial.matchXMaterial(block.getType()));
    }
}