package gg.bwhub.bwcore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import gg.bwhub.bwcore.Core;
import gg.bwhub.bwcore.menus.PunishmentMenus;
import gg.bwhub.bwcore.profile.Profile;
import gg.bwhub.bwcore.profile.ProfileRepository;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

@CommandAlias("suspend")
@Description("Suspends a player")
public class SuspendCommand extends BaseCommand {

    @Default
    @CommandCompletion("*")
    public void command(CommandSender sender, OnlinePlayer player) {
        sender.sendMessage("§aOpening menu..");
        if(sender instanceof Player) {
            ((Player) sender).openInventory(new PunishmentMenus(player.getPlayer().getName()).punishmentTypeSelection());
            Core.getInstance().punishmentCache.put(sender.getName(), player.getPlayer().getName());
        }
    }
}
