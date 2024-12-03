package org.Bliss.data.repositories;

import org.Bliss.data.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepo extends MongoRepository<Room, String> {
}
