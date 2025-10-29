package com.example.shop.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 컨트롤러에서 받은 요청을 해석하고 비즈니스 규칙을 적용하는 계층.
 * 현재 과제 지침대로 Repository/Entity 미구현 상태이므로
 * 아래 로직은 목업 형태로만 작성되어 있음.
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    // private final ProductRepository productRepository; // (추가 예정)

    public Long createProduct(Map<String, Object> body) {
        // TODO: name, price, stockQuantity 검증 후 저장
        // return productRepository.save(entity).getId();
        return 1L; // 목업 ID
    }

    public List<Map<String, Object>> getProducts() {
        // TODO: productRepository.findAll()
        return Collections.emptyList();
    }

    public Map<String, Object> getProduct(Long productId) {
        // TODO: productRepository.findById(productId)
        return Map.of(
                "id", productId,
                "name", "sample",
                "price", 0,
                "stockQuantity", 0
        );
    }

    public void updateProduct(Long productId, Map<String, Object> body) {
        // TODO: 부분 업데이트 적용 (name/price/stockQuantity)
    }

    public void deleteProduct(Long productId) {
        // TODO: 삭제 처리
    }
}
