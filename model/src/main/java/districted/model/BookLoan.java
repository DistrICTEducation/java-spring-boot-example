package districted.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.PositiveOrZero;
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
@Table(name = "book_loan", schema = "dbo")
public class BookLoan {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "checkout_date", nullable = false)
    @JsonSerialize()
    @JsonDeserialize()
    private Date checkoutDate;

    @Column(name = "due_date", nullable = false)
    @JsonSerialize()
    @JsonDeserialize()
    private Date dueDate;

    @Column(name = "return_date")
    @JsonSerialize()
    @JsonDeserialize()
    private Date returnDate;

    @Column(name = "fine_amount")
    @PositiveOrZero(message = "The number of copies must be zero or greater.")
    private Double fineAmount;

    @Column(name = "fine_status")
    private String fineStatus;

    @Column(name = "fine_status_date")
    @JsonSerialize()
    @JsonDeserialize()
    private Date fineStatusDate;

    @AssertTrue(message = "The checkout date must be before or equal to the due date.")
    private boolean isCheckoutDateBeforeOrEqualToDueDate() {
        return checkoutDate.compareTo(dueDate) <= 0;
    }

    @AssertTrue(message = "The checkout date must be before or equal to the return date.")
    private boolean isCheckoutDateBeforeOrEqualToReturnDate() {
        return checkoutDate.compareTo(returnDate) <= 0;
    }

    @AssertTrue(message = "The due date must be before or equal to the fine status date.")
    private boolean isDueDateBeforeOrEqualToFineStatusDate() {
        return dueDate.compareTo(fineStatusDate) <= 0;
    }
}
