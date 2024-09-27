package net.ritana5.underworld.language.bedwars2023;

import com.tomkeuper.bedwars.api.language.Language;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.Arrays;

import static net.ritana5.underworld.language.MessagePath.*;

public class Message {

    public Message() {
        setup();
    }

    private void setup() {
        for (Language l : Language.getLanguages()) {
            YamlConfiguration yml = l.getYml();
            switch (l.getIso()) {
                case "pt":
                    yml.addDefault(MESSAGES_ARENA_START_TUTORIAL, Arrays.asList("&a" + "▬".repeat(72),
                            "&f&l                 Bed Wars Underworld",
                            "",
                            "&e&l      Todos os geradores estão no máximo! Sua cama tem",
                            "&e&l   três camadas de proteção! Clique com o botão esquerdo enquanto",
                            "&e&l      segura a lã para ativar a construção de pontes!",
                            "",
                            "&a" + "▬".repeat(72)));
                    yml.addDefault(MESSAGES_UNDERWORLD_MENU, "Menu Submundo");
                    yml.addDefault(MESSAGES_DIAMOND_NAME, "%BuyStatus%2x Diamante");
                    yml.addDefault(MESSAGES_DIAMOND_NAME_CHAT, "2x Diamante");
                    yml.addDefault(MESSAGES_DIAMOND_LORE, Arrays.asList("&7Receba 2 Diamantes em troca", "&7de 4 almas.", "", "&7Custo: &b%price% Almas %disfla%"));
                    yml.addDefault(MESSAGES_EMERALD_NAME, "%BuyStatus%2x Esmeralda");
                    yml.addDefault(MESSAGES_EMERALD_NAME_CHAT, "2x Esmeralda");
                    yml.addDefault(MESSAGES_EMERALD_LORE, Arrays.asList("&7Receba 2 Esmeraldas em troca", "&7de 5 almas.", "", "&7Custo: &b%price% Almas %disfla%"));
                    yml.addDefault(MESSAGES_RECALL_NAME, "%BuyStatus%Chamar Companheiros");
                    yml.addDefault(MESSAGES_RECALL_NAME_CHAT, "Chamar Companheiros");
                    yml.addDefault(MESSAGES_RECALL_LORE, Arrays.asList("&7Teleporta todos os companheiros de equipe", "&7de volta à base após 5 segundos!", "", "&7Custo: &b%price% Almas %disfla%"));
                    yml.addDefault(MESSAGES_RECALL_5, Arrays.asList("&6RECALL! &bVocê será teleportado", "&bde volta à sua base em 5 segundos!"));
                    yml.addDefault(MESSAGES_RECALL_4, Arrays.asList("&6RECALL! &bVocê será teleportado", "&bde volta à sua base em 4 segundos!"));
                    yml.addDefault(MESSAGES_RECALL_3, Arrays.asList("&6RECALL! &bVocê será teleportado", "&bde volta à sua base em 3 segundos!"));
                    yml.addDefault(MESSAGES_RECALL_2, Arrays.asList("&6RECALL! &bVocê será teleportado", "&bde volta à sua base em 2 segundos!"));
                    yml.addDefault(MESSAGES_RECALL_1, Arrays.asList("&6RECALL! &bVocê será teleportado", "&bde volta à sua base em 1 segundo!"));
                    yml.addDefault(MESSAGES_STICK_NAME, "%BuyStatus%Bastão das Segundas Chances");
                    yml.addDefault(MESSAGES_STICK_NAME_CHAT, "Bastão das Segundas Chances");
                    yml.addDefault(MESSAGES_STICK_LORE, Arrays.asList("&7Manter este bastão no", "&7seu inventário permitirá", "&7evitar a morte por um breve momento", "&7quando estiver com pouca vida!", "", "&7Custo: &b%price% Almas %disfla%", "&7Cooldown: &630 segundos"));
                    yml.addDefault(MESSAGES_SWORD_NAME, "%BuyStatus%Espada Doce ou Travessura");
                    yml.addDefault(MESSAGES_SWORD_NAME_CHAT, "Espada Doce ou Travessura");
                    yml.addDefault(MESSAGES_SWORD_LORE, Arrays.asList("&7Usar esta espada para atacar", "&7jogadores fornecerá uma Doce ou", "&7Travessura!", "", "&7Custo: &b%price% Almas %disfla%", "&7Cooldown: &65 segundos"));
                    yml.addDefault(MESSAGES_ICE_NAME, "%BuyStatus%Ponte de Gelo");
                    yml.addDefault(MESSAGES_ICE_NAME_CHAT, "Ponte de Gelo");
                    yml.addDefault(MESSAGES_ICE_LORE, Arrays.asList("&7Crie uma Ponte de Gelo na", "&7direção em que você está olhando, mas", "&7cuidado, a ponte derrete enquanto", "&7você anda sobre ela!", "", "&7Custo: &b%price% Almas %disfla%"));
                    yml.addDefault(MESSAGES_ENDER_NAME, "%BuyStatus%Baú de Ender Portátil");
                    yml.addDefault(MESSAGES_ENDER_NAME_CHAT, "Baú de Ender Portátil");
                    yml.addDefault(MESSAGES_ENDER_LORE, Arrays.asList("&7Leve este baú de ender para todos os lugares", "&7que você for! Ele desaparecerá 30", "&7segundos após ser colocado", "", "&7Custo: &b%price% Almas %disfla%"));
                    yml.addDefault(MESSAGES_SOUL_PLURAL, "Almas");
                    yml.addDefault(MESSAGES_MAIN_NAME, "&bComércio Submundo");
                    yml.addDefault(MESSAGES_MAIN_LORE, Arrays.asList("&7Colete almas matando", "&7jogadores, monstros, e destruindo", "&7camas.", "", "&7Suas almas: &b%souls%", "", "&eClique para abrir!"));
                    yml.addDefault(MESSAGES_INVALID_PLAYER, "&cJogador não encontrado.");
                    yml.addDefault(MESSAGES_INVALID_AMOUNT, "&cQuantidade inválida.");
                    yml.addDefault(MESSAGES_ADMIN_ONLY, "&cEste comando só pode ser executado por um jogador ou administrador.");
                    yml.addDefault(MESSAGES_UNKNOWN_COMMAND, "&cComando desconhecido.");
                    break;
                default:
                    yml.addDefault(MESSAGES_ARENA_START_TUTORIAL, Arrays.asList("&a" + "▬".repeat(72),
                            "&f&l                 Bed Wars Underworld",
                            "",
                            "&e&l        All generators are maxed! Your bed has three",
                            "&e&l       layers of protection! Left click while holding",
                            "&e&l             wool to activate bridge building!",
                            "",
                            "&a" + "▬".repeat(72)));
                    yml.addDefault(MESSAGES_UNDERWORLD_MENU, "Underworld Menu");
                    yml.addDefault(MESSAGES_DIAMOND_NAME, "%BuyStatus%2x Diamond");
                    yml.addDefault(MESSAGES_DIAMOND_NAME_CHAT, "2x Diamond");
                    yml.addDefault(MESSAGES_DIAMOND_LORE, Arrays.asList("&7Receive 2 Diamond in exchange", "&7for 4 souls.", "", "&7Cost: &b%price% Souls %disfla%"));
                    yml.addDefault(MESSAGES_EMERALD_NAME, "%BuyStatus%2x Emerald");
                    yml.addDefault(MESSAGES_EMERALD_NAME_CHAT, "2x Emerald");
                    yml.addDefault(MESSAGES_EMERALD_LORE, Arrays.asList("&7Receive 2 Emerald in exchange", "&7for 5 souls.", "", "&7Cost: &b%price% Souls %disfla%"));
                    yml.addDefault(MESSAGES_RECALL_NAME, "%BuyStatus%Recall Teammates");
                    yml.addDefault(MESSAGES_RECALL_NAME_CHAT, "Recall Teammates");
                    yml.addDefault(MESSAGES_RECALL_LORE, Arrays.asList("&7Teleports all teammates back to", "&7base after 5 seconds!", "", "&7Cost: &b%price% Souls %disfla%"));
                    yml.addDefault(MESSAGES_RECALL_5, Arrays.asList("&6RECALL! &bYou will be teleported", "&bback to your base in 5 seconds!"));
                    yml.addDefault(MESSAGES_RECALL_4, Arrays.asList("&6RECALL! &bYou will be teleported", "&bback to your base in 4 seconds!"));
                    yml.addDefault(MESSAGES_RECALL_3, Arrays.asList("&6RECALL! &bYou will be teleported", "&bback to your base in 3 seconds!"));
                    yml.addDefault(MESSAGES_RECALL_2, Arrays.asList("&6RECALL! &bYou will be teleported", "&bback to your base in 2 seconds!"));
                    yml.addDefault(MESSAGES_RECALL_1, Arrays.asList("&6RECALL! &bYou will be teleported", "&bback to your base in 1 second!"));
                    yml.addDefault(MESSAGES_STICK_NAME, "%BuyStatus%Stick of Second Chances");
                    yml.addDefault(MESSAGES_STICK_NAME_CHAT, "Stick of Second Chances");
                    yml.addDefault(MESSAGES_STICK_LORE, Arrays.asList("&7Keeping this stick in", "&7your inventory will allow you to", "&7evade death for a short moment", "&7when low on health!", "", "&7Cost: &b%price% Souls %disfla%", "&7Cooldown: &630 seconds"));
                    yml.addDefault(MESSAGES_SWORD_NAME, "%BuyStatus%Trick or Treat Sword");
                    yml.addDefault(MESSAGES_SWORD_NAME_CHAT, "Trick or Treat Sword");
                    yml.addDefault(MESSAGES_SWORD_LORE, Arrays.asList("&7Using this sword to damage", "&7players will provide a Trick or", "&7Treat!", "", "&7Cost: &b%price% Souls %disfla%", "&7Cooldown: &65 seconds"));
                    yml.addDefault(MESSAGES_ICE_NAME, "%BuyStatus%Ice Bridge");
                    yml.addDefault(MESSAGES_ICE_NAME_CHAT, "Ice Bridge");
                    yml.addDefault(MESSAGES_ICE_LORE, Arrays.asList("&7Spawn an Ice Bridge in the", "&7direction you're looking but", "&7beware, the bridge melts when", "&7you walk over it!", "", "&7Cost: &b%price% Souls %disfla%"));
                    yml.addDefault(MESSAGES_ENDER_NAME, "%BuyStatus%Portable Enderchest");
                    yml.addDefault(MESSAGES_ENDER_NAME_CHAT, "Portable Enderchest");
                    yml.addDefault(MESSAGES_ENDER_LORE, Arrays.asList("&7Take this enderchest everywhere", "&7you go! It will despawn 30", "&7seconds after being placed", "", "&7Cost: &b%price% Souls %disfla%"));
                    yml.addDefault(MESSAGES_SOUL_PLURAL, "Souls");
                    yml.addDefault(MESSAGES_MAIN_NAME, "&bUnderworld Trading");
                    yml.addDefault(MESSAGES_MAIN_LORE, Arrays.asList("&7Collect souls by killing", "&7players, mobs, and breaking", "&7beds.", "", "&7Your souls: &b%souls%", "", "&eClick to open!"));
                    yml.addDefault(MESSAGES_INVALID_PLAYER, "&cPlayer not found.");
                    yml.addDefault(MESSAGES_INVALID_AMOUNT, "&cInvalid amount.");
                    yml.addDefault(MESSAGES_ADMIN_ONLY, "&cThis command can only be run by a player or an admin.");
                    yml.addDefault(MESSAGES_UNKNOWN_COMMAND, "&cUnknown Command.");
                    break;
            }
            yml.options().copyDefaults(true);
            l.save();
        }
    }
}
