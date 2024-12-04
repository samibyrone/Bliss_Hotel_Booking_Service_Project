package org.Bliss.services;

import org.Bliss.data.model.Room;
import org.Bliss.data.model.RoomStatus;
import org.Bliss.data.model.RoomType;
import org.Bliss.data.repositories.RoomRepo;
import org.Bliss.dtos.request.RoomRequest;
import org.Bliss.dtos.response.RoomResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class RoomServicesImplTest {
    @Autowired
    private RoomServices roomServices;
    @Autowired
    private RoomRepo roomRepo;

    @BeforeEach
    public void setUp() {
        roomRepo.deleteAll();

        Room room = new Room();
        room.setRoomNumber("306");
        room.setRoomType(RoomType.SINGLE);
        room.setPricePerNight(new BigDecimal("100"));
        room.setStatus(RoomStatus.AVAILABLE);
        roomRepo.save(room);
    }

    @Test
    public void testCheckRoomAvailability() {
        RoomRequest roomRequest = new RoomRequest();
        roomRequest.setRoomType(RoomType.SINGLE);
        roomRequest.setCheckInDate(new Date());
        roomRequest.setCheckOutDate(new Date());

        RoomResponse roomResponse = roomServices.checkAvailability(roomRequest);
        assertEquals(1, roomResponse.getAvailableRooms().size());
    }

//    @Test
//    public void testBookARoom(){
//        RoomRequest roomRequest = new RoomRequest();
//        roomRequest.setRoomType(RoomType.SINGLE);
//        roomRequest.setCheckInDate(new Date());
//        roomRequest.setCheckOutDate(new Date());
//
//        RoomResponse roomResponse = roomServices.bookRoom(roomRequest);
//        assertEquals("Room booked successfully", roomResponse.getMessage());
//
//    }

    @Test
    public void testBookANonExistentRoom() {
        RoomRequest roomRequest = new RoomRequest();
        roomRequest.setRoomId("999");
        roomRequest.setRoomType(RoomType.SINGLE);
        roomRequest.setCheckInDate(new Date());
        roomRequest.setCheckOutDate(new Date());

        RoomResponse roomResponse = roomServices.bookRoom(roomRequest);
        assertEquals("Room not found", roomResponse.getMessage());
    }
}