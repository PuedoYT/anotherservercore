package gg.bwhub.bwcore.punishments;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import gg.bwhub.bwcore.Core;
import gg.bwhub.bwcore.mongodb.IMongoRepository;
import org.bson.Document;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class PunishmentRepository implements IMongoRepository<String, Punishment> {

    private MongoCollection<Document> punishments = Core.getInstance().getMongoDB().getDatabase().getCollection("punishments");

    public void createDocument(String target, String staff, PunishmentList punishment) {
        Core.getInstance().getMongoDB().getDatabase().getCollection("punishments").insertOne(new Document()
                .append("_id", UUID.randomUUID().toString())
                .append("target", target)
                .append("staff", staff)
                .append("type", punishment.getType())
                .append("reason", punishment.getReason())
                .append("removalReason", "")
                .append("removed", false)
                .append("startDate", Timestamp.from(Instant.now()).getTime())
                .append("endDate", Timestamp.from(Instant.now().plusMillis(
                        punishment.getLong(findAllPunishmentsForReason(target, punishment).size()) == -1 ?
                                -1 : punishment.getLong(findAllPunishmentsForReason(target, punishment).size())
                )).getTime())
                .append("active", true)
        );
    }

    @Override
    public Punishment findByKey(String key) {
        Document document = punishments.find(Filters.eq("_id", key)).first();
        if(document == null) return null;

        return Core.getInstance().getGson().fromJson(document.toJson(), Punishment.class);
    }

    public Punishment findByUsername(String key) {
        Document document = punishments.find(Filters.eq("target", key)).first();
        if(document == null) return null;

        return Core.getInstance().getGson().fromJson(document.toJson(), Punishment.class);
    }

    public Punishment findActivePunishmentByUsername(String key, PunishmentType type) {
        Document document = punishments.find(Filters.and(
                Filters.eq("target", key),
                Filters.eq("type", type),
                Filters.eq("active", true))).first();
        if(document == null) return null;

        System.out.println(document.toJson());

        return Core.getInstance().getGson().fromJson(document.toJson(), Punishment.class);
    }

    public List<Document> findAllPunishmentsForReason(String key, PunishmentList type) {
        List<Document> documents = new ArrayList<>();
        for(Document document : punishments.find(Filters.and(
                Filters.eq("target", key),
                Filters.eq("type", type)))) {
            documents.add(document);
        }

        return documents;
    }

    @Override
    public void save(Punishment Punishment) {
        punishments.updateOne(Filters.eq("_id", Punishment.getPunishmentID()),
                        new Document("$set", Document.parse(Core.getInstance().getGson().toJson(Punishment))),
                        Core.UPDATE_OPTIONS);
    }

    @Override
    public void delete(Punishment Punishment) {
        punishments.deleteOne(Filters.eq("_id", Punishment.getPunishmentID()));
    }

}
