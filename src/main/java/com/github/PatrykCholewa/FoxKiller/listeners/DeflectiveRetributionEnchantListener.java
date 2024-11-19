package com.github.PatrykCholewa.FoxKiller.listeners;

import com.github.PatrykCholewa.FoxKiller.enchantments.EnchantmentDefinitions;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import static com.github.PatrykCholewa.FoxKiller.utils.EnchantmentUtils.getEnchantmentRef;

public class DeflectiveRetributionEnchantListener implements Listener {

    private static final Enchantment ENCHANTMENT = getEnchantmentRef(EnchantmentDefinitions.DEFLECTIVE_RETRIBUTION);

    @EventHandler
    public static void onDeath(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getEntity();

        if (!player.getActiveItem().containsEnchantment(ENCHANTMENT)) {
            return;
        }

        if (!player.isBlocking() && event.getDamage(EntityDamageEvent.DamageModifier.BLOCKING) >= 0) {
            return;
        }

        Entity cause = event.getDamageSource().getDirectEntity();

        if (!(cause instanceof Projectile)) {
            return;
        }

        cause.setVelocity(cause.getVelocity().multiply(25));
    }

}
