package com.example.billetms.services;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter

public class BilletDTO {
    Long id;
    String bookerId;
    String bookId;
    LocalDateTime bookingDate;
    LocalDateTime endDate;
    LocalDateTime extendDate;
    Boolean isExtend;
    Boolean isOnWaitList;
}
