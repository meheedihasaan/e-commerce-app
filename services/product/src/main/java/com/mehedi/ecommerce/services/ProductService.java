package com.mehedi.ecommerce.services;

import com.mehedi.ecommerce.entities.Category;
import com.mehedi.ecommerce.exceptions.NotFoundException;
import com.mehedi.ecommerce.models.requests.CreateProductRequest;
import com.mehedi.ecommerce.models.requests.UpdateProductRequest;
import com.mehedi.ecommerce.entities.Product;
import com.mehedi.ecommerce.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final CategoryService categoryService;
    private final ProductRepository productRepository;

    public Page<Product> getAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product findById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Product not found."));
    }

    public UUID create(CreateProductRequest request) {
        Category category = categoryService.findById(request.categoryId());

        Product product = Product.builder()
                .name(request.name())
                .description(request.description())
                .quantity(request.quantity())
                .price(request.price())
                .category(category)
                .build();

        product = productRepository.save(product);
        return product.getId();
    }

    public void update(UUID id, UpdateProductRequest request) {
        Product product = findById(id);
        Category category = categoryService.findById(request.categoryId());

        product.setName(request.name());
        product.setDescription(request.description());
        product.setQuantity(request.quantity());
        product.setPrice(request.price());
        product.setCategory(category);

        productRepository.save(product);
    }

    public void delete(UUID id) {
        Product product = findById(id);
        productRepository.delete(product);
    }
}
