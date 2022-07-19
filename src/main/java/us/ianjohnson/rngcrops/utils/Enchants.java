package us.ianjohnson.rngcrops.utils;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import us.ianjohnson.rngcrops.enchants.EnchantmentMelonChopper;

import java.lang.reflect.Field;

import static org.bukkit.enchantments.Enchantment.registerEnchantment;

public class Enchants {

	public static final EnchantmentMelonChopper MELON_CHOPPER = new EnchantmentMelonChopper();

	public static boolean registered = false;

	/**
	 * Register and inject all enchantments to the server
	 *
	 * @return the result of the enchantment registration
	 */
	public static EnchantmentRegistrationResult registerLocalEnchantments() {
		if (registered) return EnchantmentRegistrationResult.ALREADY_REGISTERED;

		try {
			Field fieldAcceptingNew = Enchantment.class.getDeclaredField("acceptingNew");
			fieldAcceptingNew.setAccessible(true);
			fieldAcceptingNew.set(null, true);
			fieldAcceptingNew.setAccessible(false);

			//ENCHANTMENT REGISTRATION
			registerEnchantment(MELON_CHOPPER);

		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			Bukkit.getLogger().info("RNGCrops: Failed to register local enchantments");
			return EnchantmentRegistrationResult.FIELD_VALUE_FAILURE;
		} catch (
				IllegalStateException e) { //Thrown by Enchantment.registerEnchantment() if "acceptingNew" field is true. Not likely to happen.
			Bukkit.getLogger().info("RNGCrops: Failed to register enchantment. Enchantment.acceptingNew is true.");
			return EnchantmentRegistrationResult.NOT_ACCEPTING_NEW;
		}

		registered = true;
		Bukkit.getLogger().info("RNGCrops: Enchantment registration successful.");
		Enchantment.stopAcceptingRegistrations();
		return EnchantmentRegistrationResult.SUCCESS;
	}
}
