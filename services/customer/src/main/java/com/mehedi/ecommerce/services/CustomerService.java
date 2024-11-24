package com.mehedi.ecommerce.services;

import com.mehedi.ecommerce.models.requests.CreateCustomerRequest;
import com.mehedi.ecommerce.models.requests.UpdateCustomerRequest;
import com.mehedi.ecommerce.entities.Customer;
import com.mehedi.ecommerce.exceptions.NotFoundException;
import com.mehedi.ecommerce.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Page<Customer> getAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public Customer findById(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found."));
    }

    public String create(CreateCustomerRequest request) {
        Customer customer = Customer.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();
        customer = customerRepository.save(customer);
        return customer.getId();
    }

    public void update(String id, UpdateCustomerRequest request) {
        Customer customer = findById(id);
        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setEmail(request.email());
        customer.setAddress(request.address());
        customerRepository.save(customer);
    }

    public void delete(String id) {
        Customer customer = findById(id);
        customerRepository.delete(customer);
    }
}
