package districted.converter;

import com.google.common.base.Preconditions;
import districted.dto.BookLoanDtoIn;
import districted.model.Book;
import districted.model.BookLoan;
import districted.model.Member;
import districted.service.BookLoanService;
import districted.service.BookService;
import districted.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BookLoanDtoInConverter {
    private final MemberService memberService;
    private final BookService bookService;
    private final BookLoanService bookLoanService;

    public BookLoan convert(BookLoanDtoIn bookLoanDtoIn) {
        Book book = bookService.getById(bookLoanDtoIn.getBookId());
        Preconditions.checkArgument(book != null, "No book with given book id.");
        Member member = memberService.getById(bookLoanDtoIn.getMemberId());
        Preconditions.checkArgument(member != null, "No member with given member id.");

        return BookLoan.builder()
            .member(member)
            .book(book)
            .checkoutDate(bookLoanDtoIn.getCheckoutDate())
            .dueDate(bookLoanDtoIn.getDueDate())
            .fineAmount(bookLoanDtoIn.getFineAmount())
            .fineStatus(bookLoanDtoIn.getFineStatus())
            .fineStatusDate(bookLoanDtoIn.getFineStatusDate())
            .returnDate(bookLoanDtoIn.getReturnDate())
            .build();
    }

    public BookLoan convert(UUID id, BookLoanDtoIn bookLoanDtoIn) {
        BookLoan bookLoan = bookLoanService.getById(id);
        Preconditions.checkArgument(bookLoan != null, "No book loan with given id.");
        // Book and Member references can't change after creation
        Preconditions.checkArgument(! bookLoan.getBook().getId().equals(bookLoanDtoIn.getBookId()), "Books of existing loan and given loan don't match.");
        Preconditions.checkArgument(! bookLoan.getMember().getId().equals(bookLoanDtoIn.getMemberId()), "Members of existing loan and given loan don't match.");
        // Verify that both references exist
        Book book = bookService.getById(bookLoanDtoIn.getBookId());
        Preconditions.checkArgument(book != null, "No book with given book id.");
        Member member = memberService.getById(bookLoanDtoIn.getMemberId());
        Preconditions.checkArgument(member != null, "No member with given member id.");

        bookLoan.setCheckoutDate(bookLoanDtoIn.getCheckoutDate());
        bookLoan.setDueDate(bookLoanDtoIn.getDueDate());
        bookLoan.setFineAmount(bookLoanDtoIn.getFineAmount());
        bookLoan.setFineStatus(bookLoanDtoIn.getFineStatus());
        bookLoan.setFineStatusDate(bookLoanDtoIn.getFineStatusDate());
        bookLoan.setReturnDate(bookLoanDtoIn.getReturnDate());

        return bookLoan;
    }
}
