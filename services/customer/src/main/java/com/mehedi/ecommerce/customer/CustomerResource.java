package com.mehedi.ecommerce.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/customers")
public class CustomerResource {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<Page<Customer>> findAll(Pageable pageable) {
        return ResponseEntity.ok(customerService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable String id) {
        return ResponseEntity.ok(customerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CreateCustomerRequest request) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(customerService.create(request)).toUri()).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @Valid @RequestBody UpdateCustomerRequest request) {
        customerService.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
