package gg.bwhub.bwcore.punishments;

import gg.bwhub.bwcore.utils.TimeUnits;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum PunishmentList {

    OTHER(PunishmentType.BAN, "Other", "Other - Contact our team for more details", Arrays.asList("-1")),
    BUG_ABUSE(PunishmentType.BAN, "BugAbuse", "Exploiting bugs to gain an advantage", Arrays.asList("7d", "14d", "30d", "-1")),
    TEAMING(PunishmentType.BAN, "Teaming", "Teaming with other players", Arrays.asList("7d", "14d", "30d", "-1")),
    EXCESSIVE_RUNNING(PunishmentType.BAN, "Running", "Running excessively away from fights", Arrays.asList("0h", "1h", "3h", "6h", "12h")),
    EXCESSIVE_CAMPING(PunishmentType.BAN, "Camping", "Camping or stalling intentionally a game", Arrays.asList("0h", "1h", "3h", "6h", "12h")),
    BOOSTING(PunishmentType.BAN, "Boosting", "Using alternate accounts to boost a player's statistics", Arrays.asList("14d", "30d", "90d", "-1")),
    CHEATING(PunishmentType.BAN, "Cheating", "Using blacklisted mods that provide an unfair competitive advantage", Arrays.asList("30d", "90d", "-1"));

    @Getter private PunishmentType type;
    @Getter private String identifier;
    @Getter private String reason;
    @Getter private List<String> durationLadder;

    PunishmentList(PunishmentType type, String identifier, String reason, List<String> durationLadder) {
        this.type = type;
        this.identifier = identifier;
        this.reason = reason;
        this.durationLadder = durationLadder;
    }

    public long getLong(int index) {
        if(index >= durationLadder.size()) { index = durationLadder.size(); }
        if(durationLadder.get(index) == "-1") return -1;

        Long time = Long.parseLong(durationLadder.get(index).replaceAll("[a-z_A-Z]", ""));
        Long miliseconds = TimeUnits.getTimeUnitFromShortcut(durationLadder.get(index), time) * 1000;

        return miliseconds;
    }


}

