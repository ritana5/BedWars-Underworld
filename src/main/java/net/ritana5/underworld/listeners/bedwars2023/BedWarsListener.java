package net.ritana5.underworld.listeners.bedwars2023;

import com.hakan.core.HCore;
import com.tomkeuper.bedwars.api.arena.GameState;
import com.tomkeuper.bedwars.api.arena.IArena;
import com.tomkeuper.bedwars.api.arena.team.ITeam;
import com.tomkeuper.bedwars.api.events.gameplay.GameStateChangeEvent;
import com.tomkeuper.bedwars.api.events.player.PlayerBedBreakEvent;
import com.tomkeuper.bedwars.api.events.player.PlayerKillEvent;
import com.tomkeuper.bedwars.api.language.Language;
import com.tomkeuper.bedwars.support.papi.SupportPAPI;
import net.ritana5.underworld.Underworld;
import net.ritana5.underworld.language.MessagePath;
import net.ritana5.underworld.support.bedwars2023.BedWars2023;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.tomkeuper.bedwars.api.language.Language.getList;

public class BedWarsListener implements Listener {
    private static final Random random = new Random();
    private static final List<EntityType> mobTypes = List.of(EntityType.ZOMBIE, EntityType.SKELETON, EntityType.GIANT);
    public IArena arena;

    public BedWarsListener() {
    }

    static Player ps;

//    @EventHandler
//    public void onTrickOrTreat(PlayerInteractEvent event) {
//        if (HCore.itemBuilder(event.getItem()).getName().equals(ChatColor.translateAlternateColorCodes('&', "&r&f" + Language.getMsg(ps, "addons.underworld.sword.item-nameChat")))) {
//            event.getItem().getItemMeta().getItemFlags().clear();
//        }
//    }


    @EventHandler
    public void onPortableEnderchest(BlockPlaceEvent event) {
        Block block = event.getBlock();

        // Check if the placed block is an Ender Chest
        if (block.getType() == Material.ENDER_CHEST) {
            // Schedule task to remove the Ender Chest after 30 seconds (600 ticks)
            new BukkitRunnable() {
                @Override
                public void run() {
                    // Check if the block is still an Ender Chest
                    if (block.getType() == Material.ENDER_CHEST) {
                        block.setType(Material.AIR); // Remove the Ender Chest
                    }
                }
            }.runTaskLater(JavaPlugin.getPlugin(Underworld.class), 600L); // 600 ticks = 30 seconds
        }
    }

    @EventHandler
    public void onRecall(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getItem() != null && event.getItem().getType() == Material.ENDER_PEARL) {
                if (HCore.itemBuilder(event.getItem()).getName().equals(ChatColor.translateAlternateColorCodes('&', "&r&f" + Language.getMsg(ps, "addons.underworld.recall.item-nameChat")))) {
                    event.setCancelled(true);
                    // Consume the Ender Pearl
                    int amount = event.getItem().getAmount();
                    event.getItem().setAmount(amount - 1);
                    Player player = event.getPlayer();
                    List<Player> member = arena.getTeam(player).getMembers();
                    for (Player player5 : new ArrayList<>(member)) {
                        for (String five : getList(player5, MessagePath.MESSAGES_RECALL_5)) {
                            player5.sendMessage(SupportPAPI.getSupportPAPI().replace(player5, five));
                        }
                    }
                    for (Player player4 : new ArrayList<>(member)) {
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                for (String four : getList(player4, MessagePath.MESSAGES_RECALL_4)) {
                                    player4.sendMessage(SupportPAPI.getSupportPAPI().replace(player4, four));
                                }
                            }
                        }.runTaskLater(JavaPlugin.getPlugin(Underworld.class), 20L); // 1 second
                    }
                    for (Player player3 : new ArrayList<>(member)) {
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                for (String three : getList(player3, MessagePath.MESSAGES_RECALL_3)) {
                                    player3.sendMessage(SupportPAPI.getSupportPAPI().replace(player3, three));
                                }
                            }
                        }.runTaskLater(JavaPlugin.getPlugin(Underworld.class), 40L); // 2 seconds
                    }
                    for (Player player2 : new ArrayList<>(member)) {
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                for (String two : getList(player2, MessagePath.MESSAGES_RECALL_2)) {
                                    player2.sendMessage(SupportPAPI.getSupportPAPI().replace(player2, two));
                                }
                            }
                        }.runTaskLater(JavaPlugin.getPlugin(Underworld.class), 60L); // 3 seconds
                    }
                    for (Player player1 : new ArrayList<>(member)) {
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                for (String one : getList(player1, MessagePath.MESSAGES_RECALL_1)) {
                                    player1.sendMessage(SupportPAPI.getSupportPAPI().replace(player1, one));
                                }
                            }
                        }.runTaskLater(JavaPlugin.getPlugin(Underworld.class), 80L); // 4 seconds
                    }
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            member.forEach(player1 -> player1.teleport(arena.getTeam(player1).getSpawn()));
                        }
                    }.runTaskLater(JavaPlugin.getPlugin(Underworld.class), 100L); // 5 seconds
                }
            }
        }
    }

    @EventHandler
    public void onKill(PlayerKillEvent e) {
        Player player = e.getKiller();
        Player victim = e.getVictim();
        if (player == null || victim.equals(player)) return;
        Integer current = JavaPlugin.getPlugin(Underworld.class).getSouls().get(player);
        Integer now = current + 5;
        JavaPlugin.getPlugin(Underworld.class).getSouls().put(player, now);
    }

    @EventHandler
    public void onBedBreak(PlayerBedBreakEvent e) {
        Player player = e.getPlayer();
        if (player == null) return;
        Integer current = JavaPlugin.getPlugin(Underworld.class).getSouls().get(player);
        Integer now = current + 5;
        JavaPlugin.getPlugin(Underworld.class).getSouls().put(player, now);
    }

    @EventHandler
    public void onGameStart(GameStateChangeEvent event) {
        arena = event.getArena();
        if (BedWars2023.isMode(arena) && event.getNewState() == GameState.playing) {
            HCore.registerListeners(new ShopListener());
            Bukkit.getScheduler().runTaskLater(JavaPlugin.getPlugin(Underworld.class), () -> {
                for (ITeam team : arena.getTeams()) {
                    for (Player player : new ArrayList<>(team.getMembers())) {
                        ps = player;
                        JavaPlugin.getPlugin(Underworld.class).getSouls().put(player, 1000);
                        for (String tutorialMessage : getList(player, MessagePath.MESSAGES_ARENA_START_TUTORIAL)) {
                            player.sendMessage(SupportPAPI.getSupportPAPI().replace(player, tutorialMessage));
                        }
                    }
                }

                List<Location> emeraldGeneratorLocations = new ArrayList<>(event.getArena().getConfig().getArenaLocations("generator.Emerald"));
                spawnMobsPeriodically(event.getArena().getTeams().size(), emeraldGeneratorLocations);
            }, 5L);
        }
    }

    private void spawnMobsPeriodically(int teamCount, List<Location> locations) {
        Bukkit.getScheduler().runTaskTimer(JavaPlugin.getPlugin(Underworld.class), () -> {
            for (int i = 0; i < teamCount; i++) {
                Location spawnLocation = getRandomLocation(locations.get(i % locations.size()), 6);
                EntityType entityType = mobTypes.get(random.nextInt(mobTypes.size()));

                if (entityType == EntityType.ZOMBIE) {
                    spawnZombieWithHelmet(spawnLocation.clone().add(0, 2, 0));
                } else if (entityType == EntityType.SKELETON) {
                    spawnSkeletonWithHelmetAndBow(spawnLocation.clone().add(0, 2, 0));
                } else if (entityType == EntityType.GIANT) {
                    spawnGiantWithHelmet(spawnLocation.clone().add(0, 2, 0));
                }
            }
        }, 0L, 8 * 60 * 20L); // 8 minutes in ticks
    }

    private void spawnZombieWithHelmet(Location location) {
        Zombie zombie = location.getWorld().spawn(location, Zombie.class);
        zombie.getEquipment().setHelmet(createGoldenHelmet());
        zombie.setBaby(false); // Ensure the zombie is not a baby (hostile)
    }

    private void spawnSkeletonWithHelmetAndBow(Location location) {
        org.bukkit.entity.Skeleton skeleton = location.getWorld().spawn(location, org.bukkit.entity.Skeleton.class);
        skeleton.getEquipment().setHelmet(createGoldenHelmet());
        skeleton.getEquipment().setItemInHand(createBow());
        skeleton.setSkeletonType(org.bukkit.entity.Skeleton.SkeletonType.NORMAL); // Ensure the skeleton is normal type
    }

    private void spawnGiantWithHelmet(Location location) {
        org.bukkit.entity.Giant giant = location.getWorld().spawn(location, org.bukkit.entity.Giant.class);
        giant.getEquipment().setHelmet(createGoldenHelmet());
    }

    private Location getRandomLocation(Location center, int radius) {
        int x = center.getBlockX() + random.nextInt(radius * 2) - radius;
        int z = center.getBlockZ() + random.nextInt(radius * 2) - radius;
        return new Location(center.getWorld(), x + 0.5, center.getBlockY(), z + 0.5);
    }

    private ItemStack createGoldenHelmet() {
        ItemStack helmet = HCore.itemBuilder(Material.GOLD_HELMET).build();
        ItemMeta meta = helmet.getItemMeta();
        // Add any customizations here if needed
        helmet.setItemMeta(meta);
        return helmet;
    }

    private ItemStack createBow() {
        ItemStack bow = HCore.itemBuilder(Material.BOW).build();
        ItemMeta meta = bow.getItemMeta();
        // Add any customizations here if needed
        bow.setItemMeta(meta);
        return bow;
    }

    public static Player getPlayers() {
        return ps;
    }
}
