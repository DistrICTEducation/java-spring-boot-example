package districted.service;

import districted.model.Book;
import districted.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book getById(Integer id) {
        return bookRepository.getReferenceById(id);
    }

    @Transactional
    public Book create(Book book) {
        return this.bookRepository.save(book);
    }

    @Transactional
    public Book update(Book book) {
        return this.bookRepository.save(book);
    }

    public void delete(Book book) {
        this.bookRepository.delete(book);
    }
}
