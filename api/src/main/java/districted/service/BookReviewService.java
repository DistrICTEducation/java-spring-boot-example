package districted.service;

import com.google.common.base.Preconditions;
import districted.model.BookReservation;
import districted.model.BookReview;
import districted.repository.BookReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookReviewService {
    private final BookReviewRepository bookReviewRepository;

    public List<BookReview> getAll() {
        return this.bookReviewRepository.findAll();
    }

    public BookReview getById(UUID id) {
        return bookReviewRepository.getReferenceById(id);
    }

    @Transactional
    public BookReview create(BookReview bookReview) {
        Preconditions.checkArgument(bookReview != null);
        Preconditions.checkArgument(bookReview.getId() == null);

        return this.bookReviewRepository.save(bookReview);
    }

    @Transactional
    public BookReview update(BookReview bookReview) {
        Preconditions.checkArgument(bookReview != null);
        Preconditions.checkArgument(bookReview.getId() != null);

        return this.bookReviewRepository.save(bookReview);
    }

    public void delete(BookReview bookReview) {
        Preconditions.checkArgument(bookReview != null);
        Preconditions.checkArgument(bookReview.getId() != null);

        this.bookReviewRepository.delete(bookReview);
    }
}
