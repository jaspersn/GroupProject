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

public class Felling implements Listener {

    public final BlockFace[] FACES = {BlockFace.UP, BlockFace.DOWN, BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST};
    private static final int BLOCK_LIMIT = 128; // Maximum number of blocks that can be broken in one hit

    @EventHandler
    // Runs every time the player breaks a block
    public void onMine(BlockBreakEvent event) {
        Player p = event.getPlayer();
        World w = p.getWorld();
        ItemStack itemInHand = p.getInventory().getItemInMainHand();
        // Don't run if player is crouching
        if (!p.isSneaking()) {
            // Check for valid item
            if (itemInHand != null && itemInHand.hasItemMeta()) {
                ItemMeta meta = p.getInventory().getItemInMainHand().getItemMeta();
                if (meta.hasLore()) {
                    // If is a felling axe
                    if (meta.getLore().contains(ChatColor.GREEN + "Felling")) {
                        short durability = itemInHand.getDurability();
                        int maxDurability = itemInHand.getType().getMaxDurability() - durability;
                        boolean hasDurability = (maxDurability > 0);
                        Set<Block> blocksInTree = getBlocksInVein(event.getBlock());
                        // Break blocks and damage axe
                        for (Block b : blocksInTree) {
                            if (hasDurability) {
                                b.breakNaturally(itemInHand);
                                durability++;
                                maxDurability = itemInHand.getType().getMaxDurability() - durability;
                                hasDurability = (maxDurability > 0);
                            }
                        }
                        if (!hasDurability) p.getInventory().remove(itemInHand);
                        else itemInHand.setDurability(durability);
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

    public static ItemStack getFellingAxe(Material material) {
        ItemStack fellingAxe = new ItemStack(material);
        ItemMeta meta = fellingAxe.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "Felling");
        meta.setLore(lore);
        fellingAxe.setItemMeta(meta);
        return fellingAxe;
    }

    // Returns whether is a valid block to break (ores)
    private boolean isValidBlock(Block b) {
        List<Material> validBlocks = new LinkedList<>();
        validBlocks.add(Material.LOG);
        validBlocks.add(Material.LEAVES);
        boolean isValid = false;
        for(Material m : validBlocks) {
            if (b.getType().equals(m)) isValid = true;
        }
        return isValid;
    }
}
