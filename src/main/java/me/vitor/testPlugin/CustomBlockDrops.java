package me.vitor.testPlugin;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import lombok.Setter;
import lombok.Getter;

public class CustomBlockDrops {

    /*
    blockType = Material, example Material.SNOW_BLOCK
    ItemStack = org.bukkit.inventory.ItemStack, example new ItemStack(Material.SNOWBALL)
    dropAmount = int Quantity of items dropped, default 1, example 1, 2, 3
    dropChance = double probability of dropping the item, default 1, example: 0.125, 0.25, 0.50
     */

    private final Material blockType;
    private final ItemStack itemStack;
    @Getter @Setter
    private int dropAmount = 1;
    @Getter @Setter
    private double dropChance = 1; //example: 0.125, 0.25, 0.50

    public CustomBlockDrops(Material blockType, ItemStack itemStack) {
        this.blockType = blockType;
        this.itemStack = itemStack;
    }

    public void modifyDrops(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (block.getType() == blockType) {
            event.setDropItems(false); // Impede os drops padrão

            int drop = shouldDropItem() ? dropAmount : 0;
            ItemStack item = new ItemStack(itemStack.getType(), drop);
            event.getPlayer().getInventory().addItem(item); // Dá o item ao jogador
        }
    }

    public Material getBlockType() {
        return blockType;
    }

    private boolean shouldDropItem() {
        double randomNumber = Math.random();

        return randomNumber < dropChance;
    }


}
