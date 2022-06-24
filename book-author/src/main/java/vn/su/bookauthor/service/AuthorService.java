package vn.su.bookauthor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.su.bookauthor.entity.Author;
import vn.su.bookauthor.entity.Book;
import vn.su.bookauthor.repository.AuthorRepository;
import vn.su.bookauthor.validate.NotFoundException;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    public Author addAuthor(Author authorRequest) {

        if (authorRepository.findById(authorRequest.getId()).isEmpty()) {
            return authorRepository.save(authorRequest);
        } else {
            throw new NotFoundException("Đã có tác giả này");
        }

    }

    public Author findAuthorById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            return author.get();
        } else {
            throw new NotFoundException("Không tìm thấy tác giả hợp lệ");
        }
    }

    public String deleteAuthor(Long id) {
        Optional<Author> authorDiscarded = authorRepository.findById(id);
        if (authorDiscarded.isPresent()) {
            authorRepository.delete(authorDiscarded.get());
            return "Đã xoá thành công tác giả " + authorDiscarded.get().getName();
        } else {
            throw new NotFoundException("Không tìm thấy tác giả để xoá");
        }

    }

    public Author updateAuthor(Author authorUpdate) {
        Optional<Author> existAuthor = authorRepository.findById(authorUpdate.getId());
        if (existAuthor.isPresent()) {
            authorUpdate.setName(existAuthor.get().getName());
            return authorRepository.save(authorUpdate);
        } else {
            throw new NotFoundException("Không tìm thấy tác giả ");
        }

    }

    public List<Book> getBookByAuthorId(Long id) {  
        return authorRepository.findById(id).get().getListBook();
    }

}