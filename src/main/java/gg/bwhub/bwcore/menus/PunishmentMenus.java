package gg.bwhub.bwcore.menus;

import gg.bwhub.bwcore.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PunishmentMenus {

    private String username;
    public PunishmentMenus(String username) {
        this.username = username;
    }

    public Inventory punishmentTypeSelection() {
        Inventory inventory = Bukkit.createInventory(null, 36, "§7Select offence type");
        inventory.setItem(21, new ItemBuilder(Material.IRON_SWORD, 1).setName("§bGameplay Offences").toItemStack());
        inventory.setItem(23, new ItemBuilder(Material.PAPER, 1).setName("§bChat Offences").toItemStack());
        return inventory;
    }

    public Inventory gameplayOffencesMenu() {
        Inventory inventory = Bukkit.createInventory(null, 36, "§7Suspend " + username);
        inventory.setItem(10, new ItemBuilder(Material.IRON_SWORD, 1).setName("§bCheating").toItemStack());
        inventory.setItem(11, new ItemBuilder(Material.PISTON_STICKY_BASE, 1).setName("§bBug Abuse").toItemStack());
        return inventory;
    }
}
