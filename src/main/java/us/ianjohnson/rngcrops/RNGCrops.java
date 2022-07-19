package us.ianjohnson.rngcrops;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import us.ianjohnson.rngcrops.events.RNGCropsListener;
import us.ianjohnson.rngcrops.utils.Enchants;
import us.ianjohnson.rngcrops.utils.Utilities;

import java.io.File;
import java.util.*;

public final class RNGCrops extends JavaPlugin implements CommandExecutor {

	@Getter
	private static RNGCrops instance;
	@Getter
	private static final String consolePrefix = "[RNGCrops] ";
	@Getter
	private static final String pluginPrefix = "§7[§eRNGCrops§7] §r";
	private static final Map<String, String> phrases = new HashMap<>();

	@Override
	public void onEnable() {
		instance = this;
		Utilities.checkForUpdates();
		registerEvents();
		Objects.requireNonNull(getServer().getPluginCommand("rngcrops")).setExecutor(this);
		Enchants.registerLocalEnchantments();
		Bukkit.getLogger().info(consolePrefix + "Loaded all enchants.");
		loadFiles();
		loadBstats();
	}

	@Override
	public void onDisable() {
		instance = null;
	}

	private void loadBstats() {
//		int pluginId = 15830;
//
//		Metrics metrics = new Metrics(this, pluginId);
//
//		metrics.addCustomChart(new Metrics.SimplePie("plugin_version", () -> getDescription().getVersion()));
//		metrics.addCustomChart(new Metrics.SimplePie("plugin_name", () -> getDescription().getName()));
//		metrics.addCustomChart(new Metrics.SimplePie("plugin_author", () -> getDescription().getAuthors().toString()));
	}

	public void loadFiles() {

		File langFile = new File(getDataFolder(), "language.yml");
		FileConfiguration langFileConfig = new YamlConfiguration();
		File configFile = new File(getDataFolder(), "config.yml");

		if (!configFile.exists()) {
			Utilities.loadResource(this, "config.yml");
		}
		if (!langFile.exists()) {
			Utilities.loadResource(this, "language.yml");
		}
		try {
			langFileConfig.load(langFile);
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		for (String langString : langFileConfig.getKeys(false)) {
			phrases.put(langString, langFileConfig.getString(langString));
		}
		Bukkit.getLogger().info(consolePrefix + "Loaded Audio Files, Phrases, and Config Values");
	}

	public static String getPhrase(String key) {
		return phrases.get(key);
	}

	private void registerEvents() {
		getServer().getPluginManager().registerEvents(new RNGCropsListener(), this);
	}

	@Override
	public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		ItemStack item = new ItemStack(Material.IRON_AXE, 1);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<>();
		lore.add("§7Melon Chopper I");
		meta.setLore(lore);
		item.setItemMeta(meta);

		ItemStack item1 = new ItemStack(Material.IRON_AXE, 1);
		ItemMeta meta1 = item1.getItemMeta();
		List<String> lore1 = new ArrayList<>();
		lore1.add("§7Melon Chopper II");
		meta1.setLore(lore1);
		item1.setItemMeta(meta1);

		ItemStack item2 = new ItemStack(Material.IRON_AXE, 1);
		ItemMeta meta2 = item2.getItemMeta();
		List<String> lore2 = new ArrayList<>();
		lore2.add("§7Melon Chopper III");
		meta2.setLore(lore2);
		item2.setItemMeta(meta2);

		ItemStack item3 = new ItemStack(Material.IRON_AXE, 1);
		ItemMeta meta3 = item3.getItemMeta();
		List<String> lore3 = new ArrayList<>();
		lore3.add("§7Melon Chopper IV");
		meta3.setLore(lore3);
		item3.setItemMeta(meta3);

		ItemStack item4 = new ItemStack(Material.IRON_AXE, 1);
		ItemMeta meta4 = item4.getItemMeta();
		List<String> lore4 = new ArrayList<>();
		lore4.add("§7Melon Chopper V");
		meta4.setLore(lore4);
		item4.setItemMeta(meta4);

		player.getInventory().addItem(item, item1, item2, item3, item4);
		sender.sendMessage(pluginPrefix + "Invalid Arguments");
		return true;
	}
}
