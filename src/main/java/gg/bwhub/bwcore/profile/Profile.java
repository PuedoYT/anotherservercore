package gg.bwhub.bwcore.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import gg.bwhub.bwcore.ranks.RankRepository;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Getter @Setter
public class Profile {

    @Expose @SerializedName("username")
    private final String username;
    @Expose private boolean online;
    @Expose private String rank;
    @Expose private int currency;
    @Expose private List<String> friendList;
    @Expose private HashMap<UUID, Long> grants, punishments;

    public Profile(String username) {
        this.username = username;
        this.online = true;
        this.rank = "";
        this.currency = 0;
        this.friendList = new ArrayList<>();
        this.grants = new HashMap<>();
        this.punishments = new HashMap<>();
    }
}
