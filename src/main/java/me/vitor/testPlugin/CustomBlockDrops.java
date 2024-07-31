package me.vitor.testPlugin;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class CustomBlockDrops {
    private final Material blockType;
    private final ItemStack dropItem;
    private final int dropAmount;
    private final double dropChance;

    public CustomBlockDrops(Material blockType, ItemStack dropItem, int dropAmount,double dropChance){

        this.blockType = blockType;
        this.dropItem = dropItem;
        this.dropAmount = dropAmount;
        this.dropChance = dropChance;

    }

    public void modifyDrops(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (block.getType() == blockType) {
            event.setDropItems(false); // Impede os drops padrão
            ItemStack item = new ItemStack(dropItem.getType(), dropAmount);
            event.getPlayer().getInventory().addItem(item); // Dá o item ao jogador
        }
    }

    public Material getBlockType() {
        return blockType;
    }


}
