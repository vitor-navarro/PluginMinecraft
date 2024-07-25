package me.vitor.testPlugin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Optional;
import co.aikar.commands.annotation.Subcommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getPluginManager;

@CommandAlias("spleef")
public class MinigameSpleef extends BaseCommand {

    private static int maxPlayers = 12;
    private static List<Player> players;
    private static boolean minigameActive;

    @Default
    public static void onCommand(Player player) {

        players = new ArrayList<Player>();
        minigameActive = true;



        //create minigame, use functions
    }

    //TODO add teleport to minigame
    @Subcommand("addplayer|add")
    public static void addPlayer(Player player){

        if(minigameActive == false){
            player.sendMessage("Minigame spleef não ativado, execute /spleef");
            return;
        } else if(players.size() >= maxPlayers){
            player.sendMessage("Sala spleef cheia");
            return;
        }

        players.add(player);

        for(Player player1 : players){
            String message = String.format("O %s entrou na partida \u00A7b <%d/%d>", player.getName(), players.size(), maxPlayers);
            player1.sendMessage(message);
        }
    }

    @Subcommand("shovel|giveshovel|getshovel")
    public static void giveShovel(Player player){
        ItemStack shovel = new ItemStack(Material.DIAMOND_SHOVEL);

        ItemMeta meta = shovel.getItemMeta();

        var name = Component.text("Spleef Shovel").color(TextColor.color(0x55FFFF)); //Azul claro

        meta.displayName(name);
        shovel.setItemMeta(meta);

        shovel.addUnsafeEnchantment(Enchantment.EFFICIENCY, 10);

        //ocultar encantamentos
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        shovel.setItemMeta(meta);

        player.getInventory().addItem(shovel);
    }

    @Subcommand("createdeathlayer|CDL")
    public static void createDeathLayer(Player player, @Optional Integer width, @Optional Integer length){

        Location location = player.getLocation();
        player.sendMessage("CDL");
    }

    @Subcommand("createsnowlayer|CNL")
    public static void createSnowLayer(Player player, @Optional Integer width, @Optional Integer length){

        Location location = player.getLocation();
        player.sendMessage("CNL");
    }

    @Subcommand("createminigamepreset|create|minigame|game")
    public static void createMinigamePreset(Player player){

        Location location = player.getLocation();
        player.sendMessage("Minigame");
    }


}
