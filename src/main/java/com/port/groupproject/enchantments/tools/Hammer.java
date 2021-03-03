package com.port.groupproject.enchantments.tools;

import org.bukkit.ChatColor;
import org.bukkit.Location;
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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Hammer implements Listener {

    private List<Material> validBlocks = new LinkedList<>();

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
                    // If is a hammer
                    if (meta.getLore().contains(ChatColor.GRAY + "Hammer")) {
                        int maxDurability = itemInHand.getType().getMaxDurability() - itemInHand.getDurability();
                        boolean hasDurability = maxDurability > 0;
                        Queue<Location> grid = getBlockList(p, w, event.getBlock().getLocation());
                        // Break blocks and damage hammer
                        short durability = itemInHand.getDurability();
                        while (!grid.isEmpty() && hasDurability) {
                            Location current = grid.remove();
                            if (isValidBlock(current.getBlock())) {
                                current.getBlock().breakNaturally(itemInHand);
                                durability++;
                            }
                            maxDurability = itemInHand.getType().getMaxDurability() - durability;
                            hasDurability = maxDurability > 0;
                        }
                        if (!hasDurability) p.getInventory().remove(itemInHand);
                        else itemInHand.setDurability(durability);
                    }
                }
            }
        }
    }

    // Returns a Queue of the location of the blocks adjacent to the one being broken
    private Queue<Location> getBlockList(Player p, World w, Location l) {
        BlockFace face = getBlockFace(p);
        Queue<Location> grid = new LinkedList<>();
        if (face == BlockFace.SOUTH || face == BlockFace.NORTH) { // no z
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    grid.add(new Location(w, l.getX() + x, l.getY() + y, l.getZ()));
                }
            }
        } else if (face == BlockFace.UP || face == BlockFace.DOWN) { // no y
            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    grid.add(new Location(w, l.getX() + x, l.getY(), l.getZ() + z));
                }
            }
        } else if (face == BlockFace.EAST || face == BlockFace.WEST) { // no x
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    grid.add(new Location(w, l.getX(), l.getY() + y, l.getZ() + z));
                }
            }
        }
        return grid;
    }

    // Returns what face of the block the player is looking at
    private BlockFace getBlockFace(Player p) {
        List<Block> lastTwoTargets = p.getLastTwoTargetBlocks(null, 100);
        if (lastTwoTargets.size() != 2 || !lastTwoTargets.get(1).getType().isOccluding()) return null;
        return lastTwoTargets.get(1).getFace(lastTwoTargets.get(0));
    }

    // Returns whether is a valid block to break (stone and ores)
    private boolean isValidBlock(Block b) {
        validBlocks.add(Material.STONE);
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

    public static ItemStack getHammer(Material material) {
        ItemStack hammer = new ItemStack(material);
        ItemMeta meta = hammer.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Hammer");
        String type = material.name();
        type = type.substring(0, 1).toUpperCase() + type.substring(1, type.indexOf("_")).toLowerCase();
        meta.setDisplayName(type + " Hammer");
        meta.setLore(lore);
        hammer.setItemMeta(meta);
        return hammer;
    }
}
