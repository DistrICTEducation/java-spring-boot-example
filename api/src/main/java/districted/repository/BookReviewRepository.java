package districted.repository;

import districted.model.BookReview;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface BookReviewRepository extends JpaRepository<BookReview, UUID> {
}
