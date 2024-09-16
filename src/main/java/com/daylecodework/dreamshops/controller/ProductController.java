package com.daylecodework.dreamshops.controller;

import com.daylecodework.dreamshops.exceptions.ResourceNotFoundException;
import com.daylecodework.dreamshops.model.Product;
import com.daylecodework.dreamshops.request.AddProductRequest;
import com.daylecodework.dreamshops.request.ProductUpdateRequest;
import com.daylecodework.dreamshops.response.ApiResponse;
import com.daylecodework.dreamshops.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {
    private final IProductService productService;

    // List<Product> getAllProducts();
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(new ApiResponse("success", products));
    }

    // Product getProductById(Long id);
    @GetMapping("/product/{productId}/product")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId) {
        try {
            Product products = productService.getProductById(productId);
            return ResponseEntity.ok(new ApiResponse("success", products));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(ex.getMessage(), null));
        }
    }

    // Product addProduct (AddProductRequest request);
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product) {
        try {
            Product theProduct = productService.addProduct(product);
            return ResponseEntity.ok(new ApiResponse("success", theProduct));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(ex.getMessage(), INTERNAL_SERVER_ERROR));
        }
    }

    // Product updateProduct(ProductUpdateRequest product, Long productId);
    @PutMapping("/product/{productId}/update")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductUpdateRequest product, @PathVariable Long productId) {
        try {
            Product theProduct = productService.updateProduct(product, productId);
            return ResponseEntity.ok(new ApiResponse("updated product success", theProduct));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(ex.getMessage(), NOT_FOUND));
        }
    }

    // void deleteProductById(Long id);
    @DeleteMapping("/product/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProductById(productId);
            return ResponseEntity.ok(new ApiResponse("delete product success", productId));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(ex.getMessage(), NOT_FOUND));
        }
    }

    // List<Product> getProductByBrandAndName(String brand, String name);
    @GetMapping("/products/by-branch-and-name")
    public ResponseEntity<ApiResponse> getProductByBranchAndName(@RequestParam String productBrand, @RequestParam String productName) {
        try {
            List<Product> products = productService.getProductByBrandAndName(productBrand, productName);
            if (products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("no product found with name and brand", null));
            }

            return ResponseEntity.ok(new ApiResponse("success", products));
        } catch (Exception ex) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(ex.getMessage(), INTERNAL_SERVER_ERROR));
        }
    }

    // List<Product> getProductsByCategoryAndBrand(String category, String brand);
    @GetMapping("/products/by-branch-and-category")
    public ResponseEntity<ApiResponse> getProductByCategoryAndName(@RequestParam String category, @RequestParam String productBrand) {
        try {
            List<Product> products = productService.getProductsByCategoryAndBrand(category, productBrand);
            if (products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("no product found with category and brand", null));
            }

            return ResponseEntity.ok(new ApiResponse("success", products));
        } catch (Exception ex) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(ex.getMessage(), INTERNAL_SERVER_ERROR));
        }
    }

    // List<Product> getProductByName(String name);
    @GetMapping("/products/{productName}/products")
    public ResponseEntity<ApiResponse> getProductByName(@PathVariable String productName) {
        try {
            List<Product> products = productService.getProductByName(productName);
            if (products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("no product found", null));
            }

            return ResponseEntity.ok(new ApiResponse("success", products));
        } catch (Exception ex) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(ex.getMessage(), INTERNAL_SERVER_ERROR));
        }
    }

    // List<Product> getProductsByBrand(String brand);
    @GetMapping("/products/by-brand")
    public ResponseEntity<ApiResponse> getProductByBrand(@RequestParam String productBrand) {
        try {
            List<Product> products = productService.getProductsByBrand(productBrand);
            if (products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("no product found", null));
            }

            return ResponseEntity.ok(new ApiResponse("success", products));
        } catch (Exception ex) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(ex.getMessage(), INTERNAL_SERVER_ERROR));
        }
    }

    // List<Product> getProductsByCategory(String category);
    @GetMapping("/products/{productCategory}/all/products")
    public ResponseEntity<ApiResponse> getProductByCategory(@PathVariable String productCategory) {
        try {
            List<Product> products = productService.getProductsByCategory(productCategory);
            if (products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("no product found", null));
            }

            return ResponseEntity.ok(new ApiResponse("success", products));
        } catch (Exception ex) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(ex.getMessage(), INTERNAL_SERVER_ERROR));
        }
    }


}
