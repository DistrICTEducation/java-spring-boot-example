package districted.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "book", schema = "dbo")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Pattern(regexp = "^\\d{9}[\\dX]$", message = "Invalid ISBN-10 format")
    private String isbn;

    @Column(nullable = false)
    @Pattern(regexp = "^(978|979)\\d{10}$", message = "Invalid ISBN-13 format")
    private String isbn13;

    @Column(nullable = false)
    @Size(max = 255)
    private String title;

    @Column(nullable = false)
    @Size(max = 255)
    private String author;

    @Column(nullable = false)
    @Size(max = 255)
    private String publisher;

    @Column(name = "publication_year")
    private Integer publicationYear;

    @Column(name = "publication_language")
    @Size(max = 50)
    private String publicationLanguage;

    @Size(max = 100)
    private String genre;

    @Column(name = "number_of_pages")
    @Min(value = 1, message = "The number of pages must be at least 1")
    private Integer numberOfPages;

    @Column(name = "number_of_copies", nullable = false)
    @PositiveOrZero(message = "The number of copies must be zero or greater.")
    private Integer numberOfCopies;
}
