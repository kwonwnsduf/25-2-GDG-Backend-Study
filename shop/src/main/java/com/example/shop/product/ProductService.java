package com.example.shop.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    /** 상품 등록 */
    @Transactional
    public Long createProduct(Product request) {
        // request는 컨트롤러에서 받은 바디(임시 객체)이므로
        // 실제로 저장할 새 엔티티를 따로 만들어 줌
        Product product = new Product(
                request.getName(),
                request.getPrice(),
                request.getStock(),
                request.getDescription(),
                request.getStatus()
        );
        productRepository.save(product);
        return product.getId();
    }

    /** 전체 상품 조회 */
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /** 단일 상품 조회 */
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new RuntimeException("상품을 찾을 수 없습니다. id=" + id);
        }
        return product;
    }

    /** 상품 수정 */
    @Transactional
    public void updateProduct(Long id, Product request) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new RuntimeException("상품을 찾을 수 없습니다. id=" + id);
        }

        product.update(
                request.getName(),
                request.getPrice(),
                request.getStock(),
                request.getDescription(),
                request.getStatus()
        );
        // 엔티티가 영속 상태라서 별도 save() 호출 없이 변경 감지로 업데이트됨
    }

    /** 상품 삭제 */
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new RuntimeException("상품을 찾을 수 없습니다. id=" + id);
        }
        productRepository.deleteById(id);
    }
}

