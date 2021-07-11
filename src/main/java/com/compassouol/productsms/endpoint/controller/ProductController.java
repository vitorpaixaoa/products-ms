package com.compassouol.productsms.endpoint.controller;

import com.compassouol.productsms.endpoint.service.ProductService;
import com.compassouol.productsms.exception.BadRequestException;
import com.compassouol.productsms.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping("products")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {
    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<Iterable<Product>> list() {
        return new ResponseEntity<>(productService.list(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Product> newProduct(@Valid @RequestBody Product product, BindingResult result) {
        if (result.hasErrors()) {
            throw new BadRequestException(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        }
        return ResponseEntity.ok(productService.newProduct(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody @Valid Product product,
                                                 BindingResult result) {
        if (result.hasErrors()) {
            throw new BadRequestException(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        }
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> removeProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.deleteUser(id));
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Product>> searchProduct(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) Double min_price,
            @RequestParam(required = false) Double max_price) {
        return ResponseEntity.ok(productService.search(q, min_price, max_price));
    }
}
