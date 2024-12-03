package org.Bliss.data.repositories;

import org.Bliss.data.model.Booking;
import org.Bliss.data.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface BookingRepo extends MongoRepository<Booking, String> {
    List<Booking> findByRoomAndCheckInDateLessThanEqualAndAndCheckOutDateGreaterThanEqual(Room room, Date checkOutDate, Date checkInDate);
}
