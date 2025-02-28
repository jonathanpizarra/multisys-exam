package com.multisys.dishtansya.service.impl;

import com.multisys.dishtansya.model.Product;
import com.multisys.dishtansya.repository.ProductRepository;
import com.multisys.dishtansya.req.ProductReq;
import com.multisys.dishtansya.service.ProductService;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public String orderProduct(ProductReq req){
        Optional<Product> product = productRepository.findById(req.getProductId());
        Product product1 = new Product();
        product1.setId(1L);
        product1.setAvailableStock(100);
        product1.setName("p1");
//        if(product.isPresent()){
            Product p = product1;
            if(req.getQuantity() > p.getAvailableStock()){
                return "Failed to order this product due to unavailability of the stock";
            }else{
                int remaining = p.getAvailableStock() - req.getQuantity();
                p.setAvailableStock(remaining);
                productRepository.save(p);
                return "You have successfully ordered this product.";
            }
//        }else{
//            throw new RuntimeException("Product does not exist");
//        }
    }

}
