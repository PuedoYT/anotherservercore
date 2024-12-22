package gg.bwhub.bwcore.events.menus;

import gg.bwhub.bwcore.Core;
import gg.bwhub.bwcore.commands.SuspendCommand;
import gg.bwhub.bwcore.menus.PunishmentMenus;
import gg.bwhub.bwcore.punishments.PunishmentList;
import gg.bwhub.bwcore.punishments.PunishmentRepository;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.Timestamp;
import java.time.Instant;

public class PunishmentMenuListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        ItemStack itemStack = event.getCurrentItem();

        event.setCancelled(true);

        if(inventory.getName().equalsIgnoreCase("§7Select offence type")) {
            switch (itemStack.getType()) {
                case IRON_SWORD:
                    player.openInventory(new PunishmentMenus(player.getName()).gameplayOffencesMenu());
                    break;
                case PAPER:
                    break;
                default:
                    break;
            }
        }

        if(inventory.getName().contains("§7Suspend ")) {
            switch (itemStack.getType()) {
                case IRON_SWORD:
                    PunishmentRepository punishmentRepository = new PunishmentRepository();
                    punishmentRepository.createDocument(Core.getInstance().punishmentCache.get(player.getName()), player.getName(), PunishmentList.CHEATING);

                    player.closeInventory();
                    Core.getInstance().punishmentCache.remove(player.getName());

                    player.sendMessage("§aSuccessfully punished a player for Cheating!");
                    break;
                case PAPER:
                    break;
                default:
                    break;
            }
        }

    }
}
