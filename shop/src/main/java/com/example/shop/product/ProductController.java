package com.example.shop.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    // 상품 정보 등록
    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody Map<String, Object> body) {
        // ex) name, price, stockQuantity 예상
        Long productId = productService.createProduct(body);
        return ResponseEntity.created(URI.create("/products/" + productId)).build();
    }

    // 상품 목록 조회
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getProducts() {
        // 현재는 저장소가 없으므로 빈 리스트/목업 반환
        return ResponseEntity.ok(productService.getProducts());
    }

    // 개별 상품 상세 조회
    @GetMapping("/{productId}")
    public ResponseEntity<Map<String, Object>> getProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    // 상품 정보 수정(부분 업데이트)
    @PatchMapping("/{productId}")
    public ResponseEntity<Void> updateProduct(
            @PathVariable Long productId,
            @RequestBody Map<String, Object> body) {
        productService.updateProduct(productId, body);
        return ResponseEntity.ok().build();
    }

    // 상품 삭제
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}