package com.github.PatrykCholewa.FoxKiller.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FoxKillListener implements Listener {

    @EventHandler
    public static void onEvent(EntityDeathEvent event) {
        if (event.getEntity().getType() != EntityType.FOX) {
            return;
        }

        if (event.getDamageSource().getDamageType() != DamageType.PLAYER_ATTACK) {
            return;
        }

        event.getDrops().addAll(createRandomDrop());
        event.setDroppedExp(7);
    }

    private static List<ItemStack> createRandomDrop() {
        List<ItemStack> list = new ArrayList<>();
        ItemStack is;

        int rand = new Random().nextInt();
        if ((rand % 3) == 0) {
            is = new ItemStack(Material.CAKE);
            list.add(is);
        }
        if ((rand % 7) == 0) {
            is = new ItemStack(Material.INFESTED_STONE_BRICKS);
            list.add(is);
        }

        if ((rand % 31) == 0) {
            is = new ItemStack(Material.OMINOUS_BOTTLE);
            list.add(is);
        }

        if ((rand % 47) == 0) {
            is = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta skullMeta = (SkullMeta) is.getItemMeta();
            skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer("Zirekall"));
            is.setItemMeta(skullMeta);
            list.add(is);
        }

        return list;
    }

}
