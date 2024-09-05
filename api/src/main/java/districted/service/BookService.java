package districted.service;

import com.google.common.base.Preconditions;
import districted.model.Book;
import districted.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> getAll() {
        return this.bookRepository.findAll();
    }

    public Book getById(Integer id) {
        return bookRepository.getReferenceById(id);
    }

    @Transactional
    public Book create(Book book) {
        Preconditions.checkArgument(book != null);
        Preconditions.checkArgument(book.getId() == null);

        return this.bookRepository.save(book);
    }

    @Transactional
    public Book update(Book book) {
        Preconditions.checkArgument(book != null);
        Preconditions.checkArgument(book.getId() != null);

        return this.bookRepository.save(book);
    }

    public void delete(Book book) {
        Preconditions.checkArgument(book != null);
        Preconditions.checkArgument(book.getId() != null);
        this.bookRepository.delete(book);
    }
}
