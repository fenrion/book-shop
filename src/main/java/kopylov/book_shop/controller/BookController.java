package kopylov.book_shop.controller;

import kopylov.book_shop.domain.dto.BookDto;
import kopylov.book_shop.domain.model.Book;
import kopylov.book_shop.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/books")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping()
    public String listBooks(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "brand", required = false) String brand,
            @RequestParam(name = "year", required = false) Integer year,
            Model model
    ) {


        List<Book> allBooks = bookService.getFilteredBooks(title, brand, year);
        List<Book> booksForPage = bookService.getBooksForPage(allBooks, page, size);
        int totalPages = bookService.getTotalPages(allBooks, size);

        model.addAttribute("books", booksForPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", allBooks.size());
        model.addAttribute("pageSize", size);
        model.addAttribute("titleFilter", title);
        model.addAttribute("brandFilter", brand);
        model.addAttribute("yearFilter", year);

        return "books";
    }

    @PostMapping
    public String createBook(@ModelAttribute BookDto bookDto) {
        bookService.saveBook(bookDto);
        return "redirect:/api/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return "redirect:/api/books";
    }

    @GetMapping("/edit/{id}")
    public String getBook(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        return "edit";
    }

    @PutMapping("/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute BookDto bookDto) {
        bookService.updateBook(id, bookDto);
        return "redirect:/api/books";
    }
}
