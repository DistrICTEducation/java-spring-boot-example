package districted.repository;

import districted.model.BookLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface BookLoanRepository extends JpaRepository<BookLoan, UUID> {
}
