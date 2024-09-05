package districted.converter;

import com.google.common.base.Preconditions;
import districted.dto.BookReservationDtoIn;
import districted.model.Book;
import districted.model.BookReservation;
import districted.model.Member;
import districted.service.BookReservationService;
import districted.service.BookService;
import districted.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BookReservationDtoInConverter {
    private final MemberService memberService;
    private final BookService bookService;
    private final BookReservationService bookReservationService;

    public BookReservation convert(BookReservationDtoIn bookReservationDtoIn) {
        Book book = bookService.getById(bookReservationDtoIn.getBookId());
        Member member = memberService.getById(bookReservationDtoIn.getMemberId());

        return BookReservation.builder()
            .member(member)
            .book(book)
            .reservationDate(bookReservationDtoIn.getReservationDate())
            .pickupDate(bookReservationDtoIn.getPickupDate())
            .status(bookReservationDtoIn.getStatus())
            .build();
    }

    public BookReservation convert(UUID id, BookReservationDtoIn bookReservationDtoIn) {
        BookReservation bookReservation = bookReservationService.getById(id);
        Preconditions.checkArgument(bookReservation != null, "No book reservation with given id.");
        // Book and Member references can't change after creation
        Preconditions.checkArgument(! bookReservation.getBook().getId().equals(bookReservationDtoIn.getBookId()), "Books of existing reservation and given reservation don't match.");
        Preconditions.checkArgument(! bookReservation.getMember().getId().equals(bookReservationDtoIn.getMemberId()), "Members of existing reservation and given reservation don't match.");
        // Verify that both references exist
        Book book = bookService.getById(bookReservationDtoIn.getBookId());
        Preconditions.checkArgument(book != null, "No book with given book id.");
        Member member = memberService.getById(bookReservationDtoIn.getMemberId());
        Preconditions.checkArgument(member != null, "No member with given member id.");

        bookReservation.setReservationDate(bookReservationDtoIn.getReservationDate());
        bookReservation.setStatus(bookReservationDtoIn.getStatus());
        bookReservation.setPickupDate(bookReservationDtoIn.getPickupDate());

        return bookReservation;
    }
}
