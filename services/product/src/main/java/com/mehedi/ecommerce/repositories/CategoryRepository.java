package com.mehedi.ecommerce.repositories;

import com.mehedi.ecommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
