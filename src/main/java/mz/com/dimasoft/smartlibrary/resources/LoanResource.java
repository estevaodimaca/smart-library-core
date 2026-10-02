package mz.com.dimasoft.smartlibrary.resources;

import mz.com.dimasoft.smartlibrary.dto.LoanDTO;
import mz.com.dimasoft.smartlibrary.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/loans")
public class LoanResource {

    @Autowired
    private LoanService service;

    @PostMapping
    public ResponseEntity<LoanDTO> create(@RequestParam UUID userId, @RequestParam UUID bookId) {
        LoanDTO dto = service.createLoan(userId, bookId);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LoanDTO>> findByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(service.findByUserId(userId));
    }
}
