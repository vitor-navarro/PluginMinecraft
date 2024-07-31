package me.vitor.testPlugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class SnowballHitListener implements Listener {

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event){
        if (event.getEntity() instanceof Snowball){

            Material snowblock = Material.SNOW_BLOCK;

            if(event.getHitBlock().getType() == snowblock){
                event.getHitBlock().setType(Material.AIR);
            }
        }
    }


}
