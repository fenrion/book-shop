package kopylov.book_shop.service.impl;

import kopylov.book_shop.domain.dto.BookDto;
import kopylov.book_shop.domain.mapper.BookMapper;
import kopylov.book_shop.domain.model.Book;
import kopylov.book_shop.repository.BookRepository;
import kopylov.book_shop.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    BookRepository bookRepository;
    BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book saveBook(BookDto bookDto) {
        Book book = bookMapper.BookDtoToBook(bookDto);
        return bookRepository.save(book);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    @Override
    public Book updateBook(Long id, BookDto updBook) {
        Book book = bookMapper.BookDtoToBook(updBook);
        book.setId(id);
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> getBooksForPage(List<Book> allBooks, int page, int size) {
        int startIndex = page * size;
        int endIndex = Math.min(startIndex + size, allBooks.size());
        if (startIndex >= allBooks.size()) {
            return List.of();
        }

        return allBooks.subList(startIndex, endIndex);
    }

    @Override
    public int getTotalPages(List<Book> allBooks, int size) {
        return (int) Math.ceil((double) allBooks.size() / size);
    }

    @Override
    public List<Book> getFilteredBooks(String title, String brand, Integer year) {
        List<Book> allBooks = getAllBooks();

        return allBooks.stream()
                .filter(book -> title == null || book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .filter(book -> brand == null || book.getBrand().toLowerCase().contains(brand.toLowerCase()))
                .filter(book -> year == null || book.getYear().equals(year))
                .collect(Collectors.toList());
    }
}
