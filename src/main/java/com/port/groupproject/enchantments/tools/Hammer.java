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
import java.util.Set;

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
                        boolean hasDurability = (maxDurability > 0);
                        List<Location> grid = getBlockList(p, w, event.getBlock().getLocation());
                        // Break blocks and damage hammer
                        for (Location l : grid) {
                            if (hasDurability && isValidBlock(l.getBlock())) {
                                l.getBlock().breakNaturally(itemInHand);
                                itemInHand.setDurability((short) (itemInHand.getDurability() + 1));
                            }
                        }
                    }
                }

            }
        }
    }

    // Returns a list of the location of 8 blocks adjacent to the one being broken
    private List<Location> getBlockList(Player p, World w, Location l) {
        BlockFace face = getBlockFace(p);
        List<Location> grid = new LinkedList<>();
        if (face == BlockFace.SOUTH || face == BlockFace.NORTH) {
            grid.add(new Location(w, l.getX(), l.getY() + 1, l.getZ()));
            grid.add(new Location(w, l.getX() + 1, l.getY() + 1, l.getZ()));
            grid.add(new Location(w, l.getX() - 1, l.getY() + 1, l.getZ()));
            grid.add(new Location(w, l.getX() + 1, l.getY(), l.getZ()));
            grid.add(new Location(w, l.getX() - 1, l.getY(), l.getZ()));
            grid.add(new Location(w, l.getX() + 1, l.getY() - 1, l.getZ()));
            grid.add(new Location(w, l.getX() - 1, l.getY() - 1, l.getZ()));
            grid.add(new Location(w, l.getX(), l.getY() - 1, l.getZ()));
        } else if (face == BlockFace.UP || face == BlockFace.DOWN) {
            grid.add(new Location(w, l.getX() + 1, l.getY(), l.getZ()));
            grid.add(new Location(w, l.getX() + 1, l.getY() , l.getZ() - 1));
            grid.add(new Location(w, l.getX() + 1, l.getY(), l.getZ() + 1));
            grid.add(new Location(w, l.getX() - 1, l.getY(), l.getZ()));
            grid.add(new Location(w, l.getX() - 1, l.getY(), l.getZ() - 1));
            grid.add(new Location(w, l.getX() - 1, l.getY(), l.getZ() + 1));
            grid.add(new Location(w, l.getX(), l.getY(), l.getZ() + 1));
            grid.add(new Location(w, l.getX(), l.getY(), l.getZ() - 1));
        } else if (face == BlockFace.EAST || face == BlockFace.WEST) {
            grid.add(new Location(w, l.getX(), l.getY() + 1, l.getZ()));
            grid.add(new Location(w, l.getX(), l.getY() + 1, l.getZ() + 1));
            grid.add(new Location(w, l.getX(), l.getY() + 1, l.getZ() - 1));
            grid.add(new Location(w, l.getX(), l.getY(), l.getZ() + 1));
            grid.add(new Location(w, l.getX(), l.getY(), l.getZ() - 1));
            grid.add(new Location(w, l.getX(), l.getY() - 1, l.getZ() + 1));
            grid.add(new Location(w, l.getX(), l.getY() - 1, l.getZ() - 1));
            grid.add(new Location(w, l.getX(), l.getY() - 1, l.getZ()));
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
