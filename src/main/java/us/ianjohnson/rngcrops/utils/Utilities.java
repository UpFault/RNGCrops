package us.ianjohnson.rngcrops.utils;

import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import us.ianjohnson.rngcrops.RNGCrops;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.logging.Logger;

public class Utilities {

	private static final Logger logger = Bukkit.getLogger();
	private static boolean upToDate = false;
	private static String latest = "";

	public static File loadResource(Plugin plugin, String resource) {
		File folder = plugin.getDataFolder();
		if (!folder.exists())
			folder.mkdir();
		File resourceFile = new File(folder, resource);
		try {
			if (!resourceFile.exists()) {
				resourceFile.createNewFile();
				try (InputStream in = plugin.getResource(resource);
					 OutputStream out = Files.newOutputStream(resourceFile.toPath())) {
					assert in != null;
					ByteStreams.copy(in, out);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resourceFile;
	}

	@SuppressWarnings("all")
	public static void checkForUpdates() {

		try {
			byte[] bytes = new URL("https://raw.githubusercontent.com/UpFault/RNGCrops/master/version.txt").openStream().readAllBytes();
			String pluginVersion = getTrueVersion(RNGCrops.getInstance().getDescription().getVersion());
			String githubVersion = new String(bytes, StandardCharsets.UTF_8);

			if (Double.parseDouble(getTrueVersion(githubVersion)) == Double.parseDouble(getTrueVersion(pluginVersion))) {
				upToDate = true;
				logger.info("You are running the latest version of RNGCrops!");
				logger.info("Trying again in 5 minutes...");
			} else {
				logger.warning("A new version of RNGCrops is available! " + pluginVersion + " -> " + githubVersion);
				logger.warning("Download it at https://github.com/UpFault/RNGCrops/releases/latest");
				upToDate = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getTrueVersion(String version) {
		String v = version;
		v = v.replace("v", "");
		v = v.replace("beta", "");
		v = v.replace("release", "");
		v = v.replace("-", "");
		return v;
	}
}
