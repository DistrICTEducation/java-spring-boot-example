package districted.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@With
@EqualsAndHashCode
public class BookReservationDtoIn {
    private UUID id;

    private UUID memberId;

    private Integer bookId;

    private Date reservationDate;

    private String status;

    private Date pickupDate;
}
