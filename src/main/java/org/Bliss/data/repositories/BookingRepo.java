package org.Bliss.data.repositories;

import org.Bliss.data.model.Booking;
import org.Bliss.data.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BookingRepo extends MongoRepository<Booking, String> {
    List<Booking> findByRoomAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(Room room, Date checkOutDate, Date checkInDate);
    Optional<Booking> findById(String bookingId);
}
