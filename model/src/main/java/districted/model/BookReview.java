package districted.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
@Table(name = "book_review", schema = "dbo")
public class BookReview {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    @Min(value = 1, message = "The rating must be between 1 and 5.")
    @Max(value = 5, message = "The rating must be between 1 and 5.")
    private Integer rating;

    @Column(name = "review_date", nullable = false)
    private Date reviewDate;
}
