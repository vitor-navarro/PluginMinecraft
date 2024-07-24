package me.vitor.testPlugin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Optional;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@CommandAlias("spleef")
public class MinigameSpleef extends BaseCommand {

    @Default
    public static void onCommand(Player player) {

    }

    @Subcommand("shovel|giveshovel")
    public static void giveShovel(Player player){

        player.sendMessage("Shovel");
    }

    @Subcommand("createdeathlayer|CDL")
    public static void createDeathLayer(Player player, @Optional Integer width, @Optional Integer height){

        Location location = player.getLocation();
        player.sendMessage("CDL");
    }

    @Subcommand("createsnowlayer|CNL")
    public static void createSnowLayer(Player player, @Optional Integer width, @Optional Integer height){

        Location location = player.getLocation();
        player.sendMessage("CNL");
    }

    @Subcommand("createminigamepreset|create|minigame|game")
    public static void createMinigamePreset(Player player){

        Location location = player.getLocation();
        player.sendMessage("Minigame");
    }
}
