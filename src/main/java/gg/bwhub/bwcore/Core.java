package gg.bwhub.bwcore;

import co.aikar.commands.BukkitCommandManager;
import com.google.gson.Gson;
import com.mongodb.client.model.UpdateOptions;
import gg.bwhub.bwcore.commands.ProfileCommand;
import gg.bwhub.bwcore.commands.RankCommand;
import gg.bwhub.bwcore.commands.SuspendCommand;
import gg.bwhub.bwcore.events.ConnectionListener;
import gg.bwhub.bwcore.events.PlayerChatListener;
import gg.bwhub.bwcore.events.menus.PunishmentMenuListener;
import gg.bwhub.bwcore.mongodb.MongoDB;
import gg.bwhub.bwcore.profile.Profile;
import gg.bwhub.bwcore.ranks.Rank;
import gg.bwhub.bwcore.ranks.RankRepository;
import gg.bwhub.bwcore.utils.json.GsonProvider;
import lombok.Getter;

import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Getter
public final class Core extends JavaPlugin {

    public static UpdateOptions UPDATE_OPTIONS;
    @Getter private static Core instance;
    private static BukkitCommandManager commandManager;
    private static RankRepository rankRepository;

    private MongoDB mongoDB;
    private Gson gson;

    public HashMap<String, String> punishmentCache = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        UPDATE_OPTIONS = new UpdateOptions().upsert(true);
        commandManager = new BukkitCommandManager(this);

        this.mongoDB = new MongoDB();
        this.gson = GsonProvider.GSON;

        rankRepository = new RankRepository();

        commandManager.registerCommand(new ProfileCommand());
        commandManager.registerCommand(new RankCommand());
        commandManager.registerCommand(new SuspendCommand());
        commandManager.getCommandCompletions().registerAsyncCompletion("ranks", c -> {
            List<String> ranks = new ArrayList<>();
            for(Document document : mongoDB.getDatabase().getCollection("ranks").find())
            { ranks.add(document.getString("name")); }
            return ranks;
        });

        Bukkit.getPluginManager().registerEvents(new ConnectionListener(), instance);
        Bukkit.getPluginManager().registerEvents(new PlayerChatListener(), instance);
        Bukkit.getPluginManager().registerEvents(new PunishmentMenuListener(), instance);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
