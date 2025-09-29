package com.jeyz9.spring_sqlite.controllers;

import com.jeyz9.spring_sqlite.models.Product;
import com.jeyz9.spring_sqlite.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product Management API", description = "API สำหรับจัดการข้อมูลสินค้า")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "ดึงข้อมูลสินค้าทั้งหมด", description = "คืนค่ารายการสินค้าทั้งหมดที่มีในระบบ")
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @Operation(summary = "ค้นหาสินค้าด้วย ID", description = "ดึงข้อมูลสินค้าหนึ่งชิ้นจาก ID ที่ระบุ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "พบสินค้า",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "404", description = "ไม่พบสินค้าจาก ID ที่ระบุ",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "สร้างสินค้าใหม่", description = "เพิ่มสินค้าชิ้นใหม่เข้าไปในระบบ")
    @ApiResponse(responseCode = "201", description = "สร้างสินค้าสำเร็จ",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Product.class)) })
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @Operation(summary = "อัปเดตข้อมูลสินค้า", description = "แก้ไขข้อมูลสินค้าที่มีอยู่จาก ID ที่ระบุ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "อัปเดตข้อมูลสินค้าสำเร็จ",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "404", description = "ไม่พบสินค้าจาก ID ที่ระบุ",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product productDetails) {
        return productService.updateProduct(id, productDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "ลบสินค้า", description = "ลบสินค้าหนึ่งชิ้นออกจากระบบจาก ID ที่ระบุ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "ลบสินค้าสำเร็จ"),
            @ApiResponse(responseCode = "404", description = "ไม่พบสินค้าจาก ID ที่ระบุ")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        if (productService.deleteProduct(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}