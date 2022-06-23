package vn.su.bookauthor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.su.bookauthor.entity.Book;
import vn.su.bookauthor.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
    
    @Autowired
    BookService bookService;
    @GetMapping
    public ResponseEntity<List<Book>> showListBookAll() {

        return ResponseEntity.ok(bookService.getAll());
    }
    
    @PostMapping("/add")
    public ResponseEntity<Book> addNewBook(@RequestBody Book bookRequest) {

        return ResponseEntity.status(201).body(bookService.addBook(bookRequest));

    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable Long id) {

        return ResponseEntity.status(200).body(bookService.findBookById(id));
    }
    
    @PutMapping("/update")
    public ResponseEntity<Book> updateBookById(@RequestBody Book bookUpdate ) {

        return ResponseEntity.status(201).body(bookService.updateBook(bookUpdate));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable Long id) {
        
        return ResponseEntity.status(204).body(bookService.deleteBook(id));
    }
}
