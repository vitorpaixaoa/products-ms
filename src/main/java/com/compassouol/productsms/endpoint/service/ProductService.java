package com.compassouol.productsms.endpoint.service;
import com.compassouol.productsms.exception.NotFoundException;
import com.compassouol.productsms.model.Product;
import com.compassouol.productsms.repository.ProductCustomRepository;
import com.compassouol.productsms.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductCustomRepository productCustomRepository;

    public Iterable<Product> list() {
        log.info("Buscando todos os produtos");
        return productRepository.findAll();
    }

    public Product newProduct(Product product) {
        log.info("Criando um novo produto");
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        log.info("Buscando o produto pelo id: " + id);
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No such product found with id: " + id));
    }

    public Product updateProduct(Long id, Product productDetails) {
        log.info("Atualizando o produto pelo id: " + id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No such product found with id: " + id));

        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setDescription(productDetails.getDescription());

        return productRepository.save(product);
    }

    public Map<String, Boolean> deleteUser(Long id) {
        log.info("Removendo o produto pelo id: " + id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No such product found with id: " + id));
        productRepository.delete(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    public Iterable<Product> search(@Nullable String text, @Nullable Double min_price, @Nullable Double max_price) {
        return productCustomRepository.search(text, min_price, max_price);
    }

}
