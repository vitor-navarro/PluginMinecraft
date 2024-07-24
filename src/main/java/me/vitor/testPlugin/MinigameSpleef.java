package me.vitor.testPlugin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Optional;
import org.bukkit.entity.Player;

@CommandAlias("spleef")
public class MinigameSpleef extends BaseCommand {

    @Default
    public static void onCommand(Player player) {

    }

    public static void giveShovel(){}

    public static void createDeathLayer(){}

    public static void createSnowLayer(@Optional int width, @Optional int height){}

    public static void createMinigamePreset(){}
}
