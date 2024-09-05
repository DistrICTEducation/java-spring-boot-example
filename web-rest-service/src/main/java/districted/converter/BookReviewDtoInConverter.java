package districted.converter;

import com.google.common.base.Preconditions;
import districted.dto.BookReviewDtoIn;
import districted.model.Book;
import districted.model.BookReview;
import districted.model.Member;
import districted.service.BookReviewService;
import districted.service.BookService;
import districted.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BookReviewDtoInConverter {
    private final MemberService memberService;
    private final BookService bookService;
    private final BookReviewService bookReviewService;

    public BookReview convert(BookReviewDtoIn bookReviewDtoIn) {
        Book book = bookService.getById(bookReviewDtoIn.getBookId());
        Preconditions.checkArgument(book != null, "No book with given id.");

        Member member = memberService.getById(bookReviewDtoIn.getMemberId());
        Preconditions.checkArgument(member != null, "No member with given id.");

        return BookReview.builder()
            .member(member)
            .book(book)
            .rating(bookReviewDtoIn.getRating())
            .build();
    }

    public BookReview convert(UUID id, BookReviewDtoIn bookReviewDtoIn) {
        BookReview bookReview = bookReviewService.getById(id);
        Preconditions.checkArgument(bookReview != null, "No book review with given id.");
        // Book and Member references can't change after creation
        Preconditions.checkArgument(! bookReview.getBook().getId().equals(bookReviewDtoIn.getBookId()), "Books of existing review and given review don't match.");
        Preconditions.checkArgument(! bookReview.getMember().getId().equals(bookReviewDtoIn.getMemberId()), "Members of existing review and given review don't match.");
        // Verify that both references exist
        Book book = bookService.getById(bookReviewDtoIn.getBookId());
        Preconditions.checkArgument(book != null, "No book with given book id.");
        Member member = memberService.getById(bookReviewDtoIn.getMemberId());
        Preconditions.checkArgument(member != null, "No member with given member id.");

        bookReview.setRating(bookReviewDtoIn.getRating());

        return bookReview;
    }
}
