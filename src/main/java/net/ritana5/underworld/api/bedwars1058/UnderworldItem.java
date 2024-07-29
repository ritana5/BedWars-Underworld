package net.ritana5.underworld.api.bedwars1058;

import net.ritana5.underworld.bedwars1058.API;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

/**
 * Abstract base class for representing a rotation item.
 * <p>
 * This class provides basic functionality for storing and manipulating the
 * properties of a rotation item, such as its name, lore, price, and currency.
 * Subclasses should override the {@link #execute(Player, Block)} method to implement
 * specific behavior for when the item is used.
 */
public abstract class UnderworldItem {
    /**
     * Returns the item stack for the rotation item.
     *
     * @return the item stack for the rotation item
     */
    public abstract ItemStack getItem();

    /**
     * Returns the default name for the rotation item.
     *
     * @return the default name for the rotation item
     */
    public abstract String defaultName();

    /**
     * Returns the default lore for the rotation item.
     *
     * @return the default lore for the rotation item
     */
    public abstract List<String> defaultLore();

    /**
     * Returns the price of the rotation item.
     *
     * @return the price of the rotation item
     */
    public abstract int getPrice();

    /**
     * Returns the currency used to purchase the rotation item.
     *
     * @return the currency used to purchase the rotation item
     */
    public abstract HashMap<Player, Integer> getCurrency();

    /**
     * Executes the action for the rotation item.
     * <p>
     * This method should be implemented by subclasses to define the specific
     * behavior for when the item is used.
     *
     * @param player the player using the item
     * @param block  the clicked block
     * @return true if the action was successful, false otherwise
     */
    public abstract boolean execute(Player player, Block block);

    /**
     * Returns whether the rotation item requires a block to be clicked.
     *
     * @return true if a block is required, false otherwise
     */
    public abstract boolean isBlockRequired();

    /**
     * Registers the rotation item with the item rotation API.
     */
    public void register() {
        API.items.add(this);
    }

    public abstract boolean execute(Player player);
}