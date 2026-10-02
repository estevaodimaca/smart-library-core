package mz.com.dimasoft.smartlibrary.resources;

import mz.com.dimasoft.smartlibrary.domain.entities.Book;
import mz.com.dimasoft.smartlibrary.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookResource {

    @Autowired
    private BookRepository repository;

    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        // Retorna todos os livros reais do banco
        return ResponseEntity.ok(repository.findAll());
    }
}
