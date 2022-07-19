package us.ianjohnson.rngcrops.utils;

import org.bukkit.enchantments.Enchantment;

public class EnchantmentRegistrationResult {

	public static EnchantmentRegistrationResult ALREADY_REGISTERED;
	public static EnchantmentRegistrationResult FIELD_VALUE_FAILURE;
	public static EnchantmentRegistrationResult NOT_ACCEPTING_NEW;
	public static EnchantmentRegistrationResult SUCCESS;
	public boolean success;
	public Enchantment enchantment;
	static boolean registered;

	public EnchantmentRegistrationResult(boolean success, Enchantment enchantment, boolean registered) {
		this.success = success;
		this.enchantment = enchantment;
		EnchantmentRegistrationResult.registered = registered;
	}
}
