package gg.bwhub.bwcore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import gg.bwhub.bwcore.profile.Profile;
import gg.bwhub.bwcore.profile.ProfileRepository;
import gg.bwhub.bwcore.ranks.Rank;
import gg.bwhub.bwcore.ranks.RankRepository;
import org.bukkit.command.CommandSender;

@CommandAlias("rank")
@Description("Manage ranks")
public class RankCommand extends BaseCommand {

    RankRepository rankRepository = new RankRepository();

    @Default
    @CommandCompletion("*")
    public void help(CommandSender sender, OnlinePlayer player) {
        sender.sendMessage(
                "§c§lAdmin » §fHelp for /rank §f\n" +
                "\n/rank create" +
                "\n/rank delete" +
                "\n/rank edit"
        );
    }

    @Subcommand("create")
    public void create(CommandSender sender, String rankName) {
        rankRepository.createDocument(rankName);
        sender.sendMessage("§c§lAdmin » §fSuccessfully created the rank " + rankName);
    }

    @Subcommand("edit display")
    @CommandCompletion("@ranks")
    public void editDisplay(CommandSender sender, String rankName, String display) {
        if(rankRepository.findByKey(rankName) == null) sender.sendMessage("§c§lAdmin » §fUnable to find a rank named §l" + rankRepository + " §r!");

        Rank editedRank = new RankRepository().findByKey(rankName);
        editedRank.setDisplayname(display);
        rankRepository.save(editedRank);

        sender.sendMessage("§c§lAdmin » §fSuccessfully edited displayname to " + display);
    }

    @Subcommand("edit prefix")
    @CommandCompletion("@ranks")
    public void editPrefix(CommandSender sender, String rankName, String prefix) {
        if(rankRepository.findByKey(rankName) == null) sender.sendMessage("§c§lAdmin » §fUnable to find a rank named §l" + rankRepository + " §r!");

        Rank editedRank = new RankRepository().findByKey(rankName);
        editedRank.setPrefix(prefix);
        rankRepository.save(editedRank);

        sender.sendMessage("§c§lAdmin » §fSuccessfully edited prefix to " + prefix);
    }

    @Subcommand("edit color")
    @CommandCompletion("@ranks")
    public void editColor(CommandSender sender, String rankName, String color) {
        if(rankRepository.findByKey(rankName) == null) sender.sendMessage("§c§lAdmin » §fUnable to find a rank named §l" + rankRepository + " §r!");

        Rank editedRank = new RankRepository().findByKey(rankName);
        editedRank.setColor(color);
        rankRepository.save(editedRank);

        sender.sendMessage("§c§lAdmin » §fSuccessfully edited color to " + color.toString());
    }

    @Subcommand("edit color")
    @CommandCompletion("@ranks")
    public void editWeight(CommandSender sender, String rankName, int weight) {
        if(rankRepository.findByKey(rankName) == null) sender.sendMessage("§c§lAdmin » §fUnable to find a rank named §l" + rankRepository + " §r!");

        Rank editedRank = new RankRepository().findByKey(rankName);
        editedRank.setWeight(weight);
        rankRepository.save(editedRank);

        sender.sendMessage("§c§lAdmin » §fSuccessfully edited color to " + weight);
    }
}
