package gg.bwhub.bwcore.ranks;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import gg.bwhub.bwcore.Core;
import gg.bwhub.bwcore.mongodb.IMongoRepository;
import org.bson.Document;

import java.util.ArrayList;

public class RankRepository implements IMongoRepository<String, Rank> {

    private MongoCollection<Document> ranks = Core.getInstance().getMongoDB().getDatabase().getCollection("ranks");

    public RankRepository() {
        if(findByKey("User") == null) {
            Core.getInstance().getMongoDB().getDatabase().getCollection("ranks").insertOne(new Document()
                    .append("name", "User")
                    .append("displayname", "§aUser")
                    .append("color", "§a")
                    .append("prefix", "§a")
                    .append("weight", 0)
                    .append("permissions", new ArrayList<String>())
            );
        }
    }

    public void createDocument(String rankName) {
        Core.getInstance().getMongoDB().getDatabase().getCollection("ranks").insertOne(new Document()
                .append("name", rankName)
                .append("displayname", "")
                .append("color", "")
                .append("prefix", "")
                .append("weight", 0)
                .append("permissions", new ArrayList<String>())
        );
    }

    @Override
    public Rank findByKey(String key) {
        Document document = ranks.find(Filters.eq("name", key)).first();
        if(document == null) return null;

        return Core.getInstance().getGson().fromJson(document.toJson(), Rank.class);
    }

    @Override
    public void save(Rank rank) {
        ranks.updateOne(Filters.eq("name", rank.getName()),
                        new Document("$set", Document.parse(Core.getInstance().getGson().toJson(rank))),
                        Core.UPDATE_OPTIONS);
    }

    @Override
    public void delete(Rank rank) {
        ranks.deleteOne(Filters.eq("name", rank.getName()));
    }

}
