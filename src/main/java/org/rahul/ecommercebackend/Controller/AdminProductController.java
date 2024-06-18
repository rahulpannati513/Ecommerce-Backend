package org.rahul.ecommercebackend.Controller;

import jakarta.persistence.Entity;
import org.rahul.ecommercebackend.Exception.ProductException;
import org.rahul.ecommercebackend.Model.Product;
import org.rahul.ecommercebackend.Request.CreateProductRequest;
import org.rahul.ecommercebackend.Response.ApiResponse;
import org.rahul.ecommercebackend.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

    @Autowired
    private ProductService productService;


    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request){

        Product product = productService.createProduct(request);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) throws ProductException {

        productService.deleteProduct(productId);
        ApiResponse res = new ApiResponse();
        res.setMessage("Product deleted successfully");
        res.setStatus(true);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAllProduct() throws ProductException {
        List<Product> products = productService.findAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product req,
                                                 @PathVariable Long productId) throws ProductException {
        Product product = productService.updateProduct(productId, req);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/creates")
    public ResponseEntity<ApiResponse> createMultiipleProduct(@RequestBody CreateProductRequest[] request){

        for(CreateProductRequest product : request){
            productService.createProduct(product);
        }
        ApiResponse res = new ApiResponse();
        res.setMessage("Product created successfully");
        res.setStatus(true);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }



}
