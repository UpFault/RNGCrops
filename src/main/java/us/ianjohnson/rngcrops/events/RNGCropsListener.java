package us.ianjohnson.rngcrops.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RNGCropsListener implements Listener {

	@EventHandler
	@SuppressWarnings("all")
	public void onBlockBreak(BlockBreakEvent event) {
		Block blockBroken = event.getBlock();
		ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
		ItemMeta itemMeta = itemInHand.getItemMeta();
		double rngChance;
		if (itemInHand == null) {
			return;
		}

		if (blockBroken.getType() == Material.MELON) {
			if (itemMeta.getLore().contains("Melon Chopper I")) {
				rngChance = 2.5;
				dropMelons(blockBroken, rngChance);
				return;
			}
			if (itemMeta.getLore().contains("Melon Chopper II")) {
				rngChance = 5.5;
				dropMelons(blockBroken, rngChance);
				return;
			}
			if (itemMeta.getLore().contains("Melon Chopper III")) {
				rngChance = 8.6;
				dropMelons(blockBroken, rngChance);
				return;
			}
			if (itemMeta.getLore().contains("Melon Chopper IV")) {
				rngChance = 11.1;
				dropMelons(blockBroken, rngChance);
				return;
			}
			if (itemMeta.getLore().contains("Melon Chopper V")) {
				rngChance = 14.6;
				dropMelons(blockBroken, rngChance);
				return;
			}
		}
	}

	private static void dropMelons(Block blockBroken, double rngChance) {
		double chance = Math.random() * 100;

		if (chance < rngChance) {
			blockBroken.getWorld().dropItemNaturally(blockBroken.getLocation(), new ItemStack(org.bukkit.Material.MELON, blockBroken.getDrops().size()));
			Bukkit.getLogger().info("RNGCrops: Chance: " + chance + "RNGChance: " + rngChance);
		} else {
			Bukkit.getLogger().info("RNGCrops: Chance: " + chance + "RNGChance: " + rngChance);
			blockBroken.getWorld().dropItemNaturally(blockBroken.getLocation(), new ItemStack(org.bukkit.Material.MELON, (int) (blockBroken.getDrops().size() * rngChance)));
		}
	}
}
