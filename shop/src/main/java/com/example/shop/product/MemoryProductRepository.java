package com.example.shop.product;

import com.example.shop.product.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Qualifier("subProductRepository")
public class MemoryProductRepository implements ProductRepository {

    private final Map<Long, Product> store = new HashMap<>();
    private long sequence = 0L;

    @Override
    public Product findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void save(Product product) {
        if (product.getId() == null) {
            sequence++;
            product.setId(sequence);
        }
        store.put(product.getId(), product);
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}
