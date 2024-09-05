package districted.resource;

import districted.converter.BookReservationDtoInConverter;
import districted.dto.BookReservationDtoIn;
import districted.model.BookLoan;
import districted.model.BookReservation;
import districted.service.BookReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/book-reservations")
@RequiredArgsConstructor
@Validated
public class BookReservationResource {
    private final BookReservationService bookReservationService;
    private final BookReservationDtoInConverter bookReservationDtoInConverter;

    @GetMapping
    public ResponseEntity<List<BookReservation>> getBookLoans() {
        return ResponseEntity.ok(this.bookReservationService.getAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookReservation> getBookReservationById(@PathVariable UUID id) {
        BookReservation body = bookReservationService.getById(id);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/{id}/book-reservation")
    public ResponseEntity<BookReservation> createBookReservation(@RequestBody @Validated BookReservationDtoIn bookReservationDtoIn) {
        BookReservation bookReservation = bookReservationDtoInConverter.convert(bookReservationDtoIn);
        BookReservation body = bookReservationService.create(bookReservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @PostMapping("/{id}/book-reservation/{reservationId}")
    public ResponseEntity<BookReservation> updateBookReservation(@PathVariable UUID reservationId, @RequestBody @Validated BookReservationDtoIn bookReservationDtoIn) {
        BookReservation bookReservation = bookReservationDtoInConverter.convert(reservationId, bookReservationDtoIn);
        BookReservation body = bookReservationService.update(bookReservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @DeleteMapping(value = "/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable UUID reservationId) {
        BookReservation bookReservation = bookReservationService.getById(reservationId);
        bookReservationService.delete(bookReservation);
        return ResponseEntity.noContent().build();
    }
}
