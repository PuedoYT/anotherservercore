package gg.bwhub.bwcore.ranks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Getter @Setter
public class Rank {

    @Expose @SerializedName("name")
    private String name;
    @Expose private String prefix;
    @Expose private String displayname;
    @Expose private String color;
    @Expose private int weight;
    @Expose private ArrayList<String> permissions;
    public Rank(String rankName) {
        this.name = rankName;
        this.prefix = "N/A";
        this.displayname = "N/A";
        this.color = "§7";
        this.weight = 0;
        this.permissions = new ArrayList<>();
    }
}
