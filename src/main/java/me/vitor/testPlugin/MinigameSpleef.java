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
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

@CommandAlias("spleef")
public class MinigameSpleef extends BaseCommand {

    private static int maxPlayers = 12;
    private static List<Player> players;
    private static boolean minigameActive;

    @Default
    public static void onCommand(Player player) {

        if(minigameActive){
            player.sendMessage("Minigame\u00A7b spleef\u00A7r já ativo");
            return;
        }

        players = new ArrayList<Player>();
        minigameActive = true;
        //create minigame, use functions
    }

    //TODO add teleport to minigame
    @Subcommand("addplayer|add")
    public static void addPlayer(Player player){

        if(!minigameActive){
            player.sendMessage("Minigame \u00A7bspleef\u00A7r não ativado, execute /spleef");
            return;
        } else if (players.contains((player))) {
            player.sendMessage("Você já está na sala de\u00A7b spleef");
            return;
        } else if(players.size() >= maxPlayers){
            player.sendMessage("Sala\u00A7c spleef\u00A7r cheia");
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

        //TODO deixar a pá com durabilidade infinita

        player.getInventory().addItem(shovel);
    }

    //TODO set private and remove subcommand, finish startMinigame first
    @Subcommand("createdeathlayer|CDL")
    public static void createDeathLayer(Player player, @Optional Integer width, @Optional Integer length){

        width = width == null ? 16 : width; //caso null criará 16x16
        length = length == null ? 4 : length;

        if(width % 2 == 1){
            player.sendMessage("O argumento Width tem que ser número par");
            return;
        }

        Location location = player.getLocation();

        double baseX = location.getX() + 1.0;
        double baseY = location.getY() - 1.0;
        double baseZ = location.getZ() + 1.0;

        //Creates top layer without base
        width -=1;
        for(int x = (int)baseX; x < baseX + width; x++){
            for(int z = (int) baseZ - width / 2; z < baseZ + Math.ceil((float) width / 2); z++){
                Block block = player.getWorld().getBlockAt(x, (int) baseY, z);
                block.setType(Material.LAVA);
            }
        }

        width += 1;

        //Create Base
        for(int x = (int)baseX; x < (baseX + width + 1) ; x++){

            for(int z = (int) baseZ - width / 2; z < baseZ + Math.ceil((float) width / 2); z++){

                Block block1 = player.getWorld().getBlockAt(x-1, (int) baseY-1, z);
                block1.setType(Material.BEDROCK);
            }

            //last line
            player.getWorld().getBlockAt(x-1, (int) baseY-1, (int) (baseZ + ((double) width /2) +1)).setType(Material.BEDROCK);

            //Square top edge Z axis
            //first z line
            player.getWorld().getBlockAt(x-1, (int) baseY, (int) (baseZ - ((double) width /2))).setType(Material.BEDROCK);

            //last z line
            player.getWorld().getBlockAt(x-1, (int) baseY, (int) (baseZ + ((double) width /2) + 1)).setType(Material.BEDROCK);
        }

        // Square top edge X axis
        for (int z = (int) (baseZ - (double) width / 2); z <= (baseZ + Math.ceil((float) width / 2)); z++) {
            // First X line
            player.getWorld().getBlockAt((int) baseX - 1, (int) baseY, z).setType(Material.BEDROCK);

            // last X line
            player.getWorld().getBlockAt((int) (baseX + width), (int) baseY, z).setType(Material.BEDROCK);
        }

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

    @Subcommand("start|int")
    public static void startMinigame(){
        //verify deathlayer (exact 1)
        //verify layer (more than 1)
    }


}
