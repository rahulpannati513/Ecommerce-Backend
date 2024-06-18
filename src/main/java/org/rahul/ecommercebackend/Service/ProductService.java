package org.rahul.ecommercebackend.Service;

import org.rahul.ecommercebackend.Exception.ProductException;
import org.rahul.ecommercebackend.Model.Product;
import org.rahul.ecommercebackend.Request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public Product createProduct(CreateProductRequest request);
    public String deleteProduct(Long productId) throws ProductException;
    public Product updateProduct(Long productId, Product productRequest) throws ProductException;
    public Product findProductById(Long productId) throws ProductException;
    public List<Product> findAllProducts() throws ProductException;
    public Page<Product> findAllProductsByCategory(String category, List<String> colors,List<String> sizes,Integer minPrice,Integer maxPrice,
    Integer minDiscount,String sort,String stock,Integer pageNumber, Integer pageSize);

}
