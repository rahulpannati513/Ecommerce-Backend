package org.rahul.ecommercebackend.Service;

import org.rahul.ecommercebackend.Exception.ProductException;
import org.rahul.ecommercebackend.Model.CategoryProduct;
import org.rahul.ecommercebackend.Model.Product;
import org.rahul.ecommercebackend.Model.Productsize;
import org.rahul.ecommercebackend.Repository.CategoryRepository;
import org.rahul.ecommercebackend.Repository.ProductRepository;
import org.rahul.ecommercebackend.Request.CreateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplementation implements ProductService{

        @Autowired
        private ProductRepository productRepository;
        @Autowired
        private CategoryRepository categoryRepository;
        @Autowired
        private UserService userService;


    @Override
    public Product createProduct(CreateProductRequest request) {
        CategoryProduct topLevelRequest = categoryRepository.findByName(request.getTopLevelCategory());
        if(topLevelRequest == null){
            CategoryProduct topLevelCategory = new CategoryProduct();
            topLevelCategory.setName(request.getTopLevelCategory());
            topLevelCategory.setLevel(1);

            topLevelRequest = categoryRepository.save(topLevelCategory);

        }
        CategoryProduct secondLevelRequest = categoryRepository.findByNameAndParent(request.getSecondLevelCategory(),topLevelRequest.getName());
        if(secondLevelRequest == null){
            CategoryProduct secondLevelCategory = new CategoryProduct();
            secondLevelCategory.setName(request.getSecondLevelCategory());
            secondLevelCategory.setParentCategory(topLevelRequest);
            secondLevelCategory.setLevel(2);
            secondLevelRequest = categoryRepository.save(secondLevelCategory);;
        }

        CategoryProduct thirdLevelRequest = categoryRepository.findByNameAndParent(request.getThirdLevelCategory(),secondLevelRequest.getName());
        if(thirdLevelRequest == null){
            CategoryProduct thirdLevelCategory = new CategoryProduct();
            thirdLevelCategory.setName(request.getThirdLevelCategory());
            thirdLevelCategory.setParentCategory(secondLevelRequest);
            thirdLevelCategory.setLevel(3);

            thirdLevelRequest = categoryRepository.save(thirdLevelCategory);

        }
        Product product = new Product();
        product.setTitle(request.getTitle());
        product.setColor(request.getColor());
        product.setDescription(request.getDescription());
        product.setImageUrl(request.getImageUrl());
        product.setBrand(request.getBrand());
        product.setPrice(request.getPrice());
        product.setDiscountedPrice(request.getDiscountedPrice());
        product.setDiscountPercent(request.getDiscountedPercentage());
        product.setQuantity(request.getQuantity());
        product.setCategory(thirdLevelRequest);
        product.setCreatedAt(LocalDateTime.now());
        product.setProductSize(request.getSize());

        return productRepository.save(product);
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        Product product = findProductById(productId);
        productRepository.delete(product);
        return "Product Deleted Successfully";
    }

    @Override
    public Product updateProduct(Long productId, Product productRequest) throws ProductException {

        Product product = findProductById(productId);

        if(productRequest.getQuantity()!=0){
            product.setQuantity(productRequest.getQuantity());
        }

        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Long productId) throws ProductException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isPresent()){
            return optionalProduct.get();
        }
        throw  new ProductException("Product Not Found with Id : "+productId);
    }

    @Override
    public List<Product> findProductByCategory(String category) throws ProductException {
        return List.of();
    }

    @Override
    public Page<Product> findAllProducts(
            String category, List<String> colors, List<String> sizes, Integer minPrice,
            Integer maxPrice, Integer minDiscount, String sort, String stock,
            Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber,pageSize);

// the filterProducts method of the productRepository is called to get a list of products that match the filter criteria
        List<Product> products = productRepository.filterProducts(category,minPrice,maxPrice,minDiscount,sort);

        if(!colors.isEmpty()){
          products= products.stream().filter(p -> colors.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColor()))).
                    collect(Collectors.toList());
        }
        if (stock!=null){
            if(stock.equalsIgnoreCase("in_stock")){
                products = products.stream().filter((p)-> p.getQuantity()>0).collect(Collectors.toList());
            }else if (stock.equals("out_of_stock")){
                products = products.stream().filter(p-> p.getQuantity()<1).collect(Collectors.toList());
            }
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = Math.min(startIndex+pageable.getPageSize(),products.size());

        List<Product> pageContent = products.subList(startIndex, endIndex);

        Page<Product> filteredProducts = new PageImpl<>(pageContent,pageable,products.size());


        return filteredProducts;
    }
}
