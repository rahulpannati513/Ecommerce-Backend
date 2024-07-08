package org.rahul.ecommercebackend.Controller;

import jakarta.persistence.GeneratedValue;
import org.rahul.ecommercebackend.Exception.ProductException;
import org.rahul.ecommercebackend.Model.Product;
import org.rahul.ecommercebackend.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> findProductByCategoryHandler(@RequestParam String category, @RequestParam List<String>color,@RequestParam List<String>size,@RequestParam Integer minPrice,
                                                                      @RequestParam Integer maxPrice,@RequestParam Integer minDiscount,@RequestParam String sort,
                                                                      @RequestParam String stock,@RequestParam Integer pageNumber,@RequestParam Integer pageSize){

       Page<Product> res = productService.findAllProductsByCategory(category,color,size,minPrice,maxPrice,minDiscount,sort,stock,pageNumber,pageSize);
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }

    @GetMapping("/products/id/{productId}")
    public ResponseEntity<Product> findProductByIdHandler(@PathVariable Long productId) throws ProductException {

        Product res = productService.findProductById(productId);
        return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
    }

}
