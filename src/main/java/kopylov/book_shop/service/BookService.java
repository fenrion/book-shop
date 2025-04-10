package kopylov.book_shop.service;

import kopylov.book_shop.domain.dto.BookDto;
import kopylov.book_shop.domain.model.Book;


import java.util.List;

public interface BookService {

    public List<Book> getAllBooks();
    public Book findById(Long id);
    public Book saveBook(BookDto bookDto);
    public Book updateBook(Long id, BookDto book);
    public void deleteBook(Long id);
    public List<Book> getBooksForPage(List<Book> allBooks, int page, int size);
    public int getTotalPages(List<Book> allBooks, int size);
    List<Book> getFilteredBooks(String title, String brand, Integer year);

}
