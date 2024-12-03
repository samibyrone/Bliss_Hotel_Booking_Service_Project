package org.Bliss.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Document
public class Booking {
    @Id
    private long bookingId;
    private long roomId;
    private long userId;
    private Date checkInDate;
    private Date checkOutDate;
    private BigDecimal price;
}
