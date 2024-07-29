package net.ritana5.underworld.support.bedwars1058;

import com.andrei1058.bedwars.api.BedWars;
import com.andrei1058.bedwars.api.arena.IArena;
import com.hakan.core.HCore;
import net.ritana5.underworld.Underworld;
import net.ritana5.underworld.items.bedwars1058.*;
import net.ritana5.underworld.language.bedwars1058.Message;
import net.ritana5.underworld.listeners.bedwars1058.BedWarsListener;
import net.ritana5.underworld.listeners.bedwars1058.IceBridgeListener;
import net.ritana5.underworld.utils.Support;
import net.ritana5.underworld.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;

import static net.ritana5.underworld.Underworld.bw1058Api;
import static net.ritana5.underworld.Underworld.support;
import static net.ritana5.underworld.utils.Utils.color;

public class BedWars1058 {
    public BedWars1058() {
        start();
    }

    public void start() {
        support = Support.BEDWARS1058;
        bw1058Api = Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider();


        loadMessages();
        loadListeners();
    }


    public void initConfig(File file) {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        JavaPlugin.getPlugin(Underworld.class).groups = config.getStringList("arena-groups").stream().map(String::toLowerCase).collect(Collectors.toList());
    }


    public void loadMessages() {
        new Message();
    }

    public static boolean isMode(IArena arena) {
        return JavaPlugin.getPlugin(Underworld.class).groups.contains(arena.getGroup().toLowerCase());
    }


    public void loadListeners() {
        new Diamond().register(); // 0
        new Emerald().register(); // 1
        new RecallTeammates().register(); // 2
        new StickOfSecondChances().register(); // 3
        new TrickOrTreatSword().register(); // 4
        new IceBridge().register(); // 5
        new PortableEnderchest().register(); // 6
        Bukkit.getPluginManager().enablePlugin(JavaPlugin.getPlugin(Underworld.class));

        File folder = new File("plugins/BedWars1058/Addons/Underworld");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File configFile = new File(folder, "config.yml");
        if (!configFile.exists()) {
            try {
                Files.copy(JavaPlugin.getPlugin(Underworld.class).getResource("config.yml"), configFile.toPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        initConfig(configFile);

        Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider();
        Underworld.bw1058Api = Bukkit.getServicesManager().getRegistration(com.andrei1058.bedwars.api.BedWars.class).getProvider();
        JavaPlugin.getPlugin(Underworld.class).initConfig(configFile);
        HCore.registerListeners(new BedWarsListener());
        HCore.registerListeners(new IceBridgeListener());
        Bukkit.getConsoleSender().sendMessage("[BedWars1058] " + color("The BedWars-Underworld addon was originally contributed to by IAmTheDefender."));
        Utils.info("&4B&ce&6d&eW&aa&9r&5s&d-&8U&7n&8d&7e&8r&7w&8o&7r&8l&7d");
        Underworld.log.info("Plugin Version: " + JavaPlugin.getPlugin(Underworld.class).getDescription().getVersion());
        if (!(JavaPlugin.getPlugin(Underworld.class).getDescription().getVersion().equals("BETA-1.0"))) {
            Underworld.log.info("WARNING: You are using an outdated version of the plugin! Please update at or (WIP)");
        } else {
            Underworld.log.info(("You are running on the latest release!"));
        }
        Underworld.log.info("Server Version: " + JavaPlugin.getPlugin(Underworld.class).getServer().getVersion() + "\n");
        Utils.info("Running on: &fBedWars&c1058");
    }
}
