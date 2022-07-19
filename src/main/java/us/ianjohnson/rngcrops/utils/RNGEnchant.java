package us.ianjohnson.rngcrops.utils;

import io.papermc.paper.enchantments.EnchantmentRarity;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.EntityCategory;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@SuppressWarnings("all")
public abstract class RNGEnchant extends Enchantment {

	public RNGEnchant(NamespacedKey id) {
		super(id);
	}

	public abstract boolean canEnchantItem(ItemStack item);

	@Override
	public abstract EnchantmentTarget getItemTarget();

	@Override
	public abstract int getMaxLevel();

	@Override
	public abstract String getName();

	public abstract int getEnchantmentTableMinimumLevel();

	public abstract int getEnchantmentTableMaximumLevel();

	public abstract int getEnchantmentChance();

	@Override
	public boolean conflictsWith(Enchantment other) {
		return false;
	}

	@Override
	public int getStartLevel() {
		return 1;
	}

	@Override
	public boolean isCursed() {
		return false;
	}

	@Override
	public boolean isTreasure() {
		return false;
	}

	@Override
	public @NotNull String translationKey() {
		return "";
	}

	@Override
	public @NotNull Component displayName(int level) {
		return Component.text(getStartLevel());
	}

	@Override
	public boolean isTradeable() {
		return false;
	}

	@Override
	public boolean isDiscoverable() {
		return false;
	}

	@Override
	public @NotNull EnchantmentRarity getRarity() {
		return EnchantmentRarity.RARE;
	}

	@Override
	public float getDamageIncrease(int level, @NotNull EntityCategory entityCategory) {
		return 0;
	}

	@Override
	public @NotNull Set<EquipmentSlot> getActiveSlots() {
		return Set.of(EquipmentSlot.HAND);
	}
}
