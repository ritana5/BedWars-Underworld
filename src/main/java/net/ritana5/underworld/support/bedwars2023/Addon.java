package net.ritana5.underworld.support.bedwars2023;

import com.hakan.core.HCore;
import com.tomkeuper.bedwars.api.BedWars;
import net.ritana5.underworld.Underworld;
import net.ritana5.underworld.items.bedwars2023.*;
import net.ritana5.underworld.language.bedwars2023.Message;
import net.ritana5.underworld.listeners.bedwars2023.BedWarsListener;
import net.ritana5.underworld.listeners.bedwars2023.IceBridgeListener;
import net.ritana5.underworld.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;

import static net.ritana5.underworld.utils.Utils.color;


public class Addon extends com.tomkeuper.bedwars.api.addon.Addon {

    public Addon() {
    }

    @Override
    public String getAuthor() {
        return Underworld.instance.getDescription().getAuthors().get(0);
    }

    @Override
    public Plugin getPlugin() {
        JavaPlugin.getPlugin(Underworld.class);
        return Underworld.getInstance();
    }

    @Override
    public String getVersion() {
        return Underworld.instance.getDescription().getVersion();
    }

    @Override
    public String getDescription() {
        return Underworld.instance.getDescription().getDescription();
    }

    @Override
    public String getName() {
        return Underworld.instance.getDescription().getName();
    }

    public void initConfig(File file) {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        JavaPlugin.getPlugin(Underworld.class).groups = config.getStringList("arena-groups").stream().map(String::toLowerCase).collect(Collectors.toList());
    }

    public void load() {
        Bukkit.getPluginManager().enablePlugin(JavaPlugin.getPlugin(Underworld.class));

        File folder = new File("plugins/BedWars2023/Addons/Underworld");
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


        new Diamond().register(); // 0
        new Emerald().register(); // 1
        new RecallTeammates().register(); // 2
        new StickOfSecondChances().register(); // 3
        new TrickOrTreatSword().register(); // 4
        new IceBridge().register(); // 5
        new PortableEnderchest().register(); // 6

        new Message();
        Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider();
        Underworld.bw2023Api = Bukkit.getServicesManager().getRegistration(com.tomkeuper.bedwars.api.BedWars.class).getProvider();
        JavaPlugin.getPlugin(Underworld.class).initConfig(configFile);
        HCore.registerListeners(new BedWarsListener());
        HCore.registerListeners(new IceBridgeListener());
        Bukkit.getConsoleSender().sendMessage("[BedWars2023] " + color("The BedWars-Underworld addon was originally contributed to by IAmTheDefender."));
        Utils.info("&4B&ce&6d&eW&aa&9r&5s&d-&8U&7n&8d&7e&8r&7w&8o&7r&8l&7d");
        Underworld.log.info("Plugin Version: " + JavaPlugin.getPlugin(Underworld.class).getDescription().getVersion());
        if (!(JavaPlugin.getPlugin(Underworld.class).getDescription().getVersion().equals("BETA-1.0"))) {
            Underworld.log.info("WARNING: You are using an outdated version of the plugin! Please update at or (WIP)");
        } else {
            Underworld.log.info(("You are running on the latest release!"));
        }
        Underworld.log.info("Server Version: " + JavaPlugin.getPlugin(Underworld.class).getServer().getVersion() + "\n");
        Utils.info("Running on: &fBedWars&c2023");
    }

    public void unload() {
        Bukkit.getPluginManager().disablePlugin(JavaPlugin.getPlugin(Underworld.class));
    }
}
