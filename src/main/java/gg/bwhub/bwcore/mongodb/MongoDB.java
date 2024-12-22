package gg.bwhub.bwcore.mongodb;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import org.bson.UuidRepresentation;

public class MongoDB {

    protected String URI = "mongodb://localhost:27017";

    private MongoClient mongoClient = MongoClients.create(MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString(URI))
            .uuidRepresentation(UuidRepresentation.STANDARD)
            .build());
    @Getter
    private MongoDatabase database = mongoClient.getDatabase("bwhub");
}
