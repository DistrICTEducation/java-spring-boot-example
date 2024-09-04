package districted.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.Date;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "book_reservation", schema = "dbo")
public class BookReservation {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "reservation_date", nullable = false)
    private Date reservationDate;

    @Column(nullable = false)
    private String status;

    @Column(name = "pickup_date")
    private Date pickupDate;

    @AssertTrue(message = "The reservation date must be before or equal to the pickup date.")
    private boolean isReservationDateBeforeOrEqualToPickupDate() {
        return reservationDate.compareTo(pickupDate) <= 0;
    }
}

