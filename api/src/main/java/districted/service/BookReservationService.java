package districted.service;

import com.google.common.base.Preconditions;
import districted.model.BookReservation;
import districted.repository.BookReservationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookReservationService {
    private final BookReservationRepository bookReservationRepository;

    public List<BookReservation> getAll() {
        return this.bookReservationRepository.findAll();
    }

    public BookReservation getById(UUID id) {
        return bookReservationRepository.getReferenceById(id);
    }

    @Transactional
    public BookReservation create(BookReservation bookReservation) {
        Preconditions.checkArgument(bookReservation != null);
        Preconditions.checkArgument(bookReservation.getId() == null);

        return this.bookReservationRepository.save(bookReservation);
    }

    @Transactional
    public BookReservation update(BookReservation bookReservation) {
        Preconditions.checkArgument(bookReservation != null);
        Preconditions.checkArgument(bookReservation.getId() != null);

        return this.bookReservationRepository.save(bookReservation);
    }

    public void delete(BookReservation bookReservation) {
        Preconditions.checkArgument(bookReservation != null);
        Preconditions.checkArgument(bookReservation.getId() != null);

        this.bookReservationRepository.delete(bookReservation);
    }
}
