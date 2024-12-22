package gg.bwhub.bwcore.profile;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import gg.bwhub.bwcore.Core;
import gg.bwhub.bwcore.mongodb.IMongoRepository;
import gg.bwhub.bwcore.mongodb.MongoDB;
import org.bson.Document;

import java.util.ArrayList;
import java.util.UUID;

public class ProfileRepository implements IMongoRepository<String, Profile> {

    private MongoCollection<Document> profiles = Core.getInstance().getMongoDB().getDatabase().getCollection("profiles");

    public void createProfile(String key) {
        if(findByKey(key) == null) {
            Core.getInstance().getMongoDB().getDatabase().getCollection("profiles").insertOne(new Document()
                    .append("username", key)
                    .append("online", true)
                    .append("rank", "User")
                    .append("currency", 0)
                    .append("friendList", new ArrayList<>())
                    .append("grants", new ArrayList<>())
                    .append("punishments", new ArrayList<>())
            );
        }
    }

    @Override
    public Profile findByKey(String key) {
        Document document = profiles.find(Filters.eq("username", key)).first();
        if(document == null) return null;

        return Core.getInstance().getGson().fromJson(document.toJson(), Profile.class);
    }

    @Override
    public void save(Profile profile) {
        profiles.updateOne(Filters.eq("username", profile.getUsername()),
                        new Document("$set", Document.parse(Core.getInstance().getGson().toJson(profile))),
                        Core.UPDATE_OPTIONS);
    }

    @Override
    public void delete(Profile profile) {
        profiles.deleteOne(Filters.eq("username", profile.getUsername()));
    }

}
