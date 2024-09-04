package districted.converter;

import districted.dto.BookDtoIn;
import districted.model.Book;
import districted.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookDtoInConverter {
    private final BookService bookService;

    public Book convert(BookDtoIn bookDtoIn) {
        return Book.builder()
            .author(bookDtoIn.getAuthor())
            .genre(bookDtoIn.getGenre())
            .isbn(bookDtoIn.getIsbn())
            .isbn13(bookDtoIn.getIsbn13())
            .numberOfCopies(bookDtoIn.getNumberOfCopies())
            .numberOfPages(bookDtoIn.getNumberOfPages())
            .publicationLanguage(bookDtoIn.getPublicationLanguage())
            .publicationYear(bookDtoIn.getPublicationYear())
            .publisher(bookDtoIn.getPublisher())
            .title(bookDtoIn.getTitle())
            .build();
    }

    public Book convert(Integer id, BookDtoIn bookDtoIn) {
        Book book = bookService.getById(id);

        book.setAuthor(bookDtoIn.getAuthor());
        book.setGenre(bookDtoIn.getGenre());
        book.setIsbn(bookDtoIn.getIsbn());
        book.setIsbn13(bookDtoIn.getIsbn13());
        book.setNumberOfCopies(bookDtoIn.getNumberOfCopies());
        book.setNumberOfPages(bookDtoIn.getNumberOfPages());
        book.setPublicationLanguage(bookDtoIn.getPublicationLanguage());
        book.setPublicationYear(bookDtoIn.getPublicationYear());
        book.setPublisher(bookDtoIn.getPublisher());
        book.setTitle(bookDtoIn.getTitle());

        return book;
    }
}
