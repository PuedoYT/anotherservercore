package gg.bwhub.bwcore.punishments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Punishment {

    @Expose @SerializedName("_id")
    private final String punishmentID;
    @Expose private String target;
    @Expose private String staff;
    @Expose private String type;
    @Expose private String reason;
    @Expose private String removalReason;
    @Expose private boolean removed;
    @Expose private long startDate;
    @Expose private long endDate;
    @Expose private boolean active;

    public Punishment(String username) {
        this.punishmentID = "";
        this.target = "";
        this.staff = "";
        this.type = "";
        this.reason = "";
        this.removalReason = "";
        this.removed = false;
        this.startDate = -1;
        this.endDate = -1;
        this.active = true;
    }
}
