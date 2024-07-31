package me.vitor.testPlugin;

import org.bukkit.Material;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class SnowballHitListener implements Listener {

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Snowball) {
            if (event.getHitBlock().getType() == Material.SNOW_BLOCK) {
                event.getHitBlock().setType(Material.AIR);
            }
        }
    }
}
