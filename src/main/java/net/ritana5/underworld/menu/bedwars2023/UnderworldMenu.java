package net.ritana5.underworld.menu.bedwars2023;

import com.hakan.core.HCore;
import com.hakan.core.ui.inventory.InventoryGui;
import com.tomkeuper.bedwars.api.language.Language;
import net.ritana5.underworld.Underworld;
import net.ritana5.underworld.api.bedwars2023.UnderworldItem;
import net.ritana5.underworld.bedwars2023.API;
import net.ritana5.underworld.utils.gsound.GSound;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class UnderworldMenu extends InventoryGui {
    public UnderworldMenu(Player player) {
        super("UnderworldMenu." + player.getDisplayName(), Language.getMsg(player, "addons.underworld.menu-title"), 3, InventoryType.CHEST);

        if (!randomSet) {
            // Generate a random index
            Random rand = new Random();
            int randomIndex = rand.nextInt(doubles.length);

            // Get the random double from the list
            randomDouble = doubles[randomIndex];

            randomSet = true;
        }
    }

    double[] doubles = {0.5, 1, 1.5, 2.0};

    private static Boolean randomSet = false;
    private static double randomDouble;

    public String setDoubles() {
        if (randomDouble == 0.5) {
            return "&7(&aUnderworld discount: 0.5x&7)";
        } else if (randomDouble == 1) {
            return "";
        } else if (randomDouble == 1.5) {
            return "&7(&cUnderworld inflation: 1.5x&7)";
        } else {
            return "&7(&cUnderworld inflation: 2.0x&7)";
        }
    }

    @Override
    public void onOpen(@Nonnull Player player) {
        HashMap<Player, Integer> souls = JavaPlugin.getPlugin(Underworld.class).getSouls();

        // 2x Diamond
        int slotForItem1 = 10;
        UnderworldItem item1 = API.items.get(0);
        String name1 = item1.defaultName();
        List<String> lores1 = item1.defaultLore();
        int price1 = item1.getPrice();

        if (randomDouble == 0.5) {
            price1 = 2;
        } else if (randomDouble == 1) {
            price1 = 4;
        } else if (randomDouble == 1.5) {
            price1 = 6;
        } else if (randomDouble == 2) {
            price1 = 8;
        }

        name1 = name1.replace("%BuyStatus%", API.getNameStatus(player, price1));
        List<String> itemLores1 = new ArrayList<>();
        for (String loress1 : lores1) {
            if (loress1.contains("%price%") && loress1.contains("%disfla%")) {
                itemLores1.add(loress1.replace("%price% Souls %disfla%", price1 + " &bSouls " + setDoubles()));
            } else {
                itemLores1.add(loress1);
            }
        }


        ItemStack itemStack1 = HCore.itemBuilder(item1.getItem()).name(true, ChatColor.RED + name1).lores(true, itemLores1).build();
        int finalPrice1 = price1;
        super.setItem(slotForItem1, itemStack1, (e) -> {
            if (JavaPlugin.getPlugin(Underworld.class).getSouls().get((Player) e.getWhoClicked()) >= finalPrice1) {
                JavaPlugin.getPlugin(Underworld.class).getSouls().put(player, JavaPlugin.getPlugin(Underworld.class).getSouls().get(player) - finalPrice1);
                // add item to player's inventory ._.
                e.getWhoClicked().getInventory().addItem(HCore.itemBuilder(item1.getItem()).build());
                // send message to the player .-.
                ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), GSound.BLOCK_NOTE_BLOCK_PLING.parseSound(), 1.0f, 2.0f);
                InventoryGui menu = new UnderworldMenu(player);
                menu.open((Player) e.getWhoClicked());
                e.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&',
                        Language.getMsg((Player) e.getWhoClicked(), "shop-new-purchase")
                                .replace("%bw_prefix%", Language.getMsg((Player) e.getWhoClicked(), "prefix"))
                                .replace("%bw_item%", "&6" + Language.getMsg(player, "addons.underworld.diamond.item-nameChat"))));
            } else {
                // send not enough resources message .-.
                ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), GSound.ENTITY_VILLAGER_NO.parseSound(), 1.0f, 1.0f);
                e.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&',
                        Language.getMsg((Player) e.getWhoClicked(), "shop-insuff-money")
                                .replace("%bw_prefix%", Language.getMsg((Player) e.getWhoClicked(), "prefix"))
                                .replace("%bw_amount%", String.valueOf(finalPrice1 - JavaPlugin.getPlugin(Underworld.class).getSouls().get((Player) e.getWhoClicked())))
                                .replace("%bw_currency%", API.convertCurrencyToString(player, souls))));
            }
        });

        // 2x Emerald
        int slotForItem2 = 11;
        UnderworldItem item2 = API.items.get(1);
        String name2 = item2.defaultName();
        List<String> lores2 = item2.defaultLore();
        int price2 = item2.getPrice();

        if (randomDouble == 0.5) {
            price2 = 2;
        } else if (randomDouble == 1) {
            price2 = 5;
        } else if (randomDouble == 1.5) {
            price2 = 7;
        } else if (randomDouble == 2) {
            price2 = 10;
        }

        name2 = name2.replace("%BuyStatus%", API.getNameStatus(player, price2));
        List<String> itemLores2 = new ArrayList<>();
        for (String loress2 : lores2) {
            if (loress2.contains("%price%") && loress2.contains("%disfla%")) {
                itemLores2.add(loress2.replace("%price% Souls %disfla%", price2 + " &bSouls " + setDoubles()));
            } else {
                itemLores2.add(loress2);
            }
        }


        ItemStack itemStack2 = HCore.itemBuilder(item2.getItem()).name(true, ChatColor.RED + name2).lores(true, itemLores2).build();
        int finalPrice2 = price2;
        super.setItem(slotForItem2, itemStack2, (e) -> {
            if (JavaPlugin.getPlugin(Underworld.class).getSouls().get((Player) e.getWhoClicked()) >= finalPrice2) {
                JavaPlugin.getPlugin(Underworld.class).getSouls().put(player, JavaPlugin.getPlugin(Underworld.class).getSouls().get(player) - finalPrice2);
                // add item to player's inventory ._.
                e.getWhoClicked().getInventory().addItem(HCore.itemBuilder(item2.getItem()).build());
                // send message to the player .-.
                ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), GSound.BLOCK_NOTE_BLOCK_PLING.parseSound(), 1.0f, 2.0f);
                e.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&',
                        Language.getMsg((Player) e.getWhoClicked(), "shop-new-purchase")
                                .replace("%bw_prefix%", Language.getMsg((Player) e.getWhoClicked(), "prefix"))
                                .replace("%bw_item%", "&6" + Language.getMsg(player, "addons.underworld.emerald.item-nameChat"))));
            } else {
                // send not enough resources message .-.
                ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), GSound.ENTITY_VILLAGER_NO.parseSound(), 1.0f, 1.0f);
                InventoryGui menu = new UnderworldMenu(player);
                menu.open((Player) e.getWhoClicked());
                e.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&',
                        Language.getMsg((Player) e.getWhoClicked(), "shop-insuff-money")
                                .replace("%bw_prefix%", Language.getMsg((Player) e.getWhoClicked(), "prefix"))
                                .replace("%bw_amount%", String.valueOf(finalPrice2 - JavaPlugin.getPlugin(Underworld.class).getSouls().get((Player) e.getWhoClicked())))
                                .replace("%bw_currency%", API.convertCurrencyToString(player, souls))));
            }
        });

        // Recall Teammates
        int slotForItem3 = 12;
        UnderworldItem item3 = API.items.get(2);
        String name3 = item3.defaultName();
        List<String> lores3 = item3.defaultLore();
        int price3 = item3.getPrice();

        if (randomDouble == 0.5) {
            price3 = 3;
        } else if (randomDouble == 1) {
            price3 = 6;
        } else if (randomDouble == 1.5) {
            price3 = 9;
        } else if (randomDouble == 2) {
            price3 = 12;
        }

        name3 = name3.replace("%BuyStatus%", API.getNameStatus(player, price3));
        List<String> itemLores3 = new ArrayList<>();
        for (String loress3 : lores3) {
            if (loress3.contains("%price%") && loress3.contains("%disfla%")) {
                itemLores3.add(loress3.replace("%price% Souls %disfla%", price3 + " &bSouls " + setDoubles()));
            } else {
                itemLores3.add(loress3);
            }
        }


        ItemStack itemStack3 = HCore.itemBuilder(item3.getItem()).name(true, ChatColor.RED + name3).lores(true, itemLores3).build();
        int finalPrice3 = price3;
        super.setItem(slotForItem3, itemStack3, (e) -> {
            if (JavaPlugin.getPlugin(Underworld.class).getSouls().get((Player) e.getWhoClicked()) >= finalPrice3) {
                JavaPlugin.getPlugin(Underworld.class).getSouls().put(player, JavaPlugin.getPlugin(Underworld.class).getSouls().get(player) - finalPrice3);
                // add item to player's inventory ._.
                e.getWhoClicked().getInventory().addItem(HCore.itemBuilder(item3.getItem()).name(ChatColor.translateAlternateColorCodes('&', "&r&f" + Language.getMsg(player, "addons.underworld.recall.item-nameChat"))).build());
                // send message to the player .-.
                ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), GSound.BLOCK_NOTE_BLOCK_PLING.parseSound(), 1.0f, 2.0f);
                e.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&',
                        Language.getMsg((Player) e.getWhoClicked(), "shop-new-purchase")
                                .replace("%bw_prefix%", Language.getMsg((Player) e.getWhoClicked(), "prefix"))
                                .replace("%bw_item%", "&6" + Language.getMsg(player, "addons.underworld.recall.item-nameChat"))));
            } else {
                // send not enough resources message .-.
                ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), GSound.ENTITY_VILLAGER_NO.parseSound(), 1.0f, 1.0f);
                InventoryGui menu = new UnderworldMenu(player);
                menu.open((Player) e.getWhoClicked());
                e.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&',
                        Language.getMsg((Player) e.getWhoClicked(), "shop-insuff-money")
                                .replace("%bw_prefix%", Language.getMsg((Player) e.getWhoClicked(), "prefix"))
                                .replace("%bw_amount%", String.valueOf(finalPrice3 - JavaPlugin.getPlugin(Underworld.class).getSouls().get((Player) e.getWhoClicked())))
                                .replace("%bw_currency%", API.convertCurrencyToString(player, souls))));
            }
        });

        // Stick of Second Chances
        int slotForItem4 = 13;
        UnderworldItem item4 = API.items.get(3);
        String name4 = item4.defaultName();
        List<String> lores4 = item4.defaultLore();
        int price4 = item4.getPrice();

        if (randomDouble == 0.5) {
            price4 = 4;
        } else if (randomDouble == 1) {
            price4 = 8;
        } else if (randomDouble == 1.5) {
            price4 = 12;
        } else if (randomDouble == 2) {
            price4 = 16;
        }

        name4 = name4.replace("%BuyStatus%", API.getNameStatus(player, price4));
        List<String> itemLores4 = new ArrayList<>();
        for (String loress4 : lores4) {
            if (loress4.contains("%price%") && loress4.contains("%disfla%")) {
                itemLores4.add(loress4.replace("%price% Souls %disfla%", price4 + " &bSouls " + setDoubles()));
            } else {
                itemLores4.add(loress4);
            }
        }


        ItemStack itemStack4 = HCore.itemBuilder(item4.getItem()).name(true, ChatColor.RED + name4).lores(true, itemLores4).build();
        int finalPrice4 = price4;
        super.setItem(slotForItem4, itemStack4, (e) -> {
            if (JavaPlugin.getPlugin(Underworld.class).getSouls().get((Player) e.getWhoClicked()) >= finalPrice4) {
                JavaPlugin.getPlugin(Underworld.class).getSouls().put(player, JavaPlugin.getPlugin(Underworld.class).getSouls().get(player) - finalPrice4);
                // add item to player's inventory ._.
                e.getWhoClicked().getInventory().addItem(HCore.itemBuilder(item4.getItem()).name(ChatColor.translateAlternateColorCodes('&', "&r&f" + Language.getMsg(player, "addons.underworld.stick.item-nameChat"))).build());
                // send message to the player .-.
                ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), GSound.BLOCK_NOTE_BLOCK_PLING.parseSound(), 1.0f, 2.0f);
                e.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&',
                        Language.getMsg((Player) e.getWhoClicked(), "shop-new-purchase")
                                .replace("%bw_prefix%", Language.getMsg((Player) e.getWhoClicked(), "prefix"))
                                .replace("%bw_item%", "&6" + Language.getMsg(player, "addons.underworld.stick.item-nameChat"))));
            } else {
                // send not enough resources message .-.
                ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), GSound.ENTITY_VILLAGER_NO.parseSound(), 1.0f, 1.0f);
                InventoryGui menu = new UnderworldMenu(player);
                menu.open((Player) e.getWhoClicked());
                e.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&',
                        Language.getMsg((Player) e.getWhoClicked(), "shop-insuff-money")
                                .replace("%bw_prefix%", Language.getMsg((Player) e.getWhoClicked(), "prefix"))
                                .replace("%bw_amount%", String.valueOf(finalPrice4 - JavaPlugin.getPlugin(Underworld.class).getSouls().get((Player) e.getWhoClicked())))
                                .replace("%bw_currency%", API.convertCurrencyToString(player, souls))));
            }
        });

        // Trick or Treat Sword
        int slotForItem5 = 14;
        UnderworldItem item5 = API.items.get(4);
        String name5 = item5.defaultName();
        List<String> lores5 = item5.defaultLore();
        int price5 = item5.getPrice();

        if (randomDouble == 0.5) {
            price5 = 3;
        } else if (randomDouble == 1) {
            price5 = 6;
        } else if (randomDouble == 1.5) {
            price5 = 9;
        } else if (randomDouble == 2) {
            price5 = 12;
        }

        name5 = name5.replace("%BuyStatus%", API.getNameStatus(player, price5));
        List<String> itemLores5 = new ArrayList<>();
        for (String loress5 : lores5) {
            if (loress5.contains("%price%") && loress5.contains("%disfla%")) {
                itemLores5.add(loress5.replace("%price% Souls %disfla%", price5 + " &bSouls " + setDoubles()));
            } else {
                itemLores5.add(loress5);
            }
        }


        ItemStack itemStack5 = HCore.itemBuilder(item5.getItem()).name(true, ChatColor.RED + name5).lores(true, itemLores5).build();
        int finalPrice5 = price5;
        super.setItem(slotForItem5, itemStack5, (e) -> {
            if (JavaPlugin.getPlugin(Underworld.class).getSouls().get((Player) e.getWhoClicked()) >= finalPrice5) {
                JavaPlugin.getPlugin(Underworld.class).getSouls().put(player, JavaPlugin.getPlugin(Underworld.class).getSouls().get(player) - finalPrice5);
                // add item to player's inventory ._.
                e.getWhoClicked().getInventory().addItem(HCore.itemBuilder(item5.getItem()).name(ChatColor.translateAlternateColorCodes('&', "&r&f" + Language.getMsg(player, "addons.underworld.sword.item-nameChat"))).build());
                // send message to the player .-.
                ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), GSound.BLOCK_NOTE_BLOCK_PLING.parseSound(), 1.0f, 2.0f);
                e.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&',
                        Language.getMsg((Player) e.getWhoClicked(), "shop-new-purchase")
                                .replace("%bw_prefix%", Language.getMsg((Player) e.getWhoClicked(), "prefix"))
                                .replace("%bw_item%", "&6" + Language.getMsg(player, "addons.underworld.sword.item-nameChat"))));
            } else {
                // send not enough resources message .-.
                ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), GSound.ENTITY_VILLAGER_NO.parseSound(), 1.0f, 1.0f);
                InventoryGui menu = new UnderworldMenu(player);
                menu.open((Player) e.getWhoClicked());
                e.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&',
                        Language.getMsg((Player) e.getWhoClicked(), "shop-insuff-money")
                                .replace("%bw_prefix%", Language.getMsg((Player) e.getWhoClicked(), "prefix"))
                                .replace("%bw_amount%", String.valueOf(finalPrice5 - JavaPlugin.getPlugin(Underworld.class).getSouls().get((Player) e.getWhoClicked())))
                                .replace("%bw_currency%", API.convertCurrencyToString(player, souls))));
            }
        });

        // Ice Bridge
        int slotForItem6 = 15;
        UnderworldItem item6 = API.items.get(5);
        String name6 = item5.defaultName();
        List<String> lores6 = item6.defaultLore();
        int price6 = item6.getPrice();

        if (randomDouble == 0.5) {
            price6 = 2;
        } else if (randomDouble == 1) {
            price6 = 4;
        } else if (randomDouble == 1.5) {
            price6 = 6;
        } else if (randomDouble == 2) {
            price6 = 8;
        }

        name6 = name6.replace("%BuyStatus%", API.getNameStatus(player, price6));
        List<String> itemLores6 = new ArrayList<>();
        for (String loress6 : lores6) {
            if (loress6.contains("%price%") && loress6.contains("%disfla%")) {
                itemLores6.add(loress6.replace("%price% Souls %disfla%", price6 + " &bSouls " + setDoubles()));
            } else {
                itemLores6.add(loress6);
            }
        }


        ItemStack itemStack6 = HCore.itemBuilder(item6.getItem()).name(true, ChatColor.RED + name6).lores(true, itemLores6).build();
        int finalPrice6 = price6;
        super.setItem(slotForItem6, itemStack6, (e) -> {
            if (JavaPlugin.getPlugin(Underworld.class).getSouls().get((Player) e.getWhoClicked()) >= finalPrice6) {
                JavaPlugin.getPlugin(Underworld.class).getSouls().put(player, JavaPlugin.getPlugin(Underworld.class).getSouls().get(player) - finalPrice6);
                // add item to player's inventory ._.
                e.getWhoClicked().getInventory().addItem(HCore.itemBuilder(item6.getItem()).name(ChatColor.translateAlternateColorCodes('&', "&r&f" + Language.getMsg(player, "addons.underworld.ice.item-nameChat"))).build());
                // send message to the player .-.
                ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), GSound.BLOCK_NOTE_BLOCK_PLING.parseSound(), 1.0f, 2.0f);
                e.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&',
                        Language.getMsg((Player) e.getWhoClicked(), "shop-new-purchase")
                                .replace("%bw_prefix%", Language.getMsg((Player) e.getWhoClicked(), "prefix"))
                                .replace("%bw_item%", "&6" + Language.getMsg(player, "addons.underworld.ice.item-nameChat"))));
            } else {
                // send not enough resources message .-.
                ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), GSound.ENTITY_VILLAGER_NO.parseSound(), 1.0f, 1.0f);
                InventoryGui menu = new UnderworldMenu(player);
                menu.open((Player) e.getWhoClicked());
                e.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&',
                        Language.getMsg((Player) e.getWhoClicked(), "shop-insuff-money")
                                .replace("%bw_prefix%", Language.getMsg((Player) e.getWhoClicked(), "prefix"))
                                .replace("%bw_amount%", String.valueOf(finalPrice6 - JavaPlugin.getPlugin(Underworld.class).getSouls().get((Player) e.getWhoClicked())))
                                .replace("%bw_currency%", API.convertCurrencyToString(player, souls))));
            }
        });

        // Portable Enderchest
        int slotForItem7 = 16;
        UnderworldItem item7 = API.items.get(6);
        String name7 = item5.defaultName();
        List<String> lores7 = item7.defaultLore();
        int price7 = item7.getPrice();

        if (randomDouble == 0.5) {
            price7 = 2;
        } else if (randomDouble == 1) {
            price7 = 4;
        } else if (randomDouble == 1.5) {
            price7 = 6;
        } else if (randomDouble == 2) {
            price7 = 8;
        }

        name7 = name7.replace("%BuyStatus%", API.getNameStatus(player, price7));
        List<String> itemLores7 = new ArrayList<>();
        for (String loress7 : lores7) {
            if (loress7.contains("%price%") && loress7.contains("%disfla%")) {
                itemLores7.add(loress7.replace("%price% Souls %disfla%", price7 + " &bSouls " + setDoubles()));
            } else {
                itemLores7.add(loress7);
            }
        }


        ItemStack itemStack7 = HCore.itemBuilder(item7.getItem()).name(true, ChatColor.RED + name7).lores(true, itemLores7).build();
        int finalPrice7 = price7;
        super.setItem(slotForItem7, itemStack7, (e) -> {
            if (JavaPlugin.getPlugin(Underworld.class).getSouls().get((Player) e.getWhoClicked()) >= finalPrice7) {
                JavaPlugin.getPlugin(Underworld.class).getSouls().put(player, JavaPlugin.getPlugin(Underworld.class).getSouls().get(player) - finalPrice7);
                // add item to player's inventory ._.
                e.getWhoClicked().getInventory().addItem(HCore.itemBuilder(item7.getItem()).name(ChatColor.translateAlternateColorCodes('&', "&r&f" + Language.getMsg(player, "addons.underworld.ender.item-nameChat"))).build());

                // send message to the player .-.
                ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), GSound.BLOCK_NOTE_BLOCK_PLING.parseSound(), 1.0f, 2.0f);
                e.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&',
                        Language.getMsg((Player) e.getWhoClicked(), "shop-new-purchase")
                                .replace("%bw_prefix%", Language.getMsg((Player) e.getWhoClicked(), "prefix"))
                                .replace("%bw_item%", "&6" + Language.getMsg(player, "addons.underworld.ender.item-nameChat"))));
            } else {
                // send not enough resources message .-.
                ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), GSound.ENTITY_VILLAGER_NO.parseSound(), 1.0f, 1.0f);
                InventoryGui menu = new UnderworldMenu(player);
                menu.open((Player) e.getWhoClicked());
                e.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&',
                        Language.getMsg((Player) e.getWhoClicked(), "shop-insuff-money")
                                .replace("%bw_prefix%", Language.getMsg((Player) e.getWhoClicked(), "prefix"))
                                .replace("%bw_amount%", String.valueOf(finalPrice7 - JavaPlugin.getPlugin(Underworld.class).getSouls().get((Player) e.getWhoClicked())))
                                .replace("%bw_currency%", API.convertCurrencyToString(player, souls))));
            }
        });


    }
}