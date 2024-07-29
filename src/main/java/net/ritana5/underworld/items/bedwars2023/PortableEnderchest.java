package net.ritana5.underworld.items.bedwars1058;

import com.andrei1058.bedwars.api.language.Language;
import com.hakan.core.HCore;
import net.ritana5.underworld.Underworld;
import net.ritana5.underworld.api.bedwars1058.UnderworldItem;
import net.ritana5.underworld.listeners.bedwars1058.BedWarsListener;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;

public class PortableEnderchest extends UnderworldItem {

    @Override
    public ItemStack getItem() {
        return HCore.itemBuilder(Material.ENDER_CHEST).build();
    }

    @Override
    public String defaultName() {
        return Language.getMsg(BedWarsListener.getPlayers(), "addons.underworld.ender.item-name");
    }

    @Override
    public List<String> defaultLore() {
        return Language.getList(BedWarsListener.getPlayers(), "addons.underworld.ender.item-lore");
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
    public boolean execute(Player player) {
        return false;
    }
    @Override
    public boolean execute(Player player, Block block) {
        return false;
    }

    @Override
    public boolean isBlockRequired() {
        return false;
    }

}