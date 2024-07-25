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

import static org.bukkit.Bukkit.getPluginManager;

@CommandAlias("spleef")
public class MinigameSpleef extends BaseCommand {

    @Default
    public static void onCommand(Player player) {

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
