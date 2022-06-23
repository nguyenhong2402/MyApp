package vn.su.bookauthor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.su.bookauthor.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
