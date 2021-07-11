package com.compassouol.productsms.repository;

import com.compassouol.productsms.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
