package me.vitor.testPlugin;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class CustomBlockListener implements Listener {
    private final CustomBlock customBlock;

    public CustomBlockListener(CustomBlock customBlock){
        this.customBlock = customBlock;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        customBlock.modifyDrops(event);
    }
}
