package districted.resource;

import districted.converter.BookReviewDtoInConverter;
import districted.dto.BookReviewDtoIn;
import districted.model.BookReservation;
import districted.model.BookReview;
import districted.service.BookReviewService;
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
@RequestMapping("/book-reviews")
@RequiredArgsConstructor
@Validated
public class BookReviewResource {
    private final BookReviewService bookReviewService;
    private final BookReviewDtoInConverter bookReviewDtoInConverter;

    @GetMapping
    public ResponseEntity<List<BookReview>> getBookLoans() {
        return ResponseEntity.ok(this.bookReviewService.getAll());
    }

    @GetMapping(value = "/{reviewId}")
    public ResponseEntity<BookReview> getBookReviewById(@PathVariable UUID reviewId) {
        BookReview body = bookReviewService.getById(reviewId);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/book-reviews")
    public ResponseEntity<BookReview> createBookReview(@RequestBody @Validated BookReviewDtoIn bookReviewDtoIn) {
        BookReview bookReview = bookReviewDtoInConverter.convert(bookReviewDtoIn);
        BookReview body = bookReviewService.create(bookReview);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @PostMapping("/book-reviews/{bookReviewId}")
    public ResponseEntity<BookReview> updateBookReview(@PathVariable UUID bookReviewId, @RequestBody @Validated BookReviewDtoIn bookReviewDtoIn) {
        BookReview bookReview = bookReviewDtoInConverter.convert(bookReviewId, bookReviewDtoIn);
        BookReview body = bookReviewService.update(bookReview);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @DeleteMapping(value = "/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable UUID reviewId) {
        BookReview bookReview = this.bookReviewService.getById(reviewId);
        this.bookReviewService.delete(bookReview);
        return ResponseEntity.noContent().build();
    }
}
