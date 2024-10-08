package districted.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import districted.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
