package districted.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@With
@EqualsAndHashCode
public class BookDtoIn {
    private String isbn;

    private String isbn13;

    private String title;

    private String author;

    private String publisher;

    private Integer publicationYear;

    private String publicationLanguage;

    private String genre;

    private Integer numberOfPages;

    private Integer numberOfCopies;
}
