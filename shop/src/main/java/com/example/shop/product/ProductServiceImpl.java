package com.example.shop.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Transactional
    @Override
    public Long createProduct(Product product) {
        productRepository.save(product);
        return product.getId();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Product getProductById(Long id) {
        Product product = productRepository.findById(id);
        if (product == null)
            throw new RuntimeException("상품을 찾을 수 없습니다.");
        return product;
    }

    @Transactional
    @Override
    public void updateProduct(Long id, Product reqProduct) {
        Product product = productRepository.findById(id);
        if (product == null)
            throw new RuntimeException("상품을 찾을 수 없습니다.");

        product.updateInfo(
                reqProduct.getName(),
                reqProduct.getPrice(),
                reqProduct.getStock()
        );
    }

    @Transactional
    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}