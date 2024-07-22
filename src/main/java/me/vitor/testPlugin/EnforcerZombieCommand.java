package me.vitor.testPlugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.metadata.FixedMetadataValue;


public class EnforcerZombieCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only players can use this command!");
            return true;
        }

        if (args.length > 0){
            return false;
        }

        Player player = (Player) commandSender;

        Zombie zumbi = player.getWorld().spawn(player.getLocation(), Zombie.class);

        double vidaMaxima = zumbi.getMaxHealth() * 2;
        zumbi.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(vidaMaxima);
        zumbi.setHealth(vidaMaxima);

        zumbi.setMetadata("ModifiedHealth", new FixedMetadataValue(TestPlugin.getInstance(), true));
        zumbi.setMetadata("EnforcerZombie", new FixedMetadataValue(TestPlugin.getInstance(), true));

        var zumbiNameColor = Component.text("Zumbi Enforcer").color(NamedTextColor.RED);

        zumbi.customName(zumbiNameColor);
        zumbi.setCustomNameVisible(true);

        return false;
    }
}
