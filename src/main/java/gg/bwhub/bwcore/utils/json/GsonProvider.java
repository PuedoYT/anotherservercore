package gg.bwhub.bwcore.utils.json;

/*
 *
 * Iron is a property of Kira-Development-Team
 * 18/11/2023
 * Coded by the founders of Kira-Development-Team
 * EmpireMTR & Vifez
 *
 */

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gg.bwhub.bwcore.utils.json.adapters.LongTypeAdapter;

import java.util.function.Function;

public class GsonProvider {

    private static GsonBuilder gsonBuilder = new GsonBuilder()
            .registerTypeAdapter(Long.class, new LongTypeAdapter())
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .serializeNulls()
            .setPrettyPrinting();

    public static Gson GSON = gsonBuilder.create();

    public static void useGsonBuilderThenRebuild(Function<GsonBuilder, GsonBuilder> function) {
        synchronized (gsonBuilder) {
            gsonBuilder = function.apply(gsonBuilder);
            GSON = gsonBuilder.create();
        }
    }
}