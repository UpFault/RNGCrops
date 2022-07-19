package us.ianjohnson.rngcrops.enchants;

import io.papermc.paper.enchantments.EnchantmentRarity;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.EntityCategory;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import us.ianjohnson.rngcrops.utils.RNGEnchant;

import java.util.Set;

@SuppressWarnings("all")
public class EnchantmentMelonChopper extends RNGEnchant {

	public EnchantmentMelonChopper() {
		super(new NamespacedKey("rngcrops", "melon_chopper"));
	}

	@Override
	public boolean canEnchantItem(@NotNull ItemStack item) {
		return item.getType() == Material.WOODEN_AXE || item.getType() == Material.STONE_AXE || item.getType() == Material.IRON_AXE || item.getType() == Material.GOLDEN_AXE || item.getType() == Material.DIAMOND_AXE;
	}

	@Override
	public @NotNull EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.TOOL;
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}

	@Override
	public @NotNull String getName() {
		return "Melon Chopper";
	}

	@Override
	public int getEnchantmentTableMinimumLevel() {
		return 1;
	}

	@Override
	public int getEnchantmentTableMaximumLevel() {
		return 30;
	}

	@Override
	public int getEnchantmentChance() {
		return 10;
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
