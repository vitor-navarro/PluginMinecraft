package me.vitor.testPlugin;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class CustomBlockDropsListener implements Listener {
    private final CustomBlockDrops customBlock;

    public CustomBlockDropsListener(CustomBlockDrops customBlock) {
        this.customBlock = customBlock;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        customBlock.modifyDrops(event);
    }
}
