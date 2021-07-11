package com.compassouol.productsms.repository;

import com.compassouol.productsms.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.List;

@AllArgsConstructor
@Repository
public class ProductCustomRepository {


    private final EntityManager em;

    public List<Product> search(@Nullable String text, @Nullable Double min_value, @Nullable Double max_value) {

        String query = " SELECT P FROM Product AS P ";
        String condition = " where ";


        if (text != null) {
            query += condition + " (P.name LIKE :text OR P.description LIKE :text) ";
            condition = " and ";
        }

        if (min_value != null) {
            query += condition + " P.price >= :min_value ";
            condition = " and ";
        }
        if (max_value != null) {
            query += condition + " P.price <= :max_value";
        }
        var q = em.createQuery(query, Product.class);

        if (text != null) {
            q.setParameter("text", "%"+ text+ "%");
        }

        if (min_value != null) {
            q.setParameter("min_value", min_value);
        }
        if (max_value != null) {
            q.setParameter("max_value", max_value);
        }

        System.out.println(query);
        return q.getResultList();
    }
}
