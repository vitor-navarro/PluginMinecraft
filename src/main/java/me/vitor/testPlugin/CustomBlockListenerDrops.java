package me.vitor.testPlugin;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class CustomBlockListenerDrops implements Listener {
    private final CustomBlockDrops customBlock;

    public CustomBlockListenerDrops(CustomBlockDrops customBlock){
        this.customBlock = customBlock;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        customBlock.modifyDrops(event);
    }
}
