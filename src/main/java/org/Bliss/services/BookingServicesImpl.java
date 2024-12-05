package org.Bliss.services;

import org.Bliss.data.model.Booking;
import org.Bliss.data.model.BookingStatus;
import org.Bliss.data.model.Room;
import org.Bliss.data.model.User;
import org.Bliss.data.repositories.BookingRepo;
import org.Bliss.data.repositories.RoomRepo;
import org.Bliss.data.repositories.UserRepo;
import org.Bliss.dtos.request.BookingRequest;
import org.Bliss.dtos.response.BookingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingServicesImpl implements BookingService {
    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private UserRepo userRepo;


    @Override
    public BookingResponse createBooking(BookingRequest bookingRequest) {
        User user = userRepo.findById(bookingRequest.getUserId()).orElse(null);
        Room room = roomRepo.findById(bookingRequest.getRoomId()).orElse(null);
        if(user == null || room == null){
            BookingResponse bookingResponse = new BookingResponse();
            bookingResponse.setMessage("User or Room not found");
            return bookingResponse;
        }
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setRoom(room);
        booking.setCheckInDate(bookingRequest.getCheckInDate());
        booking.setCheckOutDate(bookingRequest.getCheckOutDate());
        booking.setStatus(BookingStatus.PENDING);
        bookingRepo.save(booking);


            BookingResponse bookingResponse = new BookingResponse();
            bookingResponse.setMessage("Booking Created successfully");
            return bookingResponse;
        }

    @Override
    public BookingResponse cancelBooking(BookingRequest bookingRequest) {
        Optional<Booking> optionalBooking = bookingRepo.findById(bookingRequest.getBookingId());
        BookingResponse bookingResponse = new BookingResponse();

        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();

            if (booking.getStatus() == BookingStatus.CANCELLED) {
                bookingResponse.setMessage("Booking already Cancelled");
                bookingResponse.setSuccess(false);
            } else {
                booking.setStatus(BookingStatus.CANCELLED);
                bookingRepo.save(booking);
                bookingResponse.setMessage("Booking Cancelled successfully");
                bookingResponse.setSuccess(true);
            }
        } else {
            bookingResponse.setMessage("Booking not found");
            bookingResponse.setSuccess(false);
        }
        return bookingResponse;
    }
    @Override
    public BookingResponse getBookingDetails(BookingRequest bookingRequest) {
        Optional<Booking> optionalBooking = bookingRepo.findById(bookingRequest.getBookingId());
        BookingResponse bookingResponse = new BookingResponse();

        if(optionalBooking.isPresent()){
            Booking booking = optionalBooking.get();
            bookingResponse.setBooking(booking);
            bookingResponse.setMessage("Booking found");
            bookingResponse.setSuccess(true);
        } else {
            bookingResponse.setMessage("Booking not found");
            bookingResponse.setSuccess(false);
        }
        return bookingResponse;
    }
}
