package gg.bwhub.bwcore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import gg.bwhub.bwcore.profile.Profile;
import gg.bwhub.bwcore.profile.ProfileRepository;
import org.bukkit.command.CommandSender;

@CommandAlias("profile")
@Description("Checks a player's profile data")
public class ProfileCommand extends BaseCommand {

    @Default
    @CommandCompletion("*")
    public void command(CommandSender sender, OnlinePlayer player) {
        Profile profile = new ProfileRepository().findByKey(player.getPlayer().getName());
        sender.sendMessage("§c§lAdmin » §fProfile data for §f" + player.getPlayer().getName() + "" +
                "\nIs Online: " + profile.isOnline() +
                "\nCurrency: " + profile.getCurrency()
        );
    }
}
