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
public class BookLoanDtoIn {
    private UUID memberId;

    private Integer bookId;

    private Date checkoutDate;

    private Date dueDate;

    private Date returnDate;

    private Double fineAmount;

    private String fineStatus;

    private Date fineStatusDate;
}
