package us.ianjohnson.rngcrops;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import us.ianjohnson.rngcrops.utils.Utilities;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public final class RNGCrops extends JavaPlugin {

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
		loadFiles();
//		loadBstats();
	}

	@Override
	public void onDisable() {
		instance = null;
	}

//	private void loadBstats() {
//		int pluginId = 0;
//
//		Metrics metrics = new Metrics(this, pluginId);
//
//		metrics.addCustomChart(new SimplePie("plugin_version", () -> getDescription().getVersion()));
//		metrics.addCustomChart(new SimplePie("plugin_name", () -> getDescription().getName()));
//		metrics.addCustomChart(new SimplePie("plugin_author", () -> getDescription().getAuthors().toString()));
//	}

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
}
