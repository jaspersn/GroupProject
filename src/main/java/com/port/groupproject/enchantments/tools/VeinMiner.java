package com.port.groupproject.enchantments.tools;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class VeinMiner implements Listener {

    private final BlockFace[] FACES = {BlockFace.UP, BlockFace.DOWN, BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST};
    private final int BLOCK_LIMIT = 128; // Maximum number of blocks that can be broken in one hit
    @EventHandler
    // Runs every time the player breaks a block
    public void onMine(BlockBreakEvent event) {
        Player p = event.getPlayer();
        World w = p.getWorld();
        ItemStack itemInHand = p.getInventory().getItemInMainHand();
        // Only run if player is crouching
        if (p.isSneaking()) {
            // Check for valid item
            if (itemInHand != null && itemInHand.hasItemMeta()) {
                ItemMeta meta = p.getInventory().getItemInMainHand().getItemMeta();
                if (meta.hasLore()) {
                    // If is a hammer
                    if (meta.getLore().contains(ChatColor.YELLOW + "Vein Miner")) {
                        int maxDurability = itemInHand.getType().getMaxDurability() - itemInHand.getDurability();
                        boolean hasDurability = (maxDurability > 0);
                        Set<Block> blocksInVein = getBlocksInVein(event.getBlock());
                        // Break blocks and damage pickaxe
                        for (Block b : blocksInVein) {
                            if (hasDurability) {
                                b.breakNaturally(itemInHand);
                                itemInHand.setDurability((short) (itemInHand.getDurability() + 1));
                            }
                        }
                    }
                }
            }
        }
    }

    // Returns a Set of blocks that are connected to the target block (includes a recursive call)
    // Method inspired by: https://stackoverflow.com/questions/41203118/iterate-over-all-connected-blocks-of-same-type/41426666
    private Set<Block> getBlocksInVein(Block b) {
        Set<Block> blocksInVein = new HashSet<>();
        LinkedList<Block> list = new LinkedList<>();
        list.add(b);
        if (isValidBlock(b)) {
            while ((b = list.poll()) != null) {
                getAdjacentBlocks(b, blocksInVein, list); // recursive call
            }
        }
        return blocksInVein;
    }

    // Gets blocks adjacent to the given block and adds them to both a Set and List used in getBlocksInVein
    // Method inspired by: https://stackoverflow.com/questions/41203118/iterate-over-all-connected-blocks-of-same-type/41426666
    private void getAdjacentBlocks(Block block, Set<Block> blocksInVein, List<Block> todo) {
        for (BlockFace face : FACES) {
            Block current = block.getRelative(face);
            if (blocksInVein.size() < BLOCK_LIMIT) {
                if (current.getType() == block.getType()) {
                    if (blocksInVein.add(current)) {
                        todo.add(current);
                    }
                }
            } else break;
        }
    }

    // Returns the item given in the parameter with the "Vein Miner" enchantment
    public static ItemStack getVeinMiner(Material material) {
        ItemStack veinMiner = new ItemStack(material);
        ItemMeta meta = veinMiner.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Vein Miner");
        meta.setLore(lore);
        veinMiner.setItemMeta(meta);
        return veinMiner;
    }

    // Returns whether is a valid block to break (ores)
    private boolean isValidBlock(Block b) {
        List<Material> validBlocks = new LinkedList<>();
        validBlocks.add(Material.COAL_ORE);
        validBlocks.add(Material.REDSTONE_ORE);
        validBlocks.add(Material.IRON_ORE);
        validBlocks.add(Material.GOLD_ORE);
        validBlocks.add(Material.DIAMOND_ORE);
        boolean isValid = false;
        for(Material m : validBlocks) {
            if (b.getType().equals(m)) isValid = true;
        }
        return isValid;
    }
}
