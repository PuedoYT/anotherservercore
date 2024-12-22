package gg.bwhub.bwcore.mongodb;

import java.util.UUID;

public interface IMongoRepository<K, V> {
    V findByKey(K key);

    void save(V object);
    void delete(V object);
}
