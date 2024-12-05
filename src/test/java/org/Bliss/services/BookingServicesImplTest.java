package org.Bliss.services;

import org.Bliss.data.model.*;
import org.Bliss.data.repositories.BookingRepo;
import org.Bliss.data.repositories.RoomRepo;
import org.Bliss.data.repositories.UserRepo;
import org.Bliss.dtos.request.BookingRequest;
import org.Bliss.dtos.response.BookingResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class BookingServicesImplTest {
    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BookingServicesImpl bookingServices;


    @BeforeEach
    public void setUp() {
        bookingRepo.deleteAll();
        roomRepo.deleteAll();
        userRepo.deleteAll();
    }

    @Test
    public void testCreateBooking() {
        User user = new User();
        user.setUserId("1");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john@doe.com");
        user.setPassword("password");
        userRepo.save(user);

        Room room = new Room();
        room.setRoomId("1");
        room.setRoomNumber("306");
        room.setRoomType(RoomType.SINGLE);
        room.setPricePerNight(new BigDecimal("100"));
        room.setStatus(RoomStatus.AVAILABLE);
        roomRepo.save(room);

        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setUserId("1");
        bookingRequest.setRoomId("1");
        bookingRequest.setCheckInDate(new Date());
        bookingRequest.setCheckOutDate(new Date());

        BookingResponse bookingResponse = bookingServices.createBooking(bookingRequest);
        assertEquals("Booking Created successfully", bookingResponse.getMessage());
//        Booking savedBooking = bookingRepo.findById(bookingResponse.getBooking().getBookingId());
        Optional<Booking> savedBooking = bookingRepo.findById(bookingResponse.getBooking().getBookingId());
       assertNotNull(savedBooking);
//        assertEquals(BookingStatus.PENDING, savedBooking.getStatus());
    }
}