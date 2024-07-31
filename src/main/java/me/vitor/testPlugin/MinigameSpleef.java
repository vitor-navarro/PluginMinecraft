package me.vitor.testPlugin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

@CommandAlias("spleef")
public class MinigameSpleef extends BaseCommand {

    private static final int maxPlayers = 12;
    private static List<Player> players;
    private static boolean minigameActive;

    @Default
    public static void onCommand(Player player) {

        if (minigameActive) {
            player.sendMessage("Minigame\u00A7b spleef\u00A7r já ativo");
            return;
        }

        players = new ArrayList<Player>();
        minigameActive = true;
        //create minigame, use functions
    }

    //TODO add teleport to minigame
    @Subcommand("addplayer|add")
    public static void addPlayer(Player player) {

        if (!minigameActive) {
            player.sendMessage("Minigame \u00A7bspleef\u00A7r não ativado, execute /spleef");
            return;
        } else if (players.contains((player))) {
            player.sendMessage("Você já está na sala de\u00A7b spleef");
            return;
        } else if (players.size() >= maxPlayers) {
            player.sendMessage("Sala\u00A7c spleef\u00A7r cheia");
            return;
        }

        players.add(player);

        for (Player player1 : players) {
            String message = String.format("O %s entrou na partida \u00A7b <%d/%d>", player.getName(), players.size(), maxPlayers);
            player1.sendMessage(message);
        }
    }

    @Subcommand("shovel|giveshovel|getshovel")
    public static void giveShovel(Player player) {
        ItemStack shovel = new ItemStack(Material.DIAMOND_SHOVEL);

        ItemMeta meta = shovel.getItemMeta();

        var name = Component.text("Spleef Shovel").color(TextColor.color(0x55FFFF)); //Azul claro

        shovel.addUnsafeEnchantment(Enchantment.EFFICIENCY, 10);

        meta.displayName(name);
        meta.setUnbreakable(true);

        //Hide unbreakable and enchantments, DONT use HIDE_ENCHANTS
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        shovel.setItemMeta(meta);

        player.getInventory().addItem(shovel);
    }

    private static void createLayer(Player player, Integer size, Material material, int incrementY) {

        Location location = player.getLocation();

        double baseX = location.getX() + 4.0;
        double baseY = location.getY() - 1.0;
        double baseZ = location.getZ() + 1.0;

        baseY += incrementY;

        for (int x = (int) baseX; x < baseX + size; x++) {
            for (int z = (int) baseZ - size / 2; z < baseZ + Math.ceil((float) size / 2); z++) {
                Block block = player.getWorld().getBlockAt(x, (int) baseY, z);
                block.setType(material);
            }
        }
    }

    private static void createEdgeSquare(Player player, Integer size, Material material, int incrementY) {
        Location location = player.getLocation();

        double baseX = location.getX() + 4.0;
        double baseY = location.getY() - 1.0;
        double baseZ = location.getZ() + 1.0;

        baseY += incrementY;

        for (int x = (int) baseX; x < (baseX + size + 1); x++) {
            //First z line
            player.getWorld().getBlockAt(x - 1, (int) baseY, (int) (baseZ - ((double) size / 2))).setType(material);
            //Last z line
            player.getWorld().getBlockAt(x - 1, (int) baseY, (int) (baseZ + ((double) size / 2) + 2) - 1).setType(material);
        }

        for (int z = (int) (baseZ - (double) size / 2); z <= (baseZ + Math.ceil((float) size / 2)); z++) {
            //First x line
            player.getWorld().getBlockAt((int) baseX - 1, (int) baseY, z).setType(material);
            //Last x line
            player.getWorld().getBlockAt((int) (baseX + size + 1), (int) baseY, z).setType(material);
        }
    }


    //TODO set private and remove subcommand, finish startMinigame first
    @Subcommand("createdeathlayer")
    public static void createDeathLayer(Player player, @Optional Integer width) {

        width = width == null ? 16 : width; //caso null criará 16x16

        if (width % 2 == 1) {
            player.sendMessage("O argumento Width tem que ser número par");
            return;
        }

        //Creates top layer without base
        Material material = Material.LAVA;
        createLayer(player, width, material, 0);

        //Create Base layer with bedrock
        material = Material.BEDROCK;
        createLayer(player, width, material, -1);

        //Create external bedrock ring
        createEdgeSquare(player, width, material, -1);

        //Create external bedrock ring 2
        createEdgeSquare(player, width, material, 0);

        //3 glass layers
        for (int i = 0; i < 5; i++) {
            createEdgeSquare(player, width, Material.GLASS, (i + 1));
        }
    }

    //TODO set private and remove subcommand, finish startMinigame first
    @Subcommand("createsnowlayer")
    public static void createSnowLayer(Player player, @Optional Integer width, int incrementY) {

        width = width == null ? 16 : width; //caso null criará 16x16

        if (width % 2 == 1) {
            player.sendMessage("O argumento Width tem que ser número par");
            return;
        }

        createLayer(player, width, Material.SNOW_BLOCK, incrementY);
        createEdgeSquare(player, width, Material.BEDROCK, incrementY);

        //5 glass layers
        for (int i = 0; i < 5; i++) {
            createEdgeSquare(player, width, Material.GLASS, (i + 1 + incrementY));
        }
    }

    //TODO create a miniLobby, with sign to exit to the lobby and sign to enter the minigame
    //optional if you have a main lobby for the minigame
    @Subcommand("createMiniLobby")
    public static void createMiniLobby(Player player) {

        Location location = player.getLocation();

        int x = (int) player.getX();
        int y = (int) player.getY();
        int z = (int) player.getZ();

        Block centralBlock = player.getWorld().getBlockAt(x, (y - 1), z);
        centralBlock.setType(Material.BLUE_WOOL);
        int tamanho = 2;

        for (int dx = -tamanho; dx <= tamanho; dx++) {
            for (int dz = -tamanho; dz <= tamanho; dz++) {

                if (dx == 0 && dz == 0) continue;
                player.getWorld().getBlockAt(x + dx, y - 1, z + dz).setType(Material.QUARTZ_BLOCK);
            }
        }

        x += 2;
        y += 1;

        //sign support block
        player.getWorld().getBlockAt(x + 1, y, z).setType(Material.QUARTZ_BLOCK);

        //sign
        Block signBlock = player.getWorld().getBlockAt(x, y, z);
        signBlock.setType(Material.OAK_WALL_SIGN);

        BlockState state = signBlock.getState();
        if (state instanceof WallSign wallSign) {
            wallSign.setFacing(BlockFace.EAST);
        }


    }


    @CommandCompletion("@range:0-10 @range:0-50")
    @Syntax("/spleef createminigamepreset <int layers> <int width>")
    @Subcommand("createminigamepreset|create|minigame|game")
    public static void createMinigamePreset1(Player player, @Optional Integer layers, @Optional Integer width) {

        width = width == null ? 16 : width; //caso null criará 16x16
        layers = layers == null ? 2 : layers;

        if (width % 2 == 1) {
            player.sendMessage("O argumento Width tem que ser número par");
            return;
        }

        createDeathLayer(player, width);

        for (int i = 1; i < layers + 1; i++) {
            createSnowLayer(player, width, (6 * i));
        }

        createMiniLobby(player);

    }

    //TODO
    @Subcommand("start|int")
    public static void startMinigame() {
        //verify deathlayer (exact 1)
        //verify layer (more than 1)

        //dar visão noturna
        //dar pá

    }


}
