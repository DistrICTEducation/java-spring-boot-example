package districted.service;

import com.google.common.base.Preconditions;
import districted.model.Book;
import districted.model.BookLoan;
import districted.repository.BookLoanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookLoanService {
    private final BookLoanRepository bookLoanRepository;

    public List<BookLoan> getAll() {
        return this.bookLoanRepository.findAll();
    }
    public BookLoan getById(UUID id) {
        return bookLoanRepository.getReferenceById(id);
    }

    @Transactional
    public BookLoan create(BookLoan bookLoan) {
        Preconditions.checkArgument(bookLoan != null);
        Preconditions.checkArgument(bookLoan.getId() == null);

        return this.bookLoanRepository.save(bookLoan);
    }

    @Transactional
    public BookLoan update(BookLoan bookLoan) {
        Preconditions.checkArgument(bookLoan != null);
        Preconditions.checkArgument(bookLoan.getId() != null);

        return this.bookLoanRepository.save(bookLoan);
    }

    public void delete(BookLoan bookLoan) {
        Preconditions.checkArgument(bookLoan != null);
        Preconditions.checkArgument(bookLoan.getId() != null);

        this.bookLoanRepository.delete(bookLoan);
    }
}
