package me.vitor.testPlugin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.HelpCommand;
import net.kyori.adventure.text.Component;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.metadata.FixedMetadataValue;

@CommandAlias("spawnEnforcerZombie")
public class EnforcerZombieCommand extends BaseCommand {

    @Default
    public static void onCommand(Player player) {

        Zombie zombie = player.getWorld().spawn(player.getLocation(), Zombie.class);

        zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(zombie.getHealth()*2);
        zombie.setHealth(zombie.getHealth()*2);

        zombie.setMetadata("Modifiedhealth", new FixedMetadataValue(TestPlugin.getInstance(), true));
        zombie.setMetadata("EnforcerZombie", new FixedMetadataValue(TestPlugin.getInstance(), true));


        var zombieNameColor = Component.text("Enforcer Zombie");

        zombie.customName(zombieNameColor);
        zombie.setCustomNameVisible(true);

        //Fazer uma função para a vida aparece abaixo do nome do zumbi
        //fazer uma feature / função


    }

    @HelpCommand
    public void doHelp(CommandSender sender, CommandHelp help){

        help.showHelp();
    }

}
