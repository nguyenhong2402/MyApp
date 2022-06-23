package vn.su.bookauthor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.su.bookauthor.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    
}
