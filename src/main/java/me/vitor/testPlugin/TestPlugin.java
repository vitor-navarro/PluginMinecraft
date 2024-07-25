package me.vitor.testPlugin;

import co.aikar.commands.PaperCommandManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class TestPlugin extends JavaPlugin implements Listener{

    public Material item;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        item = getMaterialFromString(getConfig().getString("item"));

        getServer().getPluginManager().registerEvents(this, this);

        CustomBlock customBlock = new CustomBlock(Material.SNOW_BLOCK, new ItemStack(Material.SNOWBALL), 1);
        CustomBlockListener customBlockListener = new CustomBlockListener(customBlock);
        getServer().getPluginManager().registerEvents(customBlockListener, this);


        var manager = new PaperCommandManager(this);
        manager.enableUnstableAPI("help");

        manager.registerCommand(new CommandRegen());
        manager.registerCommand(new CommandEnforcerZombie());
        manager.registerCommand(new MinigameSpleef());

    }

    @EventHandler
    public void onPlayerInteractWithEntity(PlayerInteractEntityEvent event) {

        Entity entity = event.getRightClicked();
        Player player = event.getPlayer();


        if (player.getInventory().getItemInMainHand().getType() == item && entity.getType() == EntityType.PIG) {
            Entity pig = event.getRightClicked();

            pig.getWorld().createExplosion(pig.getLocation(), 4.0f, false, false);
        }

        if (entity instanceof Zombie && entity.hasMetadata("EnforcerZombie") && player.getInventory().getItemInMainHand().getType() == item){
            Zombie zumbi = (Zombie) event.getRightClicked();

            zumbi.getWorld().createExplosion(zumbi.getLocation(), 2.5F);
        }
    }

    public static TestPlugin getInstance(){
        return getPlugin(TestPlugin.class);
    }

    public static Material getMaterialFromString(String string){
        return Material.getMaterial(string);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

