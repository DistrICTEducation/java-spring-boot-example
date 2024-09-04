package districted.repository;

import districted.model.BookReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface BookReservationRepository extends JpaRepository<BookReservation, UUID> {
}
