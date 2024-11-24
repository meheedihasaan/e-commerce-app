package com.mehedi.ecommerce.repositories;

import com.mehedi.ecommerce.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
