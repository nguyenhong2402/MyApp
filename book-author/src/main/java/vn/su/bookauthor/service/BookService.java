package vn.su.bookauthor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.su.bookauthor.entity.Book;
import vn.su.bookauthor.repository.BookRepository;
import vn.su.bookauthor.validate.NotFoundException;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book addBook(Book bookRequest) {
        
        if (bookRepository.findById(bookRequest.getId()).isEmpty()) {
          return bookRepository.save(bookRequest);
      } else {
          throw new NotFoundException("Đã có quyển sách này, không thể thêm vào kho lưu trữ");
        }

      
    }

    public Book findBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        }else {
            throw new NotFoundException("Không tìm thấy sách hợp lệ");
        }
    }

    public String deleteBook(Long id) {
        Optional<Book> bookDiscarded = bookRepository.findById(id);
        if (bookDiscarded.isPresent()) {
            bookRepository.delete(bookDiscarded.get());
            return "Đã xoá thành công quyển " + bookDiscarded.get().getName();
        } else {
            throw new NotFoundException("Không tìm thấy sách để xoá");
        }
        
        
    }

    public Book updateBook(Book bookUpdate) {
        Optional<Book> existBook = bookRepository.findById(bookUpdate.getId());
        if (existBook.isPresent()) {
            bookUpdate.setAuthor(existBook.get().getAuthor());
          return bookRepository.save(bookUpdate);
      } else {
          throw new NotFoundException("Không tìm thấy sách trong kho lưu trữ");
        }

        
    }
    
}
