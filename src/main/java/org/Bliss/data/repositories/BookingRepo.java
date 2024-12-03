package org.Bliss.data.repositories;

import org.Bliss.data.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepo extends MongoRepository<Booking, String> {

}
