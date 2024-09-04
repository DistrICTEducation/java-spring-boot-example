package districted.resource;

import districted.converter.BookDtoInConverter;
import districted.dto.BookDtoIn;
import districted.model.Book;
import districted.service.BookService;
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

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Validated
public class BookResource {
    private final BookService bookService;
    private final BookDtoInConverter bookDtoInConverter;

    @GetMapping(value = "/{id}")
    public ResponseEntity getBookById(@PathVariable Integer id) {
        Book body = this.bookService.getById(id);
        return ResponseEntity.ok(body);
    }

    @PostMapping()
    public ResponseEntity createBook(@RequestBody @Validated BookDtoIn bookDtoIn) {
        Book book = bookDtoInConverter.convert(bookDtoIn);
        Book body = this.bookService.create(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateBook(@PathVariable Integer id, @RequestBody @Validated BookDtoIn bookDtoIn) {
        Book book = bookDtoInConverter.convert(id, bookDtoIn);
        Book body = this.bookService.update(book);
        body.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteBook(@PathVariable Integer id) {
        Book book = this.bookService.getById(id);
        this.bookService.delete(book);
        return  ResponseEntity.noContent().build();
    }
}
