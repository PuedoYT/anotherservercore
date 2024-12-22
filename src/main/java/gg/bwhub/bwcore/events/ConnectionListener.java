package gg.bwhub.bwcore.events;

import gg.bwhub.bwcore.profile.Profile;
import gg.bwhub.bwcore.profile.ProfileRepository;
import gg.bwhub.bwcore.punishments.Punishment;
import gg.bwhub.bwcore.punishments.PunishmentRepository;
import gg.bwhub.bwcore.punishments.PunishmentType;
import org.bson.Document;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class ConnectionListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        PunishmentRepository punishmentRepository = new PunishmentRepository();
        if(punishmentRepository.findActivePunishmentByUsername(player.getName(), PunishmentType.BAN) != null) {
            Punishment punishment = punishmentRepository.findActivePunishmentByUsername(player.getName(), PunishmentType.BAN);
            long days = TimeUnit.MILLISECONDS.toDays(punishment.getEndDate() - Timestamp.from(Instant.now()).getTime());
            long hours = TimeUnit.MILLISECONDS.toHours((punishment.getEndDate() - Timestamp.from(Instant.now()).getTime()) - TimeUnit.DAYS.toMillis(days));

            player.kickPlayer(
                    "§cYour account has been suspended for §f" + days + "d " + hours + "h §cfrom Harmonia" +
                    "\n§cfor §f" + punishment.getReason() +
                    "\n" +
                    "\n§cIf you believe this suspension is incorrect, you may open an appeal on our" +
                    "\n§cdiscord server: §fdiscord.gg/bwhub §cand provide your ban ID: §f" + punishment.getPunishmentID());
        }

        player.sendMessage("§fWelcome to §b§lBWHub.gg §f!");
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        ProfileRepository profileManager = new ProfileRepository();

        if(profileManager.findByKey(player.getName()) == null) {
            profileManager.createProfile(player.getName());
            System.out.println("Created new profile for: " + player.getName());
        }
    }

    @EventHandler
    public void onLogout(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        Profile profile = new ProfileRepository().findByKey(player.getName());
        profile.setOnline(false);

        new ProfileRepository().save(profile);
    }
}
