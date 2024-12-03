package org.Bliss.data.repositories;

import org.Bliss.data.model.Room;
import org.Bliss.data.model.RoomStatus;
import org.Bliss.data.model.RoomType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RoomRepo extends MongoRepository<Room, String> {
    List<Room> findAllByRoomTypeAndStatus(RoomType roomType, RoomStatus status);
}
