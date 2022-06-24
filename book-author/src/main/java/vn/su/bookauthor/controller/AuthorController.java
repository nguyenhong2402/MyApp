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

import vn.su.bookauthor.entity.Author;
import vn.su.bookauthor.entity.Book;
import vn.su.bookauthor.service.AuthorService;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<Author>> showListAuthorAll() {
        return ResponseEntity.ok(authorService.getAll());
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<List<Book>> showListBookByAuthorId(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getBookByAuthorId(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Author> addNewAuthor(@RequestBody Author authorRequest) {

        return ResponseEntity.status(201).body(authorService.addAuthor(authorRequest));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findAuthorById(@PathVariable Long id) {

        return ResponseEntity.status(200).body(authorService.findAuthorById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Author> updateAuthorById(@RequestBody Author authorUpdate) {

        return ResponseEntity.status(201).body(authorService.updateAuthor(authorUpdate));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAuthorById(@PathVariable Long id) {

        return ResponseEntity.status(204).body(authorService.deleteAuthor(id));
    }
}