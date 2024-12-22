package gg.bwhub.bwcore.events;

import gg.bwhub.bwcore.profile.Profile;
import gg.bwhub.bwcore.profile.ProfileRepository;
import gg.bwhub.bwcore.ranks.Rank;
import gg.bwhub.bwcore.ranks.RankRepository;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        Profile profile = new ProfileRepository().findByKey(player.getName());
        RankRepository rankRepository = new RankRepository();
        Rank rank = rankRepository.findByKey(profile.getRank());
        player.sendMessage("player >> " + profile.getRank());
        player.sendMessage(rank.getName());

        event.setFormat(rank.getPrefix() + " §f" + player.getName() + "§7: §f" + event.getMessage());
    }
}
