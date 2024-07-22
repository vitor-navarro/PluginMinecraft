package me.vitor.testPlugin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Sound.ENTITY_CAT_PURREOW;

@CommandAlias("curar|regen")
public class RegenCommand extends BaseCommand {

    @Default
    @CommandCompletion("@range:0-20")
    @Syntax("<quantidade> - Cura")
    @Description("Cura o jogador")
    public static void onCommand(Player player, @Optional Integer quantity) {
        var regen = quantity == null ? 20 : quantity;
        var health = player.getHealth();

        if(health + regen > 20) {
            player.setHealth(20);
            player.sendMessage(ChatColor.GREEN + "cura 100%");
        } else{
            player.sendMessage(ChatColor.GREEN + "Você curou " + (regen/2) + " corações");
            player.setHealth(health + regen);
        }

        player.playSound(player.getLocation(), ENTITY_CAT_PURREOW, 5.5f, 5.5f);
    }

    @HelpCommand
    public void doHelp(CommandSender sender, CommandHelp help){
        help.showHelp();
    }


}
