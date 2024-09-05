package districted.resource;

import districted.converter.BookLoanDtoInConverter;
import districted.dto.BookLoanDtoIn;
import districted.model.Book;
import districted.model.BookLoan;
import districted.service.BookLoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/book-loans")
@RequiredArgsConstructor
@Validated
public class BookLoanResource {
    private final BookLoanService bookLoanService;
    private final BookLoanDtoInConverter bookLoanDtoInConverter;

    @GetMapping
    public ResponseEntity<List<BookLoan>> getBookLoans() {
        return ResponseEntity.ok(this.bookLoanService.getAll());
    }

    @GetMapping(value = "/book-loans/{bookLoanId}")
    public ResponseEntity<BookLoan> getBookLoanById(@PathVariable UUID bookLoanId) {
        BookLoan body = bookLoanService.getById(bookLoanId);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/book-loans")
    public ResponseEntity<BookLoan> createBookLoan(@RequestBody @Validated BookLoanDtoIn bookLoanDtoIn) {
        BookLoan bookLoan = bookLoanDtoInConverter.convert(bookLoanDtoIn);
        BookLoan body = bookLoanService.create(bookLoan);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @PutMapping("/book-loans/{bookLoanId}")
    public ResponseEntity<BookLoan> updateBookLoan(@PathVariable UUID bookLoanId, @RequestBody @Validated BookLoanDtoIn bookLoanDtoIn) {
        BookLoan bookLoan = bookLoanDtoInConverter.convert(bookLoanId, bookLoanDtoIn);
        BookLoan body = bookLoanService.update(bookLoan);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @DeleteMapping(value = "/{bookLoanId}")
    public ResponseEntity<Void> deleteBookLoan(@PathVariable UUID bookLoanId) {
        BookLoan bookLoan = bookLoanService.getById(bookLoanId);
        bookLoanService.delete(bookLoan);
        return ResponseEntity.noContent().build();
    }
}
