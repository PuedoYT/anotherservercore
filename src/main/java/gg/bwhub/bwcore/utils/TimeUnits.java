package gg.bwhub.bwcore.utils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public enum TimeUnits {

    SECOND("Second(s)", 1, "s"),
    MINUTE("Minute(s)", 60, "m"),
    HOUR("Hour(s)", 60*60, "h"),
    DAY("Day(s)", 24*60*60, "d"),
    WEEK("Week(s)", 7*24*60*60, "w");

    private String name;
    private int toSecond;
    private String shortcut;

    TimeUnits(String name, int toSecond, String shortcut) {
        this.name = name;
        this.toSecond = toSecond;
        this.shortcut = shortcut;
    }

    public int getToSecond() {
        return toSecond;
    }

    public String getName() {
        return name;
    }

    public String getShortcut() {
        return shortcut;
    }

    public static List<String> getUnitsAsString() {
        List<String> units = new ArrayList<String>();
        for(TimeUnits unit : TimeUnits.values()) {
            units.add(unit.getShortcut().toLowerCase());
        }
        return units;
    }

    public static TimeUnits getUnit(String unit) {
        for(TimeUnits units : TimeUnits.values()) {
            if(units.getShortcut().toLowerCase().equals(unit.toLowerCase())) {
                return units;
            }
        }
        return null;
    }

    public static long getTimeUnitFromShortcut(String s, Long duration){
        List<String> units = TimeUnits.getUnitsAsString();
        if(units.contains(s.replaceAll("\\d", ""))) {
            TimeUnits unit = TimeUnits.getUnit(s.replaceAll("\\d", ""));
            long seconds = duration * unit.getToSecond();
            return seconds;
        }
        return 0;
    }

    public static String findTime(Timestamp timestamp) {
        String diff = "";

        Date date = new Date(System.currentTimeMillis());
        Date date1 = new Date(timestamp.getTime());

        final long ms = date1.getTime() - date.getTime();

        final long years = TimeUnit.MILLISECONDS.toDays(ms) / 365;
        final long days = TimeUnit.MILLISECONDS.toDays(ms) %  365;
        final long hours = TimeUnit.MILLISECONDS.toHours(ms) % 24;
        final long minutes = TimeUnit.MILLISECONDS.toMinutes(ms) % 60;
        final long seconds = TimeUnit.MILLISECONDS.toSeconds(ms) % 60;

        if (years > 0) diff = diff.concat(years + "y ");
        if (days > 0) diff = diff.concat(days + "d ");
        if (hours > 0) diff = diff.concat(hours + "h ");
        if (minutes > 0) diff = diff.concat(minutes + "m ");
        if (seconds > 0) diff = diff.concat(seconds + "s ");

        if (!(diff.length() < 1)) diff = diff.substring(0, diff.length()-1);
        if (diff.isEmpty()) diff = diff.concat("0s");

        return diff;
    }
}
