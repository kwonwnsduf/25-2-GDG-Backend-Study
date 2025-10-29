package com.example.shop.product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

    @PersistenceContext
    private EntityManager em;

    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    public List<Product> findAll() {
        return em.createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
    }

    /** 예: 고유한 상품코드(code)로 조회 (필드명에 맞게 사용) */
    public Product findByCode(String code) {
        List<Product> result = em.createQuery(
                        "SELECT p FROM Product p WHERE p.code = :code",
                        Product.class)
                .setParameter("code", code)
                .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    public void save(Product product) {
        em.persist(product);
    }

    public void deleteById(Long id) {
        Product product = em.find(Product.class, id);
        if (product != null) em.remove(product);
    }
}
